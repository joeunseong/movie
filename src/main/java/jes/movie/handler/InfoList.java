package jes.movie.handler;

import java.util.Arrays;
import jes.movie.domain.Info;

public class InfoList {
  static final int DEFAULT_SIZE = 100;
  int size = 0;
  Info[] list;
  
  public InfoList() {
    this.list = new Info[DEFAULT_SIZE];
  }
  
  public InfoList(int capacity) {
    if (capacity < DEFAULT_SIZE || capacity > 10000)
      this.list = new Info[DEFAULT_SIZE];
    else
      this.list = new Info[capacity];
  }

  public void add(Info info) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
      System.out.printf("새 배열을 %d 개 생성하였음!\n", newCapacity);
    }
    this.list[this.size++] = info;
  }

  public Info[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }
  
  
  
  
}
