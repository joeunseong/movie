package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.InfoObjectFileDao;
import jes.movie.domain.Info;

public class InfoDeleteServlet implements Servlet {

  InfoObjectFileDao infoDao;

  public InfoDeleteServlet(InfoObjectFileDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();


    if (infoDao.delete(no) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영화 정보가 없습니다.");
    }
  }
}
