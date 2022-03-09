import java.io.*;

public class Bronze {
  public static void main(String[] args) throws Exception {
    Scanner Pumpkin = new Scanner(System.in);

    final int R = Pumpkin.nextInt(); //rows
    final int C = Pumpkin.nextInt(); //cols
    final int E = Pumpkin.nextInt(); //elevation
    final int N = Pumpkin.nextInt(); //instructions

    int[][] map = new int[R][C];

    //read into int[][]
    for (int i = 0; i < R; i++) {
      for (int k = 0; k < C; k++) {
        map[i][k] = Pumpkin.nextInt();
      }
    }

    for (int i = 0; i < N; i++) {
      int targetRow = Pumpkin.nextInt();
      int targetCol = Pumpkin.nextInt();
      int strength = Pumpkin.nextInt();

      stomp(map, targetRow, targetCol, strength);
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
