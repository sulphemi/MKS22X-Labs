  /**IMPORTANT:
   *Please read through the logic of this sketch before you try to modify anything.
   *Please answer ALL questions in the source code BEFORE writing any code.
   */
  BurnTrees treeSim;
  int ROWS;
  int COLS;
  double DENSITY;
  int SQUARESIZE;

  void setup() {
    size(600, 500);
    ROWS = 50;
    COLS = 60;
    /**question 1 *********************************
     *At this point you have initialized width, height,ROWS,COLS. You can change these values
     *to alter the screen size, but you cannot just change one value!
     *What must be true about the ratio of these values in order for this simulation to display squares?
     *ANSWER HERE: The values must share a common factor, the size of the squares
     */

    DENSITY = .61;
    treeSim = new BurnTrees(ROWS, COLS, DENSITY);
    /**question 2 *********************************
     *Given that you can change the size() and the number of ROWS and COLS,
     *How do you calculate the SQUARESIZE (size of each square you draw) using an expression?
     *ANSWER : The dimensions of the window should be the size of the square times the number of rows/columns, so squaresize = size / columns
     * DO NOT just write a number, it must work when you change the size() command or the ROWS and COLS
     */
    SQUARESIZE = width / COLS;//side length

  }

  /*DO NOT UPDATE THIS METHOD*/
  void draw() {
    background(0);
    //frameCount tells you how many times the draw method was called.
    if (frameCount % 10 == 0) {
      treeSim.tick();
      //println("Testing code:"+ frameCount);
    }
    /**question 3 *********************************
     *Here we only call tick() when the frameCount % 10 == 0.
     *Why do we want to do this?
     *(The print statement is NOT part of the simulation, it is to help you answer this question)
     *hint:  If you cannot figure this out analytically, experiment to test
     *       the difference by changing the code. A print statement is commented out to facilitate testing.
     *ANSWER HERE: The processing window runs at either 30 or 60 frames per second. By only running the simulation on frameCounts that are divisible by 10, the program would tick 3 or 6 times per second, as opposed to 30/60 times per second
     */

    String[]lines = treeSim.toString().split("\n");

    //Convert the 2D array into a grid of RED/GREEN/WHITE/GREY Squares.
    stringToSquares(lines);

    //Output the resulting time and dimensions of the simulation
    if (treeSim.done()) {
      fill(0);
      textSize(20);
      text("Simulation of "+COLS+"by"+ROWS+" board lasted "+treeSim.getTicks()+" ticks", 20, 20);
    }
  }

  /**
   *Clicking the mouse will reset the simulation by re-constructing the treeSim variable.
   */
  void mouseClicked() {
    /**question 4 *********************************
     *We can easily reset the simulation by clicking the mouse.
     *Please use the same values that it was initialized with in the setup.
     * ANSWER: UPDATE THE NEXT LINE
     */
    setup();
  }


  /** question 5 *********************************
   *Write the method that will take an array of the different lines of the board string,
   *and draw ROWSxCOLS colored squares on the screen
   *@param lines: an array of the lines of the toString method that has ROWS strings, each having a length of COLS
   *              e.g. [ "@ @@","@@@ "," @ @"] for a ROWS=3, COLS=4 board.
   *ANSWER: Complete this method.
   */
  void stringToSquares(String[]lines) {
    /**Complete this method.
     *1. Break up your screen by drawing ROWSxCOLS squares of the same color.
     *2. Decide how to fill them in using the String[] parameter
     *   Colors: Fire = RED, Tree = GREEN, SPACE = WHITE, ASH = GREY
     */
     
     int r, c;
     
     for (int i = 0; i < lines.length; i++) {
       for (int k = 0; k < lines[i].length(); k++) {
         switch (lines[i].charAt(k)) {
           case ' ': //SPACE
             println("space");
             fill(#12130F);
             break;
           case 'w': //FIRE
           println("fire");
             fill(#EF2D56);
             break;
           case '@': //TREE
           println("tree");
             fill(#6BFFB8);
             break;
           case '.': //ASH
           println("ash");
             fill(#BFADA3);
             break;
           default:
           println("default");
             break;
       }
       
       rect(k * SQUARESIZE, i * SQUARESIZE, SQUARESIZE, SQUARESIZE);
     }
    }
  }


  /***THIS IS YOUR PRIOR LAB (only a subset of methods)***/

  import java.util.*;
  public class BurnTrees {
    private int[][]map;
    private int ticks;
    private static final int TREE = 2;
    private static final int FIRE = 1;
    private static final int ASH = 3;
    private static final int SPACE = 0;
    private ArrayDeque<Integer>frontier;


    /**Determine if the simulation is still burning
     *@return false if any fires are still burning, true otherwise
     */
    public boolean done() {
      return frontier.size() == 0;
    }


    public void spread(int row, int col) {
      map[row][col]=ASH;

      if (row > 0) {
        if (map[row-1][col]==TREE) {
          map[row-1][col]=FIRE;
          frontier.add(row-1);
          frontier.add(col);
        }
      }
      if (row < map.length-1) {
        if (map[row+1][col]==TREE) {
          map[row+1][col]=FIRE;
          frontier.add(row+1);
          frontier.add(col);
        }
      }
      if (col > 0) {
        if (map[row][col-1]==TREE) {
          map[row][col-1]=FIRE;
          frontier.add(row);
          frontier.add(col-1);
        }
      }
      if (col < map[0].length-1) {
        if (map[row][col+1]==TREE) {
          map[row][col+1]=FIRE;
          frontier.add(row);
          frontier.add(col+1);
        }
      }
    }

    /**This is the core of the simulation. All of the logic for advancing to the next round goes here.
     *All existing fires spread new fires, and turn to ash
     *new fires should remain fire, and not spread.
     */
    public void tick() {
      if (!done()) { //THIS IS THE ONLY MODIFICATION TO THE LAB
        ticks++;
        int fires = frontier.size();
        while (fires > 0) {
          int r = frontier.remove();
          int c = frontier.remove();
          spread(r, c);
          fires-=2;
        }
      }
    }

    /**Initialize the simulation.
     *If you add more instance variables you can add more here,
     *otherwise it is complete
     */
    public BurnTrees(int rows, int cols, double density) {
      frontier = new ArrayDeque<Integer>();
      map = new int[rows][cols];
      for (int r=0; r<map.length; r++ ) {
        for (int c=0; c < map[r].length; c++ ) {
          if (Math.random() < density) {
            map[r][c]=TREE;
          }
        }
      }
      start();//set the left column on fire.
    }

    public void start() {
      for (int i = 0; i < map.length; i++) {
        if (map[i][0]==TREE) {
          map[i][0]=FIRE;
          frontier.add(i);
          frontier.add(0);
        }
      }
    }

    public int getTicks() {
      return ticks;
    }

    public String toString() {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < map.length; i++) {
        for (int c = 0; c < map[i].length; c++) {
          if (map[i][c]==SPACE)
            builder.append(" ");
          else if (map[i][c]==TREE)
            builder.append("@");
          else if (map[i][c]==FIRE)
            builder.append("w");
          else if (map[i][c]==ASH)
            builder.append(".");
        }
        builder.append("\n");
      }
      return builder.toString();
    }
  }
