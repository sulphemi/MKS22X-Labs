import java.util.Random;

int x,y;
int MODE;

void setup() {
         size(800, 800);
         MODE = 1;
         x = width/2;
         y = height/2;
}
void draw() {
         background(255);
         x = change(x);
         y = change(y);
         avatar(x,y);
}
int change(int value){
  Random RNGesus = new Random();
  /**
   mode 1: return a random location on the screen.
   mode 2: change value by +1, 0, or -1 randomly
   mode 3: change value by +1 , but if it goes past the end of the screen ,
         wrap back around to the other end of the screen.
  */

  switch(MODE){
   case 1:
     return RNGesus.nextInt(width + 1);
   case 2:
     return RNGesus.nextInt(3) - 2;
   case 3:
     return ++value < width ? value : value - 1;
   default:
     return width/2;
  }
}

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
