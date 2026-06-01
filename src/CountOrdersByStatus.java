import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountOrdersByStatus {
    public static class Order {
        private String status;
        public Order(String status) { this.status = status; }
        public String getStatus() { return status; }
    }

    public Map<String, Long> countByStatus(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getStatus,
                        Collectors.counting()
                ));
    }
}