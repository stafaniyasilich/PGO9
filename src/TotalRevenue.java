import java.util.List;

public class TotalRevenue {
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

    public double totalRevenue(List<Order> orders) {
        return orders.stream()
                .filter(order -> !"CANCELLED".equals(order.getStatus()))
                .mapToDouble(Order::totalValue)
                .sum();
    }
}