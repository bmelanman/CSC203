import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public class Main {

    Car car1 = new Car("A", 2020, 20000);
    Car car2 = new Car("B", 2020, 20000);
    Car car3 = new Car("A", 2021, 1200);

    BiPredicate<Car, Car> lambda = (car1, car2) -> (car1.getBrand().compareTo(car2.getBrand()));

    Comparator<Car> myComp = (car) ->

}