package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.dao.json.InfoJsonFileDao;
import jes.movie.domain.Info;

public class InfoDetailServlet implements Servlet {

  InfoJsonFileDao infoDao;

  public InfoDetailServlet(InfoJsonFileDao infoDao) {
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
