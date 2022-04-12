void avatar(int x, int y) {
  int[] xcor = {300, 258, 342, 342, 300, 258, 190, 294, 295, 406, 306, 305, 182, 264, 171, 171, 245, 265, 418, 337, 355, 355, 418, 429};
  int[] ycor = {58, 155, 154, 154, 246, 154, 126, 523, 273, 126, 273, 525, 164, 475, 184, 184, 466, 475, 165, 475, 464, 464, 165, 182};
  
  
  for (int i = 0; i < xcor.length; i++) {
    xcor[i] -= 300;
    xcor[i] += x;
  }
  
  for (int i = 0; i < ycor.length; i++) {
    ycor[i] -= 300;
    ycor[i] += y;
  }
  
  noStroke();
  fill(220, 147, 255);
  for (int i = 0; i < xcor.length; i += 3) {
    triangle(xcor[i], ycor[i], xcor[i + 1], ycor[i + 1], xcor[i + 2], ycor[i + 2]);
  }
}

int xp,yp;
void setup(){
  size(800,600);
  xp = 0;
  yp = 0;
}
void draw(){
  background(255);
  avatar(xp,yp);
  xp++;
  yp++;
  avatar(100,100);
  avatar(600,300);
}
