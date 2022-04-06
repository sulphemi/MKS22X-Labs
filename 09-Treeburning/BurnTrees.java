import java.util.*;
public class BurnTrees{
  private int[][]map;
  private int ticks;
  private static final int TREE = 2;
  private static final int FIRE = 1;
  private static final int ASH = 3;
  private static final int SPACE = 0;

  private Frontier frontier;


  /*Determine if the simulation is still burning
   *@return false if any fires are still burning, true otherwise
   */
  public boolean done(){ //return if there is stuff in the stuff and the stuff (THE QUEUE!!!)
    return frontier.size() == 0;
  }


  /*This is the core of the simulation. All of the logic for advancing to the next round goes here.
   *All existing fires spread new fires, and turn to ash
   *new fires should remain fire, and not spread.
   */
  public void tick(){
    //there is a more efficient way to do this but wheeeeeeeee
    Frontier newFrontier = new Frontier(); //stores the next set of trees that are on fire
    while (frontier.size() > 0) {
      int[] coords = frontier.remove(); //store pointer, this is an int[] of size 2 representing (x, y)
      //try to set all four directions on fire
      setOnFire(coords[0] - 1, coords[1], newFrontier);
      setOnFire(coords[0], coords[1] - 1, newFrontier);
      setOnFire(coords[0] + 1, coords[1], newFrontier);
      setOnFire(coords[0], coords[1] + 1, newFrontier);
    }
    ticks++;//leave this here.
  }

  private boolean onBoard(int x, int y) {
    //lazy implementation, feel free to fight me
    try {
      map[x][y]; //will except if x y not on board
      return true;
    } catch (ArrayIndexOutOfBoundsException E) {
      return false;
    }
  }

  private boolean spreadable(int x, int y) { //returns if fire is able to spread to this square
    return onBoard(x, y) && map[x][y] == TREE;
  }

  private boolean setOnFire(int x, int y, Frontier naughtyList) {
    if (spreadable(x, y)) {
      map[x][y] = FIRE;
      naughtyList.add(x, y);
      return true;
    } else {
      return false; //this code is dumb but it works so im keeping it
    }
  }

  /***********************YOU MIGHT UPDATE THIS**************************/

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
    for(int i = 0; i < map.length; i++){
      if(map[i][0]==TREE){
        map[i][0]=FIRE;
        frontier.add(i, 0); //add the fwooshy fire to the fwooshy frontier
      }
    }
  }



    public static void main(String[]args){
      int WIDTH = 20;
      int HEIGHT = 20;
      int DELAY = 200;
      double DENSITY = .7;
      if(args.length > 1){
        WIDTH = Integer.parseInt(args[0]);
        HEIGHT = Integer.parseInt(args[1]);
        DENSITY = Double.parseDouble(args[2]);
      }
      if(args.length > 3){
        DELAY = Integer.parseInt(args[3]);
      }
      BurnTrees b = new BurnTrees(WIDTH,HEIGHT,DENSITY);


      int ans = b.animate(DELAY);//animate all screens
      System.out.println(ans);//print the final answer

      //int ans = b.outputAll();//print all screens one after another
      //System.out.println(ans);//print the final answer
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
  public String toStringColor(){ //haha i updated it
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < map.length; i++) {
      for (int c = 0; c < map[i].length; c++) {
        if(map[i][c]==0)
          builder.append(' ');
        else if(map[i][c]==2) {
          builder.append(Text.color(Text.GREEN));
          builder.append('@');
        }
        else if(map[i][c]==1) {
          builder.append(Text.color(Text.RED));
          builder.append('w');
        }
        else if(map[i][c]==3) {
          builder.append(Text.color(Text.DARK));
          builder.append('.');
        }
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
