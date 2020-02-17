package jes.movie.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import jes.movie.domain.Review;
import jes.movie.util.Prompt;

public class ReviewUpdateCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  Prompt prompt;

  public ReviewUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

      Review oldReview = (Review) in.readObject();
      Review newReview = new Review();

      newReview.setNo(oldReview.getNo());

      newReview.setMovieTitle(prompt.inputString(
          String.format("영화제목(%s)? ", oldReview.getMovieTitle()), oldReview.getMovieTitle()));

      newReview.setReviewSummary(prompt.inputString("리뷰 내용? ", oldReview.getReviewSummary()));

      newReview.setUpdateDay(new Date(System.currentTimeMillis()));
      newReview.setViewCount(0);

      if (oldReview.equals(newReview)) {
        System.out.println("리뷰가 수정이 취소되었습니다.");
        return;
      }
      out.writeUTF("/review/update");
      out.writeObject(newReview);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("리뷰가 수정되었습니다.");
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}

