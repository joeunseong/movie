package jes.movie.util;

public class LinkedList2 {
  Node first;
  Node last;
  int size;
  
  static class Node{
    Object value;
    Node next;
  }

  public void add(Object value ) {
    Node newNode = new Node();
    newNode.value = value;
    
    if(first == null) {
      last = first = newNode.next;
    } else {
      last.next = newNode;
      last = newNode;
    }
    this.size++;
  }
  
  public void add(int index, Object value) {
    if(index<0 || index> this.size) {
      return;
    }
    
    Node newNode = new Node();
    newNode.value = value;
    
    Node cursor = first;
    for(int i =0; i <index -1; i++) {
      cursor = cursor.next;
    }
    
    if(index == 0) {
      newNode.next = first;
    } else {
      newNode.next = cursor.next;
      cursor = newNode.next;
    }
    this.size++;
  }
  
  public Object get(int index) {
    if(index < 0 || index >this.size) {
      return null;
    }
    
    Node cursor =first;
    for(int i=0; i< index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }
  
  
}
