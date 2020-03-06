package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.dao.json.ReviewJsonFileDao;

public class ReviewDeleteServlet implements Servlet {

  ReviewJsonFileDao reviewDao;

  public ReviewDeleteServlet(ReviewJsonFileDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (reviewDao.delete(no) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 리뷰가 없습니다.");
    }
  }
}
