import java.util.List;
import java.util.Map;

public class test {
    /**
     * Return the total number of iced coffees ordered by all of the customers.
     * 
     * @param ordersPerCustomer A map of customer names to to a List of all their
     *                          CoffeeOrders
     * @return the total number of iced coffees ordered
     */
    public static int getAverageSweetness(Map<String, List<CoffeeOrder>> ordersPerCustomer) {

        int sum = 0;
        int count = 0;

        for (Map.Entry<String, List<CoffeeOrder>> order : ordersPerCustomer.entrySet()) {

            for (CoffeeOrder coffee : order.getValue()) {

                sum += coffee.getSweetness();
                count++;
            }
        }

        return sum / count;
    }
}
