import java.util.*;

public class Quick {
  public static void OldMain(String[] args) {
    int[][] testcases = {
      {9, 8, 7, 6, 5},
      {999, 4, 3, 2, 1, 999},
      {1, 2, 3, 4},
      {5, 1, 2, 3, 4, 6, 7, 7}
    };

    for (int[] a : testcases) {
      System.out.println(Arrays.toString(a));
      partition(a, 0, a.length - 1);
      System.out.println(Arrays.toString(a));
      System.out.println();
    }
  }

  public static int[] randomArray(int length) {
    int[] array = new int[length];
    //Random Silver = new Random();

    for (int i = 0; i < length; i++) {
      array[i] = randInt(-10, 20);
    }

    return array;
  }

  public static int randInt(int lower, int upper) {
    return (int)(Math.random() * (upper - lower) + lower);
  }

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

  public static int quickselect(int[] data, int index, int partitionLowerBound, int partitionUpperBound) {
    int pivotIndex = partition(data, partitionLowerBound, partitionUpperBound);
    if (pivotIndex == index) {
      return data[index];
    } else if (pivotIndex > index) {
      return quickselect(data, index, pivotIndex, partitionUpperBound); //search in right half of array
    } else {
      return quickselect(data, index, partitionLowerBound, pivotIndex); //search in left half of array
    }
  }

  public static int quickselect(int[] data, int index) {
    return quickselect(data, index, 0, data.length - 1);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      int[] array = randomArray(10);

      int[] copy = new int[array.length];
      for (int k = 0; k < copy.length; k++) {
        copy[k] = array[k];
      }

      Arrays.sort(copy);
      System.out.println(Arrays.toString(copy));
      System.out.println("Index 4: " + quickselect(array, 4));
    }
  }
}
