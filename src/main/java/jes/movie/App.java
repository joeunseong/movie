
package jes.movie;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    int[] no = new int[100];
    String[] movieTitle = new String[100];
    String[] genre = new String[100];
    String[] summary = new String[100];
    String[] director = new String[100];
    String[] actor = new String[100];
    String[] kmrb = new String[100];
    Date[] openDate = new Date[100];
    int[] runningTime = new int[100];
    
    int count = 0;

    for (int i = 0; i < 100; i++) {
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

      System.out.print("관람등급? ");
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
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %d분\n", 
          no[i], genre[i], summary[i], director[i], actor[i], kmrb[i], 
          openDate[i], runningTime[i]);
    }
  }
}


