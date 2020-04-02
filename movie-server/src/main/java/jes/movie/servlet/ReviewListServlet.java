package jes.movie.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;

public class ReviewListServlet implements Servlet {

  ReviewDao reviewDao;

  public ReviewListServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Review> reviews = reviewDao.findAll();
    for (Review r : reviews) {
      out.printf("%d, %s, %s, %s, %s\n", r.getNo(), r.getMovieTitle(), r.getReviewSummary(),
          r.getUpdateDay(), r.getViewCount());
    }
  }
}
