/*Print all words that are made of the letters a-e inclusive.
*@param length : the length of the words that are to be printed
*/
public static void printAllWords(int length){
  printAllWords(length,"");
}

/*Print all words that are made of the letters a-e inclusive.
*@param length : either how many more letters or the total length depending on implementation
*@param word   : the partial word so far.
*/
public static void printAllWords(int length,String word){
  //WRITE THIS METHOD
}

public static void MakeWords(int n, String word) {
  if (n == 0) {
    System.out.println(word);
    return;
  }

  for (char letter = 'a'; letter <= 'c'; letter++) {
    MakeWords(n - 1, word + letter);
  }
}

public static void MakeWords(int n) {
  MakeWords(n, "");
}

public static void main(String[] args) {
  MakeWords(Integer.parseInt(args[0]));
}
