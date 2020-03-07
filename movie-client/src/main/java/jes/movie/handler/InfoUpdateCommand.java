package jes.movie.handler;

import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoUpdateCommand implements Command {

  InfoDao infoDao;
  Prompt prompt;

  public InfoUpdateCommand(InfoDao infoDao, Prompt prompt) {
    this.infoDao = infoDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Info oldInfo = null;
      try {
        oldInfo = infoDao.findByNo(no);
      } catch(Exception e) {
        System.out.println("해당 번호의 영화 정보가 없습니다!");
        return;
      }
      
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

      newInfo.setOpenDate(prompt.inputDate(String.format("개봉일(%s)? ", oldInfo.getOpenDate()),
          oldInfo.getOpenDate()));

      newInfo.setRunningTime(prompt.inputInt(String.format("러닝타임(%s)", oldInfo.getRunningTime()),
          oldInfo.getRunningTime()));

      if (oldInfo.equals(newInfo)) {
        System.out.println("영화 정보 변경을 취소하였습니다.");
        return;
      }

      infoDao.update(newInfo);
      System.out.println("영화 정보가 변경되었습니다.");
    } catch (Exception e) {
      System.out.println("영화 정보 변경 실패!");
    }
  }
}
