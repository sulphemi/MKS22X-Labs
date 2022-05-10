static int SPRING = 2;
static int MODE = SPRING;

static float SPRING_LENGTH = 50;
static float SPRING_DAMPEN = 0.990;
static float SPRING_CONSTANT = 0.015;

static int GRAVITY = 0;

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

void keyPressed() {
  switch (key) {
    case '1':
      //increase spring constant
      break;
    case '2':
      //decrease spring constant
      break;
    case '3':
      //increase spring dampen
      break;
    case '4':
      //decrease spring dampen
      break;
    case '5':
      //increase spring length
      break;
    case '6':
      //decrease spring length
      break;
    case '7':
      //increase gravity
      break;
    case '8':
      //decrease gravity
      break;
  }
}
