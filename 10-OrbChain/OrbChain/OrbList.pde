public class OrbList {
  OrbNode first, last;

  //create a fixed orb
  OrbList() {
    first = new FixedOrbNode(0, height/5);
    last = new FixedOrbNode(width, height/5);
    //link them to eachother
    first.next = last;
    last.prev = first;
  }

  /**
  *complete this method
  */
  void add(OrbNode orb){
    //insert orb at the end of the list before the last node.
    OrbNode current = last.prev;
    current.next = orb;
    last.prev = orb;
    
    orb.prev = current;
    orb.next = last;
  }

  /**
  *complete this method
  *process all nodes by running move.
  */
  void processAll() {
    OrbNode current = first;
    //advance current to next until it is null, move() each of the nodes
    while (current != null) {
      current.move();
      current = current.next;
    }
  }
  /**
  *complete this method
  *Display all nodes by running their display().
  */
  void display() {
    OrbNode current = first;
    //advance current to next until it is null, display() each of the nodes
    while (current != null) {
      current.display();
      current = current.next;
    }
  }
  
  OrbNode getNodeAt(int x, int y) {
    OrbNode current = first;
    while (current != null) {
      if (dist(x, y, current.x, current.y) <= current.radius) {
        return current;
      }
      current = current.next;
    }

    return null; //matching orb not found
  }
  
  boolean delete(OrbNode target) {
    if (target == null) {
      return false;
    }
    
    OrbNode current = first;
    while (current != null) {
      if (current == target) { //equivalence check is fine since we are comparing mem address
        OrbNode before = current.prev;
        OrbNode after = current.next;
        
        if (before != null) {before.next = after;}
        if (after != null) {after.prev = before;}
        
        return true; //removed successfully
      }
      current = current.next;
    }
    return false; //removed unsuccessfully
  }
  
  void add(int xcor, OrbNode addition) {
    for (OrbNode current = first; current != null; current = current.next) {
      if (current.x > xcor) {
        if (current.prev != null) {current.prev.next = addition;}
        addition.prev = current.prev;
        addition.next = current;
        current.prev = addition;
        return;
      }
    }
  }
}
