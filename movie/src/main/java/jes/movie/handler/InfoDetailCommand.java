package jes.movie.handler;

import java.util.List;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoDetailCommand implements Command {

  public Prompt prompt;
  List<Info> infoList;

  public InfoDetailCommand(Prompt prompt, List<Info> list) {
    this.prompt = prompt;
    infoList = list;
  }

  @Override
  public void excute() {
    int index = indexOfInfo(prompt.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Info info = this.infoList.get(index);
    System.out.printf("영화명 : %s\n", info.getMovieTitle());
    System.out.printf("장르: %s\n", info.getGenre());
    System.out.printf("줄거리: %s\n", info.getSummary());
    System.out.printf("감독: %s\n", info.getDirector());
    System.out.printf("출연: %s\n", info.getActor());
    System.out.printf("관람등급: %s\n", info.getKmrb());
    System.out.printf("개봉일: %s\n", info.getOpenDate());
    System.out.printf("러닝타임: %d\n", info.getRunningTime());
  }

  private int indexOfInfo(int no) {
    for (int i = 0; i < this.infoList.size(); i++) {
      if (this.infoList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
