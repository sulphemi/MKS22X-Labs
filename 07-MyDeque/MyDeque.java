public class MyDeque<E> {
  private E[] data;
  private int size;
  private int start, end; //these both point to empty space

  public MyDeque() {
    this(32); //initialCapacity defaults to 32
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
    for (int i = 0; i < data.length; i++) {
      output += data[i];
      if (i != data.length - 1) {output += ", ";}
    }
    output += "]";
    return output;
  }

  public String toString() {
    String output = "[";
    for (int i = 0; i < size(); i++) {
      output += data[getIndex(i)];
      output += (i != size() - 1) ? ", " : "]";
    }
    return output;
  }

  //NTS: NEED A RESIZE THINGY
  public void addFirst(E element) {
    //where the element is supposed to go in terms of the data structure
    int perceivedIndex = -1;
    data[getIndex(perceivedIndex)] = element;
    size++;
    decrementStart();
    if (start == end) {
      resize();
    }
  }

  public void addLast(E element) {
    //where the element is supposed to go in terms of the data structure
    int perceivedIndex = size;
    data[getIndex(perceivedIndex)] = element;
    size++;
    incrementEnd();
    if (start == end) {
      resize();
    }
  }
  // public E removeFirst(){ }
  // public E removeLast(){ }
  // public E getFirst(){ }
  // public E getLast(){ }

  //doubles the size of data
  private void resize() {
    @SuppressWarnings("unchecked")
    E[] newThingy = (E[])new Object[data.length * 2];

    //fill the array from index 0 to whatever
    for (int i = 0; i < size; i++) {
      newThingy[i] = data[getIndex(i)];
    }
    //at this point, newThingy is an array filled with elements from the deque
    //from index 0 to size() - 1
    start = newThingy.length - 1; //the next space is at end
    end = size; //next space is at size
    data = newThingy; //overwrite memory address
  }

  //abstraction go brrrr
  //converts PERCEIVED INDEX (in deque) into ACTUAL INDEX (in data)
  private int getIndex(int index) {
    index += start + 1;
    if (index >= data.length) {
      //wrap
      index -= data.length;
    }
    return index;
  }

  //hopefully these methods wont go spoom too hard
  private void decrementStart() {
    start--;
    if (start < 0) {start = data.length - 1;} //start wraps to end of array
  }

  private void incrementStart() {
    start++;
    if (start >= data.length) {start = 0;} //wraps to start
  }

  private void decrementEnd() {
    end--;
    if (end < 0) {end = data.length - 1;} //wraps to end
  }

  private void incrementEnd() {
    end++;
    if (end >= data.length) {end = 0;} //end wraps to beginning of array
  }

  public static void main(String[] args) {
    MyDeque<String> aaaa = new MyDeque<String>(10);
    String[] aa = {"3", "4", null, null, "1", "2"};
    aaaa.data = aa;
    aaaa.start = 3;
    aaaa.end = 2;
    aaaa.size = 4;
    aaaa.addFirst("0");
    aaaa.addLast("5");
    System.out.println(aaaa.toStringDebug());
    System.out.println(aaaa.toString());
    System.out.println("start: " + aaaa.start);
    System.out.println("end:   " + aaaa.end);
  }
}
