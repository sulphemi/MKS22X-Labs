import java.util.ArrayList;

public class QueenBoard {
  /***** FIELDS *****/
  private int[][] board; //2d array representing board
  private int queensAdded; //keeps track of how many queens were added
  private boolean animated; //whether to bore user with animation
  private int delay; //the wait time between animation frames (in ms)

  /***** CONSTRUCTORS *****/
  public QueenBoard(int n) { //initializes the board for size n
    board = new int[n][n];
    queensAdded = 0;
    animated = false;
    delay = 0;
  }

  private QueenBoard(int[][] array, int existingQueens) { //note that board will now be linked to array
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

  /***** UTILITIES *****/
  private void display(int row) { //row refers to the row of the latest queen placed
    //essentially prints a readable toDebugString
    Text.wait(delay);
    Text.clear();
    String output = "";

    for (int i = 0; i < board.length; i++) {
      for (int k = 0; k < board.length; k++) {
        if (board[i][k] == 0 && i > row) { //square is blank AND there is no queen on this row
          output += '_';
        } else if (board[i][k] > 0) { //square is a queen
          output += 'Q';
        } else { //square is invalid (represent with an X)
          output += 'X';
        }
        if (i != board.length) {
          output += ' ';
        }
      }
      if (i != board.length - 1) {
        output += '\n';
      }
    }

    System.out.println(output);
    System.out.println("Queen count: " + (row + 1));
  }

  private static String padLeft(String str, int target, String pad) {
    while (str.length() < target) {
      str = pad + str;
    }
    return str;
  }

  private QueenBoard deepCopy() {
    //copy board to fresh int[][]
    int[][] newBoard = new int[board.length][board.length];

    for (int i = 0; i < board.length; i++) {
      for (int k = 0; k < board.length; k++) {
        newBoard[i][k] = board[i][k];
      }
    }

    return new QueenBoard(newBoard, queensAdded);
  }

  private static int parse(String str) {
    return Integer.parseInt(str);
  }

  /***** DEPRECIATED *****/
  private static boolean oldSolve(QueenBoard QB, int row) {
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

  private static boolean listSolutions(ArrayList<QueenBoard> list, QueenBoard QB, int row) {
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
      for (int k = 0; k < board.length; k++) { //loop through 2D array
        output += board[i][k] == 1 ? 'Q' : '_';
        if (i != board.length) {
          output += ' '; //space between
        }
      }
      if (i != board.length - 1) {
        output += '\n'; //newline after each row
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

  /***** SOLVE METHODS *****/
  private boolean solve(int row) {
    if (row == board.length) { //means we reached end and found solution
      return true;
    } else {
      for (int i = 0; i < board.length; i++) { //loop through next row
        if (addQueen(row, i)) { //check if queen can be placed
          if (solve(row + 1)) { //call on next row
            return true;
          } else {
            removeQueen(row, i); //remove queen and move to next branch
          }
        }
      }

      return false; //means we didnt find solution
    }
  }

  private boolean solveWithAnimation(int row) {
    if (row == board.length) {
      return true;
    } else {
      for (int i = 0; i < board.length; i++) {
        if (addQueen(row, i)) {
          display(row); //display once after changing the board
          if (solveWithAnimation(row + 1)) {
            return true;
          } else {
            removeQueen(row, i);
          }
        }
      }

      return false;
    }
  }

  private int countSolutions(int row) {
    int count = 0;

    if (row == board.length) {
      return 1;
    } else {
      for (int i = 0; i < board.length; i++) {
        if (addQueen(row, i)) {
            count += countSolutions(row + 1);
            removeQueen(row, i);
          }
        }
      }

      return count;
    }

  /***** WRAPPERS *****/
  public boolean solve() {
    //throw an exception if board is not empty
    if (queensAdded != 0) {
      throw new IllegalStateException("The board is not empty! I'm not Magnus Carlsen, I don't know how to solve a non-empty board!");
    }

    if (animated) {
      return solveWithAnimation(0);
    } else {
      return solve(0);
    }
    //Mr.K will yell at me if i do this but alternate one-liner:
    //return animated ? solveWithAnimation(0) : solve(0);
  }

  public int countSolutions() {
    if (queensAdded != 0) {
      throw new IllegalStateException("Start with an empty board! Also I like your hat.");
    }

    return countSolutions(0);
  }

  /***** MAIN *****/
  public static void main(String[] args) { //usage: java QueenBoard *size* (delay)
    try {
      int size;
      if (args.length > 0) {
        size = parse(args[0]);
      } else {
        size = 8;
      }

      QueenBoard QB = new QueenBoard(size);

      if (args.length > 1) {
        int delay = parse(args[1]);
        QB.setAnimate(true);
        QB.setDelay(delay);
      }

      System.out.println(QB.solve());
    } catch (NumberFormatException NFE) {
      System.out.println("Il y a un problème avec votre parametres.");
    }
  }
}
