package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Info;

public class InfoHandler {

  static final int INFO_SIZE = 100;
  public Scanner keyboard;
  
   int infoCount = 0;
   Info[] infos;
   
   public InfoHandler(Scanner keyboard) {
     this.keyboard = keyboard;
     this.infos = new Info[INFO_SIZE];
   }
     
  public void addInfo() {
    Info info = new Info();

    System.out.print("번호? ");
    info.setNo(keyboard.nextInt());

    keyboard.nextLine();

    System.out.print("영화명? ");
    info.setMovieTitle(keyboard.nextLine());

    System.out.print("장르? ");
    info.setGenre(keyboard.nextLine());

    System.out.print("줄거리? ");
    info.setSummary(keyboard.nextLine());

    System.out.print("감독? ");
    info.setDirector(keyboard.nextLine());

    System.out.print("출연? ");
    info.setActor(keyboard.nextLine());

    System.out.print("관람등급? ");
    info.setKmrb(keyboard.nextLine());

    System.out.print("개봉일? ");
    info.setOpenDate(Date.valueOf(keyboard.nextLine()));

    System.out.print("러닝타임? ");
    info.setRunningTime(keyboard.nextInt());
    keyboard.nextLine();
    this.infos[this.infoCount++] = info;
    System.out.println("등록되었습니다.");
  }
  
  public void listInfo() {
    for (int i = 0; i < this.infoCount; i++) {
      Info in = this.infos[i];
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n", 
          in.getNo(), in.getMovieTitle(), in.getGenre(), in.getSummary(), in.getDirector(), 
          in.getActor(), in.getKmrb(), in.getOpenDate(), in.getRunningTime());
    }
  }
  
}
