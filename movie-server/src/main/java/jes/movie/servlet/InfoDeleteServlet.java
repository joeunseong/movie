package jes.movie.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jes.movie.dao.InfoDao;

public class InfoDeleteServlet implements Servlet {

  InfoDao infoDao;

  public InfoDeleteServlet(InfoDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? "); // 사용자에게 출력하라!
    out.println("!{}!"); // 사용자로부터 한 줄의 문자열을 입력 받아서 보내라!
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    if (infoDao.delete(no) > 0) {
      out.println("영화 정보를 삭제했습니다.");

    } else {
      out.println("해당 번호의 영화 정보가 없습니다.");
    }
  }
}
