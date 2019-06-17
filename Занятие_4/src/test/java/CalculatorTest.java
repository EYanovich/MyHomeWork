import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void sumTest() {
        double num1 = 15.3;
        double num2 = 12.8;
        double expectedResult = 28.1;
        Assertions.assertEquals(expectedResult, calculator.sum(num1,num2));
    }

    @Test
    void divisionTest() {
        long num1 = 150;
        long num2 = 3;
        long expectedResult = 50;
        Assertions.assertEquals(expectedResult, calculator.division(num1,num2));
    }

    @Test
    void sqrtTest() {
        double num = 36;
        double expectedResult = 6;
        Assertions.assertEquals(expectedResult, calculator.sqrt(num));
    }

    @Test
    void squareOfNumberTest() {
        int num1 = 6;
        int expectedResult = 36;
        Assertions.assertEquals(expectedResult, calculator.squareOfNumber(num1));
    }

    @Test
    void multiplicationTest() {
        double num1 = 25;
        double num2 = 3;
        double expectedResult = 75;
        Assertions.assertEquals(expectedResult, calculator.multiplication(num1,num2));
    }
}