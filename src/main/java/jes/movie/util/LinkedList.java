package jes.movie.util;

import java.lang.reflect.Array;

public class LinkedList<E> {
  Node<E> first;
  Node<E> last;
  int size;

  static class Node<T> {
    T value;
    Node<T> next;
  }

  public void add(E value) {
    Node<E> newNode = new Node<>();
    newNode.value = value;

    if (first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    this.size++;
  }

  public E get(int index) {
    if (index < 0 || index > this.size) {
      return null;
    }

    Node<E> cursor = first;
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    return (E) cursor.value;
  }

  public void add(int index, E value) {
    if (index < 0 || index > this.size) {
      return;
    }
    Node<E> newNode = new Node<>();
    newNode.value = value;

    Node<E> cursor = first;
    for (int i = 0; i < index - 1; i++) {
      cursor = cursor.next;
    }

    if (index == 0) {
      newNode.next = first;
      first = newNode;
    } else {
      newNode.next = cursor.next;
      cursor.next = newNode;
    }
  }

  public E remove(int index) {
    if (index < 0 || index > this.size) {
      return null;
    }

    Node<E> cursor = first;
    for (int i = 0; i < index - 1; i++) {
      cursor = cursor.next;
    }

    Node<E> deleteNode = null;
    if (index == 0) {
      deleteNode = first;
      first = deleteNode.next;
    } else {
      deleteNode = cursor.next;
      cursor.next = deleteNode.next;
    }
    deleteNode.next = null;
    return deleteNode.value;
  }
  
  public E set(int index, E value) {
    if(index < 0 || index > this.size) {
      return null;
    }
    
    Node<E> cursor = first;
    for(int i =0; i< index; i++) {
      cursor = cursor.next;
    }
    E oldValue = cursor.value;
    cursor.value = value;
    return oldValue;
  }
  
  public Object[] toArray() {
    Object[] arr = new Object[size];
    Node cursor = first;
    for(int i =0; i<size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) { 
    if(arr.length<size) {
      arr = (E[])Array.newInstance(arr.getClass().getComponentType(), size);
    }
    
    Node<E> cursor = first;
    for(int i =0; i<size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }
  
  public int size() {
    return this.size;
  }
}
