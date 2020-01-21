package jes.movie.handler;

import java.util.List;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewDetailCommand implements Command {

  public Prompt prompt;

  List<Review> reviewList;

  public ReviewDetailCommand(Prompt prompt, List<Review> list) {
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

    Review review = this.reviewList.get(index);
    System.out.printf("번호: %d\n", review.getNo());
    System.out.printf("영화제목: %s\n", review.getMovieTitle());
    System.out.printf("리뷰 내용: %s\n", review.getReviewSummary());
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
