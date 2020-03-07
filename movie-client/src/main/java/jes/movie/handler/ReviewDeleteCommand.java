package jes.movie.handler;

import jes.movie.dao.ReviewDao;
import jes.movie.util.Prompt;

public class ReviewDeleteCommand implements Command {
  ReviewDao reviewDao;
  Prompt prompt;

  public ReviewDeleteCommand(ReviewDao reviewDao, Prompt prompt) {
    this.reviewDao = reviewDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");
      reviewDao.delete(no);
      System.out.println("해당 리뷰를 삭제했습니다.");
      
    } catch (Exception e) {
      System.out.println("리뷰 삭제 실패!");
    }
  }
}

