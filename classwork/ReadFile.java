import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ReadFile {
  public static int[] getValuesFromFile(String filename) throws FileNotFoundException{
    File text = new File(filename);
    //code here to read file into array
  }

  public static void main(String args[]){
    int[]nums = new int[0];
    try{
      nums = getValuesFromFile("Numbers.csv");
    }catch(FileNotFoundException e){
      //error handling code
    }
}
