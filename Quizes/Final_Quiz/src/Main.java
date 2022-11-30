import Pets.Cat;
import Pets.Pet;
import Pets.Zuko;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("----- Dogs -----");
        List<Dog> dogs = List.of(
                new Dog(1, 2),
                new Dog(2, 3),
                new Dog(4, 7),
                new Dog(9, 12),
                new Dog(5, 9)
        );

        System.out.println(
                dogs.stream().filter(dog -> dog.getNumberOfTricks() < 9).peek(
                        dog -> dog.setAge(dog.getAge() + 1)
                ).toList()
        );
        System.out.println("----- Pets -----");

        Pet pet = new Cat();
        Pet pet2 = new Zuko();
        pet2.speak(pet);

        System.out.println("----- Food -----");

        Food scone = new Scone("@", 7, 3);
        Food waffle = new Waffle("#", 3, 5, 4);

        scone.display();
        System.out.println();

        waffle.display();
        System.out.println();

        scone.boost();
        System.out.println();

        waffle.boost();
        System.out.println();
        waffle.boost();
        System.out.println();

        System.out.println(scone);
        System.out.println(waffle);

        System.out.println("----------------");
    }
}
