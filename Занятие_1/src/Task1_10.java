import java.util.Arrays;
import java.util.Scanner;

public class Task1_10 {
    private static int arSize;

    public static void main(String[] args) {
        System.out.print("Введите размер массива:");
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        try {
            arSize = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Введено не целое число!");
        }

        int ar[] = new int[arSize];
        int j = 0;
        while (j <= arSize - 1) {
            ar[j] = (int) (Math.random() * (100 - 0) + 0);
            j++;
        }

        System.out.println(Arrays.toString(ar));
        boolean flag = true;
        for (int i = 1; i < ar.length; i++) {
            if (ar[i] <= ar[i - 1]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("Массив является возрастающей последовательностью");
        } else {
            System.out.println("Массив не является возрастающей последовательностью");
        }
    }
}
