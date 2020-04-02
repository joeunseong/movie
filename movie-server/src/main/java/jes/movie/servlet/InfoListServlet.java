package jes.movie.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;

public class InfoListServlet implements Servlet {

  InfoDao infoDao;

  public InfoListServlet(InfoDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Info> infos = infoDao.findAll();
    for(Info info:infos) {
      out.printf("=> %d, %s, %s, %s ,%s, %s, %s, %s, %d\n",
          info.getNo(),
          info.getMovieTitle(),
          info.getGenre(),
          info.getSummary(),
          info.getDirector(),
          info.getActor(),
          info.getKmrb(),
          info.getOpenDate(),
          info.getRunningTime());
    }
  }
}
