package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.ReviewObjectFileDao;
import jes.movie.domain.Review;

public class ReviewAddServlet implements Servlet {

  ReviewObjectFileDao reviewDao;

  public ReviewAddServlet(ReviewObjectFileDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Review review = (Review) in.readObject();

    if (reviewDao.insert(review) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 리뷰가 있습니다.");
    }
  }
}
