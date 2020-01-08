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
    System.out.print("번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();
    
    int index = indexOfReview(no);
    
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
    System.out.print("번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();

    int index = indexOfReview(no);
    
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Review oldReview = this.reviewList.get(index);
    String inputStr = null;
    Review newReview = new Review();
    newReview.setNo(oldReview.getNo());
    
    System.out.printf("영화제목(%s)? ", oldReview.getMovieTitle());
    inputStr = keyboard.nextLine();
    if(inputStr.length() == 0) {
      newReview.setMovieTitle(oldReview.getMovieTitle());
    } else {
      newReview.setMovieTitle(inputStr);
    }
    
    System.out.printf("리뷰 내용? ");
    inputStr = keyboard.nextLine();
    if(inputStr.length() == 0) {
      newReview.setReviewSummary(oldReview.getMovieTitle());
    } else {
      newReview.setReviewSummary(inputStr);
    }
    
    newReview.setUpdateDay(new Date(System.currentTimeMillis()));
    newReview.setViewCount(0);
    
    this.reviewList.set(index, newReview);
    
    System.out.println("리뷰가 수정되었습니다.");
  }
  
  public void deleteReview() {
    System.out.print("번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();

    int index = indexOfReview(no);
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
