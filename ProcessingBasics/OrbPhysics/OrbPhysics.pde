ArrayList<Orb> orbList;
Orb centerOrb;
int MODE;
boolean drawBackground;

static final int GRAVITY = 0;
static final int ORBIT = 1;

void setup() {
  size(1000, 800);
  orbList = new ArrayList<Orb>();
  noStroke(); //prettier this way
  
  centerOrb = new Orb(width / 2, height / 2, 0, 0, 10);
  MODE = GRAVITY;
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
  
  //note to self use a switch statement when implementing SPRING mode
  if (MODE == ORBIT) {
    centerOrb.display();
    for (Orb o : orbList) {
      centerOrb.attract(o);
    }
  }
  
  fill(0);
  text(frameRate, 20, 20);
  text(orbList.size(), 20, 40);
}

void keyPressed() {
  switch (key) {
    case ' ':
      if (++MODE >= 2) {
        MODE = 0;
      }
      break;
    case 'b':
      drawBackground = !drawBackground;
      break;
    case 8: //backspace is 8
      orbList = new ArrayList(); //frees the arraylist from memory and assigns it a new one
      break;
  }
}
