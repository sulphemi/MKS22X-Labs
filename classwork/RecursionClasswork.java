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
    return splitArray(nums, 0, 0, 0);
  }

  public static boolean splitArray(int[] nums, int pointer, int sum1, int sum2) {
    //base case
    if (pointer >= nums.length) {
      return sum1 == sum2;
    }

    return splitArray(nums, pointer + 1, sum1 + nums[pointer], sum2) || splitArray(nums, pointer + 1, sum1, sum2 + nums[pointer]);
  }

  public static void main(String[] args) {
    //char[] letters = {'a', 'b', 'c'};
    //printNoDoubleLetterWords(Integer.parseInt(args[0]), letters);

    int[] arr = new int[args.length];

    for (int i = 0; i < args.length; i++) {
      arr[i] = Integer.parseInt(args[i]);
    }

    System.out.println(splitArray(arr));
  }
}
