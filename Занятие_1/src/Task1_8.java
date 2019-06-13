import java.util.Arrays;
import java.util.Scanner;

public class Task1_8 {
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
        int min = ar[0];
        int max = ar[0];
        for (int i = 0; i <= arSize - 1; i++) {
            if (min > ar[i])
                min = ar[i];
            if (max < ar[i])
                max = ar[i];
        }
        System.out.println("Минимальное значение в массиве: " + min);
        System.out.println("Максимальное значение в массиве: " + max);
    }
}
