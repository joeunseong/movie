package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jes.movie.dao.json.MemberJsonFileDao;
import jes.movie.domain.Member;

public class MemberUpdateServlet implements Servlet {

  MemberJsonFileDao memberDao;

  public MemberUpdateServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = (Member) in.readObject();

    if (memberDao.update(member) > 0) {
      out.writeUTF("OK");
      
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }
}
