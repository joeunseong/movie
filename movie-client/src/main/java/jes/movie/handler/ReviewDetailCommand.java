package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewDetailCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public ReviewDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/review/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Review review = (Review) in.readObject();
      System.out.printf("번호: %d\n", review.getNo());
      System.out.printf("영화제목: %s\n", review.getMovieTitle());
      System.out.printf("리뷰 내용: %s\n", review.getReviewSummary());
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
