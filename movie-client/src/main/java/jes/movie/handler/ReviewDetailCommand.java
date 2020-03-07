package jes.movie.handler;

import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewDetailCommand implements Command {
  ReviewDao reviewDao;
  Prompt prompt;

  public ReviewDetailCommand(ReviewDao reviewDao, Prompt prompt) {
    this.reviewDao = reviewDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");
      Review review = reviewDao.findByNo(no);
      
      System.out.printf("번호: %d\n", review.getNo());
      System.out.printf("영화제목: %s\n", review.getMovieTitle());
      System.out.printf("리뷰 내용: %s\n", review.getReviewSummary());
    } catch (Exception e) {
      System.out.println("리뷰 조회 실패!");
    }
  }
}
