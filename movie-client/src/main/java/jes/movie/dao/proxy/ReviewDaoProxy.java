package jes.movie.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;

public class ReviewDaoProxy implements ReviewDao {
  ObjectInputStream in;
  ObjectOutputStream out;

  public ReviewDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(Review review) throws Exception {
    out.writeUTF("/review/add");
    out.writeObject(review);
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
  public List<Review> findAll() throws Exception {
    out.writeUTF("/review/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<Review>) in.readObject();
  }


  @Override
  public Review findByNo(int no) throws Exception {
    out.writeUTF("/review/detail");
    out.writeInt(no);
    out.flush();
    String response = in.readUTF();

    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return (Review) in.readObject();
  }

  @Override
  public int update(Review review) throws Exception {
    out.writeUTF("/review/update");
    out.writeObject(review);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/review/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();

    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 0;
  }
}
