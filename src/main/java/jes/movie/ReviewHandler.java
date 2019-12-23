package jes.movie;

import java.sql.Date;
import java.util.Scanner;

public class ReviewHandler {
  
  static class Review {
    int no;
    String movieTitle;
    String reviewSummary;
    Date updateDay;
    int viewCount;
  }

  static final int REVIEW_SIZE = 100;
  static int reviewCount = 0;
  static Review[] reviews = new Review[REVIEW_SIZE];
  static Scanner keyboard;

  static void addReview() {
    Review review = new Review();

    System.out.print("번호? ");
    review.no = Integer.parseInt(keyboard.nextLine());

    System.out.print("영화 제목?");
    review.movieTitle = keyboard.nextLine();

    System.out.print("내용? ");
    review.reviewSummary = keyboard.nextLine();

    review.updateDay = new Date(System.currentTimeMillis());
    review.viewCount = 0;
    reviews[reviewCount++] = review;
    System.out.println("저장되었습니다.");

  }
  static void listReview() {
    for (int i = 0; i < reviewCount; i++) {
      Review r = reviews[i];
      System.out.printf("%d, %s, %s, %s, %s\n", r.no, r.movieTitle, r.reviewSummary,
          r.updateDay, r.viewCount);
    }
  }
}
