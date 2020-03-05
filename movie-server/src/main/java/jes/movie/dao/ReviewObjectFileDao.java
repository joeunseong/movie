package jes.movie.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import jes.movie.domain.Review;

public class ReviewObjectFileDao extends AbstractObjectFileDao<Review>{
  
  public ReviewObjectFileDao(String filename) {
    super(filename);
  }
  
  public int insert(Review review) throws Exception {
    if (indexOf(review.getNo()) > -1) { 
      return 0;
    }
    list.add(review); 
    saveData();
    return 1;
  }

  public List<Review> findAll() throws Exception {
    return list;
  }

  public Review findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(Review review) throws Exception {
    int index = indexOf(review.getNo());
    if (index == -1) {
      return 0;
    }

    list.set(index, review); 
    saveData();
    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }
    list.remove(index);
    saveData();
    return 1;
  }

  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
  
  @Override
  protected <K> int indexOf(K key) {
    for(int i =0; i<list.size(); i++) {
      if(list.get(i).getNo() == (int) key) {
        return i;
      }
    }
    return -1;
  }
}
