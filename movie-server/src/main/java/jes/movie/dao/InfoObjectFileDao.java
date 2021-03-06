package jes.movie.dao;

import java.util.List;
import jes.movie.domain.Info;

public class InfoObjectFileDao extends AbstractObjectFileDao<Info> implements InfoDao {
  
public InfoObjectFileDao(String filename) {
  super(filename);
}  

  @Override
  public int insert(Info info) throws Exception {
    if (indexOf(info.getNo()) > -1) { 
      return 0;
    }

    list.add(info); 
    saveData();
    return 1;
  }

  @Override
  public List<Info> findAll() throws Exception {
    return list;
  }

  @Override
  public Info findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  @Override
  public int update(Info info) throws Exception {
    int index = indexOf(info.getNo());
    if (index == -1) { 
      return 0;
    }

    list.set(index, info); 
    saveData();
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();
    return 1;
  }
  
  @Override
    protected <K> int indexOf(K key) {
    for(int i =0; i< list.size(); i++) {
      if(list.get(i).getNo() == (int) key) {
        return i;
      }
    }
    return -1;
    }
}
