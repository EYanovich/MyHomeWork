public class Student extends Person {
    int age;
    String faculty;
    double avgRaiting;
    int persNumber;

    public void setAge(int age) {
        age = this.age;
    }

    protected void setFaculty(String faculty) {
        faculty = this.faculty;
    }

    public void setName(String name) {
        name = name;
    }

    double avgRaiting(int[] massReit) {
        double avgRaiting = 0;
        for (int i = 0; i <= massReit.length - 1; i++) {
            avgRaiting = avgRaiting + massReit[i];
        }
        return avgRaiting / massReit.length;
    }

    private void generatePersNumber() {
        this.persNumber = (int) Math.random() * 100000;
    }

}
