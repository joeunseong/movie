package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.InfoObjectFileDao;
import jes.movie.domain.Info;

public class InfoDetailServlet implements Servlet {

  InfoObjectFileDao infoDao;

  public InfoDetailServlet(InfoObjectFileDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    Info info = infoDao.findByNo(no);

    if (info != null) {
      out.writeUTF("OK");
      out.writeObject(info);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영화정보가 없습니다.");
    }
  }
}
