package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.InfoObjectFileDao;
import jes.movie.domain.Info;

public class InfoListServlet implements Servlet {

  InfoObjectFileDao infoDao;
  
  public InfoListServlet(InfoObjectFileDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(infoDao.findAll());
  }
}
