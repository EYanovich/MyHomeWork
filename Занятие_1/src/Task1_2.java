import java.util.Scanner;

public class Task1_2 {
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

        long num = 1;
        String str1 = "";
        for (int i = 0; i <= arSize - 1; i++) {
            if (i == arSize - 1)
                str1 = str1 + (ar[i]);
            else {
                str1 = str1 + (ar[i] + " * ");
            }
            num = num * ar[i];
        }
        System.out.println(str1 + "=" + num);

    }
}
