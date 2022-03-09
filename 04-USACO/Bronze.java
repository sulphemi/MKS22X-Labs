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

  public static void stomp(int[][] map, int row, int col, int depth) {
    //find highest elevation by iterating through
    int highest = map[row][col];
    for (int i = row; i < row + 3; i++) {
      for (int k = col; k < col + 3; k++) {
        if (map[i][k] > highest) {
          highest = map[i][k];
        }
      }
    }

    //iterate through
    for (int i = row; i < row + 3; i++) {
      for (int k = col; k < col + 3; k++) {
        if (map[i][k] == highest) {
          map[i][k] -= depth;//lower
        }
      }
    }
  }
}
