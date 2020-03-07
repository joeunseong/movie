package jes.movie.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;

public class InfoDaoProxy implements InfoDao {
  ObjectInputStream in;
  ObjectOutputStream out;

  public InfoDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(Info info) throws Exception {
    out.writeUTF("/info/add");
    out.writeObject(info);
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
  public List<Info> findAll() throws Exception {
    out.writeUTF("/info/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<Info>) in.readObject();
  }


  @Override
  public Info findByNo(int no) throws Exception {
    out.writeUTF("/info/detail");
    out.writeInt(no);
    out.flush();
    String response = in.readUTF();

    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return (Info) in.readObject();
  }

  @Override
  public int update(Info info) throws Exception {
    out.writeUTF("/info/update");
    out.writeObject(info);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/info/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();

    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 0;
  }
}
