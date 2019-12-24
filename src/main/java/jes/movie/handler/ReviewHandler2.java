package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Review;


public class ReviewHandler2 {

  static final int REVIEW_SIZE = 100;
  static int reviewCount = 0;
  static Review[] reviews = new Review[REVIEW_SIZE];
  public static Scanner keyboard;

  public static void addReview() {
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

  public static void listReview() {
    for (int i = 0; i < reviewCount; i++) {
      Review r = reviews[i];
      System.out.printf("%d, %s, %s, %s, %s\n", r.no, r.movieTitle, r.reviewSummary, r.updateDay,
          r.viewCount);
    }
  }

  public static void detailReview() {
    System.out.print("게시물 번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();

    Review review = null;
    for (int i = 0; i < reviewCount; i++) {
      if (reviews[i].no == no) {
        review = reviews[i];
        break;
      }
    }
    if (review == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    System.out.printf("번호: %d\n", review.no);
    System.out.printf("영화제목: %s\n", review.movieTitle);
    System.out.printf("리뷰 내용: %s\n", review.reviewSummary);
  }
}
