public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Animal animal = new Animal(4);

        Person person = new Person("Jane", 500.00, 4);

        Professor professor = new Professor(1000.00, true, "David", 5.00, 2);

        Student student = new Student(3.5, 5000.00, "John", 20.00, 2);

        System.out.println(animal.equals(person));
        System.out.println(person.equals(animal));
        System.out.println(professor.equals(person));
        System.out.println(animal.equals(student));
    }
}