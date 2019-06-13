import java.util.Arrays;
import java.util.Scanner;

public class Task1_11 {
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
        int arNew[] = new int[arSize];
        int j = 0;
        while (j <= arSize - 1) {
            ar[j] = (int) (Math.random() * (100 - 0) + 0);
            j++;
        }

        System.out.println(Arrays.toString(ar));

        for (int i = 0; i < ar.length; i++) {
            if (i == 0)
                arNew[i] = ar[i + 1] / 2;
            else if (i != ar.length - 1)
                arNew[i] = (ar[i - 1] + ar[i + 1]) / 2;
            else
                arNew[i] = ar[i - 1] / 2;
        }
        System.out.println(Arrays.toString(arNew));
    }
}
