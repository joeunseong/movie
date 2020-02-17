package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.domain.Info;

public class InfoListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  public InfoListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      out.writeUTF("/info/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Info> infos = (List<Info>) in.readObject();
      for (Info in : infos) {
        System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n", in.getNo(), in.getMovieTitle(),
            in.getGenre(), in.getSummary(), in.getDirector(), in.getActor(), in.getKmrb(),
            in.getOpenDate(), in.getRunningTime());
      }

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}

