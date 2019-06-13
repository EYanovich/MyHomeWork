public class Task0_1 {
    public static void main(String[] args) {
        int c = 5;
        int i = 0;
        c = c + ++i;
        System.out.println(c);
        // ++i В первую очередь выполняется i = i + 1, затем с = с + i;

        c = 5;
        i = 0;
        c = c + i++;
        System.out.println(c);
        // i++ В первую очередь выполняется с = с + i, затем i = i + 1;
    }
}
