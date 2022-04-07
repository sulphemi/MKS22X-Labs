import java.util.*;

public class Frontier extends ArrayDeque<int[]> {
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
}
