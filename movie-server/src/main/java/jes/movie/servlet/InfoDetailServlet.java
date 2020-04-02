package jes.movie.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;

public class InfoDetailServlet implements Servlet {

  InfoDao infoDao;

  public InfoDetailServlet(InfoDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Info info = infoDao.findByNo(no);

    if (info != null) {
      out.printf("영화명 : %s\n", info.getMovieTitle());
      out.printf("장르: %s\n", info.getGenre());
      out.printf("줄거리: %s\n", info.getSummary());
      out.printf("감독: %s\n", info.getDirector());
      out.printf("출연: %s\n", info.getActor());
      out.printf("관람등급: %s\n", info.getKmrb());
      out.printf("개봉일: %s\n", info.getOpenDate());
      out.printf("러닝타임: %d\n", info.getRunningTime());
    } else {
      out.printf("해당 번호의 영화정보가 없습니다.");
    }
  }
}
