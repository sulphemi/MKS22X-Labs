import java.util.*;

public class Quick {
  public static int partition(int[] array, int start, int end) {
    int pivotIndex = randInt(start, end);
    swap(array, pivotIndex, start);
    pivotIndex = start;
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
          swap(array, leftPointer, rightPointer);
          rightPointer--;
        }
        bonk = !bonk; //flip boolean
      } else if (array[leftPointer] < array[pivotIndex]) { //
        //no action needed. advance left pointer.
        leftPointer++;
      } else {
        //deport number to right side
        //that means swap the terms
        swap(array, leftPointer, rightPointer);
        rightPointer--;
      }
    }

    //leftPointer now == rightPointer
    //put pivot in its place
    //that means swap pivot with index before center OR index after center
    //depending on which represents which half
    if (array[leftPointer] < array[pivotIndex]) {
      //if center value is less than the pivot, we can swap center with pivot
      swap(array, leftPointer, pivotIndex);
      pivotIndex = leftPointer;
    } else {
      //else put the pivot before the center
      swap(array, leftPointer - 1, pivotIndex);
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

  public static void quicksort(int[] data) {
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
    int pivotIndex = randInt(start, end);
    swap(array, start, pivotIndex);
    pivotIndex = start;
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
        swap(array, leftPointer, rightPointer);
        rightPointer--; //decrement right
      }
    }

    //put pivot in its place
    //that means swap pivot with index before center OR index after center
    //depending on which represents which half
    if (array[leftPointer] < array[pivotIndex]) {
      //if center value is less than the pivot, we can swap center with pivot
      swap(array, pivotIndex, leftPointer);
      pivotIndex = leftPointer;
    } else {
      //else put the pivot before the center
      swap(array, leftPointer - 1, pivotIndex);
      pivotIndex = leftPointer - 1;
    }

    int pivotEnd = pivotIndex; //keeps track of where pivot ends
    leftPointer = start; //move leftPointer back to start
    while (leftPointer != pivotIndex) { //loop through left portion
      if (array[leftPointer] == array[pivotIndex]) {
        //swap with term right before pivot
        swap(array, leftPointer, pivotIndex - 1);
        pivotIndex--;
      } else {
        leftPointer++;
      }
    }

    int[] pivotBounds = {pivotIndex, pivotEnd};
    return pivotBounds;
  }

  public static void swap(int[] array, int source, int target) {
    int swapped = array[target];
    array[target] = array[source];
    array[source] = swapped;
  }

  //lower and upper inclusive
  public static int randInt(int lower, int upper) {
    return (int)(Math.random() * (upper - lower + 1) + lower);
  }
}
