import java.util.Arrays;

public class Task1_5 {
    public static void main(String[] args) {
        int ar[] = new int[]{1, 0, 7, 0, 8, 6, 0, 0};

        System.out.println(Arrays.toString(ar));
        int arSize = ar.length;
        boolean flag = false;
        for (int i = 0; i <= arSize - 1; i++) {
            if (ar[i] == 0) {
                System.out.println(i + 1);
                flag = true;
            }
        }
        if (flag == false)
            System.out.println("Нулевых элементов в массиве нет.");
    }
}
