package jes.movie.util;

public interface List<E> {
  public abstract void add(E value);
  public abstract void add(int index, E value);
  public abstract E get(int idx);
  public abstract E set(int index, E obj);
  public abstract E remove(int index);
  public abstract Object[] toArray();
  public abstract E[] toArray(E[] arr);

}
