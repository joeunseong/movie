package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.dao.json.ReviewJsonFileDao;

public class ReviewListServlet implements Servlet {

  ReviewJsonFileDao reviewDao;

  public ReviewListServlet(ReviewJsonFileDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(reviewDao.findAll());
  }
}
