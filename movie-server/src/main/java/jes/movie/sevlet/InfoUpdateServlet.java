package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Info;

public class InfoUpdateServlet implements Servlet {

  List<Info> infos;

  public InfoUpdateServlet(List<Info> infos) {
    this.infos = infos;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Info info = (Info) in.readObject();

    int index = -1;
    for (int i = 0; i < infos.size(); i++) {
      if (infos.get(i).getNo() == info.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      infos.set(index, info);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영화 정보가 없습니다.");
    }
  }
}
