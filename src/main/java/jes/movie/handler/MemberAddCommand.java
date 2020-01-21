package jes.movie.handler;

import java.sql.Date;
import java.util.List;
import jes.movie.domain.Member;
import jes.movie.util.Prompt;

public class MemberAddCommand implements Command {

  public Prompt prompt;
  List<Member> memberList;

  public MemberAddCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    memberList = list;
  }

  @Override
  public void excute() {
    Member member = new Member();
    member.setNo(prompt.inputInt("번호? "));
    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setPhoto(prompt.inputString("사진? "));
    member.setTel(prompt.inputString("전화? "));
    member.setRegisterDate(new Date(System.currentTimeMillis()));
    memberList.add(member);
    System.out.println("저장되었습니다.");
  }
}
