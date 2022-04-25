ArrayList<Orb> orbList;
Orb aaa;

void setup() {
  size(1000, 700);
  orbList = new ArrayList<Orb>();
  noStroke(); //prettier this way
  
  aaa = new Orb(width / 2, height / 2, 0, 0, 10);
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
  
  aaa.display();
  for (Orb o : orbList) {
    o.move();
    o.display();
    
    aaa.attract(o);
  }
  fill(0);
  text(frameRate, 20, 20);
  text(orbList.size(), 20, 40);
}

private float rand(float lower, float upper) {
  return (float)Math.random() * (upper - lower + 1) + lower;
}
