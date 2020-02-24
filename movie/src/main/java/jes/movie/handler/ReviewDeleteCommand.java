package jes.movie.handler;

import java.util.List;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewDeleteCommand implements Command {

  public Prompt prompt;

  List<Review> reviewList;

  public ReviewDeleteCommand(Prompt prompt, List<Review> list) {
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

    this.reviewList.remove(index);
    System.out.println("리뷰 정보를 삭제했습니다.");
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
