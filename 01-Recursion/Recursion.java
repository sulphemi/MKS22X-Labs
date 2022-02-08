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

  //this method takes negatives
  public static double sqrt(double n) {
    //yell at user for inputting negative
    if (n < 0) {
      throw new IllegalArgumentException("bwahahahahahaaa did you actually just put a *negative* number in a *sqrt* function??? this isnt precalc stop doing that");
    }

    return sqrt(n, n / 2);
  }

  //precondition: n > 0
  public static double sqrt(double n, double guess) {
    //check for accuracy
    //the target percent error is 0.01% or 0.0001
    if (Math.abs((guess * guess - n) / n) < 0.0001) {
      return guess;
    }

    return sqrt(n, (n / guess + guess) / 2.0);
  }

  /*
  *@param length how long the words must be
  *param word the variable to store the partial solution (should start at "")
  *@return the number of words that have no adjacent matching letters using the letters a-z.
  *Repetition allowed except when letters are adjacent.
  */
  public static long countNoDoubleLetterWords(int length, String word) {
    //Hint: not a wrapper method, but you must call it starting with "" as your word.
    long count = 0;

    //base
    if (length == word.length()) {
      return 1;
    }

    for (char letter = 'a'; letter <= 'z'; letter++) {
      if (! word.endsWith("" + letter)) {
        count += countNoDoubleLetterWords(length, word + letter);
      }
    }

    return count;
  }


  /*
  *@param n any non-negative value
  *@return the nth term of the fibonacci sequence. 0, 1, 1, 2, 3, 5 etc.
  */
  public static int fibIter(int n, int f1, int f2) {
    //DO NOT call fibIter more than once

    //base case
    if (n == 0) {
      return f1;
    }

    //iterate
    //fibIter(n - 1, first term, second term)
    return fibIter(n - 1, f2, f1 + f2);
  }


  //returns if partial sum is possible
  public boolean partialSum(int start, int[] arr, int target) {
    //base case
    if ()

    
  }



  /*** MAIN ***/
  public static void main(String[] args) {
    //char[] letters = {'a', 'b', 'c'};
    //printNoDoubleLetterWords(Integer.parseInt(args[0]), letters);

    for (int i = 0; i <= 10; i++) {
      System.out.println(fibIter(i, 0, 1));
    }
  }
}
