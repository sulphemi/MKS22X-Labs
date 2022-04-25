ArrayList<Orb> orbList;
Orb aaa;
boolean orbitMode;

void setup() {
  size(1000, 700);
  orbList = new ArrayList<Orb>();
  noStroke(); //prettier this way
  
  aaa = new Orb(width / 2, height / 2, 0, 0, 10);
  orbitMode = false;
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
  
  if (orbitMode) {
    aaa.display();
    for (Orb o : orbList) {
      aaa.attract(o);
    }
  }
  fill(0);
  text(frameRate, 20, 20);
  text(orbList.size(), 20, 40);
}

void keyPressed() {
  if (key == ' ') {
    orbitMode = !orbitMode;
  }
}
