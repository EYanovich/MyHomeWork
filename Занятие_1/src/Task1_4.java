import java.util.Arrays;

public class Task1_4 {

    public static void main(String[] args) {


        int ar[] = new int[]{1, 0, 0, 2, 0, 6, 2, 0};

        System.out.println(Arrays.toString(ar));
        int arSize = ar.length;
        int sum = 0;
        for (int i = 0; i <= arSize - 1; i++) {
            if (ar[i] == 0)
                sum += 1;
        }
        if (sum == 0)
            System.out.println("Нулевых элементов в массиве нет.");
        else
            System.out.println(sum);
    }
}
