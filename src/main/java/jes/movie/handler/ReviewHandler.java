package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Review;


public class ReviewHandler {

  int reviewCount = 0;
  Review[] reviews = new Review[REVIEW_SIZE];
  
  static final int REVIEW_SIZE = 100;
  public static Scanner keyboard;

  public void addReview() {
    Review review = new Review();

    System.out.print("번호? ");
    review.no = Integer.parseInt(keyboard.nextLine());

    System.out.print("영화 제목?");
    review.movieTitle = keyboard.nextLine();

    System.out.print("내용? ");
    review.reviewSummary = keyboard.nextLine();

    review.updateDay = new Date(System.currentTimeMillis());
    review.viewCount = 0;
    this.reviews[this.reviewCount++] = review;
    System.out.println("저장되었습니다.");

  }

  public void listReview() {
    for (int i = 0; i < this.reviewCount; i++) {
      Review r = this.reviews[i];
      System.out.printf("%d, %s, %s, %s, %s\n", r.no, r.movieTitle, r.reviewSummary, r.updateDay,
          r.viewCount);
    }
  }

  public void detailReview() {
    System.out.print("게시물 번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();

    Review review = null;
    for (int i = 0; i < this.reviewCount; i++) {
      if (this.reviews[i].no == no) {
        review = this.reviews[i];
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
