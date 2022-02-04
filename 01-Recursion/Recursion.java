public class Recursion {
  /*Print all words that are made of the letters a-e inclusive.
  *@param length : the length of the words that are to be printed
  */
  public static void printAllWords(int length) {
    printAllWords(length,"");
  }

  /*Print all words that are made of the letters a-e inclusive.
  *@param length : either how many more letters or the total length depending on implementation
  *@param word   : the partial word so far.
  */
  public static void printAllWords(int n, String word) {
    if (n == 0) {
      System.out.println(word);
      return;
    }

    for (char letter = 'a'; letter <= 'e'; letter++) {
      printAllWords(n - 1, word + letter);
    }
  }

  /*Print all words that are made of the characters in the array of letters.
  *There may not be consecutive equal letters, so:
  *aax is not allowed, but axa is allowed.
  *@param length : the length of the words that are to be printed
  *@param letters: the letters you should be using,
  *@precondition: letters contains at least 2 characters, and has no duplicates.
  */
  public static void printNoDoubleLetterWords(int length,char[] letters) {
    printNoDoubleLetterWords(length,"",letters);
  }

  /*Print all words that are made of the characters in letters. There may not be consecutive equal letters,
  *aax is not allowed, but axa is allowed.
  *@param length : either how many more letters need to be
  *@param word   : the partial word so far.
  *@param letters: the letters you should be using
  */
  public static void printNoDoubleLetterWords(int length, String word, char[] letters) {
    if (length == 0) {
      System.out.println(word);
      return;
    }

    for (char letter : letters) {
      //check if adding letter makes double letter
      if (!(word.length() >= 1 && word.endsWith("" + letter))) {
        printNoDoubleLetterWords(length - 1, word + letter, letters);
      }
    }
  }


  public static String reverse(String s) {
    if (s.length() < 2) {
      return s;
    } else {
      //the string without the first character
      String headless = s.substring(1);
      char firstLetter = s.charAt(0);
      return reverse(headless) + firstLetter;
    }
  }


  public static double sqrt(double n) {
    return 0;
  }

  //note that this function does not take negatives
  public static double sqrt(double n, double guess) {
    //yell at user for inputting negative
    if (n < 0) {
      throw new IllegalArgumentException("bwahahahahahaaa did you actually just put a *negative* number in a *sqrt* function??? this isnt precalc stop doing that");
    }

    return 0;
  }

  /*** MAIN ***/
  public static void main(String[] args) {
    //char[] letters = {'a', 'b', 'c'};
    //printNoDoubleLetterWords(Integer.parseInt(args[0]), letters);

    System.out.println(reverse(args[0]));
  }
}
