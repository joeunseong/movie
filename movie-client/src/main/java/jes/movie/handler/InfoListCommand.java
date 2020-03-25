package jes.movie.handler;

import java.util.List;
import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;

public class InfoListCommand implements Command {

    InfoDao infoDao;

  public InfoListCommand(InfoDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void execute() {
    try {
      List<Info> infos = infoDao.findAll();
      for (Info in : infos) {
        System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n",
            in.getNo(), 
            in.getMovieTitle(),
            in.getGenre(), 
            in.getSummary(), 
            in.getDirector(), 
            in.getActor(), 
            in.getKmrb(),
            in.getOpenDate(), 
            in.getRunningTime());
      }

    } catch (Exception e) {
      System.out.println("영화 정보 목록 조회 실패!");
    }
  }
}

