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

  public static void quicksortDutch(int[] data, int start, int end) {
    if (start < end) {
      int[] pivotBounds = partitionDutch(data, start, end);
      int pivotStart = pivotBounds[0];
      int pivotEnd = pivotBounds[1];

      //call self on both sides
      quicksortDutch(data, start, pivotStart - 1);
      quicksortDutch(data, pivotEnd + 1, end);
    }
  }

  public static void quicksortDutch(int[] data) {
    quicksortDutch(data, 0, data.length - 1);
  }

  //algorithm:
  //until left and right converge:
  //  if @left < pivot: in right place, advance left
  //  if @left = pivot: ignore for now, advance left
  //  if @left > pivot: swap @left and @right
  //then loop through left portion and swap with pivot portion if needed
  //returns an ordered pair of values representing the bounds of the pivot region
  public static int[] partitionDutch(int[] array, int start, int end) {
    int pivotIndex = start; //let start be pivot
    int leftPointer = start + 1;
    int rightPointer = end;

    while (leftPointer != rightPointer) { //until these two converge:
      if (array[leftPointer] == array[pivotIndex]) {
        leftPointer++; //ignore for now
        continue;
      }
      if (array[leftPointer] < array[pivotIndex]) {
        leftPointer++; //in correct place
        continue;
      }
      if (array[leftPointer] > array[pivotIndex]) {
        //swap left and right
        int swapped = array[leftPointer];
        array[leftPointer] = array[rightPointer];
        array[rightPointer] = swapped;
        rightPointer--; //decrement right
      }
    }

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

    int pivotEnd = pivotIndex; //keeps track of where pivot ends
    leftPointer = start; //move leftPointer back to start
    while (leftPointer != pivotIndex) { //loop through left portion
      if (array[leftPointer] == array[pivotIndex]) {
        //swap with term right before pivot
        int swapped = array[pivotIndex - 1];
        array[pivotIndex - 1] = array[leftPointer];
        array[leftPointer] = swapped;
        pivotIndex--;
      } else {
        leftPointer++;
      }
    }

    int[] pivotBounds = {pivotIndex, pivotEnd};
    return pivotBounds;
  }
}
