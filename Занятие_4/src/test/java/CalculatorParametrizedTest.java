import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorParametrizedTest {

    Calculator calculator = new Calculator();

    @DisplayName("Проверка метода sum")
    @ParameterizedTest()
    @CsvSource({
            "15.3, 12.8, 28.1",
            "2, 3, 5",
            "0, 16, 16"
    })
    void sumTest(double num1, double num2, double expectedResult) {
        Assertions.assertEquals(expectedResult, calculator.sum(num1, num2));
    }

    @DisplayName("Проверка метода division")
    @ParameterizedTest()
    @CsvSource({
            "150, 3, 50",
            "15, 3, 5",
            "100, 4, 25"
    })
    void divisionTest(long num1, long num2, long expectedResult) {
        Assertions.assertEquals(expectedResult, calculator.division(num1, num2));
    }

    @DisplayName("Проверка метода sqrt")
    @ParameterizedTest()
    @ValueSource(doubles = {36, 25, 81})
    void sqrtTest(double num) {
        Assertions.assertNotNull(calculator.sqrt(num));
    }

    @Disabled
    @Test
    void squareOfNumberTest() {
        int num1 = 6;
        int expectedResult = 35;
        Assertions.assertEquals(expectedResult, calculator.squareOfNumber(num1));
    }

    @Disabled("Тест не выполняфется")
    @Test
    void multiplicationTest() {
        double num1 = 25;
        double num2 = 3;
        double expectedResult = 75;
        Assertions.assertEquals(expectedResult, calculator.multiplication(num1, num2));
    }
}