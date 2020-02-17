package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public InfoAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
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
    try {
      out.writeUTF("/info/add");
      out.writeObject(info);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
