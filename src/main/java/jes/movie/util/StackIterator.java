package jes.movie.util;

public class StackIterator<E> implements Iterator<E> {
  Stack<E> stack;
  int cursor;
  
  public StackIterator(Stack<E> stack) {
    this.stack = stack.clone();
  }

  @Override
  public boolean hasNext() {
    return !stack.empty();
  }

  @Override
  public E next() {
    return stack.pop();
  }
  

}
