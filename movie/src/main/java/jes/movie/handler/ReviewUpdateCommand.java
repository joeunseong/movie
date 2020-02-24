package jes.movie.handler;

import java.sql.Date;
import java.util.List;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewUpdateCommand implements Command {

  public Prompt prompt;

  List<Review> reviewList;

  public ReviewUpdateCommand(Prompt prompt, List<Review> list) {
    this.prompt = prompt;
    reviewList = list;

  }

  @Override
  public void excute() {
    int index = indexOfReview(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Review oldReview = this.reviewList.get(index);
    Review newReview = new Review();
    newReview.setNo(oldReview.getNo());

    newReview.setMovieTitle(prompt.inputString(
        String.format("영화제목(%s)? ", oldReview.getMovieTitle()), oldReview.getMovieTitle()));

    newReview.setReviewSummary(prompt.inputString("리뷰 내용? ", oldReview.getReviewSummary()));

    newReview.setUpdateDay(new Date(System.currentTimeMillis()));
    newReview.setViewCount(0);

    if (oldReview.equals(newReview)) {
      System.out.println("리뷰가 수정이 취소되었습니다.");
      return;
    }

    this.reviewList.set(index, newReview);
    System.out.println("리뷰가 수정되었습니다.");
  }

  private int indexOfReview(int no) {
    for (int i = 0; i < this.reviewList.size(); i++) {
      if (this.reviewList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
