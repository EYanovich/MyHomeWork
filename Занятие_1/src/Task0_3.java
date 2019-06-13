public class Task0_3 {
    public static void main(String[] args) {
        //Вариант 1
        for (int i = 2; i<=20; i = i + 2)
        {
            System.out.println(i);
        }

        //Вариант 2
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
