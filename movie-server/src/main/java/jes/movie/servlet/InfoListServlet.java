package jes.movie.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.dao.InfoDao;

public class InfoListServlet implements Servlet {

  InfoDao infoDao;
  
  public InfoListServlet(InfoDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(infoDao.findAll());
  }
}
