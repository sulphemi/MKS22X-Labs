import java.util.*;
import java.io.*;

public class Silver {
  public static void main(String[] args) throws Exception {
    String input = args.length == 0 ? "ctravel.in" : args[0]; //default to ctravel.in
    Scanner Pudding = new Scanner(new File(input)); //Pudding, the trusty file parser!
    int[][] map; //2d array representing the map
    //map details:
    //-1 means upsteppable
    //int >= 0 means steppable

    final int N = Pudding.nextInt(); //rows
    final int M = Pudding.nextInt(); //columns
    final int T = Pudding.nextInt(); //seconds

    //scan into map
    Pudding.nextLine(); //extract newLine
    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      String line = Pudding.nextLine();
      for (int k = 0; k < M; k++) {
        map[i][k] = line.charAt(k) == '*' ? -1 : 0;
      }
    }

    System.out.println(Arrays.deepToString(map));
  }

  public static advance(int[][] array) {
    int[][] newMap;
    for (int i = 0; i < array.length; i++) {
      for (int k = 0; i < array[i].length; k++) {
        if (array[i][k] == -1) { //if it is a tree
          newMap[i][k] = -1; //transfer tree over
        } else {
          int sum = 0;
        }
      }
    }
  }
}
