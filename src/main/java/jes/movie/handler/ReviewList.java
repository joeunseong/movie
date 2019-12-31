package jes.movie.handler;

import java.util.Arrays;
import jes.movie.domain.Review;

public class ReviewList {
  static final int DEFAULT_SIZE = 100;

  int size = 0;
  Review[] list;

  public ReviewList() {
    this.list = new Review[DEFAULT_SIZE];
  }

  public ReviewList(int capacity) {
    if (capacity < DEFAULT_SIZE || capacity > 10000)
      this.list = new Review[DEFAULT_SIZE];
    else
      this.list = new Review[capacity];
  }

  public void add(Review review) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
      System.out.printf("새 배열을 %d 개 생성하였음!\n", newCapacity);
    }
    this.list[this.size++] = review;
  }

  public Review[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

  public Review get(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.list[i].getNo() == no) {
        return this.list[i];
      }
    }
    return null;
  }
}
