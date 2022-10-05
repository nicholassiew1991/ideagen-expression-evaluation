import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ExpressionEvaluationTest {

  public static Stream<Arguments> testCalculate() {
    return Stream.of(
      Arguments.of("1 + 1", 2.0),
      Arguments.of("2 * 2", 4.0),
      Arguments.of("1 + 2 + 3", 6.0),
      Arguments.of("6 / 2", 3.0),
      Arguments.of("11 + 23", 34.0),
      Arguments.of("11.1 + 23", 34.1),
      Arguments.of("1 + 1 * 3", 4.0)
    );
  }

  @ParameterizedTest
  @MethodSource
  void testCalculate(String expression, double expected) {
    double actual = ExpressionEvaluation.calculate(expression);
    Assertions.assertEquals(expected, actual);
  }
}
