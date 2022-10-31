import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Person> people = new ArrayList<>();

        people.add(new Guest(true, 10));
        people.add(new Guest(false, 10));
        people.add(new Vendor(4, 1));
        people.add(new Guest(true, 10));
        people.add(new Vendor(9, 0));

        getPrice(people, 12.00);
    }

    public static void getPrice(ArrayList<Person> people, double tip){

        for (Person person : people){
            if (person.getClass().equals(Vendor.class)){
                System.out.println("Price: " + ((Vendor) person).calculatePayment(tip));
            }
        }
    }
}