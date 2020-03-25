package jes.movie.handler;

import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoAddCommand implements Command {

  Prompt prompt;
  InfoDao infoDao;

  public InfoAddCommand(InfoDao infoDao, Prompt prompt) {
    this.infoDao = infoDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Info info = new Info();
    
    info.setMovieTitle(prompt.inputString("영화명? "));
    info.setGenre(prompt.inputString("장르? "));
    info.setSummary(prompt.inputString("줄거리? "));
    info.setDirector(prompt.inputString("감독? "));
    info.setActor(prompt.inputString("출연? "));
    info.setKmrb(prompt.inputString("관람등급? "));
    info.setOpenDate(prompt.inputDate("개봉일? "));
    info.setRunningTime(prompt.inputInt("러닝타임? "));
    
    try {
      infoDao.insert(info);
      System.out.println("저장하였습니다.");
      
    } catch (Exception e) {
      System.out.println("영화 정보 등록 실패!");
    }
  }
}
