package jes.movie.handler;

import java.sql.Date;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewUpdateCommand implements Command {
  ReviewDao reviewDao;
  Prompt prompt;

  public ReviewUpdateCommand(ReviewDao reviewDao, Prompt prompt) {
    this.reviewDao = reviewDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Review oldReview = null;
      try {
        oldReview = reviewDao.findByNo(no);
        
      } catch(Exception e) {
        System.out.println("해당 번호의 리뷰가 없습니다!");
        return;
      }
          
      Review newReview = new Review();

      newReview.setNo(oldReview.getNo());

      newReview.setMovieTitle(prompt.inputString(
          String.format("영화제목(%s)? ", oldReview.getMovieTitle()), oldReview.getMovieTitle()));

      newReview.setReviewSummary(prompt.inputString("리뷰 내용? ", oldReview.getReviewSummary()));

      newReview.setUpdateDay(new Date(System.currentTimeMillis()));
      newReview.setViewCount(0);

      if (oldReview.equals(newReview)) {
        System.out.println("리뷰가 변경을 취소되었습니다.");
        return;
      }

      reviewDao.update(newReview);
      System.out.println("리뷰가 변경되었습니다.");
    } catch (Exception e) {
      System.out.println("리뷰 변경 실패!");
    }
  }
}

