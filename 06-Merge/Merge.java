import java.util.Arrays;

public class Merge {
  //takes two arrays and returns them, merged
  public static int[] merge(int[] left, int[] right) {
    int leftie = 0;
    int righty = 0;
    int[] tray = new int[left.length + right.length]; //merged array is length of both combined

    //on each iteration we will always be adding one term to this array,
    //so a for loop such as this will suffice
    for (int i = 0; i < tray.length; i++) {
      if (leftie < left.length && righty < right.length) {
        //pick the larger one
        if (left[leftie] < right[righty]) {
          tray[i] = left[leftie];
          leftie++;
        } else {
          tray[i] = right[righty];
          righty++;
        }
      } else {
        //add from the one that still has terms
        if (leftie < left.length) {
          tray[i] = left[leftie];
          leftie++;
        } else {
          tray[i] = right[righty];
          righty++;
        }
      }
    }

    return tray;
  }

  public static void merge(int[] destination, int[] left, int[] right) {

  }

  public static int[] dumbMerge(int[] left, int[] right) {
    int[] blob = new int[left.length + right.length];

    int i = 0;
    while (i < left.length) {
      blob[i] = left[i];
      i++;
    }
    while (i < blob.length) {
      blob[i] = right[i - left.length];
      i++;
    }
    Arrays.sort(blob);
    return blob;
  }

  public static int[] mergesortH(int[] data) {
    System.out.println(Arrays.toString(data));
    if (data.length == 1) {
      //base case: array is of length 1 and therefore sorted
      return data; //already sorted
    } else {
      //array is greater than length 1
      //split array in half
      int splitIndex = data.length / 2;
      System.out.println(splitIndex);
      int[] left = copyArray(data, 0, splitIndex - 1);
      int[] right = copyArray(data, splitIndex, data.length - 1);

      left = mergesortH(left);
      right = mergesortH(right);

      return merge(left, right);
    }
  }

  public static void main(String[] args) {
    int[] a = {0, 69, 429, 69420};
    int[] b = {1, 39, 40, 42, 43, 45, 999};

    System.out.println(Arrays.toString(merge(a, b)));
    System.out.println(Arrays.equals(merge(a, b), dumbMerge(a, b)));
  }

  public static int randInt(int lower, int upper) {
    return (int)(Math.random() * (upper - lower + 1) + lower);
  }

  public static int[] randArray(int size) {
    int[] array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = randInt(0, 10);
    }
    return array;
  }

  //start inclusive, end inclusive
  public static int[] copyArray(int[] array, int start, int end) {
    if (start > end) {
      throw new IllegalArgumentException("start cannot be greater than end!");
    } else {
      int[] copy = new int[end - start + 1];
      int i = 0;
      while (i < copy.length) {
        copy[i] = array[start];
        i++;
        start++;
      }
      return copy;
    }
  }

  public static void main0(String[] args) {
    int[] a = {4, 5, 3, 2, 1, 2};

    System.out.println(Arrays.toString(mergesortH(a)));
  }
}
