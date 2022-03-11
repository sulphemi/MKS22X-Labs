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
      String oowoo = Pudding.readLine();
      for (int k = 0; k < M; k++) {
        map[i][k] = oowoo.charAt() == '*' ? -1 : 0;
      }
    }

    System.out.println(Arrays.deepToString(map));
  }
}
