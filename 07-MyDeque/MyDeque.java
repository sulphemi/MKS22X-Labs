public class MyDeque<E> {
  private E[] data;
  private int size;
  private int start, end;

  // public MyDeque(){  }
  public MyDeque(int initialCapacity) {
    size = initialCapacity;
    @SuppressWarnings("unchecked")
    E[] weirdObjectArray = (E[])new Object[initialCapacity];
    data = weirdObjectArray;
  }
  // public int size(){ }
  // public String toString(){ }
  // public void addFirst(E element){ }
  // public void addLast(E element){ }
  // public E removeFirst(){ }
  // public E removeLast(){ }
  // public E getFirst(){ }
  // public E getLast(){ }
}
