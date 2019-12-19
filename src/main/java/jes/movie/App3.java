package jes.movie;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    int[] no = new int[100];
    String[] movieTitle = new String[100];
    String[] reviewSummary = new String[100];
    Date[] updateDay = new Date[100];
    int[] viewCount = new int[100];
    int count = 0;

    for (int i = 0; i < 100; i++) {
      System.out.print("번호? ");
      no[i] = Integer.parseInt(keyboard.nextLine());

      System.out.print("영화 제목?");
      movieTitle[i] = keyboard.nextLine();

      System.out.print("내용? ");
      reviewSummary[i] = keyboard.nextLine();

      updateDay[i] = new Date(System.currentTimeMillis());
      viewCount[i] = 0;

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
      System.out.printf("%d, %s, %s, %s, %d\n", 
          no[i], movieTitle[i], reviewSummary[i], updateDay[i], viewCount[i] );
    }
  }
}


