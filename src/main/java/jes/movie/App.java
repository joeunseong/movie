
package jes.movie;

import java.sql.Date;
import java.util.Scanner;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static String command;

  static class Info {
    int no;
    String movieTitle;
    String genre;
    String summary;
    String director;
    String actor;
    String kmrb;
    Date openDate;
    int runningTime;
  }

  static class Member {
    int no;
    String name;
    String email;
    String password;
    String photo;
    String tel;
    Date registerDate;

  }
  static class Review {
    int no;
    String movieTitle;
    String reviewSummary;
    Date updateDay;
    int viewCount;

  }

  static final int INFO_SIZE = 100;
  static final int MEMBER_SIZE = 100;
  static final int REVIEW_SIZE = 100;

  static int infoCount = 0;
  static int memberCount = 0;
  static int reviewCount = 0;

  static Info[] infos = new Info[INFO_SIZE];
  static Member[] members = new Member[MEMBER_SIZE];
  static Review[] reviews = new Review[REVIEW_SIZE];

  public static void main(String[] args) {
 
    do {
      prompt();
      switch (command) {
        case "/info/add":
          addInfo();
          break;
        case "/info/list":
          listInfo();
          break;
        case "/member/add":
          addMember();
          break;

        case "/member/list":
          listMember();
          break;
        case "/review/add":
          addReview();
          break;

        case "/review/list":
          listReview();
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("종료");

    keyboard.close();
  }
  
  static void prompt() {
    System.out.print("\n명령> ");
    command = keyboard.nextLine();
  }
  static void addInfo() {
    Info info = new Info();

    System.out.print("번호? ");
    info.no = keyboard.nextInt();

    keyboard.nextLine();

    System.out.print("영화명? ");
    info.movieTitle = keyboard.nextLine();

    System.out.print("장르? ");
    info.genre = keyboard.nextLine();

    System.out.print("줄거리? ");
    info.summary = keyboard.nextLine();

    System.out.print("감독? ");
    info.director = keyboard.nextLine();

    System.out.print("출연? ");
    info.actor = keyboard.nextLine();

    System.out.print("관람등급? ");
    info.kmrb = keyboard.nextLine();

    System.out.print("개봉일? ");
    info.openDate = Date.valueOf(keyboard.nextLine());

    System.out.print("러닝타임? ");
    info.runningTime = keyboard.nextInt();
    keyboard.nextLine();
    infos[infoCount++] = info;
    System.out.println("저장되었습니다.");
  }
  
  static void listInfo() {
    for (int i = 0; i < infoCount; i++) {
      Info in = infos[i];
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n", 
          in.no, in.movieTitle, in.genre, in.summary, in.director, in.actor, 
          in.kmrb, in.openDate, in.runningTime);
    }
  }
  
  static void addMember() {
    Member member = new Member();

    System.out.print("번호? ");
    member.no = Integer.parseInt(keyboard.nextLine());

    System.out.print("이름? ");
    member.name = keyboard.nextLine();

    System.out.print("이메일? ");
    member.email = keyboard.nextLine();

    System.out.print("암호? ");
    member.password = keyboard.nextLine();

    System.out.print("사진? ");
    member.photo = keyboard.nextLine();

    System.out.print("전화? ");
    member.tel = keyboard.nextLine();

    member.registerDate = new Date(System.currentTimeMillis());

    members[memberCount++] = member;
  }
  
  static void listMember() {
    for (int i = 0; i < memberCount; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s, %s\n", 
          m.no, m.name, m.email, m.tel, m.registerDate);
    }
  }
  
  static void addReview() {
    Review review = new Review();

    System.out.print("번호? ");
    review.no = Integer.parseInt(keyboard.nextLine());

    System.out.print("영화 제목?");
    review.movieTitle = keyboard.nextLine();

    System.out.print("내용? ");
    review.reviewSummary = keyboard.nextLine();

    review.updateDay = new Date(System.currentTimeMillis());
    review.viewCount = 0;
    reviews[reviewCount++] = review;
  }
  static void listReview() {
    for (int i = 0; i < reviewCount; i++) {
      Review r = reviews[i];
      System.out.printf("%d, %s, %s, %s, %s\n", r.no, r.movieTitle, r.reviewSummary,
          r.updateDay, r.viewCount);
    }
  }
}


