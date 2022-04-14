ArrayList<Orb> orbList;
void setup() {
  size(1000, 700);
  orbList = new ArrayList<Orb>();
}
void mouseClicked() {
  //add a new Orb to the orbList, constructed as follows:
  //The x and y positions are the same as the mouse
  //the radius should be between in the range [20.0,70.0)
  //the xSpeed and ySpeed should be in the range [-3.0,3.0)
}
void draw() {
  background(255);
  for (Orb o : orbList) {
    o.move();
    o.display();
  }
  fill(0);
  text(frameRate,20,20);
  text(orbList.size(),20,40);
}

public class Orb{
  float x,y;
  float xSpeed,ySpeed;
  float radius;
  color c;

  public Orb(float x_,float y_,float xSpeed_, float ySpeed_, float radius_ ){
    x = x_;
    y = y_;
    xSpeed = xSpeed_;
    ySpeed = ySpeed_;
    radius = radius_;
    //random color... why not.
    c = color(random(255),random(255),random(255));
  }


  void display(){
    //Part 1:
    //draw a ellipse at the x,y position, with the correct radius.
    //make sure it is the correct color
    //make sure you read the parameters of ellipse, so that you have the correct size.
    //radius is NOT one of the parameters of ellipse by default.
  }

  void move(){
    //PART 2
    //change the x based on the xSpeed
    //change the y based on the ySpeed

    //PART 3
    //Change the speed when you collide with the end of the screen (all 4 sides)

    //Part 4
    //Add a small adjustment for gravity. Gravity is a ySpeed acceleration...
    //You don't need a variable for this if every object experiences the same
    //gravitational constant (find the value that looks nice experimentally, 9.8 will not work well).

  }
}
