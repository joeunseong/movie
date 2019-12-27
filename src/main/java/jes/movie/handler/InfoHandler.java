package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Info;

public class InfoHandler {

  static final int INFO_SIZE = 100;
  public static Scanner keyboard;
  
   int infoCount = 0;
   Info[] infos = new Info[INFO_SIZE];
  
  public void addInfo() {
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
    this.infos[this.infoCount++] = info;
    System.out.println("등록되었습니다.");
  }
  
  public void listInfo() {
    for (int i = 0; i < this.infoCount; i++) {
      Info in = this.infos[i];
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n", 
          in.no, in.movieTitle, in.genre, in.summary, in.director, in.actor, 
          in.kmrb, in.openDate, in.runningTime);
    }
  }
  
}
