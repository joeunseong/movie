package jes.movie.handler;

import java.util.List;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoUpdateCommand implements Command {

  public Prompt prompt;
  List<Info> infoList;

  public InfoUpdateCommand(Prompt prompt, List<Info> list) {
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

    Info oldInfo = this.infoList.get(index);
    Info newInfo = new Info();
    newInfo.setNo(oldInfo.getNo());

    newInfo.setMovieTitle(prompt.inputString(String.format("영화명(%s)", oldInfo.getMovieTitle()),
        oldInfo.getMovieTitle()));

    newInfo.setGenre(
        prompt.inputString(String.format("장르(%s)", oldInfo.getGenre()), oldInfo.getGenre()));

    newInfo.setSummary(
        prompt.inputString(String.format("줄거리", oldInfo.getSummary()), oldInfo.getSummary()));

    newInfo.setDirector(prompt.inputString(String.format("감독(%s)? ", oldInfo.getDirector()),
        oldInfo.getDirector()));

    newInfo.setActor(
        prompt.inputString(String.format("출연(%s)? ", oldInfo.getActor()), oldInfo.getActor()));

    newInfo.setKmrb(
        prompt.inputString(String.format("관람등급(%s)? ", oldInfo.getKmrb()), oldInfo.getKmrb()));

    newInfo.setOpenDate(
        prompt.inputDate(String.format("개봉일(%s)? ", oldInfo.getOpenDate()), oldInfo.getOpenDate()));

    newInfo.setRunningTime(prompt.inputInt(String.format("러닝타임(%s)", oldInfo.getRunningTime()),
        oldInfo.getRunningTime()));

    if (oldInfo.equals(newInfo)) {
      System.out.println("정보 변경을 취소하였습니다.");
      return;
    }

    this.infoList.set(index, newInfo);
    System.out.println("정보가 수정되었습니다.");
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
