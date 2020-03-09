package jes.movie.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;

public class ReviewDaoProxy implements ReviewDao {
  DaoProxyHelper daoProxyHelper;

  public ReviewDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Review review) throws Exception {
    class InsertWorker implements Worker {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/review/add");
        out.writeObject(review);
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
  public List<Review> findAll() throws Exception {
    Worker worker = new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/review/list");
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return in.readObject();
      }
    };
    Object result = daoProxyHelper.request(worker);
    return (List<Review>) result;
  }

  @Override
  public Review findByNo(int no) throws Exception {
    Object result = daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/review/detail");
        out.writeInt(no);
        out.flush();
        String response = in.readUTF();

        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }

        return in.readObject();
      }
    });
    return (Review) result;
  }

  @Override
  public int update(Review review) throws Exception {
    return (int) daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/review/update");
        out.writeObject(review);
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
      out.writeUTF("/review/delete");
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
