package jes.movie.dao;

import java.util.List;
import jes.movie.domain.Info;

public interface InfoDao {
  
  public int insert(Info info) throws Exception;

  public List<Info> findAll() throws Exception;

  public Info findByNo(int no) throws Exception;

  public int update(Info info) throws Exception;

  public int delete(int no) throws Exception;
  
}
