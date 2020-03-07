package jes.movie.handler;

import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoDetailCommand implements Command {

  InfoDao infoDao;
  Prompt prompt;

  public InfoDetailCommand(InfoDao infoDao, Prompt prompt) {
    this.infoDao = infoDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Info info = infoDao.findByNo(no);
      
      System.out.printf("영화명 : %s\n", info.getMovieTitle());
      System.out.printf("장르: %s\n", info.getGenre());
      System.out.printf("줄거리: %s\n", info.getSummary());
      System.out.printf("감독: %s\n", info.getDirector());
      System.out.printf("출연: %s\n", info.getActor());
      System.out.printf("관람등급: %s\n", info.getKmrb());
      System.out.printf("개봉일: %s\n", info.getOpenDate());
      System.out.printf("러닝타임: %d\n", info.getRunningTime());
      
    } catch (Exception e) {
      System.out.println("영화 정보 조회 실패!");
    }
  }
}
