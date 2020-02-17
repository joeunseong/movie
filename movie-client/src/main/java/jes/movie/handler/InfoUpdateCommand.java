package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoUpdateCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public InfoUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");
      out.writeUTF("/info/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Info oldInfo = (Info) in.readObject();
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
        System.out.println("정보 변경을 취소하였습니다.");
        return;
      }

      out.writeUTF("/info/update");
      out.writeObject(newInfo);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("정보가 수정되었습니다.");
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
