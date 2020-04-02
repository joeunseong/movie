package jes.movie.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;

public class ReviewAddServlet implements Servlet {

  ReviewDao reviewDao;

  public ReviewAddServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Review review = new Review();

    out.println("영화 제목? ");
    out.println("!{}!");
    out.flush();
    review.setMovieTitle(in.nextLine());

    out.println("내용? ");
    out.println("!{}!");
    out.flush();
    review.setReviewSummary(in.nextLine());

    if (reviewDao.insert(review) > 0) {
      out.println("리뷰를 저장하였습니다.");

    } else {
      out.println("리뷰 등록 실패했습니다.");
    }
  }
}
