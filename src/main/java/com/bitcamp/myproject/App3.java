package com.bitcamp.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    int[] no = new int[10000];
    String[] movieTitle = new String[10000];
    String[] review = new String[10000];
    Date[] updateDay = new Date[10000];
    int count = 0;
    int viewCount = 0;

    for (int i = 0; i < 10000; i++) {
      System.out.print("번호? ");
      no[i] = Integer.parseInt(keyboard.nextLine());

      System.out.print("영화 제목?");
      movieTitle[i] = keyboard.nextLine();

      System.out.print("내용? ");
      review[i] = keyboard.nextLine();

      Date today = new Date(System.currentTimeMillis());
      updateDay[i] = today;

      count++;
      System.out.print("계속 등록하시겠습니까?(Y/n)");
      String answer = keyboard.nextLine();
      if (!answer.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();

    System.out.println();
    for (int i = 0; i < count; i++) {
      System.out.printf("번호: %s\n", no[i]);
      System.out.printf("영화 제목: %s\n", movieTitle[i]);
      System.out.printf("내용: %s\n", review[i]);
      System.out.printf("작성일: %s\n", updateDay[i]);
      System.out.printf("조회수: %s\n", viewCount);
      System.out.println();
    }
  }
}


