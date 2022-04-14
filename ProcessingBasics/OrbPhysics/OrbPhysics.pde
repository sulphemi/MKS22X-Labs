ArrayList<Orb> orbList;
void setup() {
  size(1000, 700);
  orbList = new ArrayList<Orb>();
  noStroke(); //prettier this way
}
void mouseClicked() {
  //add a new Orb to the orbList, constructed as follows:
  //The x and y positions are the same as the mouse
  //the radius should be between in the range [20.0,70.0)
  //the xSpeed and ySpeed should be in the range [-3.0,3.0)

  float xSpeed = rand(-3.0, 3.0);
  float ySpeed = rand(-3.0, 3.0);
  float radius = rand(20.0, 70.0);
  orbList.add(new Orb(mouseX, mouseY, xSpeed, ySpeed, radius));
}
void draw() {
  background(255);
  for (Orb o : orbList) {
    o.move();
    o.display();
  }
  fill(0);
  text(frameRate, 20, 20);
  text(orbList.size(), 20, 40);
  
  /*
  //bounce
  for (int i = 0; i < orbList.size(); i++) {
    for (int k = 0; k < orbList.size(); k++) {
      if (i != k && orbList.get(i).collidingWith(orbList.get(k))) {
        orbList.get(i).collide();
        break;
      }
    }
  }
  */
}

private float rand(float lower, float upper) {
  return (float)Math.random() * (upper - lower + 1) + lower;
}
