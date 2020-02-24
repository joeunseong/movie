package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Info;

public class InfoListServlet implements Servlet {

  List<Info> infos;

  public InfoListServlet(List<Info> infos) {
    this.infos = infos;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(infos);
  }
}
