package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.dao.json.InfoJsonFileDao;

public class InfoListServlet implements Servlet {

  InfoJsonFileDao infoDao;
  
  public InfoListServlet(InfoJsonFileDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(infoDao.findAll());
  }
}
