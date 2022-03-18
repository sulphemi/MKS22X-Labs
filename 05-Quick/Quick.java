import java.util.*;

public class Quick {
  public static int partition(int[] array, int start, int end) {
    int pivotIndex = start;
    int leftPointer = start + 1;
    int rightPointer = end;
    boolean bonk = true; //keeps track of where to put equal values

    while (leftPointer != rightPointer) { //stops when leftPointer == rightPointer
      if (array[leftPointer] == array[pivotIndex]) {
        if (bonk) {
          //do the code for placing to left
          leftPointer++;
        } else {
          //do the code for placing to right
          int swapped = array[rightPointer];
          array[rightPointer] = array[leftPointer];
          array[leftPointer] = swapped;
          rightPointer--;
        }
        bonk = !bonk; //flip boolean
      } else if (array[leftPointer] < array[pivotIndex]) { //
        //no action needed. advance left pointer.
        leftPointer++;
      } else {
        //deport number to right side
        //that means swap the terms
        int swapped = array[rightPointer];
        array[rightPointer] = array[leftPointer];
        array[leftPointer] = swapped;
        rightPointer--;
      }
    }

    //leftPointer now == rightPointer
    //put pivot in its place
    //that means swap pivot with index before center OR index after center
    //depending on which represents which half
    if (array[leftPointer] < array[pivotIndex]) {
      //if center value is less than the pivot, we can swap center with pivot
      int swapped = array[leftPointer];
      array[leftPointer] = array[pivotIndex];
      array[pivotIndex] = swapped;
      pivotIndex = leftPointer;
    } else {
      //else put the pivot before the center
      int swapped = array[leftPointer - 1];
      array[leftPointer - 1] = array[pivotIndex];
      array[pivotIndex] = swapped;
      pivotIndex = leftPointer - 1;
    }

    return pivotIndex;
  }

  public static int quickselect(int[] data, int index) {
    int partitionLowerBound = 0;
    int partitionUpperBound = data.length - 1;
    int pivotIndex;
    while (partitionLowerBound != partitionUpperBound) {
      pivotIndex = partition(data, partitionLowerBound, partitionUpperBound);

      if (pivotIndex < index) {
        partitionLowerBound = pivotIndex + 1;
      } else {
        partitionUpperBound = pivotIndex;
      }
    }

    return data[partitionLowerBound];
  }

  //recursive method
  public static void quicksort(int[] data, int start, int end) {
    if (start < end) {
      //partition the array
      int pivotIndex = partition(data, start, end);
      //call self on both sides of partition
      quicksort(data, start, pivotIndex - 1);
      quicksort(data, pivotIndex + 1, end);
    }
    //sinon, il n'y a rien à faire.
  }

  public static void quicksort(int[] data) {
    quicksort(data, 0, data.length - 1);
  }

  public static int partitionDutch(int[] array, int start, int end) {
    int pivotIndex = start;
    int leftPointer = start + 1;
    int rightPointer = end;

    int equalCounter = 0;

    while (leftPointer != rightPointer) { //stops when leftPointer == rightPointer
      if (array[leftPointer] < array[pivotIndex]) { //
        //no action needed. advance left pointer.
        leftPointer++;
      }
      if (array[leftPointer] > array[pivotIndex]) {
        //deport number to right side
        //that means swap the terms
        int swapped = array[rightPointer];
        array[rightPointer] = array[leftPointer];
        array[leftPointer] = swapped;
        rightPointer--;
      }
    }

    //at this point, leftPointer == rightPointer and is where pivot should be
    //put pivot in its place
    if (array[leftPointer] < array[pivotIndex]) {
      //if center value is less than the pivot, we can swap center with pivot
      int swapped = array[leftPointer];
      array[leftPointer] = array[pivotIndex];
      array[pivotIndex] = swapped;
      pivotIndex = leftPointer;
    } else {
      //else put the pivot before the center
      int swapped = array[leftPointer - 1];
      array[leftPointer - 1] = array[pivotIndex];
      array[pivotIndex] = swapped;
      pivotIndex = leftPointer - 1;
    }

    //loop through first half of array and
    int i; //declaring loop variable
    i = start + 1; //don't worry about start because that was the pivot
    leftPointer--; //decrement leftPointer because that is where we're sending values
    while (i < leftPointer) {
      if (array[i] == array[pivotIndex]) { //if value matches that of pivot
        //swap the two values
        int swapped = array[i];
        array[i] = array[leftPointer];
        array[leftPointer] = swapped;
        leftPointer--; //decrement leftPointer
        i++; //increment i
      }
    }

    //do the same for the other side
    i = end;
    rightPointer++;
    while (i > rightPointer) {
      if (array[i] == array[pivotIndex]) { //if value matches that of pivot
        //swap the two values
        int swapped = array[i];
        array[i] = array[leftPointer];
        array[leftPointer] = swapped;
        rightPointer++;
        i--;
      }
    }

    return pivotIndex;
  }

  /***** UNIMPORTANT METHODS *****/

  public static void main0(String[] args) {
    try {
      int[] array = randomArray((int)1e6);

      if (array.length < 100) {System.out.println(Arrays.toString(array));}
      else {System.out.println("For array of size " + array.length);}
      quicksort(array);

      if (array.length < 100) {System.out.println(Arrays.toString(array));}
      else {System.out.println("Got array of size " + array.length);}
      if (! checkSorted(array)) {System.out.println("WARNING: NOT SORTED! (wait then wtf did your sort do...)");}
    } catch (StackOverflowError E) {
      System.out.println("OH NOES! YOUR STACK WENT BOOM!");
    } catch (Exception E) {
      E.printStackTrace();
    }
  }

  public static int[] copyArray(int[] a) {
    int[] b = new int[a.length];
    for (int k = 0; k < a.length; k++) {
      b[k] = a[k];
    }
    return b;
  }

  public static boolean checkSorted(int[] array) {
    int val = array[0];
    for (int x : array) {
      if (x < val) {
        return false;
      }
      val = x;
    }
    return true;
  }

  public static int[] randomArray(int length) {
    int[] array = new int[length];

    for (int i = 0; i < length; i++) {
      array[i] = randInt(-9999, 9999);
    }

    return array;
  }

  public static int randInt(int lower, int upper) {
    return (int)(Math.random() * (upper - lower) + lower);
  }
}
