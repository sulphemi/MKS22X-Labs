import java.util.*;

public class Preliminary {
  public static void main(String[] args) {
    int[][] testcases = {
      {9, 8, 7, 6, 5},
      {999, 4, 3, 2, 1, 999},
      {1, 2, 3, 4},
      {5, 1, 2, 3, 4, 6, 7, 7}
    };

    for (int[] a : testcases) {
      System.out.println(Arrays.toString(a));
      pivot(a);
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

  public static void pivot(int[] array) {
    int pivotIndex = 0;
    int leftPointer = 1;
    int rightPointer = array.length - 1;

    while (leftPointer != rightPointer) { //stops when leftPointer == rightPointer
      if (array[leftPointer] <= array[pivotIndex]) { //NTS: should i include <= or <?
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

      System.out.println("leftPointer = " + leftPointer);
      System.out.println("rightPointer = " + rightPointer);
      System.out.println(Arrays.toString(array));
      System.out.println();
    }

    //leftPointer now == rightPointer
    //put pivot in its place
    //that means swap pivot with index before center OR index after center
    //depending on which represents which half
    if (array[leftPointer] < array[pivotIndex]) {
      //if center value is less than the pivot, we can swap
      int swapped = array[leftPointer];
      array[leftPointer] = array[pivotIndex];
      array[pivotIndex] = swapped;
    } else {
      //else put the pivot before the center
      int swapped = array[leftPointer - 1];
      array[leftPointer - 1] = array[pivotIndex];
      array[pivotIndex] = swapped;
    }
  }
}
