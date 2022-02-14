public class QueenBoard {
  /***** FIELDS *****/
  int[][] board;

  /***** CONSTRUCTORS *****/
  public QueenBoard(int n) { //initializes the board for size n
    board = new int[n][n];
  }

  public QueenBoard(int[][] array) { //note that board will now be linked to array
    board = array;
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
  public String toString() {
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
  //
  // public boolean solve() {} //wrapper method
  public static QueenBoard solve(QueenBoard QB, int row, int column, int placed) { //solver method
    //base case
    if (row == QB.board.length) { //means we reached the end
      return QB;
    } else {
      //recurive case
      //try to add a queen at given location
      if (QB.addQueen(row, column)) {
        //adding succeeded
        for (int i = 0; i < QB.board.length; i++) { //iterate through the columns
          return solve(QB.deepCopy(), row + 1, i, placed);
        }
        return QB;
      } else { //adding was impossible
        return QB; //end tree early
      }
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

    return new QueenBoard(newBoard);
  }

  /***** MAIN *****/
  public static void main(String[] args) {
    QueenBoard qb = new QueenBoard(8);
    System.out.println(solve(qb, 0, 0, 0));
  }
}
