package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.ReviewObjectFileDao;
import jes.movie.domain.Review;

public class ReviewDetailServlet implements Servlet {

  ReviewObjectFileDao reviewDao;

  public ReviewDetailServlet(ReviewObjectFileDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Review review = reviewDao.findByNo(no);

    if (review != null) {
      out.writeUTF("OK");
      out.writeObject(review);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 리뷰가 없습니다.");
    }
  }
}
