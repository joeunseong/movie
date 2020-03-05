package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.InfoObjectFileDao;
import jes.movie.domain.Info;

public class InfoUpdateServlet implements Servlet {

  InfoObjectFileDao infoDao;

  public InfoUpdateServlet(InfoObjectFileDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Info info = (Info) in.readObject();

    if (infoDao.update(info) > 0) {
      out.writeUTF("OK");
      
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영화 정보가 없습니다.");
    }
  }
}
