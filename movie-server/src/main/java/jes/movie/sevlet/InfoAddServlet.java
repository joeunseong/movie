package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.InfoObjectFileDao;
import jes.movie.domain.Info;

public class InfoAddServlet implements Servlet {

InfoObjectFileDao infoDao;
  
  public InfoAddServlet(InfoObjectFileDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Info info = (Info) in.readObject();

    if (infoDao.insert(info) > 0) {
      out.writeUTF("OK");
      
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 영화 정보가 있습니다.");
    }
  }
}
