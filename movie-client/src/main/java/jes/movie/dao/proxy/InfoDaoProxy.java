package jes.movie.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;

public class InfoDaoProxy implements InfoDao {

  DaoProxyHelper daoProxyHelper;

  public InfoDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Info info) throws Exception {
    class InsertWorker implements Worker {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/info/add");
        out.writeObject(info);
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
  public List<Info> findAll() throws Exception {
    Worker worker = new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/info/list");
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return in.readObject();
      }
    };

    Object result = daoProxyHelper.request(worker);
    return (List<Info>) result;
  }

  @Override
  public Info findByNo(int no) throws Exception {
    Object result = daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/info/detail");
        out.writeInt(no);
        out.flush();
        String response = in.readUTF();

        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }

        return in.readObject();
      }
    });
    return (Info) result;
  }

  @Override
  public int update(Info info) throws Exception {
    return (int) daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/info/update");
        out.writeObject(info);
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
      out.writeUTF("/info/delete");
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
