import java.util.Arrays;
import java.util.Scanner;

public class Task1_12 {
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
        int arNew[] = new int[arSize+2];
        int j = 0;
        while (j <= arSize - 1) {
            ar[j] = (int) (Math.random() * (100 - 0) + 0);
            j++;
        }

        System.out.println(Arrays.toString(ar));

        for (int i = 0; i < ar.length; i++) {
            arNew[i+2] = ar[i];
        }
        System.out.println(Arrays.toString(arNew));
    }
}
