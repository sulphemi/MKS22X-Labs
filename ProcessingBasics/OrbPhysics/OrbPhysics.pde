ArrayList<Orb> orbList;
Orb centerOrb;
int MODE;
boolean drawBackground;

static final int modeC = 3;
static final int BOUNCE = 0;
static final int ORBIT = 1;
static final int SPRING = 2;
static final String[] modeNames = {"BOUNCE", "ORBIT", "SPRING"};

static final float SPRING_CONSTANT = 0.015;
static final float SPRING_LENGTH = 100;
static final float SPRING_DAMPEN = .995;

void setup() {
  size(1000, 800);
  orbList = new ArrayList<Orb>();
  noStroke(); //prettier this way
  
  centerOrb = new Orb(width / 2, height / 2, 0, 0, 10);
  MODE = BOUNCE;
  drawBackground = true;
}
void mouseClicked() {
  // Modify mouseClicked to add a new Orb located at the mouse position such that:
  // the new Orb should have a radius of 20, with an initial xspeed(dx) of 5, and a yspeed(dy) of 0
  
  orbList.add(new Orb(mouseX, mouseY, 5, 0, 20));
}
void draw() {
  if (drawBackground) {background(255);}
  
  for (Orb o : orbList) {
    o.move();
    o.display();
    //o.drawStick();
  }
  
  switch (MODE) {
    case ORBIT:
      centerOrb.display();
      for (Orb o : orbList) {
        centerOrb.attract(o);
      }
      break;
     case SPRING:
        centerOrb.display();
        for (Orb o : orbList) {
          centerOrb.attractSpring(o);
          stroke(0);
          line(centerOrb.x, centerOrb.y, o.x, o.y);
          noStroke();
        }
        break;
  }
  
  fill(0);
  text(frameRate, 20, 20);
  text(orbList.size(), 20, 40);
  text(modeNames[MODE], 20, 60);
}

void keyPressed() {
  switch (key) {
    case ' ':
      if (++MODE >= modeC) { //DO YOU LIKE MY CODE MR.K!!!!!!!!
        MODE = 0;
      }
      break;
    case 'b':
      drawBackground = !drawBackground;
      break;
    case 8: //backspace is 8
      orbList = new ArrayList(); //frees the arraylist from memory and assigns it a new one
      break;
     case 'g':
       MODE = BOUNCE;
       break;
  }
}
