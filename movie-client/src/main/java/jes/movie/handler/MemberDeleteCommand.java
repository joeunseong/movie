package jes.movie.handler;

import jes.movie.dao.MemberDao;
import jes.movie.util.Prompt;

public class MemberDeleteCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberDeleteCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");
      if(memberDao.delete(no)>0) {
        System.out.println("게시글을 삭제했습니다.");
      } else {
        System.out.println("해당 번호의 게시글이 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("회원 정보 삭제 실패!");
    }
  }
}
