package jes.movie.handler;

import java.util.List;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoAddCommand implements Command {

  public Prompt prompt;
  List<Info> infoList;

  public InfoAddCommand(Prompt prompt, List<Info> list) {
    this.prompt = prompt;
    infoList = list;
  }

  @Override
  public void excute() {
    Info info = new Info();
    info.setNo(prompt.inputInt("번호? "));
    info.setMovieTitle(prompt.inputString("영화명? "));
    info.setGenre(prompt.inputString("장르? "));
    info.setSummary(prompt.inputString("줄거리? "));
    info.setDirector(prompt.inputString("감독? "));
    info.setActor(prompt.inputString("출연? "));
    info.setKmrb(prompt.inputString("관람등급? "));
    info.setOpenDate(prompt.inputDate("개봉일? "));
    info.setRunningTime(prompt.inputInt("러닝타임? "));
    infoList.add(info);
    System.out.println("등록되었습니다.");
  }
}
