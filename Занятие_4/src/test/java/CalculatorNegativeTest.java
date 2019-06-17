import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CalculatorNegativeTest {

    Calculator calculator = new Calculator();

    @Test
    void sumNegativeTest() {
        double num1 = 999999999;
        double num2 = 100000000;
        double expectedResult = 1.099999999E9;
        Assertions.assertEquals(expectedResult, calculator.sum(num1, num2));
    }

    @Test
    void divisionNegativeTest() {
        long num1 = 50;
        long num2 = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.division(num1, num2);
        });
    }

    @Test
    void squareOfNumberNegativeTest() {
        int num1 = 99999;
        int expectedResult = 1409865409;
        Assertions.assertEquals(expectedResult, calculator.squareOfNumber(num1));
    }
}