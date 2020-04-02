package jes.movie.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;

public class InfoUpdateServlet implements Servlet {

  InfoDao infoDao;

  public InfoUpdateServlet(InfoDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Info old = infoDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 영화 정보가 없습니다.");
      return;
    }

    Info info = new Info();

    info.setNo(no);

    out.printf("영화명(%s)? \n", old.getMovieTitle());
    out.println("!{}!");
    out.flush();
    info.setMovieTitle(in.nextLine());

    out.printf("장르(%s)? \n", old.getGenre());
    out.println("!{}!");
    out.flush();
    info.setGenre(in.nextLine());

    out.printf("줄거리(%s)? \n", old.getSummary());
    out.println("!{}!");
    out.flush();
    info.setSummary(in.nextLine());

    out.printf("감독(%s)? \n", old.getDirector());
    out.println("!{}!");
    out.flush();
    info.setDirector(in.nextLine());

    out.printf("출연(%s)? \n", old.getActor());
    out.println("!{}!");
    out.flush();
    info.setActor(in.nextLine());

    out.printf("관람등급(%s)? \n", old.getKmrb());
    out.println("!{}!");
    out.flush();
    info.setKmrb(in.nextLine());

    out.printf("개봉일(%s)? \n", old.getOpenDate());
    out.println("!{}!");
    out.flush();
    info.setOpenDate(Date.valueOf(in.nextLine()));

    out.printf("러닝타임(%s)? \n", old.getRunningTime());
    out.println("!{}!");
    out.flush();
    info.setRunningTime(Integer.parseInt(in.nextLine()));

    if (infoDao.update(info) > 0) {
      out.println("영화 정보가 변경되었습니다.");

    } else {
      out.println("영화 정보 변경에 실패했습니다.");
    }

  }
}
