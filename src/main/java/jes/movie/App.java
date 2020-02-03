package jes.movie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import jes.movie.domain.Info;
import jes.movie.domain.Member;
import jes.movie.domain.Review;
import jes.movie.handler.Command;
import jes.movie.handler.InfoAddCommand;
import jes.movie.handler.InfoDeleteCommand;
import jes.movie.handler.InfoDetailCommand;
import jes.movie.handler.InfoListCommand;
import jes.movie.handler.InfoUpdateCommand;
import jes.movie.handler.MemberAddCommand;
import jes.movie.handler.MemberDeleteCommand;
import jes.movie.handler.MemberDetailCommand;
import jes.movie.handler.MemberListCommand;
import jes.movie.handler.MemberUpdateCommand;
import jes.movie.handler.ReviewAddCommand;
import jes.movie.handler.ReviewDeleteCommand;
import jes.movie.handler.ReviewDetailCommand;
import jes.movie.handler.ReviewListCommand;
import jes.movie.handler.ReviewUpdateCommand;
import jes.movie.util.Prompt;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  static List<Review> reviewList = new ArrayList<>();
  static List<Info> infoList = new ArrayList<>();
  static List<Member> memberList = new ArrayList<>();

  public static void main(String[] args) {
    loadReviewData();
    loadMemberData();
    loadInfoData();

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/review/add", new ReviewAddCommand(prompt, reviewList));
    commandMap.put("/review/detail", new ReviewDetailCommand(prompt, reviewList));
    commandMap.put("/review/delete", new ReviewDeleteCommand(prompt, reviewList));
    commandMap.put("/review/list", new ReviewListCommand(reviewList));
    commandMap.put("/review/update", new ReviewUpdateCommand(prompt, reviewList));

    commandMap.put("/info/add", new InfoAddCommand(prompt, infoList));
    commandMap.put("/info/delete", new InfoDeleteCommand(prompt, infoList));
    commandMap.put("/info/detail", new InfoDetailCommand(prompt, infoList));
    commandMap.put("/info/update", new InfoUpdateCommand(prompt, infoList));
    commandMap.put("/info/list", new InfoListCommand(infoList));

    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));

    String command;

    while (true) {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      if (command.equals("quit")) {
        System.out.println("종료");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }

      if (command.length() == 0)
        continue;

      commandStack.push(command);

      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);

      if (commandHandler != null) {
        try {
          commandHandler.excute();
        } catch (Exception e) {
          System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    keyboard.close();

    saveReviewData();
    saveMemberData();
    saveInfoData();
  }

  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;
      if ((count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  private static void loadReviewData() {
    File file = new File("./review.data");
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Review review = new Review();
        review.setNo(in.readInt());
        review.setMovieTitle(in.readUTF());
        review.setReviewSummary(in.readUTF());
        review.setUpdateDay(Date.valueOf(in.readUTF()));
        review.setViewCount(in.readInt());
        reviewList.add(review);

      }
      System.out.printf("총 %d 개의 리뷰 데이터를 로딩했습니다.\n", reviewList.size());
    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadMemberData() {
    File file = new File("./member.data");
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.readInt());
        member.setName(in.readUTF());
        member.setEmail(in.readUTF());
        member.setPassword(in.readUTF());
        member.setPhoto(in.readUTF());
        member.setTel(in.readUTF());
        member.setRegisterDate(Date.valueOf(in.readUTF()));
        memberList.add(member);
      }
      System.out.printf("총 %d 개의 멤버 데이터를 로딩했습니다.\n", memberList.size());
    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadInfoData() {
    File file = new File("./info.data");
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Info info = new Info();
        info.setNo(in.readInt());
        info.setMovieTitle(in.readUTF());
        info.setGenre(in.readUTF());
        info.setSummary(in.readUTF());
        info.setDirector(in.readUTF());
        info.setActor(in.readUTF());
        info.setKmrb(in.readUTF());
        info.setOpenDate(Date.valueOf(in.readUTF()));
        info.setRunningTime(in.readInt());
        infoList.add(info);
      }
      System.out.printf("총 %d 개의 영화 정보 데이터를 로딩했습니다.\n", infoList.size());
    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }


  private static void saveReviewData() {
    File file = new File("./review.data");
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(reviewList.size());
      for (Review review : reviewList) {
        out.writeInt(review.getNo());
        out.writeUTF(review.getMovieTitle());
        out.writeUTF(review.getReviewSummary());
        out.writeUTF(review.getUpdateDay().toString());
        out.writeInt(review.getViewCount());
      }
      System.out.printf("총 %d 개의 리뷰 데이터를 저장했습니다.\n", reviewList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.data");
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(memberList.size());
      for (Member member : memberList) {
        out.writeInt(member.getNo());
        out.writeUTF(member.getName());
        out.writeUTF(member.getEmail());
        out.writeUTF(member.getPassword());
        out.writeUTF(member.getPhoto());
        out.writeUTF(member.getTel());
        out.writeUTF(member.getRegisterDate().toString());
      }
      System.out.printf("총 %d 개의 멤버 데이터를 저장했습니다.\n", memberList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveInfoData() {
    File file = new File("./info.data");
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(infoList.size());
      for (Info info : infoList) {
        out.writeInt(info.getNo());
        out.writeUTF(info.getMovieTitle());
        out.writeUTF(info.getGenre());
        out.writeUTF(info.getSummary());
        out.writeUTF(info.getDirector());
        out.writeUTF(info.getActor());
        out.writeUTF(info.getKmrb());
        out.writeUTF(info.getOpenDate().toString());
        out.writeInt(info.getRunningTime());
      }
      System.out.printf("총 %d 개의 영화 정보 데이터를 저장했습니다.\n", infoList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }
}
