public class Orb {
  float x, y;
  float xSpeed, ySpeed;
  float radius;
  color c;

  public Orb(float x_, float y_, float xSpeed_, float ySpeed_, float radius_ ) {
    x = x_;
    y = y_;
    xSpeed = xSpeed_;
    ySpeed = ySpeed_;
    radius = radius_;
    //random color... why not.
    c = color(random(255), random(255), random(255), random(255));
  }


  void display() {
    //Part 1:
    //draw a ellipse at the x,y position, with the correct radius.
    //make sure it is the correct color
    //make sure you read the parameters of ellipse, so that you have the correct size.
    //radius is NOT one of the parameters of ellipse by default.
    fill(c);
    ellipse(x, y, radius * 2, radius * 2);
  }

  void move() {
    //PART 2
    //change the x based on the xSpeed
    //change the y based on the ySpeed

    x += xSpeed;
    y += ySpeed;

    //PART 3
    //Change the speed when you collide with the end of the screen (all 4 sides)
    

    //Part 4
    //Add a small adjustment for gravity. Gravity is a ySpeed acceleration...
    //You don't need a variable for this if every object experiences the same
    //gravitational constant (find the value that looks nice experimentally, 9.8 will not work well)
  }
  
  boolean collidingWith(Orb other) {
    return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2)) < (this.radius + other.radius);
  }
  
  void collide() {
    xSpeed *= -1;
    x += xSpeed;
    
    ySpeed *= -1;
    y += ySpeed;
  }
  
  void attract(Orb other) {
    final float gConst = 20.0;
    float d = dist(x, y, other.x, other.y);
    other.xSpeed += gConst * (x - other.x) / Math.pow(d, 2);
    other.ySpeed += gConst * (y - other.y) / Math.pow(d, 2);
  }
  
  void drawStick() {
    stroke(0);
    line(x, y, x + xSpeed * 10, y + ySpeed * 10);
    noStroke();
  }
  
  void bounceOnEdge() {
     //if on bottom/top edge:

    if (y - radius < 0 || y + radius > height) {
      ySpeed *= -1;
      y += ySpeed;
    }

    //if on left/right edge:

    if (x - radius < 0 || x + radius > width) {
      xSpeed *= -1;
      x += xSpeed;
    }
  }
  
  void applyGravity() {
    final float gravity = .20;
    ySpeed += gravity;
  }
}
