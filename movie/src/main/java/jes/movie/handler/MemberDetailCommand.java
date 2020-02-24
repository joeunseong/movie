package jes.movie.handler;

import java.util.List;
import jes.movie.domain.Member;
import jes.movie.util.Prompt;

public class MemberDetailCommand implements Command {

  public Prompt prompt;
  List<Member> memberList;

  public MemberDetailCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    memberList = list;
  }

  @Override
  public void excute() {
    int index = indexOfMember(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    Member member = this.memberList.get(index);
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisterDate());
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
