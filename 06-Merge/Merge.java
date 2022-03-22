import java.util.Arrays;

public class Merge {
  //takes two arrays and returns them, merged
  public static int[] merge(int[] left, int[] right) {
    int leftie = 0;
    int righty = 0;
    int[] tray = new int[left.length + right.length]; //merged array is length of both combined

    while (leftie + righty < tray.length) {
      if (righty == right.length || left[leftie] <= right[righty]) {
        tray[leftie + righty] = left[leftie];
        leftie++;
        continue;
      } else if (leftie == left.length || left[leftie] > right[righty]) {
        tray[leftie + righty] = right[righty];
        righty++;
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

  public static void main(String[] args) {
    int[] a = {0, 0, 0, 3, 4, 5, 6, 7, 10, 999, 69420};
    int[] b = {1, 2, 3, 727, 727};

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
}
