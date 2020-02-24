package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Review;

public class ReviewDeleteServlet implements Servlet {

  List<Review> reviews;

  public ReviewDeleteServlet(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    int index = -1;
    for (int i = 0; i < reviews.size(); i++) {
      if (reviews.get(i).getNo() == no) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      reviews.remove(index);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 리뷰가 없습니다.");
    }
  }
}
