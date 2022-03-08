import java.io.*;

public class Bronze {
  public static void main(String[] args) throws Exception {
    Scanner Pumpkin = new Scanner(System.in);

    final int R = Pumpkin.nextInt();
    final int C = Pumpkin.nextInt();
    final int E = Pumpkin.nextInt();
    final int N = Pumpkin.nextInt();

    int[][] map = new int[R][C];

    //read into int[][]
    for (int i = 0; i < R; i++) {
      for (int k = 0; k < C; k++) {
        map[i][k] = Pumpkin.nextInt();
      }
    }
  }
}
