package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Info;

public class InfoAddServlet implements Servlet {

  List<Info> infos;

  public InfoAddServlet(List<Info> infos) {
    this.infos = infos;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Info info = (Info) in.readObject();

    int i = 0;
    for (; i < infos.size(); i++) {
      if (infos.get(i).getNo() == info.getNo()) {
        break;
      }
    }

    if (i == infos.size()) {
      infos.add(info);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 영화 정보가 있습니다.");
    }
  }
}
