import java.util.Arrays;
import java.util.Scanner;

public class Task1_7 {
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
            ar[j] = (int) (Math.random() * (10 - 1) + 1);
            j++;
        }

        System.out.println(Arrays.toString(ar));
        int c = 0;
        int q = arSize - 1;
        for (int i = 0; i <= q; i++) {
            c = ar[i];
            ar[i] = ar[q];
            ar[q] = c;
            q--;
        }
        System.out.println(Arrays.toString(ar));
    }
}
