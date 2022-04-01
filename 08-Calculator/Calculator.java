import java.util.*;

public class Calculator {
  public static double eval(String expression) {
    Deque<Double> Deck = new ArrayDeque<Double>();
    String[] arrayified = expression.split(" ");

    for (int i = 0; i < arrayified.length; i++) {
      String thingy = arrayified[i];
      switch (thingy) {
        case "+":
          if (Deck.size() < 2) {throw new IllegalArgumentException("too few operands! (+ at index " + i + ")");}
          double Jack = Deck.pop();
          double Jill = Deck.pop();
          Deck.push(Jack + Jill);
          break;
        case "-":
          if (Deck.size() < 2) {throw new IllegalArgumentException("too few operands! (- at index " + i + ")");}
          double Romeo = Deck.pop();
          double Juliet = Deck.pop();
          Deck.push(Juliet - Romeo); //the irony
          break;
        case "*":
          if (Deck.size() < 2) {throw new IllegalArgumentException("too few operands! (* at index " + i + ")");}
          double Bonnie = Deck.pop();
          double Clyde = Deck.pop();
          Deck.push(Bonnie * Clyde);
          break;
        case "/":
          if (Deck.size() < 2) {throw new IllegalArgumentException("too few operands! (/ at index " + i + ")");}
          double Jessie = Deck.pop();
          double James = Deck.pop();
          Deck.push(James / Jessie);
          break;
        case "%":
          if (Deck.size() < 2) {throw new IllegalArgumentException("too few operands! (% at index " + i + ")");}
          double Solo = Deck.pop();
          double Chewie = Deck.pop();
          Deck.push(Chewie % Solo);
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

    if (Deck.size() != 1) {throw new IllegalArgumentException("too many operands!");}
    return Deck.peek();
  }

  public static void main(String[] args) {
    HashMap<String, Double> testcases = new HashMap<String, Double>();
    testcases.put("4 5 7 2 + - *", -16.0);
    testcases.put("3 4 + 2 * 7 /", 2.0);
    testcases.put("5 7 + 6 2 - *", 48.0);
    testcases.put("4 2 + 3 5 1 - * +", 18.0);
    testcases.put("11 3 - 4 + 2.5 *", 30.0);
    testcases.put("10 2.0 +", 12.0);
    testcases.put("8 2 + 99 9 - * 2 + 9 -", 893.0);
    testcases.put("1 2 3 4 5 + * - -", 26.0);
    testcases.put("3 4 %", 3.0);
    testcases.put("99 3 %", 0.0);
    testcases.put("99 3 3 % *", 0);

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

    testExceptions();
  }

  public static void testExceptions() {
    String[] testcases = {
      "4 2 3 5 1 - + * / -",
      "3 4 5",
      "* * *",
      "uwu",
      "1 1 1 +",
      "1 1 1 - * +",
      ""
    };

    for (String expression : testcases) {
      try {
        eval(expression);
        System.out.println("NO EXCEPTION: " + expression);
      } catch (IllegalArgumentException E) {
        System.out.println(E.toString() + ": " + expression);
      }
    }
  }
}
