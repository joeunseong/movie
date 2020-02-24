package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Review;

public class ReviewAddServlet implements Servlet {

  List<Review> reviews;

  public ReviewAddServlet(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Review review = (Review) in.readObject();

    int i = 0;
    for (; i < reviews.size(); i++) {
      if (reviews.get(i).getNo() == review.getNo()) {
        break;
      }
    }

    if (i == reviews.size()) {
      reviews.add(review);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 리뷰가 있습니다.");
    }
  }
}
