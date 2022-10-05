import java.util.*;

public class ExpressionEvaluation {

  public static double calculate(String expression) {

    List<String> postfix = convertToPostFix(expression);
    Stack<String> output = new Stack<>();

    for (String token : postfix) {
      if (NumberUtils.isNumeric(token) == true) {
        output.push(token);
      }
      else {
        double value1 = Double.parseDouble(output.pop());
        double value2 = Double.parseDouble(output.pop());
        double sum = calculate(value1, value2, token);
        output.push(String.valueOf(sum));
      }
    }

    return Double.parseDouble(output.pop());
  }

  private static List<String> convertToPostFix(String expression) {

    Map<String, Integer> precedences = new HashMap<>();
    precedences.put("+", 1);
    precedences.put("-", 1);
    precedences.put("*", 2);
    precedences.put("/", 2);

    List<String> tokens = tokenise(expression);

    Stack<String> outputStack = new Stack<>();
    Stack<String> operatorsStack = new Stack<>();

    for (String token : tokens) {
      if (NumberUtils.isNumeric(token) == true) {
        outputStack.push(token);
      }
      else if (precedences.containsKey(token) == true) {
        while (operatorsStack.isEmpty() == false && precedences.get(token) <= precedences.get(operatorsStack.peek())) {
          outputStack.push(operatorsStack.pop());
        }
        operatorsStack.push(token);
      }
    }

    while (operatorsStack.isEmpty() == false) {
      outputStack.push(operatorsStack.pop());
    }

    return outputStack;
  }

  private static List<String> tokenise(String expression) {
    expression = expression.replace(" ", "");

    List<String> tokens = new ArrayList<>();

    StringBuilder sb = new StringBuilder();

    for (char c : expression.toCharArray()) {
      boolean isDigit = Character.isDigit(c) || c == '.';

      if (isDigit == true) {
        sb.append(c);
      }
      else {
        tokens.add(sb.toString());
        tokens.add(String.valueOf(c));
        sb = new StringBuilder();
      }
    }

    if (sb.isEmpty() == false) {
      tokens.add(sb.toString());
    }
    return tokens;
  }

  private static double calculate(double value1, double value2, String operator) {
    return switch (operator) {
      case "+" -> value2 + value1;
      case "-" -> value2 - value1;
      case "*" -> value2 * value1;
      case "/" -> value2 / value1;
      default -> 0;
    };
  }
}
