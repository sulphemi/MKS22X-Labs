import java.util.*;

public class Frontier {
  private Queue<int[]> frontier;
  public Frontier(){}
  public int size(){}
  public void add(int[] location) {
    assert location.size == 2; //location is an ordered pair
    frontier.add(location);
  }
  public int[] remove(){}
}
