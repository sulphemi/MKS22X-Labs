import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
  public static void main(String[] args) /*NOTE TO SELF REMOVE THIS LATER*/ throws FileNotFoundException {
      File file = new File("Maze1.txt");
      Scanner steve = new Scanner(file);
      String txt = "";
      int lines = 0;
      char[][] maze;

      do {
        txt += steve.nextLine();
        lines++;
      } while (steve.hasNextLine());

      maze = new char[lines][txt.length() / lines];

      for (int i = 0; i < lines; i++) {
        for (int k = 0; k < maze[0].length; k++) {
          maze[i][k] = txt.charAt(k + (maze[0].length * i));
        }
      }



      System.out.println("Lines = " + lines);
      System.out.println(toString(maze));
    }

    private static String toString(char[][] array) {
      String output = "";
      for (int i = 0; i < array.length; i++) {
        for (int k = 0; k < array[0].length; k++) {
          output += array[i][k];
        }
        if (i != array.length - 1) {
          output += '\n';
        }
      }

      return output;
    }
  }
