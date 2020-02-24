package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Review;

public class ReviewListServlet implements Servlet {

  List<Review> reviews;

  public ReviewListServlet(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(reviews);
  }
}
