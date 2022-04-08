import java.util.*;
public class BurnTrees{
  private int[][]map;
  private int ticks;
  private static final int TREE = 2;
  private static final int FIRE = 1;
  private static final int ASH = 3;
  private static final int SPACE = 0;

  private Frontier burnyList;


  /*Determine if the simulation is still burning
   *@return false if any fires are still burning, true otherwise
   */
  public boolean done(){ //return if there is stuff in the stuff and the stuff (THE QUEUE!!!)
    return burnyList.size() == 0;
  }


  /*This is the core of the simulation. All of the logic for advancing to the next round goes here.
   *All existing fires spread new fires, and turn to ash
   *new fires should remain fire, and not spread.
   */
  public void tick(){
    //there is a more efficient way to do this but wheeeeeeeee
    int fires = burnyList.size();
    for (int i = 0; i < fires; i++) {
      int[] coords = burnyList.remove(); //store pointer, this is an int[] of size 2 representing (x, y)
      //try to set all four directions on fire
      setOnFire(coords[0] - 1, coords[1]);
      setOnFire(coords[0], coords[1] - 1);
      setOnFire(coords[0] + 1, coords[1]);
      setOnFire(coords[0], coords[1] + 1);

      map[coords[0]][coords[1]] = ASH; //set previously burning spot to ash
    }
    ticks++;//leave this here.
  }

  private boolean onBoard(int x, int y) {
    //lazy implementation, feel free to fight me
    try {
      int thing = map[x][y]; //will except if x y not on board
      return true;
    } catch (ArrayIndexOutOfBoundsException E) {
      return false;
    }
  }

  private boolean spreadable(int x, int y) { //returns if fire is able to spread to this square
    return onBoard(x, y) && map[x][y] == TREE;
  }

  private boolean setOnFire(int x, int y) {
    if (spreadable(x, y)) {
      map[x][y] = FIRE;
      burnyList.add(x, y);
      return true;
    } else {
      return false; //this code is dumb
    }
  }

  /***********************YOU MIGHT UPDATE THIS**************************/

  public BurnTrees(int[][] map) { //create from existing map
    this.map = map;
    start();
  }

  /*Initialize the simulation.
   *If you add more instance variables you can add more here,
   *otherwise it is complete
   */
  public BurnTrees(int width,int height, double density){
    map = new int[height][width];
    for(int r=0; r<map.length; r++ ){
      for(int c=0; c<map[r].length; c++ ){
        if(Math.random() < density){
           map[r][c]=TREE;
         }
       }
     }
     start();//set the left column on fire.
  }


  /*
   *Sets the trees in the left column of the forest on fire
   */
  public void start(){
    //If you add more instance variables you can add more here,
    //otherwise it is complete.
    burnyList = new Frontier(); //init burnyList
    for(int i = 0; i < map.length; i++){
      if(map[i][0]==TREE){
        map[i][0]=FIRE;
        burnyList.add(i, 0); //add the fwooshy fire to the fwooshy burnyList
      }
    }
  }

  public static void testSpiral() throws Exception {
    int[][] spiralOfDeath = {
      {TREE , TREE , TREE , TREE , TREE , TREE , TREE },
      {SPACE, SPACE, SPACE, SPACE, SPACE, SPACE, TREE },
      {SPACE, TREE , TREE , TREE , TREE , SPACE, TREE },
      {SPACE, TREE , SPACE, SPACE, TREE , SPACE, TREE },
      {SPACE, TREE , SPACE, TREE , TREE , SPACE, TREE },
      {SPACE, TREE , SPACE, SPACE, SPACE, SPACE, TREE },
      {SPACE, TREE , TREE , TREE , TREE , TREE , TREE }
    };

    BurnTrees b = new BurnTrees(spiralOfDeath);
    System.out.println(b.toString());
    for (;;) {
      System.in.read();
      b.tick();
      System.out.println(b.toString());
    }
  }

    public static void main(String[]args) throws Exception {
      int WIDTH = 20;
      int HEIGHT = 20;
      int DELAY = 200;
      double DENSITY = 1;
      if(args.length > 1){
        WIDTH = Integer.parseInt(args[0]);
        HEIGHT = Integer.parseInt(args[1]);
        DENSITY = Double.parseDouble(args[2]);
      }
      if(args.length > 3){
        DELAY = Integer.parseInt(args[3]);
      }
      BurnTrees b = new BurnTrees(WIDTH,HEIGHT,DENSITY);
      System.out.print(b.toStringColor());
      for (;;) {
        System.in.read();
        if (b.done()) {break;}
        b.tick();
        System.out.print(b.toStringColor());
      }

      //int ans = b.animate(DELAY);//animate all screens
      //System.out.println(ans);//print the final answer

      //int ans = b.outputAll();//print all screens one after another
      //System.out.println(ans);//print the final answer
    }

  //runs a single simulation with given parameters and return ticks
  public static int runSimulation(int N, int M, int density) {
    BurnTrees Arson = new BurnTrees(N, M, density);
    return Arson.run();
  }

  //repeats times and returns the average
  public static double repeatSimulation(int N, int M, int density, int times) {
    double sum = 0;
    for (int i = 0; i < times; i++) {
      sum += runSimulation(N, M, density);
    }
    return sum / times;
  }


  /***********************DO NOT UPDATE THINGS BELOW HERE**************************/

  /*DO NOT UPDATE THIS
   *PLEASE READ SO YOU SEE HOW THE SIMULATION IS SUPPOSED TO WORK!!!
   */
  public int run(){
    while(!done()){
      tick();
    }
    return getTicks();
  }


  /*DO NOT UPDATE THIS*/
  public int getTicks(){
    return ticks;
  }

  /*DO NOT UPDATE THIS*/
  public String toString(){
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < map.length; i++) {
      for (int c = 0; c < map[i].length; c++) {
        if(map[i][c]==SPACE)
          builder.append(" ");
        else if(map[i][c]==TREE)
          builder.append("@");
        else if(map[i][c]==FIRE)
          builder.append("w");
        else if(map[i][c]==ASH)
          builder.append(".");
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  /*DO NOT UPDATE THIS*/
   public String toStringColor(){
     StringBuilder builder = new StringBuilder();
     for (int i = 0; i < map.length; i++) {
       for (int c = 0; c < map[i].length; c++) {
         if(map[i][c]==0)
           builder.append(" ");
         else if(map[i][c]==2)
           builder.append(Text.color(Text.GREEN)+"@");
         else if(map[i][c]==1)
           builder.append(Text.color(Text.RED)+"w");
         else if(map[i][c]==3)
           builder.append(Text.color(Text.DARK)+".");
       }
       builder.append("\n"+Text.RESET);
     }
     return builder.toString()+ticks+"\n";
   }

  /*DO NOT UPDATE THIS*/
  public int animate(int delay) {
    System.out.print(Text.CLEAR_SCREEN);
    System.out.println(Text.go(1,1)+toStringColor());
    Text.wait(delay);
    while(!done()){
      tick();
      System.out.println(Text.go(1,1)+toStringColor());
      Text.wait(delay);
    }
    return getTicks();
  }

  /*DO NOT UPDATE THIS*/
  public int outputAll(){
    System.out.println(toString());
    while(!done()){
      tick();
      System.out.println(toString());
    }
    return getTicks();
  }


}
