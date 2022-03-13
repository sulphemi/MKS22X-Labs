import java.util.*;
import java.io.*;

public class Silver {
  public static long solve(String filename) {
    try {
      Scanner Pudding = new Scanner(new File(filename)); //Pudding, the trusty file parser!
    } catch (FileNotFoundException E) {
      return -1;
    }
    long[][] map; //2d array representing the map
    //map details:
    //-1 means upsteppable
    //int >= 0 means steppable

    final int N = Pudding.nextInt(); //rows
    final int M = Pudding.nextInt(); //columns
    final int T = Pudding.nextInt(); //seconds

    //scan into map
    Pudding.nextLine(); //extract newLine
    map = new long[N][M];
    for (int i = 0; i < N; i++) {
      String line = Pudding.nextLine();
      for (int k = 0; k < M; k++) {
        map[i][k] = line.charAt(k) == '*' ? -1 : 0;
      }
    }

    final int R1 = Pudding.nextInt();
    final int C1 = Pudding.nextInt();

    map[R1][C1] = 1; //the position of the elephant
    for (int i = 0; i < T; i++) {
      map = advance(map);
    }

    final int R2 = Pudding.nextInt();
    final int C2 = Pudding.nextInt();
    return map[R1][C1];
  }

  public static long[][] advance(long[][] array) {
    long[][] newMap = new long[array.length][array[0].length];
    for (int i = 0; i < array.length; i++) {
      for (int k = 0; k < array[i].length; k++) {
        if (array[i][k] == -1) { //if it is a tree
          newMap[i][k] = -1; //transfer tree over
        } else { //if not a tree, transfer the number of ways to step into the square
          long sum = 0;
          if (valid(array, i + 1, k)) {sum += array[i + 1][k];}
          if (valid(array, i, k + 1)) {sum += array[i][k + 1];}
          if (valid(array, i - 1, k)) {sum += array[i - 1][k];}
          if (valid(array, i, k - 1)) {sum += array[i][k - 1];}
          newMap[i][k] = sum;
        }
      }
    }
    return newMap;
  }

  public static boolean valid(long[][] array, int row, int col) {
    //pray that short circuiting wont fail me
    return (row >= 0 && col >= 0 && row < array.length && col < array[row].length) && (array[row][col] != -1);
  }

  public static void print2DArray(long[][] array) {
    for (long[] row : array) {
      System.out.println(Arrays.toString(row));
      System.out.println('\n');
    }
    System.out.println('\n');
  }
}
