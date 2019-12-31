package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Review;

public class ReviewHandler {

  public Scanner keyboard;

  ReviewList reviewList;

  public ReviewHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    reviewList = new ReviewList();
    
  }
  
  public ReviewHandler(Scanner keyboard, int capacity) {
    this.keyboard = keyboard;
    reviewList = new ReviewList(capacity);
    
  }

  public void addReview() {
    Review review = new Review();

    System.out.print("번호? ");
    review.setNo(Integer.parseInt(keyboard.nextLine()));

    System.out.print("영화 제목?");
    review.setMovieTitle(keyboard.nextLine());

    System.out.print("내용? ");
    review.setReviewSummary(keyboard.nextLine());

    review.setUpdateDay(new Date(System.currentTimeMillis()));
    review.setViewCount(0);
    
    reviewList.add(review);
    System.out.println("저장되었습니다.");

  }

  public void listReview() {
    Review[] reviews = reviewList.toArray();
    for (Review r : reviews) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          r.getNo(), r.getMovieTitle(), r.getReviewSummary(), 
          r.getUpdateDay(), r.getViewCount());
    }
  }

  public void detailReview() {
    System.out.print("게시물 번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();

    Review review = reviewList.get(no);
 
    if (review == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    System.out.printf("번호: %d\n", review.getNo());
    System.out.printf("영화제목: %s\n", review.getMovieTitle());
    System.out.printf("리뷰 내용: %s\n", review.getReviewSummary());
  }
}
