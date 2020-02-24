package jes.movie.handler;

import java.util.List;
import jes.movie.domain.Member;
import jes.movie.util.Prompt;

public class MemberDeleteCommand implements Command {

  public Prompt prompt;
  List<Member> memberList;

  public MemberDeleteCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    memberList = list;
  }

  @Override
  public void excute() {
    int index = indexOfMember(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 멤버 정보가 없습니다.");
      return;
    }

    this.memberList.remove(index);
    System.out.println("해당 멤버 정보를 삭제했습니다.");
  }

  private int indexOfMember(int no) {
    for (int i = 0; i < this.memberList.size(); i++) {
      if (this.memberList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
