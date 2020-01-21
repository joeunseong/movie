package jes.movie.handler;

import java.util.Iterator;
import java.util.List;
import jes.movie.domain.Info;

public class InfoListCommand implements Command {

  List<Info> infoList;

  public InfoListCommand(List<Info> list) {
    infoList = list;
  }

  @Override
  public void excute() {
    Iterator<Info> iterator = infoList.iterator();
    while (iterator.hasNext()) {
      Info in = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n", in.getNo(), in.getMovieTitle(),
          in.getGenre(), in.getSummary(), in.getDirector(), in.getActor(), in.getKmrb(),
          in.getOpenDate(), in.getRunningTime());
    }
  }
}
