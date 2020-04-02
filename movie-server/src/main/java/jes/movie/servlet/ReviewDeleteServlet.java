package jes.movie.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jes.movie.dao.ReviewDao;

public class ReviewDeleteServlet implements Servlet {

  ReviewDao reviewDao;

  public ReviewDeleteServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    if (reviewDao.delete(no) > 0) {
      out.println("리뷰를 삭제했습니다.");

    } else {
      out.println("해당 번호의 리뷰가 없습니다.");
    }
  }
}
