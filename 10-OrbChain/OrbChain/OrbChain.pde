static int SPRING = 2;
static int MODE = SPRING;

static float SPRING_LENGTH = 50;
static float SPRING_DAMPEN = 0.990;
static float SPRING_CONSTANT = 0.015;

static float GRAVITY = 0;

final static int ADDMODE = 0;
final static int INSERTMODE = 1;
final static int DELETEMODE = 2;
final static String[] modes = {"ADD", "INSERT", "DELETE"};
static int CLICKMODE = 0;

OrbList orbs;
void setup() {
  size(1000, 800);
  orbs = new OrbList();
}
void mouseClicked() {
  switch (CLICKMODE) {
    case ADDMODE:
      orbs.add(new OrbNode(mouseX,mouseY,0,0,30));
      break;
    case INSERTMODE:
      orbs.add(mouseX, new OrbNode(mouseX, mouseY, 0, 0, 30));
      break;
    case DELETEMODE:
      orbs.delete(orbs.getNodeAt(mouseX, mouseY));
  }
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
  text("MODE: " + modes[CLICKMODE], 20, 100);
}

void keyPressed() {
  switch (key) {
    case '1':
      //increase spring constant
      SPRING_CONSTANT += 0.005f;
      break;
    case '2':
      //decrease spring constant
      if ((SPRING_CONSTANT -= 0.005f) < 0f) {SPRING_CONSTANT = 0f;}
      break;
    case '3':
      //increase spring dampen
      if ((SPRING_DAMPEN += 0.005f) > 1f) {SPRING_DAMPEN = 1f;}
      break;
    case '4':
      //decrease spring dampen
      if ((SPRING_DAMPEN -= 0.005f) < 0f) {SPRING_DAMPEN = 0f;}
      break;
    case '5':
      //increase spring length
      SPRING_LENGTH += 1;
      break;
    case '6':
      //decrease spring length
      if ((SPRING_LENGTH -= 1) < 0f) {SPRING_LENGTH = 0f;}
      break;
    case '7':
      //increase gravity
      GRAVITY += 0.05f;
      break;
    case '8':
      //decrease gravity
      if ((GRAVITY -= 0.05f) < 0f) {GRAVITY = 0f;}
      break;
    case ' ':
      if (++CLICKMODE >= modes.length) CLICKMODE = 0;
    default:
      switch (keyCode) {

      }
  }
}
