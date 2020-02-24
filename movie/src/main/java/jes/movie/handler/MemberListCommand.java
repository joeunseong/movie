package jes.movie.handler;

import java.util.Iterator;
import java.util.List;
import jes.movie.domain.Member;

public class MemberListCommand implements Command {

  List<Member> memberList;

  public MemberListCommand(List<Member> list) {
    memberList = list;
  }

  @Override
  public void excute() {
    Iterator<Member> iterator = memberList.iterator();
    while (iterator.hasNext()) {
      Member m = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s\n", 
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisterDate());
    }
  }
}
