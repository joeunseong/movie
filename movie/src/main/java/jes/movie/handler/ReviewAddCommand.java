package jes.movie.handler;

import java.sql.Date;
import java.util.List;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewAddCommand implements Command {

  public Prompt prompt;

  List<Review> reviewList;

  public ReviewAddCommand(Prompt prompt, List<Review> list) {
    this.prompt = prompt;
    reviewList = list;
  }

  @Override
  public void excute() {
    Review review = new Review();
    review.setNo(prompt.inputInt("번호? "));
    review.setMovieTitle(prompt.inputString("영화 제목? "));
    review.setReviewSummary(prompt.inputString("내용? "));
    review.setUpdateDay(new Date(System.currentTimeMillis()));
    review.setViewCount(0);
    reviewList.add(review);
    System.out.println("저장되었습니다.");
  }
}
