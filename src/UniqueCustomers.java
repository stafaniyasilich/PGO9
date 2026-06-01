import java.util.List;
import java.util.stream.Collectors;

public class UniqueCustomers {
    public static class Customer {
        private String name;
        public Customer(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static class Order {
        private Customer customer;
        public Order(Customer customer) { this.customer = customer; }
        public Customer getCustomer() { return customer; }
    }

    public List<String> uniqueCustomerNames(List<Order> orders) {
        return orders.stream()
                .map(order -> order.getCustomer().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}