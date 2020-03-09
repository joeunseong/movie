package jes.movie.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.MemberDao;
import jes.movie.domain.Member;

public class MemberDaoProxy implements MemberDao {
  DaoProxyHelper daoProxyHelper;

  public MemberDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Member member) throws Exception {
    class InsertWorker implements Worker {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/member/add");
        out.writeObject(member);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }

        return 1;
      }
    }
    InsertWorker worker = new InsertWorker();
    int resultState = (int) daoProxyHelper.request(worker);
    return resultState;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    Worker worker = new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/member/list");
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return in.readObject();
      }
    };
    Object result = daoProxyHelper.request(worker);
    return (List<Member>) result;
  }

  @Override
  public Member findByNo(int no) throws Exception {
    Object result = daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/member/detail");
        out.writeInt(no);
        out.flush();
        String response = in.readUTF();

        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }

        return in.readObject();
      }
    });
    return (Member) result;
  }

  @Override
  public int update(Member member) throws Exception {
    return (int) daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/member/update");
        out.writeObject(member);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return 1;
      }
    });
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }
}
