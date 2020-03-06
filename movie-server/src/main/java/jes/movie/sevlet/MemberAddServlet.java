package jes.movie.sevlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.MemberObjectFileDao;
import jes.movie.domain.Member;

public class MemberAddServlet implements Servlet {

  MemberObjectFileDao memberDao;

  public MemberAddServlet(MemberObjectFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = (Member) in.readObject();


    if (memberDao.insert(member) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 회원이 있습니다.");
    }
  }
}