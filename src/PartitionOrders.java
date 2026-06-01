import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionOrders {
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

    public Map<Boolean, List<Order>> partitionActiveOrdersByValue(List<Order> orders, double threshold) {
        return orders.stream()
                .filter(order -> !"CANCELLED".equals(order.getStatus()))
                .collect(Collectors.partitioningBy(
                        order -> order.totalValue() >= threshold
                ));
    }
}