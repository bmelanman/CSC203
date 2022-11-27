import java.util.Comparator;

public class Car {

    private String brand;
    private int year;
    private int milage;

    public Car(String brand, int year, int milage) {
        this.brand = brand;
        this.year = year;
        this.milage = milage;
    }

    public String getBrand() {
        return brand;
    }

//    @Override
//    public int compareTo(Car otherCar) {
//        int result = String.CASE_INSENSITIVE_ORDER.compare(brand, otherCar.getBrand());
//
//        if(result != 0){
//            return
//        }
//
//        return result;
//    }
}
