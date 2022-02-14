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
    QueenBoard qbclone = qb.deepCopy();
    System.out.println("qb:");
    System.out.println(qb.toString());
    System.out.println("copy:");
    System.out.println(qbclone);
    qb.board[0][0] = 5;
    System.out.println("qb");
    System.out.println(qb);
    System.out.println("copy:");
    System.out.println(qbclone);
  }
}
