import java.util.*;

public class MyDeque<E> {
  /***** FIELDS *****/

  private E[] data;
  private int size;
  private int start, end; //these both point to empty space

  /***** CONSTRUCTORS *****/

  public MyDeque() {
    this(32); //initialCapacity defaults to 32
  }

  public MyDeque(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("aaaaa starting capacity cannot be negative!");
    } else if (initialCapacity < 2) {
      initialCapacity = 2; //to avoid errors, just set initialCapacity to 2
    }

    @SuppressWarnings("unchecked")
    E[] weirdObjectArray = (E[])new Object[initialCapacity];
    data = weirdObjectArray;
    size = 0;
    start = data.length / 2;
    end = start + 1;
  }

  /***** PRIVATE METHODS *****/

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

  /***** PUBLIC METHODS *****/

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
    if (size == 0) {return "[]";}
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

  public E removeFirst() {
    if (size < 1) {throw new IllegalStateException("error: nothing to remuwu :3");}
    int perceivedIndex = 0;
    E removed = data[getIndex(perceivedIndex)]; //store reference so it doesnt get deleted (yet)
    data[getIndex(perceivedIndex)] = null; //remove reference to object from array
    size--;
    incrementStart();
    //no need to resize
    return removed;
  }

  public E removeLast() {
    if (size < 1) {throw new IllegalStateException("error: nothing to remuwu :3");}
    int perceivedIndex = size - 1;
    E removed = data[getIndex(perceivedIndex)]; //store reference
    data[getIndex(perceivedIndex)] = null; //remove reference
    size--;
    decrementEnd();
    //resize unneeded
    return removed;
  }

  //holy crap getIndex is so revolutionary
  public E getFirst() {
    if (size < 1) {throw new IllegalStateException("oh noe oh woe your deque is empty your program is no!");}
    return data[getIndex(0)];
  }

  public E getLast() {
    if (size < 1) {throw new IllegalStateException("oh noe oh woe your deque is empty your program is no!");}
    return data[getIndex(size - 1)];
  }
}
