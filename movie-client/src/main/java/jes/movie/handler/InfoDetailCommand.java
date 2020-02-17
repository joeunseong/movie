package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoDetailCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public InfoDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

      Info info = (Info) in.readObject();
      System.out.printf("영화명 : %s\n", info.getMovieTitle());
      System.out.printf("장르: %s\n", info.getGenre());
      System.out.printf("줄거리: %s\n", info.getSummary());
      System.out.printf("감독: %s\n", info.getDirector());
      System.out.printf("출연: %s\n", info.getActor());
      System.out.printf("관람등급: %s\n", info.getKmrb());
      System.out.printf("개봉일: %s\n", info.getOpenDate());
      System.out.printf("러닝타임: %d\n", info.getRunningTime());
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
