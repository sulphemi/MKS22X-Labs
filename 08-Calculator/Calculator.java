import java.util.*;
import java.util.HashMap;

public class Calculator {
  public static double eval(String expression) {
    Deque<Double> Deck = new ArrayDeque<Double>();
    String[] arrayified = expression.split(" ");

    for (String thingy : arrayified) {
      switch (thingy) {
        case "+":
          if (Deck.size() < 2) {throw new IllegalArgumentException("too many operators!");}
          double Jack = Deck.pop();
          double Jill = Deck.pop();
          Deck.push(Jack + Jill);
          break;
        case "-":
          if (Deck.size() < 2) {throw new IllegalArgumentException("trop beaucoup d'operators!");}
          double Romeo = Deck.pop();
          double Juliet = Deck.pop();
          Deck.push(Juliet - Romeo); //the irony
          break;
        case "*":
          if (Deck.size() < 2) {throw new IllegalArgumentException("mucho operators!");}
          double Bonnie = Deck.pop();
          double Clyde = Deck.pop();
          Deck.push(Bonnie * Clyde);
          break;
        case "/":
          if (Deck.size() < 2) {throw new IllegalArgumentException("tai duo operators!");}
          double Jessie = Deck.pop();
          double James = Deck.pop();
          Deck.push(James / Jessie);
          break;
        default:
          double Card = 727; //default value, will be overwritten
          try {
            Card = Double.parseDouble(thingy);
          } catch (NumberFormatException E) {
            throw new IllegalArgumentException("\"" + thingy + "\"? thats a weird looking number never seen it ever in my life");
          }
          Deck.push(Card);
          break;
      }
    }

    if (Deck.size() != 1) {throw new IllegalArgumentException("too little operands!");}
    return Deck.peek();
  }

  public static void main(String[] args) {
    HashMap<String, Double> testcases = new HashMap<String, Double>();
    testcases.put("4 5 7 2 + - *", -16.0);
    testcases.put("3 4 + 2 * 7 /", 2.0);
    testcases.put("5 7 + 6 2 - *", 48.0);
    testcases.put("4 2 + 3 5 1 - * +", 18.0);

    for (String expression : testcases.keySet()) {
      double val = eval(expression);
      if (val == testcases.get(expression)) {
        System.out.println("PASSED: " + expression);
      } else {
        System.out.println("FAILED: " + expression);
        System.out.println("\tExpected: " + testcases.get(expression));
        System.out.println("\tGot: " + val);
      }
    }
  }
}
