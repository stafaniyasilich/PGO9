import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopCustomers {
    public static class Customer {
        private String name;
        public Customer(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static class Order {
        private Customer customer;
        private String status;
        private double totalValue;
        public Order(Customer customer, String status, double totalValue) {
            this.customer = customer;
            this.status = status;
            this.totalValue = totalValue;
        }
        public Customer getCustomer() { return customer; }
        public String getStatus() { return status; }
        public double totalValue() { return totalValue; }
    }

    public Map<String, Double> topCustomers(List<Order> orders, int limit) {
        Map<String, Double> customerSpendingMap = orders.stream()
                .filter(order -> !"CANCELLED".equals(order.getStatus()))
                .collect(Collectors.groupingBy(
                        order -> order.getCustomer().getName(),
                        Collectors.summingDouble(Order::totalValue)
                ));

        return customerSpendingMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
}