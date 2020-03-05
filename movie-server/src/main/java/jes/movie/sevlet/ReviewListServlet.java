package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.ReviewObjectFileDao;
import jes.movie.domain.Review;

public class ReviewListServlet implements Servlet {

  ReviewObjectFileDao reviewDao;

  public ReviewListServlet(ReviewObjectFileDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(reviewDao.findAll());
  }
}
