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

  public static void main(String[] args) {
    int[][] testcases = {
      //{},
      {1},
      {3, 5389, 382, 42, 6, 2456, 532, 25},
      new int[(int)1e8],
      randArray(400, 10),
      randArray((int)1e8, 1000),
      generateSorted((int)1e6),
      generateSorted((int)1e8),
      generateReverseSorted((int)1e8),
      generateReverseSorted((int)1e8)
    };

    for (int[] x : testcases) {
      checksortedverbose(x);
    }

    System.out.println("entering infinite loop of sorting hell:");

    int count = 0;
    while (true) {
      System.out.println(count);
      checksortedverbose(randArray((int)Math.pow(10, randInt(1, 6)), 69));
      count++;
    }
  }

  //lower and upper inclusive
  public static int randInt(int lower, int upper) {
    return (int)(Math.random() * (upper - lower + 1) + lower);
  }

  public static int[] randArray(int length, int randomness) {
    int[] array = new int[length];
    for (int i = 0; i < array.length; i++) {
      array[i] = randInt(0, randomness);
    }
    return array;
  }

  public static void checksortedverbose(int[] data) {
    System.out.println("given array of length " + data.length);
    long time = System.currentTimeMillis();
    int[] copy = copyArray(data);
    Arrays.sort(copy);
    System.out.println("java sorted in " + (System.currentTimeMillis() - time) + " ms");

    time = System.currentTimeMillis();
    int index = randInt(0, data.length - 1);
    int value = quickselect(data, index);
    System.out.println("your sort finished in " + (System.currentTimeMillis() - time) + " ms");

    time = System.currentTimeMillis();
    boolean sorted = value == copy[index];

    System.out.println(sorted ? "verified in " + (System.currentTimeMillis() - time) + " ms" : "NOT SORTED");
    System.out.println();

    if (! sorted) {System.exit(418);}
  }

  public static int[] copyArray(int[] array) {
    int[] copy = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      copy[i] = array[i];
    }
    return copy;
  }

  public static int[] generateSorted(int length) {
    int[] array = new int[length];
    for (int i = 0; i < length; i++) {
      array[i] = i;
    }
    return array;
  }

  public static int[] generateReverseSorted(int length) {
    int[] array = new int[length];
    for (int i = 0; i < length; i++) {
      array[i] = length - i;
    }
    return array;
  }
}
