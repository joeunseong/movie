package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Review;
import jes.movie.util.ArrayList;
import jes.movie.util.Prompt;

public class ReviewHandler {

  public Prompt prompt;

  ArrayList<Review> reviewList;

  public ReviewHandler(Prompt prompt) {
    this.prompt = prompt;
    reviewList = new ArrayList<>();
    
  }
  
  public ReviewHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    reviewList = new ArrayList<>(capacity);
    
  }

  public void addReview() {
    Review review = new Review();

    review.setNo(prompt.inputInt("번호? "));
    review.setMovieTitle(prompt.inputString("영화 제목? "));
    review.setReviewSummary(prompt.inputString("내용? "));
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
    int index = indexOfReview(prompt.inputInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Review review = this.reviewList.get(index);
    
    System.out.printf("번호: %d\n", review.getNo());
    System.out.printf("영화제목: %s\n", review.getMovieTitle());
    System.out.printf("리뷰 내용: %s\n", review.getReviewSummary());
  }
  
  public void updateReview() {
    int index = indexOfReview(prompt.inputInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Review oldReview = this.reviewList.get(index);
    Review newReview = new Review();
    newReview.setNo(oldReview.getNo());
    
    newReview.setMovieTitle(prompt.inputString(String.format("영화제목(%s)? ", oldReview.getMovieTitle()),
          oldReview.getMovieTitle()));
    
    newReview.setReviewSummary(prompt.inputString("리뷰 내용? ", oldReview.getReviewSummary()));
    
    newReview.setUpdateDay(new Date(System.currentTimeMillis()));
    newReview.setViewCount(0);
    
    if(oldReview.equals(newReview)) {
      System.out.println("리뷰가 수정이 취소되었습니다.");
      return;
    }
    
    this.reviewList.set(index, newReview);
    System.out.println("리뷰가 수정되었습니다.");
  }
  
  public void deleteReview() {
    int index = indexOfReview(prompt.inputInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    this.reviewList.remove(index);
    System.out.println("리뷰 인덱스를 삭제했습니다.");
  }
  
  private int indexOfReview(int no) {
    for(int i =0; i< this.reviewList.size(); i++) {
      if(this.reviewList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
