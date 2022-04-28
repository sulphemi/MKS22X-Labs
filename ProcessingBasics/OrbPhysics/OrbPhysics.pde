import java.awt.Desktop;
import java.net.URI;

ArrayList<Orb> orbList;
Orb centerOrb;
int MODE;
boolean drawBackground;

static final int BOUNCE = 0;
static final int ORBIT = 1;
static final int SPRING = 2;
static final int CHAIN = 3;
static final String[] modeNames = {"BOUNCE", "ORBIT", "SPRING", "CHAIN"};

static final float SPRING_CONSTANT = 0.015;
static final float SPRING_LENGTH = 100;
static final float SPRING_DAMPEN = .995;

boolean applyGravity;
String lastAction = "init"; //im bri'ish innit
int timeSinceLastAction = 0;

void setup() {
  size(1000, 800);
  orbList = new ArrayList<Orb>();
  noStroke(); //prettier this way
  
  centerOrb = new Orb(width / 2, height / 2, 0, 0, 10);
  MODE = BOUNCE;
  drawBackground = true;
  applyGravity = true;
}
void mouseClicked() {
  // Modify mouseClicked to add a new Orb located at the mouse position such that:
  // the new Orb should have a radius of 20, with an initial xspeed(dx) of 5, and a yspeed(dy) of 0
  
  orbList.add(new Orb(mouseX, mouseY, 5, 0, 20));
  updateAction("add orb @" + mouseX + ", " + mouseY);
  
  if (rickroll == false && mouseX > width - 110 && mouseY < 30) {
    rickroll = true;
    rick = loadImage("astley.png");
    astley = loadImage("face.png");
  }
}
void draw() {
  if (drawBackground) {background(255);}
  if (rickroll) {
    rickroll();
  }
  
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
          drawLine(centerOrb, o);
        }
        break;
      case CHAIN:
        for (int i = 0; i < orbList.size() - 1; i++) {
          Orb a = orbList.get(i);
          Orb b = orbList.get(i + 1);
          
          a.attractSpring(b);
          b.attractSpring(a);
          
          drawLine(a, b);
        }
        break;
  }
  
  if (! drawBackground) {
    fill(255);
    rect(0, 0, 120, 100);
  }
    
  fill(0);
  text(frameRate, 20, 20);
  text(orbList.size(), 20, 40);
  text(modeNames[MODE], 20, 60);
  text("GRAVITY " + (applyGravity ? "ON" : "OFF"), 20, 80);
  
  text("click me!!!", width - 100, 20);
  
  timeSinceLastAction++;
  fill(timeSinceLastAction * 10);
  text(lastAction, 20, height - 100);
}

void keyPressed() {
  switch (key) {
    case ' ':
      if (++MODE >= modeNames.length) { //DO YOU LIKE MY CODE MR.K!!!!!!!!
        MODE = 0;
      }
      updateAction("change mode to " + modeNames[MODE]);
      break;
    case 'b':
      drawBackground = !drawBackground;
      updateAction("toggle background");
      break;
    case 8: //backspace is 8
      orbList = new ArrayList(); //frees the arraylist from memory and assigns it a new one
      updateAction("delete all orbs");
      break;
     case 'g':
       applyGravity = ! applyGravity;
       updateAction("toggle gravity");
       break;
  }
}

void drawLine(Orb a, Orb b) {
  stroke(0, 0, 0, 120);
  line(a.x, a.y, b.x, b.y);
  noStroke();
}

void updateAction(String action) {
  lastAction = action;
  timeSinceLastAction = 0;
}

/////////

boolean rickroll;
PImage rick;
PImage astley;
int sunSize = 30;
int astleyY = height;
void rickroll() {
  //the following code is partially sampled from khanacademy
  noStroke();
  // the beautiful blue sky
  background(82, 222, 240);
 // The sun, a little circle on the horizon
  fill(255, 204, 0);
  ellipse(width / 2, height * 3 / 4, sunSize, sunSize);

  image(rick, width / 2 - 995, astleyY); //the image is 995px wide

  // The land, blocking half of the sun
  fill(76, 168, 67);
  rect(0, height * 3 / 4, width, height);

  if (sunSize < Math.max(width, height)) {
    sunSize++;
  }
  if (astleyY > height / 2) {
    astleyY--;
  }
}
