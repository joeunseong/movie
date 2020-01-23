package jes.movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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

  static LinkedList<Review> reviewList = new LinkedList<>();
  static ArrayList<Info> infoList = new ArrayList<>();
  static LinkedList<Member> memberList = new LinkedList<>();

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
    File file = new File("./review.csv");
    FileReader in = null;
    Scanner dataScan = null;
    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;

      while (true) {
        try {
          reviewList.add(Review.valueOf(dataScan.nextLine()));
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 리뷰 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void loadMemberData() {
    File file = new File("./member.csv");
    FileReader in = null;
    Scanner dataScan = null;
    try {
      int count = 0;
      in = new FileReader(file);
      dataScan = new Scanner(in);
      while (true) {
        try {
          memberList.add(Member.valueOf(dataScan.nextLine()));
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 멤버 데이터를 로딩했습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void loadInfoData() {
    File file = new File("./info.csv");
    FileReader in = null;
    Scanner dataScan = null;
    try {
      int count = 0;
      in = new FileReader(file);
      dataScan = new Scanner(in);
      while (true) {
        try {
          infoList.add(Info.valueOf(dataScan.nextLine()));
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 영화 정보 데이터를 로딩했습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveReviewData() {
    File file = new File("./review.csv");
    FileWriter out = null;
    try {
      out = new FileWriter(file);
      int count = 0;
      for (Review review : reviewList) {
        out.write(review.toCsvString() + "\n");
        count++;
      }
      System.out.printf("총 %d 개의 리뷰 데이터를 저장했습니다.\n", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.csv");

    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (Member member : memberList) {
        out.write(member.toCsvString() + "\n");
        count++;
      }
      System.out.printf("총 %d 개의 멤버 데이터를 저장했습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());

    } finally {
      try {
        out.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveInfoData() {
    File file = new File("./info.csv");
    FileWriter out = null;
    try {
      out = new FileWriter(file);
      int count = 0;
      for (Info info : infoList) {
        out.write(info.toCsvString() + "\n");
        count++;
      }
      System.out.printf("총 %d 개의 영화 정보 데이터를 저장했습니다.\n", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (Exception e) {
      }
    }
  }
}
