import java.util.List;
import java.util.stream.Collectors;

public class ActiveOrder {
    public static class Order {
        private String id;
        private String status;

        public Order(String id, String status) {
            this.id = id;
            this.status = status;
        }
        public String getId() { return id; }
        public String getStatus() { return status; }
    }

    public List<String> activeOrderIds(List<Order> orders) {
        return orders.stream()
                .filter(order -> !"CANCELLED".equals(order.getStatus()))
                .map(Order::getId)
                .collect(Collectors.toList());
    }
}