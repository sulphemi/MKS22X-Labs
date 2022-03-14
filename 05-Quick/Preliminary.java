import java.util.*;

public class Preliminary {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(randomArray(10)));
  }

  public static int[] randomArray(int length) {
    int[] array = new int[length];
    Random Silver = new Random();

    for (int i = 0; i < length; i++) {
      array[i] = Random.nextInt();
    }

    return array;
  }
}
