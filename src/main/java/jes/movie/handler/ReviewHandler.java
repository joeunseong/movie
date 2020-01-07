package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Review;
import jes.movie.util.ArrayList;

public class ReviewHandler {

  public Scanner keyboard;

  ArrayList<Review> reviewList;

  public ReviewHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    reviewList = new ArrayList<>();
    
  }
  
  public ReviewHandler(Scanner keyboard, int capacity) {
    this.keyboard = keyboard;
    reviewList = new ArrayList<>(capacity);
    
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
    Review[] arr = new Review[this.reviewList.size()];
    this.reviewList.toArray(arr);
    for (Review r : arr) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          r.getNo(), r.getMovieTitle(), r.getReviewSummary(), 
          r.getUpdateDay(), r.getViewCount());
    }
  }

  public void detailReview() {
    System.out.print("인덱스 번호? ");
    int index = keyboard.nextInt();
    keyboard.nextLine();

    Review review = this.reviewList.get(index);
 
    if (review == null) {
      System.out.println("인덱스 번호가 유효하지 않습니다.");
      return;
    }
    System.out.printf("번호: %d\n", review.getNo());
    System.out.printf("영화제목: %s\n", review.getMovieTitle());
    System.out.printf("리뷰 내용: %s\n", review.getReviewSummary());
  }
}
