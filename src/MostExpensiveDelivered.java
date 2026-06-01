import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MostExpensiveDelivered {
    public static class Order {
        private String id;
        private String status;
        private double totalValue;
        public Order(String id, String status, double totalValue) {
            this.id = id;
            this.status = status;
            this.totalValue = totalValue;
        }
        public String getId() { return id; }
        public String getStatus() { return status; }
        public double totalValue() { return totalValue; }
    }

    public Optional<Order> mostExpensiveDeliveredOrder(List<Order> orders) {
        return orders.stream()
                .filter(order -> "DELIVERED".equals(order.getStatus()))
                .max(Comparator.comparingDouble(Order::totalValue));
    }
}