package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Review;

public class ReviewUpdateServlet implements Servlet {

  List<Review> reviews;

  public ReviewUpdateServlet(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Review review = (Review) in.readObject();

    int index = -1;
    for (int i = 0; i < reviews.size(); i++) {
      if (reviews.get(i).getNo() == review.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      reviews.set(index, review);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 수업이 없습니다.");
    }
  }
}
