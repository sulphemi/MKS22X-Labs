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
    c = color(random(255), random(255), random(255));
  }


  void display() {
    //Part 1:
    //draw a ellipse at the x,y position, with the correct radius.
    //make sure it is the correct color
    //make sure you read the parameters of ellipse, so that you have the correct size.
    //radius is NOT one of the parameters of ellipse by default.
    fill(c);
    ellipse(x, y, radius, radius);
  }

  void move() {
    //PART 2
    //change the x based on the xSpeed
    //change the y based on the ySpeed

    x += xSpeed;
    y += ySpeed;

    //PART 3
    //Change the speed when you collide with the end of the screen (all 4 sides)

    //if on bottom/top edge:

    if (y < 0 || y > height) {
      ySpeed *= -1;
      y += ySpeed;
    }

    //if on left/right edge:

    if (x < 0 || x > width) {
      xSpeed *= -1;
      x += xSpeed;
    }

    //Part 4
    //Add a small adjustment for gravity. Gravity is a ySpeed acceleration...
    //You don't need a variable for this if every object experiences the same
    //gravitational constant (find the value that looks nice experimentally, 9.8 will not work well).
    final float gravity = 2.0;
    ySpeed += gravity;
  }
}
