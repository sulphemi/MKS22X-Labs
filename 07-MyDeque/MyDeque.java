public class MyDeque<E> {
  private E[] data;
  private int size;
  private int start, end;

  public MyDeque() {
    this(64); //initialCapacity defaults to 64
  }

  public MyDeque(int initialCapacity) {
    @SuppressWarnings("unchecked")
    E[] weirdObjectArray = (E[])new Object[initialCapacity];
    data = weirdObjectArray;
    size = 0;
  }

  public int size() {
    return size;
  }

  public String toStringDebug() {
    String output = "[";
    for (int i = 0; ++i < data.length;) {
      output += data[i];
      if (i != data.length - 1) {output += ", ";}
    }
    output += "]";
    return output;
  }
  //public String toString(){ }
  // public void addFirst(E element){ }
  // public void addLast(E element){ }
  // public E removeFirst(){ }
  // public E removeLast(){ }
  // public E getFirst(){ }
  // public E getLast(){ }

  public static void main(String[] args) {
    MyDeque<Object> aaaa = new MyDeque<Object>(5);
    System.out.println(aaaa.toStringDebug());
  }
}
