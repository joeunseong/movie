package jes.movie;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    class Review {
      int no;
      String movieTitle;
      String reviewSummary;
      Date updateDay;
      int viewCount;
    }
    
    int SIZE = 100;
    int count = 0;
    Review[] reviews = new Review[SIZE]; 
    for (int i = 0; i < SIZE; i++) {
      Review review = new Review();

      System.out.print("번호? ");
      review.no = Integer.parseInt(keyboard.nextLine());

      System.out.print("영화 제목?");
      review.movieTitle = keyboard.nextLine();

      System.out.print("내용? ");
      review.reviewSummary = keyboard.nextLine();

      Date today = new Date(System.currentTimeMillis());
      review.updateDay = today;
      reviews[i] = review;
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
      Review review = reviews[i];
      System.out.printf("%d, %s, %s, %s\n",
          review.no, review.movieTitle, review.reviewSummary, review.updateDay);
    }
  }
}


