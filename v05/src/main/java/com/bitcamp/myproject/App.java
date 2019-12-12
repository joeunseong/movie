
package com.bitcamp.myproject;
import java.sql.Date;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
      Scanner keyboard = new Scanner(System.in);
      
      System.out.print("번호? ");
      int no = keyboard.nextInt();
      
      keyboard.nextLine();
      
      System.out.print("영화명? ");
      String movieTitle = keyboard.nextLine();
      
      System.out.print("장르? ");
      String genre = keyboard.nextLine();
      
      System.out.print("개봉일? ");
      Date openDate = Date.valueOf(keyboard.nextLine());
      
      System.out.print("러닝타임? ");
      int runningTime = keyboard.nextInt();
      
      keyboard.nextLine();
      
      System.out.print("줄거리? ");
      String summary = keyboard.nextLine();
     
      System.out.println();
      
      keyboard.close();
      
      System.out.printf("번호: %s\n", no);
      System.out.printf("영화명: %s\n", movieTitle);
      System.out.printf("장르: %s\n", genre);   
      System.out.printf("개봉일:  %s\n", openDate);
      System.out.printf("러닝타임: %s 분\n", runningTime);
      System.out.printf("줄거리: %s\n", summary);
          
    }
}






