package jes.movie.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;

public class ReviewUpdateServlet implements Servlet {

  ReviewDao reviewDao;

  public ReviewUpdateServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Review old = reviewDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 리뷰가 없습니다.");
      return;
    }

    Review review = new Review();

    review.setNo(old.getNo());

    out.printf("영화 제목(%s)? \n", old.getMovieTitle());
    out.println("!{}!");
    out.flush();
    review.setMovieTitle(in.nextLine());

    out.printf("내용(%s)? \n", old.getReviewSummary());
    out.println("!{}!");
    out.flush();
    review.setReviewSummary(in.nextLine());


    if (reviewDao.update(review) > 0) {
      out.println("리뷰가 변경되었습니다.");

    } else {
      out.println("리뷰 변경에 실패했습니다.");
    }
  }
}
