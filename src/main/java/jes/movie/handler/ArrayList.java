package jes.movie.handler;

import java.util.Arrays;
import jes.movie.domain.Review;

public class ArrayList {
  static final int DEFAULT_SIZE = 100;

  int size = 0;
  Object[] list;

  public ArrayList() {
    this.list = new Object[DEFAULT_SIZE];
  }

  public ArrayList(int capacity) {
    if (capacity < DEFAULT_SIZE || capacity > 10000)
      this.list = new Object[DEFAULT_SIZE];
    else
      this.list = new Object[capacity];
  }

  public void add(Object obj) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
      System.out.printf("새 배열을 %d 개 생성하였음!\n", newCapacity);
    }
    this.list[this.size++] = obj;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

  public Object get(int idx) {
    if (idx >= 0 && idx < this.size) {
      return this.list[idx];
    } else {
      return null;
    }
  }
}
