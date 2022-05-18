public class Kernel {
  float[][] kernel;

  /**Constructor takes the kernel that will be applied to the image
  *This implementation only allows 3x3 kernels
  */
  public Kernel(float[][] init) {
    assert init.length == 3 && init[0].length == 3;
    kernel = init;
  }

  /**If part of the kernel is off of the image, return black, Otherwise
  *Calculate the convolution of r/g/b separately, and return that color\
  *if the calculation for any of the r,g,b values is outside the range
  *     0-255, then clamp it to that range (< 0 becomes 0, >255 becomes 255)
  */
  color calcNewColor(PImage img, int x, int y) {
    if (x == 0 || y == 0 || x == img.width - 1|| y == img.height - 1) {
      return 0;
    }
    
    int rsum = 0;
    int gsum = 0;
    int bsum = 0;
    color[][] matrix = {
      {
        img.get(x + 1, y - 1),
        img.get(x + 1, y),
        img.get(x + 1, y + 1)
      }, {
        img.get(x, y - 1),
        img.get(x, y),
        img.get(x, y + 1)
      }, {
        img.get(x - 1, y - 1),
        img.get(x - 1, y),
        img.get(x - 1, y + 1)
      }
    };
    
    for (int i = 0; i < matrix.length; i++) {
      for (int k = 0; k < matrix.length; k++) {
        rsum += red(matrix[i][k]) * kernel[k][i];
        gsum += green(matrix[i][k]) * kernel[k][i];
        bsum += blue(matrix[i][k]) * kernel[k][i];
      }
    }
    
    return color(rsum, gsum, bsum);
  }

  /**You must write this method that applies the kernel to the source,
    *and saves the data to the destination.*/
  void apply(PImage source, PImage destination) {
    for (int y = 0; y < source.height; y++) {
      for (int x = 0; x < source.width; x++) {
        destination.set(x, y, calcNewColor(source, x, y));
      }
    }
  }
}
