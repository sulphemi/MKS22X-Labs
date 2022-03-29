public class MyDeque<E> {
  private E[] data;
  private int size;
  private int start, end; //these both point to empty space

  public MyDeque() {
    this(64); //initialCapacity defaults to 64
  }

  public MyDeque(int initialCapacity) {
    @SuppressWarnings("unchecked")
    E[] weirdObjectArray = (E[])new Object[initialCapacity];
    data = weirdObjectArray;
    size = 0;
    start = data.length / 2;
    end = start + 1;
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

  public String toString() {
    String output = "[";
    int i = start + 1;
    while (i != end) {
      if (i == data.length) {i = 0;} //if we reached end of physical array, wrap back to beginning
      output += data[i];
      output += ", ";
      i++;
    }
    return output;
  }

  //NTS: NEED A RESIZE THINGY
  public void addFirst(E element) {
    data[start] = element; //
    start--;
    size++;
    //if start == end resize
  }

  // public void addLast(E element){ }
  // public E removeFirst(){ }
  // public E removeLast(){ }
  // public E getFirst(){ }
  // public E getLast(){ }

  //doubles the size of data
  private void resize() {
    @SuppressWarnings("unchecked")
    E[] newThingy = (E[])new Object[data.length * 2];

    int i = 0; //original array pointer
    int k = 0; //new array pointer
    //copy from arraystart to middle
    while (i < start) {
      newThingy[k] = data[i];
      i++;
      k++;
    }
    //now i points to a null
    i++; //increment i so it points to something again
    //copy from middle to 
    while (i < )
  }

  public static void main(String[] args) {
    MyDeque<Object> aaaa = new MyDeque<Object>(10);
    aaaa.end = 9;
    System.out.println(aaaa.toStringDebug());
    System.out.println(aaaa.toString());
    System.out.println("start: " + aaaa.start);
    System.out.println("end:   " + aaaa.end);
  }
}
