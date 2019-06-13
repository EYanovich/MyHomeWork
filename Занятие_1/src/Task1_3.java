import java.util.Arrays;
import java.util.Scanner;

public class Task1_3 {
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
        // 1 способ
        for (int i = 2; i <= arSize - 1; i += 3) {
            ar[i] = ar[i] * 3;
        }
        System.out.println(Arrays.toString(ar));

        // 2 способ
        for (int i = 0; i <= arSize - 1; i++) {
            if ((i + 1) % 3 == 0 && i != 0)
                ar[i] = ar[i] * 3;
        }
        System.out.println(Arrays.toString(ar));
    }
}
