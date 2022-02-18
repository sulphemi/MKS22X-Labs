import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
  public static void main(String[] args) /*NOTE TO SELF REMOVE THIS LATER*/ throws FileNotFoundException {
      File file = new File("Maze1.txt");
      Scanner steve = new Scanner(file);
      String txt = "";
      int lines = 1;
      char[][] maze;

      while (steve.hasNextLine()) {
        txt += steve.nextLine();
        lines++;
      }

      maze = new char[lines][txt.length() / lines];

      for (int i = 0; i < lines; i++) {
        for (int k = 0; k < maze[0].length; k++) {
          maze[i][k] = txt.charAt(k + (maze[0].length * i));
        }
      }


      String output = "";
      for (int i = 0; i < maze.length; i++) {
        for (int k = 0; k < maze[0].length; k++) {
          output += maze[i][k];
        }
        output += '\n';
      }
      System.out.println(output);
    }
  }
