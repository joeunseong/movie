package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Review;

public class ReviewListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public ReviewListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;

  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      out.writeUTF("/review/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      List<Review> reviews = (List<Review>) in.readObject();
      for (Review r : reviews) {
        System.out.printf("%d, %s, %s, %s, %s\n", r.getNo(), r.getMovieTitle(),
            r.getReviewSummary(), r.getUpdateDay(), r.getViewCount());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
