import java.util.*;

public class Frontier extends ArrayDeque<int[]> {
  // Frontier is an extension of ArrayDeque such that it stores int[]
  // of length 2, representing an ordered pair, x and y respectively

  public boolean add(int x, int y) {
    int[] coords = {x, y};
    super.add(coords);
    return true;
  }

  public boolean add(int[] coords) {
    assert coords.length > 2;
    super.add(coords);
    return true;
  }

  public int getX(int index) {
    return get(index)[0];
  }

  public int getY(int index) {
    return get(index)[1];
  }
}
