package jes.movie.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;

public class ReviewDetailServlet implements Servlet {

  ReviewDao reviewDao;

  public ReviewDetailServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Review review = reviewDao.findByNo(no);

    if (review != null) {
      out.printf("번호: %d\n", review.getNo());
      out.printf("영화제목: %s\n", review.getMovieTitle());
      out.printf("리뷰 내용: %s\n", review.getReviewSummary());

    } else {
      out.println("해당 번호의 리뷰가 없습니다.");
    }
  }
}
