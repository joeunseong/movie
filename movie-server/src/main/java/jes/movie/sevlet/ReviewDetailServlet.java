package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Review;

public class ReviewDetailServlet implements Servlet {

  List<Review> reviews;

  public ReviewDetailServlet(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Review review = null;
    for (Review r : reviews) {
      if (r.getNo() == no) {
        review = r;
        break;
      }
    }

    if (review != null) {
      out.writeUTF("OK");
      out.writeObject(review);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 리뷰가 없습니다.");
    }
  }
}
