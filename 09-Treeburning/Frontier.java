import java.util.*;

public class Frontier extends ArrayDeque<int[]> {
  // Frontier is an extension of ArrayDeque such that it stores int[]
  // of length 2, representing an ordered pair, x and y respectively

  public boolean add(int x, int y) {
    int[] coords = {x, y};
    return super.add(coords);
  }

  public boolean add(int[] coords) {
    assert coords.length == 2;
    return super.add(coords);
  }
}
