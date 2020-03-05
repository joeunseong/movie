package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.ReviewObjectFileDao;
import jes.movie.domain.Review;

public class ReviewUpdateServlet implements Servlet {

  ReviewObjectFileDao reviewDao;

  public ReviewUpdateServlet(ReviewObjectFileDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Review review = (Review) in.readObject();

    if (reviewDao.update(review) > 0) {
      out.writeUTF("OK");
      
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
  }
}
