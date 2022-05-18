String[] names = new String[]{
    "Identity", "Blur", "Sharpen",
    "Outline", "Left Sobel", "Right Sobel",
    "Top Sobel", "Emboss"
  };

Kernel[] kernels = new Kernel[] {
    new Kernel( new float[][] {
    {0, 0, 0},
    {0, 1, 0},
    {0, 0, 0}    }) ,
    new Kernel( new float[][] {
    {.111, .111, .111},
    {.111, .111, .111},
    {.111, .111, .111}    }) ,
    new Kernel( new float[][] {
    {0, -1, 0},
    {-1, 5, -1},
    {0, -1, 0}    }) ,
    new Kernel( new float[][] {
    {-1, -1, -1},
    {-1, 8, -1},
    {-1, -1, -1}    }) ,
    new Kernel( new float[][] {
    {1, 0, -1},
    {2, 0, -2},
    {1, 0, -1}    }) ,
    new Kernel( new float[][] {
    {-1, 0, 1},
    {-2, 0, 2},
    {-1, 0, 1}    }) ,
    new Kernel( new float[][] {
    {1, 2,  1},
    {0, 0, 0},
    {-1, -2, -1}    }),
    new Kernel( new float[][] {
    {-2, -1,  0},
    {-1, 1, 1},
    {0, 1, 2}    })
  };

int currentKernel = 0;
PImage car, output;

void setup() {
  size(1450,500);
  car = loadImage("redcar.png");
  output = car.copy();
}

void draw() {
  kernels[currentKernel].apply(car,output);
  image(car,0,0);
  image(output,car.width,0);
  fill(0);
  text(names[currentKernel], 20, 20);
}

void keyPressed() {
  if (++currentKernel > kernels.length - 1) {
    currentKernel = 0;
  }
}
