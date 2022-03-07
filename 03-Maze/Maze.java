import java.util.*;
import java.io.*;
public class Maze{
  /***** FIELDS *****/
  private char[][] maze;
  private boolean animate = false; //false by default

  /***** CONSTRUCTORS *****/

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

  public Maze(int rows, int cols) {
    maze = new char[rows][cols];

    //fill maze with #s
    for (int i = 0; i < rows; i++) {
      for (int k = 0; k < cols; k++) {
        maze[i][k] = '#';
      }
    }
  }

  /***** PRIVATE HELPERS *****/

  private static void wait(int millis){
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
      System.out.println("Error 418: IM A TEAPOT");
    }
  }

  private static void clearTerminal(){
    //erase terminal
    System.out.println("\033[2J");
  }

  private static void gotoTop(){
    //go to top left of screen
    System.out.println("\033[1;1H");
  }

  private static boolean coinFlip() {
    //(not a fair coinflip)
    return randInt(0, 4) != 0;
  }

  private static int randInt(int lower, int upper) {
    //both inclusive
    return (int)(Math.random() * (upper - lower + 1) + lower);
  }

  private boolean invalid(int row, int col) {
    return (maze[row][col] == '@' || maze[row][col] == '#' || maze[row][col] == '.');
  }

  private boolean safeToCarve(int row, int col) {
    //before carving, there are fewer than 2 ways to step in
    //quick and dirty solution
    try {
      int count = 0; //keeps track of empty spaces leading into the wall
      count += maze[row + 1][col] == ' ' ? 1 : 0; //see i told you this was gonna be dirty
      count += maze[row][col + 1] == ' ' ? 1 : 0; //EDIT: JAVA DID NOT LET ME CAST BOOL TO INT AND NOT IM MAD
      count += maze[row - 1][col] == ' ' ? 1 : 0; //I WANT MY 1 + TRUE = 2 BACK STUPID LANGUAGE THAT THINKS ITS SOO COOL
      count += maze[row][col - 1] == ' ' ? 1 : 0; //BECAUSE ITS STRONGLY TYPED AND EVERYTHING BUT ITS STILL SLOWER THAN C++

      return count < 2;
    } catch (ArrayIndexOutOfBoundsException E) { //...and it gets even dirtier
      return false;
    }
  }

  /***** ~THE IMPORTANT PART~ *****/

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
  private int solve(int row, int col) { //you can add more parameters since this is private
    //automatic animation! You are welcome.
    if(animate){
      clearTerminal();
      gotoTop();
      System.out.println(this);
      wait(50);
    }

    if (maze[row][col] == 'E') {
      //base case: reached end
      return 0;
    } else if (invalid(row, col)){
      //base case: we are standing on a bad bad square and this child must die
      return -1;
    } else {
      maze[row][col] = '@';
      //recursive case: try to call self on surrounding squares
      int moves;
      moves = solve(row + 1, col);
      if (moves > -1) {return moves + 1;}

      moves = solve(row, col + 1);
      if (moves > -1) {return moves + 1;}

      moves = solve(row - 1, col);
      if (moves > -1) {return moves + 1;}

      moves = solve(row, col - 1);
      if (moves > -1) {return moves + 1;}

      //when we get to this point it means we have run out of places to go (dead end)
      maze[row][col] = '.'; //mark as dead end
      return -1; //didnt find solution
    }
  }

  //proposed generate algorithm:
  //1: fill array with # and ' ' at random
  //2: let solve method carve walls such that:
  //   only carve walls with less than two ways in
  //   random chance to carve wall or not
  //precondition: array is filled only with #s
  public void generate(int row, int col, int count) {
    if (maze[row][col] == '#' && safeToCarve(row, col)) {
      maze[row][col] = ' '; //carve
      //call self on surrounding rows
      if (coinFlip()) generate(row + 1, col, count + 1);
      if (coinFlip()) generate(row, col + 1, count + 1);
      if (coinFlip()) generate(row - 1, col, count + 1);
      if (coinFlip()) generate(row, col - 1, count + 1);
    }
  }

  /***** PUBLIC METHODS *****/

  public void setAnimate(boolean b){
    animate = b;
  }

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

  public int solve() {
    //loop through the maze to find the start
    int row = -1;
    int col = -1;
    for (int i = 0; i < maze.length; i++) {
      for (int k = 0; k < maze[0].length; k++) {
        if (maze[i][k] == 'S') {
          row = i;
          col = k;
        }
      }
    }

    assert row != -1;
    assert col != -1;

    return solve(row, col);
  }

  public void generate() {
    //start from middle
    int row = maze.length / 2;
    int col = maze[0].length / 2;
    maze[row][col] = ' ';
    generate(row + 1, col, 0);
    generate(row, col + 1, 0);
    generate(row - 1, col, 0);
    generate(row, col - 1, 0);

    //look for place to put start, starting from bottom row
    for (int i = maze.length - 2; i >= 0; i--) {
      for (int k = 0; k < maze[i].length; k++) {
        if (maze[i][k] == ' ') {
          maze[i][k] = 'S';
          i = -1; //bargain bin break
          break;
        }
      }
    }

    //look for place to put end, starting from top row
    for (int i = 1; i < maze.length; i++) {
      for (int k = 0; k < maze[i].length; k++) {
        if (maze[i][k] == ' ') {
          maze[i][k] = 'E';
          i = maze.length; //bargain bin break
          break;
        }
      }
    }
  }

  /***** MAIN *****/

  public static void main(String[] args) throws Exception {
    List<Maze> mazes = new LinkedList<Maze>();
    for (int i = 1; i <= 5; i++) {
      mazes.add(new Maze("mazes/maze" + i));
    }
    assert mazes.size() == 5;

    while (mazes.size() > 0) {
      mazes.get(0).setAnimate(true);
      mazes.get(0).solve();
      mazes.remove(0);
    }
  }
}
