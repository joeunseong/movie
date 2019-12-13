
package com.bitcamp.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    int[] no = new int[10000];
    String[] movieTitle = new String[10000];
    String[] genre = new String[10000];
    String[] summary = new String[10000];
    String[] director = new String[10000];
    String[] actor = new String[10000];
    String[] kmrb = new String[10000];
    Date[] openDate = new Date[10000];
    int[] runningTime = new int[10000];
    int count = 0;

    for (int i = 0; i < 10000; i++) {
      System.out.print("번호? ");
      no[i] = keyboard.nextInt();

      keyboard.nextLine();

      System.out.print("영화명? ");
      movieTitle[i] = keyboard.nextLine();

      System.out.print("장르? ");
      genre[i] = keyboard.nextLine();

      System.out.print("줄거리? ");
      summary[i] = keyboard.nextLine();

      System.out.print("감독? ");
      director[i] = keyboard.nextLine();

      System.out.print("출연? ");
      actor[i] = keyboard.nextLine();

      System.out.print("관람등급");
      kmrb[i] = keyboard.nextLine();

      System.out.print("개봉일? ");
      openDate[i] = Date.valueOf(keyboard.nextLine());

      System.out.print("러닝타임? ");
      runningTime[i] = keyboard.nextInt();
      keyboard.nextLine();
      count++;
      System.out.print("계속 입력하시겠습니까?(Y/n)");
      String answer = keyboard.nextLine();

      if (!answer.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();
    System.out.println();
    for (int i = 0; i < count; i++) {
      System.out.printf("번호: %s\n", no[i]);
      System.out.printf("영화명: %s\n", movieTitle[i]);
      System.out.printf("장르: %s\n", genre[i]);
      System.out.printf("줄거리: %s\n", summary[i]);
      System.out.printf("감독: %s\n", director[i]);
      System.out.printf("출연: %s\n", actor[i]);
      System.out.printf("관람등급: %s\n", kmrb[i]);
      System.out.printf("개봉일:  %s\n", openDate[i]);
      System.out.printf("러닝타임: %s 분\n", runningTime[i]);
      System.out.println();
    }
  }
}


