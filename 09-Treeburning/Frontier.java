import java.util.*;

public class Frontier extends ArrayDeque<int[]> {
  public void add(int x, int y) {
    int[] coords = {x, y};
    add(coords);
  }
}
