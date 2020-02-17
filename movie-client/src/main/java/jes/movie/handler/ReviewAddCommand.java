package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewAddCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public ReviewAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Review review = new Review();
    review.setNo(prompt.inputInt("번호? "));
    review.setMovieTitle(prompt.inputString("영화 제목? "));
    review.setReviewSummary(prompt.inputString("내용? "));
    review.setUpdateDay(new Date(System.currentTimeMillis()));
    review.setViewCount(0);
    try {
      out.writeUTF("/review/add");
      out.writeObject(review);
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
