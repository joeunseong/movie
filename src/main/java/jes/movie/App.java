
package jes.movie;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    class Info {
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

    int SIZE = 100;
    int count = 0;
    Info[] infos = new Info[SIZE];

    for (int i = 0; i < SIZE; i++) {
      Info info = new Info();// Info info는 for문 안에서만 존재하지만 new Info() 메모리는 계속 존재한다.
      count++;

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
      infos[i] = info;

      System.out.print("계속 입력하시겠습니까?(Y/n)");
      String response = keyboard.nextLine();

      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();
    System.out.println();
    for (int i = 0; i < count; i++) {
      Info info = infos[i];
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n", info.no, info.movieTitle,
          info.genre, info.summary, info.director, info.actor, info.kmrb, info.openDate,
          info.runningTime);
    }
  }
}


