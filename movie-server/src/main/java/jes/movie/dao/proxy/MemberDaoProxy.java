package jes.movie.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.MemberDao;
import jes.movie.domain.Member;

public class MemberDaoProxy implements MemberDao {
  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(Member member) throws Exception {
    out.writeUTF("/member/add");
    out.writeObject(member);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF()); 
    }

    System.out.println("저장하였습니다.");
    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    out.writeUTF("/member/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<Member>) in.readObject();
  }


  @Override
  public Member findByNo(int no) throws Exception {
    out.writeUTF("/member/detail");
    out.writeInt(no);
    out.flush();
    String response = in.readUTF();

    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return (Member) in.readObject();
  }

  @Override
  public int update(Member member) throws Exception {
    out.writeUTF("/member/update");
    out.writeObject(member);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/member/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();

    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 0;
  }
}
