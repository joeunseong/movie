package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Info;

public class InfoDeleteServlet implements Servlet {

  List<Info> infos;

  public InfoDeleteServlet(List<Info> infos) {
    this.infos = infos;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = -1;
    for (int i = 0; i < infos.size(); i++) {
      if (infos.get(i).getNo() == no) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      infos.remove(index);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영화 정보가 없습니다.");
    }
  }
}
