import java.util.*;
import java.io.*;
public class Maze{
  private char[][] maze;
  private boolean animate;//false by default
  private int startRow,startCol;

  /*Constructor loads a maze text file, and sets animate to false by default.
  When the file is not found then:
  throw a FileNotFoundException

  You may assume the file contains a rectangular ascii maze, made with the following 4 characters:
  '#' - Walls - locations that cannot be moved onto
  ' ' - Empty Space - locations that can be moved onto
  'E' - the location of the goal if any (0 or more per file)
  'S' - the location of the start(exactly 1 per file)

  You may also assume the maze has a border of '#' around the edges.
  So you don't have to check for out of bounds!
  Some text editors always include a newline at the end of a file, but that is not always present.
  Make sure your file reading is able to handle this.
  */
  public Maze(String filename) throws IOException {
    BufferedReader bob = new BufferedReader(new FileReader(filename));
    String txt = ""; //string representation of the file
    int lines = 0;
    int mazeLength;

    while (bob.ready()) {
      txt += bob.readLine(); //read line into string
      lines++;
    }

    mazeLength = txt.length() / lines;
    maze = new char[lines][mazeLength];

    for (int i = 0; i < lines; i++) {
      for (int k = 0; k < mazeLength; k++) {
        maze[i][k] = txt.charAt(k + (mazeLength * i));
      }
    }
  }

  private void wait(int millis){
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
    }
  }

  public void setAnimate(boolean b){
    animate = b;
  }

  public static void clearTerminal(){
    //erase terminal
    System.out.println("\033[2J");
  }
  public static void gotoTop(){
    //go to top left of screen
    System.out.println("\033[1;1H");
  }

  /*Return the string that represents the maze.
  It should look like the text file with some characters replaced.
  */
  public String toString(){
    String output = "";
    for (int i = 0; i < maze.length; i++) {
      for (int k = 0; k < maze[0].length; k++) {
        output += maze[i][k];
      }
      if (i != maze.length - 1) {
        output += '\n';
      }
    }

    return output;
  }

  /*Wrapper Solve Function returns the helper function
  Note the helper function has the same name, but different parameters.
  Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
  */
  // public int solve(){
  //   //only clear the terminal if you are running animation
  //   if(animate){
  //     clearTerminal();
  //   }
  //   //start solving at the location of the s.
  //   return solve(startRow,startCol);
  //
  // }

  /*
  Recursive Solve function:

  A solved maze has a path marked with '@' from S to E.

  Returns the number of @ symbols from S to E when the maze is solved,
  Returns -1 when the maze has no solution.

  Postcondition:
  The 'S' is replaced with '@'
  The 'E' remain the same
  All visited spots that were not part of the solution are changed to '.'
  All visited spots that are part of the solution are changed to '@'
  */
  private int solve(int row, int col, int moves) { //you can add more parameters since this is private
    //automatic animation! You are welcome.
    if(animate){
      clearTerminal();
      gotoTop();
      System.out.println(this);
      wait(50);
    }

    if (maze[row][col] == 'E') {
      //base case: reached end
      return moves;
    } else if (invalid(row, col)){
      //we are standing on a bad bad square
      return -1;
    } else {
      maze[row][col] = '@';
      //recursive case: try to call self on surrounding squares
      int returnVal;
      returnVal = solve(row + 1, col, moves + 1);
      if (returnVal > 0) {return returnVal;}

      returnVal = solve(row, col + 1, moves + 1);
      if (returnVal > 0) {return returnVal;}

      returnVal = solve(row + 1, col, moves + 1);
      if (returnVal > 0) {return returnVal;}

      returnVal = solve(row, col - 1, moves + 1);
      if (returnVal > 0) {return returnVal;}

      //we reached the end so backtrack
      System.out.println("bonk! " + row + " " + col);
    }

    //COMPLETE SOLVE
    return -1; //so it compiles
  }

  private boolean invalid(int row, int col) {
    return (maze[row][col] == '@' || maze[row][col] == '#' || maze[row][col] == '.');
  }

  public static void main(String[] args) throws Exception {
    Maze something = new Maze("/Users/jing/Documents/MKS22X-Labs/classwork/Maze1.txt");
    assert something != null;
    something.setAnimate(true);
    something.solve(7, 1, 0);
  }
}
