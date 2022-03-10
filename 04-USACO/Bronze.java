import java.util.*;
import java.io.*;

public class Bronze {
  public static void main(String[] args) throws Exception {
    Scanner Pumpkin = new Scanner(new File("makelake.in"));

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

    ////
    for (int[] row : map) {
      for (int i = 0; i < row.length; i++) {
        System.out.print(row[i] + " ");
      }
      System.out.println();
    }
    ////

    for (int i = 0; i < N; i++) {
      int targetRow = Pumpkin.nextInt() - 1;
      int targetCol = Pumpkin.nextInt() - 1;
      int strength = Pumpkin.nextInt();

      stomp(map, targetRow, targetCol, strength);
    }

    //depthMask(map, E);
  }

  public static void stomp(int[][] map, int row, int col, int depth) {
    //find highest elevation by iterating through
    int highest = map[row][col];
    for (int i = row; i < row + 2; i++) {
      for (int k = col; k < col + 2; k++) {
        if (map[i][k] > highest) {
          highest = map[i][k];
        }
      }
    }

    System.out.println("highest: " + highest);

    //iterate through
    for (int i = row; i < row + 2; i++) {
      for (int k = col; k < col + 2; k++) {
        if (map[i][k] == highest) {
          map[i][k] -= depth; //lower
        }
      }
    }

    System.out.println();
    for (int[] r : map) {
      for (int i = 0; i < r.length; i++) {
        System.out.print(r[i] + " ");
      }
      System.out.println();
    }
  }

  public static void depthMask(int[][] map, int depth) {
    for (int[] row : map) {
      for (int i = 0; i < row.length; i++) {
        if (row[i] < depth) {
          row[i] = depth - row[i];
        } else {
          row[i] = 0;
        }
      }
    }
  }
}
