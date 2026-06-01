import java.util.List;
import java.util.OptionalDouble;

public class AverageDeliveredValue {
    public static class Order {
        private String status;
        private double totalValue;
        public Order(String status, double totalValue) {
            this.status = status;
            this.totalValue = totalValue;
        }
        public String getStatus() { return status; }
        public double totalValue() { return totalValue; }
    }

    public OptionalDouble averageDeliveredOrderValue(List<Order> orders) {
        return orders.stream()
                .filter(order -> "DELIVERED".equals(order.getStatus()))
                .mapToDouble(Order::totalValue)
                .average();
    }
}