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
     if( reviewDao.delete(no)>0) {
      System.out.println("리뷰를 삭제했습니다.");
     } else {
       System.out.println("해당 번호의 리뷰가 없습니다.");
     }
    } catch (Exception e) {
      System.out.println("리뷰 삭제 실패!");
    }
  }
}

