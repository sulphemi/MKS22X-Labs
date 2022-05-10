static int SPRING = 2;
static int MODE = SPRING;

static float SPRING_LENGTH = 50;
static float SPRING_DAMPEN = 0.990;
static float SPRING_CONSTANT = 0.015;

static float GRAVITY = 0;

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
  
  //text
  fill(0);
  text("SPRING CONSTANT: " + SPRING_CONSTANT, 20, 20);
  text("SPRING DAMPENING: " + SPRING_DAMPEN, 20, 40);
  text("SPRING LENGTH: " + SPRING_LENGTH, 20, 60);
  text("GRAVITY: " + GRAVITY, 20, 80);
}

void keyPressed() {
  switch (keyCode) {
    case 49:
      //increase spring constant
      SPRING_CONSTANT += 0.005f;
      break;
    case 50:
      //decrease spring constant
      SPRING_CONSTANT -= 0.005f;
      break;
    case 51:
      //increase spring dampen
      SPRING_DAMPEN += 0.005f;
      break;
    case 52:
      //decrease spring dampen
      SPRING_DAMPEN -= 0.005f;
      break;
    case 53:
      //increase spring length
      SPRING_LENGTH += 1;
      break;
    case 54:
      //decrease spring length
      SPRING_LENGTH -= 1;
      break;
    case 55:
      //increase gravity
      GRAVITY += 0.05f;
      break;
    case 56:
      //decrease gravity
      GRAVITY -= 0.05f;
      break;
  }
}
