    ArrayList<Orb>orbList;
    void setup() {
      size(1000, 700);
      orbList = new ArrayList<Orb>();
    }
    void mouseClicked() {
      //add a new Orb to the orbList, constructed as follows:
      //The x and y positions are the same as the mouse
      //the radius should be between in the range [20.0,70.0)
      //the xSpeed and ySpeed should be in the range [-3.0,3.0)
    }
    void draw() {
      background(255);
      for (Orb o : orbList) {
        o.move();
        o.display();
      }
      fill(0);
      text(frameRate,20,20);
      text(orbList.size(),20,40);
    }
