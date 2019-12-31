package jes.movie.handler;

import java.util.Arrays;
import jes.movie.domain.Member;

public class MemberList {
  static final int DEFAULT_SIZE = 100;
  int size = 0;
  Member[] list;

  public MemberList() {
    this.list = new Member[DEFAULT_SIZE];
  }

  public MemberList(int capacity) {
    if (capacity < DEFAULT_SIZE || capacity > 10000)
      this.list = new Member[DEFAULT_SIZE];
    else
      this.list = new Member[capacity];
  }

  public void add(Member member) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
      System.out.printf("새 배열을 %d 개 생성하였음!\n", newCapacity);
    }
    this.list[this.size++] = member;
  }

  public Member[] toArray() {
    Member[] arr = new Member[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = list[i];
    }
    return Arrays.copyOf(this.list, this.size);
  }
}
