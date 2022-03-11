import java.util.*;
import java.io.*;

public class Silver {
  public static void main(String[] args) throws Exception {
    String input = args.length == 0 ? "ctravel.in" : args[0]; //default to ctravel.in
    Scanner Pudding = new Scanner(new File(input)); //Pudding, the trusty file parser!
    char[][] map; //2d array representing the map
    //map details:
    //-1 means upsteppable
    //int >= 0 means steppable

    final int N = Pudding.nextInt(); //rows
    final int M = Pudding.nextInt(); //columns
    final int T = Pudding.nextInt(); //seconds

    //scan into map
    Pudding.nextLine(); //extract newLine
    map = new char[N][M];
    for (int i = 0; i < N; i++) {
      String line = Pudding.nextLine();
      map[i] = line.toCharArray();
    }

    System.out.println(Arrays.deepToString(map));
  }
}
