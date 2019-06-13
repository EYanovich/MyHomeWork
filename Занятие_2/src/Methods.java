public class Methods {
    public int minNum(int num1, int num2) {
        int min = 0;
        if (num1 < num2) {
            min = num1;
        } else {
            min = num2;
        }
        return min;
    }

    public boolean checkNum(int numIn) {
        boolean checkNum = true;
        if (numIn % 2 != 0) {
            checkNum = false;
        }
        return checkNum;
    }

    public int squareNum(int num) {
        num = num * num;
        return num;
    }

    public double powNum(double num) {
        double exponent = 3;
        num = Math.pow(num, exponent);
        return num;
    }
}
