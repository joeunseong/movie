package jes.movie.handler;

import java.util.List;
import jes.movie.domain.Info;
import jes.movie.util.Prompt;

public class InfoDeleteCommand implements Command {

  public Prompt prompt;
  List<Info> infoList;

  public InfoDeleteCommand(Prompt prompt, List<Info> list) {
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

    this.infoList.remove(index);
    System.out.println("해당 영화 정보를 삭제했습니다.");
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
