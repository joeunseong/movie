package jes.movie.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {
  public void offer(E value) {
    this.add(value);
  }

  public E poll() {
    return this.remove(0);
  }

  /*
  @Override
  public Queue clone() {
    try {
      return (Queue) super.clone();
    } catch (CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }
  }
   */

  @Override
  public Queue<E> clone() {
    Queue<E> temp = new Queue<E>();

    for (int i = 0; i < this.size(); i++) {
      temp.offer(this.get(i));
    }
    return temp;
  }

  public Iterator<E> iterator() {
    return new Iterator<E>() {
      Queue<E> queue = (Queue<E>) Queue.this.clone();
 
      @Override
      public boolean hasNext() {
        return queue.size() > 0;
      }
      
      @Override
      public E next() {
        return queue.poll();
      }
    };
  }  
}
