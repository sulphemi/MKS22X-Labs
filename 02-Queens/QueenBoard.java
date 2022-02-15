public class QueenBoard {
  /***** FIELDS *****/
  int[][] board; //2d array representing board
  int queensAdded; //keeps track of how many queens were added

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
  private boolean addQueen(int row, int col) {
    //check if space is valid
    if (board[row][col] == 0) {
      //set square as queen
      board[row][col] = 1;
      //downwards
      for (int i = row + 1; i < board.length; i++) {
        board[i][col] = -1;
      }

      int i, k;
      //right diagonal
      i = row + 1;
      k = col + 1;
      while (i < board.length && k < board.length) {
        board[i][k] = -1;
        i++;
        k++;
      }

      //left diagonal
      i = row + 1;
      k = col - 1;
      while (i < board.length && k >= 0) {
        board[i][k] = -1;
        i++;
        k--;
      }

      queensAdded++;
      return true; //success
    } else {
      return false; //fail
    }
  }
  // private boolean removeQueen() {}

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
        output += padLeft("" + board[i][k], 2, " ");
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
        output += ' ';
      }
      if (i != board.length - 1) {
        output += '\n';
      }
    }

    return output;
  }
  //
  // public boolean solve() {} //wrapper method
  /*
  public static QueenBoard solve(QueenBoard QB, int row, int column) { //solver method
    //base case
    if (row == QB.board.length) { //means we reached the end
      System.out.println("reached end");
      return QB;
    } else {
      //recurive case
      //try to add a queen at given location
      if (QB.addQueen(row, column)) {
        //adding succeeded
        for (int i = 0; i < QB.board.length; i++) { //iterate through the columns
          System.out.println("iterating for i = " + i);
          System.out.println(QB);
          return QB.compare(solve(QB.deepCopy(), row + 1, i));
        }
        return QB;
      } else { //adding was impossible
        System.out.println("add failed");
        return QB; //end tree early
      }
    }
  } */

  public static boolean solve(QueenBoard QB, int row) {
    if (row == QB.board.length) {
      return true;
    } else {
      for (int i = 0; i < QB.board.length; i++) {
        QueenBoard child = QB.deepCopy();
        if (child.addQueen(row, i)) {
          System.out.println(child);
          if (solve(child, row + 1)) {
            return true;
          } else {
            continue;
          }
        } else {
          continue;
        }
      }

      return false;
    }
  }
  //
  // public int countSolutions() {}

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
    System.out.println(solve(qb, 0));
  }
}
