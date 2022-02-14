public class QueenBoard {
  /***** FIELDS *****/
  int[][] board;

  /***** CONSTRUCTORS *****/
  public QueenBoard(int n) { //initializes the board for size n
    board = new int[n][n];
  }

  /***** PRIVATE METHODS *****/
  // private boolean addQueen() {}
  // private boolean removeQueen() {}
  //
  /***** PUBLIC METHODS *****/
  public String toString() {
    String output = "";

    for (int i = 0; i < board.length; i++) {
      for (int k = 0; k < board.length; k++) {
        output += board[i][k];
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
  //
  // public int countSolutions() {}

  /***** MAIN *****/
  public static void main(String[] args) {
    QueenBoard qb = new QueenBoard(8);
    System.out.println(qb.toString());
  }
}
