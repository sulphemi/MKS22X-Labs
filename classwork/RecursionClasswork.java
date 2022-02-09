public class RecursionClasswork {
    //returns if partial sum is possible
  public static boolean partialSum(int start, int[] arr, int target, int sum) {
    //base case
    if (sum == target) {
      return true;
    }
    if (start == arr.length) {
      //means we reached the end without being able to partial sum thingy thing thing
      return false;
    }

    return (
      partialSum(start + 1, arr, target, sum + arr[start]) ||     //with current index
      partialSum(start + 1, arr, target, sum)                     //without current index
      );
  }

  //check if it is possible to split array into two sums
  public static boolean splitArray(int[] nums) {
    return splitArray(int[] nums, int pointer, int sum);
  }

  public static void main(String[] args) {
    //char[] letters = {'a', 'b', 'c'};
    //printNoDoubleLetterWords(Integer.parseInt(args[0]), letters);

    int[] arr = new int[args.length];

    for (int i = 0; i < args.length; i++) {
      arr[i] = Integer.parseInt(args[i]);
    }

    System.out.println(partialSum(0, arr, 10, 0));
  }
}
