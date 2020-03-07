package jes.movie.handler;

import java.util.List;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;

public class ReviewListCommand implements Command {
  ReviewDao reviewDao;

  public ReviewListCommand(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;

  }

  @Override
  public void execute() {
    try {
      List<Review> reviews = reviewDao.findAll();
      for (Review r : reviews) {
        System.out.printf("%d, %s, %s, %s, %s\n", r.getNo(), r.getMovieTitle(),
            r.getReviewSummary(), r.getUpdateDay(), r.getViewCount());
      }
    } catch (Exception e) {
      System.out.println("리뷰 목록 조회 실패!");
    }
  }
}
