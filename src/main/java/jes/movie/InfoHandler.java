package jes.movie;

import java.sql.Date;
import java.util.Scanner;

public class InfoHandler {

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
  
  static final int INFO_SIZE = 100;
  static int infoCount = 0;
  static Info[] infos = new Info[INFO_SIZE];
  static Scanner keyboard;
  
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
    System.out.println("등록되었습니다.");
  }
  
  static void listInfo() {
    for (int i = 0; i < infoCount; i++) {
      Info in = infos[i];
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n", 
          in.no, in.movieTitle, in.genre, in.summary, in.director, in.actor, 
          in.kmrb, in.openDate, in.runningTime);
    }
  }
  
}
