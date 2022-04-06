import java.util.*;

public class Frontier {
  private Queue<int[]> frontier;

  public Frontier() {
    frontier = new ArrayDeque<int[]>();
  }

  public int size() {
    return frontier.size();
  }

  public void add(int[] location) {
    assert location.size == 2; //location is an ordered pair
    frontier.add(location);
  }

  public void add(int x, int y) {
    int[] coords = {x, y};
    add(coords);
  }

  public int[] remove() {
    return frontier.remove();
  }
}
