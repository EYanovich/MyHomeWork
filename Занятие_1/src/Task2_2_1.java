import java.util.Arrays;
import java.util.Scanner;

public class Task2_2_1 {
    private static int arSize;

    public static int[] sort(int[] mass, int start, int end) {
        if (start >= end) {
            return mass;
        }
        int l = start;
        int r = end;
        int opora = l - (l - r) / 2;

        while (l < r) {
            while (l < opora && mass[l] <= mass[opora]) l++;
            while (r > opora && mass[r] >= mass[opora]) r--;
            if (l < r) {
                int temp = mass[l];
                mass[l] = mass[r];
                mass[r] = temp;
            }
            if (l == opora) {
                opora = r;
            } else if (r == opora) {
                opora = l;
            }
        }
        sort(mass, start, opora);
        sort(mass, opora + 1, end);
        return mass;
    }

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
        //быстрая сортировка
        System.out.println(Arrays.toString(ar));
        System.out.println(Arrays.toString(sort(ar, 0, ar.length - 1)));
    }


}
