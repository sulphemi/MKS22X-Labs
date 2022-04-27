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
    c = color(random(255), random(255), random(255), random(155) + 100);
  }


  void display() {
    //Part 1:
    //draw a ellipse at the x,y position, with the correct radius.
    //make sure it is the correct color
    //make sure you read the parameters of ellipse, so that you have the correct size.
    //radius is NOT one of the parameters of ellipse by default.
    //drawStick();
    fill(c);
    ellipse(x, y, radius * 2, radius * 2);
  }

  void move() {
    switch (MODE) {
      case BOUNCE:
        bounceOnEdge();
        break;
    }
    
    if (applyGravity) {
      applyGravity();
    }
    
    x += xSpeed;
    y += ySpeed;
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
    stroke(c);
    line(x, y, x + xSpeed * 5, y + ySpeed * 5);
    noStroke();
  }
  
  void bounceOnEdge() {
    //top edge
    if (y - radius < 0) {
      ySpeed = Math.abs(ySpeed);
    }
    
    //bottom edge
    if (y + radius > height) {
      ySpeed = - Math.abs(ySpeed);
    }

    //left edge
    if (x - radius < 0) {
      xSpeed = Math.abs(xSpeed);
    }
    
    //right edge
    if (x + radius > width) {
      xSpeed = - Math.abs(xSpeed);
    }
  }
  
  void applyGravity() {
    final float gravity = .20;
    ySpeed += gravity;
  }
  
  void attractSpring(Orb other) {
    float d = dist(x, y, other.x, other.y);
    float force = SPRING_CONSTANT * (d - SPRING_LENGTH);
    
    other.xSpeed += force * (-(other.x - x) / d);
    other.xSpeed *= SPRING_DAMPEN;
    
    other.ySpeed += force * (-(other.y - y) / d);
    other.ySpeed *= SPRING_DAMPEN;
  }
}
