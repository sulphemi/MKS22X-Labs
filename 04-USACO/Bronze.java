import java.util.*;
import java.io.*;

public class Bronze {
  public static int solve(String filename) {
    Scanner Pumpkin;
    try {
      Pumpkin = new Scanner(new File(filename));
    } catch (FileNotFoundException E) {
      return -1;
    }

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
      int targetRow = Pumpkin.nextInt() - 1;
      int targetCol = Pumpkin.nextInt() - 1;
      int times = Pumpkin.nextInt();

      while (times > 0) {
        stomp(map, targetRow, targetCol);
        times--;
      }
    }

    int sum = depthMask(map, E);
    return (sum * 5184); //1 sqyd = 5184sqin
  }

  public static void stomp(int[][] map, int row, int col) {
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
          map[i][k]--; //lower
        }
      }
    }
  }

  public static int depthMask(int[][] map, int depth) { //returns the aggregated depth
    int sum = 0;
    for (int[] row : map) {
      for (int i = 0; i < row.length; i++) {
        if (row[i] < depth) {
          row[i] = depth - row[i];
        } else {
          row[i] = 0;
        }
        sum += row[i];
      }
    }
    return sum;
  }
}
