package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewAddCommand implements Command {
  ReviewDao reviewDao;
  Prompt prompt;

  public ReviewAddCommand(ReviewDao reviewDao, Prompt prompt) {
    this.reviewDao = reviewDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Review review = new Review();
    review.setNo(prompt.inputInt("번호? "));
    review.setMovieTitle(prompt.inputString("영화 제목? "));
    review.setReviewSummary(prompt.inputString("내용? "));
    review.setUpdateDay(new Date(System.currentTimeMillis()));
    review.setViewCount(0);
    
    try {
      reviewDao.insert(review);
      System.out.println("저장하였습니다.");
      
    } catch (Exception e) {
      System.out.println("리뷰 등록 실패!");
    }
  }
}
