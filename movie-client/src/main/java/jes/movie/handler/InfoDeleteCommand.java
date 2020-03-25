package jes.movie.handler;

import jes.movie.dao.InfoDao;
import jes.movie.util.Prompt;

public class InfoDeleteCommand implements Command {

  InfoDao infoDao;
  Prompt prompt;

  public InfoDeleteCommand(InfoDao infoDao, Prompt prompt) {
    this.infoDao = infoDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");
      if(infoDao.delete(no)>0) {
        System.out.println("영화 정보를 삭제했습니다.");
      } else {
        System.out.println("해당 번호의 영화 정보가 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("영화 정보 삭제 실패!");
    }
  }
}
