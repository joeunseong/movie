package jes.movie.util;

import java.util.Arrays;
import jes.movie.domain.Info;
import jes.movie.domain.Review;

public class ArrayList<E> extends AbstractList<E>{
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

  @Override
  public void add(E obj) {
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
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if(arr.length < this.size) {
      return (E[]) Arrays.copyOf(this.list, this.size, arr.getClass());
    }
    
    System.arraycopy(this.list, 0, arr, 0, this.size);
    return arr;
    }

  @SuppressWarnings("unchecked")
  public E get(int idx) {
    if (idx >= 0 && idx < this.size) {
      return (E)this.list[idx];
    } else {
      return null;
    }
  }
  
  @SuppressWarnings("unchecked")
  public E set(int index, E obj) {
    if(index < 0 || index >=this.size)
      return null;
    E old = (E) this.list[index];
    this.list[index] = obj;
    
    return old;
  }
  
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if(index < 0 || index >= this.size)
      return null;
    
    E old = (E) this.list[index];
    
    for (int i = index + 1; i < this.size; i++) {
      this.list[i - 1] = this.list[i];
    }
      
      this.size--;
      
      this.list[this.size] = null;
      
    return old;
  }
  
  public int size() {
    return this.size;
  }
  @Override
  public void add(int index, E value) {
    if (index < 0 || index >= this.size)
      return;

    if (this.size == this.list.length) {
      grow();
    }

    for (int i = size - 1; i >= index; i--)
      this.list[i + 1] = this.list[i];

    this.list[index] = value;
    this.size++;
  }
  
  private Object[] grow() {
    return this.list = Arrays.copyOf(this.list, 
        newCapacity());
  }
  
  private int newCapacity() {
    int oldSize = this.list.length;
    return oldSize + (oldSize >> 1);
  }
  
  

}
