public class Example {
    public static void main(String[] args) {
        Student student = new Student();
        int age = 21;
        student.setAge(age);

        int[] massReit = {5, 8, 9, 6, 4};
        student.avgRaiting(massReit);
        System.out.println(student.avgRaiting(massReit));

        String faculty = "ФКП";
        student.setFaculty(faculty);

        Methods methods = new Methods();
        int numMin = 4;
        int numMax = 9;
        System.out.println(methods.minNum(numMin, numMax));

        int num = 8;
        if (methods.checkNum(num) == true) {
            System.out.println(num + " - четное число");
        } else {
            System.out.println(num + " - нечетное число");
        }

        int numSquare = 5;
        System.out.println(methods.squareNum(numSquare));

        int numX3 = 6;
        System.out.println(methods.powNum(numX3));
    }
}
