package jes.movie.handler;

import java.util.Iterator;
import java.util.List;
import jes.movie.domain.Review;

public class ReviewListCommand implements Command {

  List<Review> reviewList;

  public ReviewListCommand(List<Review> list) {
    reviewList = list;

  }

  @Override
  public void excute() {
    Iterator<Review> iterator = reviewList.iterator();
    while (iterator.hasNext()) {
      Review r = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s\n", r.getNo(), r.getMovieTitle(), r.getReviewSummary(),
          r.getUpdateDay(), r.getViewCount());
    }
  }
}
