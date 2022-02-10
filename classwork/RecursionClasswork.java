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

  //is it possible to choose a group of some of the ints + all 6's, beginning at the start index, such that the group sums to the given target
  public static boolean groupSum6(int start, int[] arr, int target, int sum) {
    //base case
    //can't stop early because all 6's have to be included
    if (start == arr.length) {
      //means we reached the end
      return sum == target;
    }

    if (arr[start] == 6) { //check if current element is 6
      return groupSum6(start + 1, arr, target, sum + arr[start]);
    } else {
      return (
        groupSum6(start + 1, arr, target, sum + arr[start]) ||     //with current index
        groupSum6(start + 1, arr, target, sum)                     //without current index
        );
    }
  }

  public static void main(String[] args) {
    //char[] letters = {'a', 'b', 'c'};
    //printNoDoubleLetterWords(Integer.parseInt(args[0]), letters);

    int[] arr = new int[args.length];

    for (int i = 0; i < args.length; i++) {
      arr[i] = Integer.parseInt(args[i]);
    }

    System.out.println(groupSum6(0, arr, 10, 0));
  }
}
