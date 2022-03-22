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
      }
      if (leftie == left.length || left[leftie] > right[righty]) {
        tray[leftie + righty] = right[righty];
        righty++;
        continue;
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
    int[] a = {1, 2, 3, 4, 5, 6};
    int[] b = {1, 2, 3};

    System.out.println(Arrays.toString(merge(a, b)));
    System.out.println(Arrays.toString(dumbMerge(a, b)));
  }
}
