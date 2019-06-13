import java.util.Scanner;

public class Task1_1 {
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
            ar[j] = (int) (Math.random() * 100);
            j++;
        }

        for (int i = 0; i <= arSize - 1; i++) {
            System.out.println(ar[i]);
        }

        for (int i = arSize - 1; i >= 0; i--) {
            System.out.println(ar[i]);
        }

    }
}
