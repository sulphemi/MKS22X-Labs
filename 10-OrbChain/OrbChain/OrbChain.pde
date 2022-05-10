final static int SPRING = 2;
final static float SPRING_LENGTH = 50;
final static float SPRING_DAMPEN = 0.990;
final static float SPRING_CONSTANT = 0.015;
final static int MODE = SPRING;
OrbList orbs;
void setup() {
  size(1000, 800);
  orbs = new OrbList();
}
void mouseClicked() {
  orbs.add(new OrbNode(mouseX,mouseY,0,0,30));
}
void draw() {
  background(255);
  orbs.processAll();
  orbs.display();
}
