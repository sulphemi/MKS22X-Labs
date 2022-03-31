import java.util.Stack;

public class Calculator {
  public static double eval(String expression) {
    Stack<Double> Deck = new Stack<Double>();
    String[] arrayified = expression.split(" ");

    for (String thingy : arrayified) {
      switch (thingy) {
        case "+":
          if (Deck.size() < 2) {throw new IllegalArgumentException("too many operators!");}
          double Jack = Deck.pop();
          double Jill = Deck.pop();
          Deck.add(Jack + Jill);
          break;
        case "-":
          if (Deck.size() < 2) {throw new IllegalArgumentException("trop beaucoup d'operators!");}
          double Romeo = Deck.pop();
          double Juliet = Deck.pop();
          Deck.add(Romeo - Juliet); //the irony
          break;
        case "*":
          if (Deck.size() < 2) {throw new IllegalArgumentException("mucho operators!");}
          double Bonnie = Deck.pop();
          double Clyde = Deck.pop();
          Deck.add(Bonnie * Clyde);
          break;
        case "/":
          if (Deck.size() < 2) {throw new IllegalArgumentException("tai duo operators!");}
          double Jessie = Deck.pop();
          double James = Deck.pop();
          Deck.add(Jessie / James);
          break;
        default:
          double Card = 727; //default value, will be overwritten
          try {
            Card = Double.parseDouble(thingy);
          } catch (NumberFormatException E) {
            throw new IllegalArgumentException("\"" + thingy + "\"? thats a weird looking number never seen it ever in my life");
          }
          Deck.add(Card);
          break;
      }
    }

    if (Deck.size() != 1) {throw new IllegalArgumentException("too little operands!");}
    return Deck.peek();
  }
}
