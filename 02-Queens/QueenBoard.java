import java.util.ArrayList;

public class QueenBoard {
  /***** FIELDS *****/
  int[][] board; //2d array representing board
  int queensAdded; //keeps track of how many queens were added
  boolean animated; //whether to bore user with animation
  int delay; //the wait time between animation frames (in ms)

  /***** CONSTRUCTORS *****/
  public QueenBoard(int n) { //initializes the board for size n
    board = new int[n][n];
    queensAdded = 0;
  }

  public QueenBoard(int[][] array, int existingQueens) { //note that board will now be linked to array
    board = array;
    queensAdded = existingQueens;
  }

  /***** PRIVATE METHODS *****/
  private void modifySquare(int row, int col, int increment) {
    //1 = add queen, -1 = remove
    //set queen square
    board[row][col] += increment;
    //downwards
    for (int i = row + 1; i < board.length; i++) {
      board[i][col] -= increment;
    }

    int i, k;
    //right diagonal
    i = row + 1;
    k = col + 1;
    while (i < board.length && k < board.length) {
      board[i][k] -= increment;
      i++;
      k++;
    }

    //left diagonal
    i = row + 1;
    k = col - 1;
    while (i < board.length && k >= 0) {
      board[i][k] -= increment;
      i++;
      k--;
    }
  }

  private boolean addQueen(int row, int col) {
    if (board[row][col] == 0) {
      modifySquare(row, col, 1);
      queensAdded++;
      return true; //success
    } else {
      return false; //fail
    }
  }

  private boolean removeQueen(int row, int col) {
    if (board[row][col] == 1) {
      modifySquare(row, col, -1);
      queensAdded--;
      return true;
    } else {
      return false;
    }
  }

  private static String padLeft(String str, int target, String pad) {
    while (str.length() < target) {
      str = pad + str;
    }
    return str;
  }
  /***** PUBLIC METHODS *****/
  public String toStringDebug() { //prints board literally
    String output = "";

    for (int i = 0; i < board.length; i++) {
      for (int k = 0; k < board.length; k++) {
        output += padLeft("" + board[i][k], 3, " ");
        output += ' ';
      }
      if (i != board.length - 1) {
        output += '\n'; //newline after row
      }
    }

    return output;
  }

  public String toString() {
    String output = "";

    for (int i = 0; i < board.length; i++) {
      for (int k = 0; k < board.length; k++) {
        output += board[i][k] == 1 ? 'Q' : '_';
        if (i != board.length) {
          output += ' ';
        }
      }
      if (i != board.length - 1) {
        output += '\n';
      }
    }

    return output;
  }

  public void setAnimate(boolean newValue) {
    animated = newValue;
  }

  public void setDelay(int newDelay) {
    delay = newDelay;
  }

  public static boolean oldSolve(QueenBoard QB, int row) {
    if (row == QB.board.length) {
      return true;
    } else {
      for (int i = 0; i < QB.board.length; i++) {
        QueenBoard child = QB.deepCopy();
        if (child.addQueen(row, i)) {
          System.out.println(child);
          if (oldSolve(child, row + 1)) {
            return true;
          }
        }
      }

      return false;
    }
  }

  public boolean solve(int row) {
    if (row == board.length) {
      return true;
    } else {
      for (int i = 0; i < board.length; i++) {
        if (addQueen(row, i)) {
          if (solve(row + 1)) {
            return true;
          } else {
            removeQueen(row, i);
          }
        }
      }

      return false;
    }
  }

  public int countSolutions(int row) {
    int count = 0;

    if (row == board.length) {
      return 1;
    } else {
      for (int i = 0; i < board.length; i++) {
        if (addQueen(row, i)) {
          System.out.println(this);
          System.out.println();
            count += countSolutions(row + 1);
          } else {
            removeQueen(row, i);
          }
        }
      }

      return count;
    }

  public static boolean listSolutions(ArrayList<QueenBoard> list, QueenBoard QB, int row) {
    if (row == QB.board.length) {
      list.add(QB);
      return true;
    } else {
      for (int i = 0; i < QB.board.length; i++) {
        QueenBoard child = QB.deepCopy();
        if (child.addQueen(row, i)) {
          listSolutions(list, child, row + 1);
        }
      }

      return false;
    }
  }

  public QueenBoard deepCopy() {
    //copy board to fresh int[][]
    int[][] newBoard = new int[board.length][board.length];

    for (int i = 0; i < board.length; i++) {
      for (int k = 0; k < board.length; k++) {
        newBoard[i][k] = board[i][k];
      }
    }

    return new QueenBoard(newBoard, queensAdded);
  }

  /***** MAIN *****/
  public static void main(String[] args) {
    QueenBoard qb = new QueenBoard(8);
    System.out.println(qb.countSolutions(0));
  }
}
