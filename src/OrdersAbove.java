import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersAbove {
    public static class Order {
        private String id;
        private double totalValue;

        public Order(String id, double totalValue) {
            this.id = id;
            this.totalValue = totalValue;
        }
        public String getId() { return id; }
        public double totalValue() { return totalValue; }
    }

    public List<Order> ordersAbove(List<Order> orders, double minValue) {
        return orders.stream()
                .filter(order -> order.totalValue() > minValue)
                .sorted(Comparator.comparingDouble(Order::totalValue).reversed())
                .collect(Collectors.toList());
    }
}