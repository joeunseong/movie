package jes.movie.handler;

import java.sql.Date;
import jes.movie.dao.MemberDao;
import jes.movie.domain.Member;
import jes.movie.util.Prompt;

public class MemberAddCommand implements Command {

  MemberDao memberDao;
  Prompt prompt;

  public MemberAddCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Member member = new Member();
    
    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setPhoto(prompt.inputString("사진? "));
    member.setTel(prompt.inputString("전화? "));
    
    try {
      memberDao.insert(member);
      System.out.println("저장하였습니다.");
      
    } catch (Exception e) {
      System.out.println("회원 등록 실패!");
    }
  }
}
