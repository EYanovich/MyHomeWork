import java.util.Arrays;
import java.util.Scanner;

public class Task2_2_2 {
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

        // Сортировка методом Шелла
        System.out.println(Arrays.toString(ar));
        int s = ar.length / 2;
        while (s > 0) {
            for (int i = 0; i < (ar.length - s); i++) {
                int k = i;
                while (k >= 0 && ar[k] > ar[k + s]) {
                    int temp = ar[k];
                    ar[k] = ar[k + s];
                    ar[k + s] = temp;
                    k--;
                }
            }
            s = s / 2;
        }
        System.out.println(Arrays.toString(ar));
    }
}
