import java.util.Arrays;

public class Merge {
  //takes two arrays and returns them, merged
  public static int[] merge(int[] left, int[] right) {
    int lefty = 0;
    int righty = 0;

    while (lefty < left.length || righty < right.length) {
      int[] bigboi = new int[left.length + right.length]; //merged array is length of both combined

      if (righty > right.length || left[lefty] < right[righty]) {
        //copy over from left
        bigboi[lefty + righty] = left[lefty];
        lefty++;
        continue;
      }
      if (lefty > left.length || ) {
        //copy over from right
        bigboi[lefty + righty] = right[righty];
        righty++;
      }


    }
  }

  public static void merge(int[] destination, int[] left, int[] right) {

  }

  public static void main(String[] args) {

  }
}
