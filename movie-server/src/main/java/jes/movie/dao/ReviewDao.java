package jes.movie.dao;

import java.util.List;
import jes.movie.domain.Info;
import jes.movie.domain.Review;

public interface ReviewDao {
  
  public int insert(Review review) throws Exception;

  public List<Review> findAll() throws Exception;

  public Review findByNo(int no) throws Exception;

  public int update(Review review) throws Exception;

  public int delete(int no) throws Exception;
  
}
