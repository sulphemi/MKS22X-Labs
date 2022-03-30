import java.util.Stack;

public class Calc {
  public static void main(String[] args) {
    //String input = "3 4 +";
    //String[] Potato = input.split();

    String[] Potato = args;

    Stack<Double> Deck = new Stack<Double>();

    for (String thingy : Potato) {
      switch (thingy) {
        case "+":
          double Jack = Deck.pop();
          double Jill = Deck.pop();
          Deck.add(Jack + Jill);
          break;
        case "-":
          double Romeo = Deck.pop();
          double Juliet = Deck.pop();
          Deck.add(Romeo - Juliet); //the irony
          break;
        case "*":
          double Bonnie = Deck.pop();
          double Clyde = Deck.pop();
          Deck.add(Bonnie * Clyde);
          break;
        case "/":
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

    System.out.println(Deck);
    System.out.println(Deck.peek());
  }
}
