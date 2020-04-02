package jes.movie.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;

public class InfoAddServlet implements Servlet {

  InfoDao infoDao;

  public InfoAddServlet(InfoDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Info info = new Info();

    out.println("영화명? ");
    out.println("!{}!");
    out.flush();
    info.setMovieTitle(in.nextLine());

    out.println("장르? ");
    out.println("!{}!");
    out.flush();
    info.setGenre(in.nextLine());

    out.println("줄거리? ");
    out.println("!{}!");
    out.flush();
    info.setSummary(in.nextLine());

    out.println("감독? ");
    out.println("!{}!");
    out.flush();
    info.setDirector(in.nextLine());

    out.println("출연? ");
    out.println("!{}!");
    out.flush();
    info.setActor(in.nextLine());

    out.println("관람등급? ");
    out.println("!{}!");
    out.flush();
    info.setKmrb(in.nextLine());

    out.println("개봉일? ");
    out.println("!{}!");
    out.flush();
    info.setOpenDate(Date.valueOf(in.nextLine()));

    out.println("러닝타임? ");
    out.println("!{}!");
    out.flush();
    info.setRunningTime(Integer.parseInt(in.nextLine()));

    if (infoDao.insert(info) > 0) {
      out.println("저장하였습니다.");

    } else {
      out.println("영화 정보 등록 실패!");
    }
  }
}
