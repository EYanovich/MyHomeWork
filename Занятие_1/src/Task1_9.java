import java.util.Arrays;
import java.util.Scanner;

public class Task1_9 {
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
        int idMin = 0;
        int idMax = 0;
        int min = ar[0];
        int max = ar[0];
        for (int i = 0; i <= arSize - 1; i++) {
            if (min > ar[i]) {
                idMin = i + 1;
                min = ar[i];
            }
            if (max < ar[i]) {
                idMax = i + 1;
                max = ar[i];
            }

        }
        System.out.println("ID минимального значения в массиве: " + idMin);
        System.out.println("ID максимального значения в массиве: " + idMax);
    }
}
