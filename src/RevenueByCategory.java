import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RevenueByCategory {
    public static class Product {
        private String category;
        public Product(String category) { this.category = category; }
        public String getCategory() { return category; }
    }

    public static class OrderItem {
        private Product product;
        private double value;
        public OrderItem(Product product, double value) {
            this.product = product;
            this.value = value;
        }
        public Product getProduct() { return product; }
        public double getValue() { return value; }
    }

    public static class Order {
        private String status;
        private List<OrderItem> items;
        public Order(String status, List<OrderItem> items) {
            this.status = status;
            this.items = items;
        }
        public String getStatus() { return status; }
        public List<OrderItem> getItems() { return items; }
    }

    public Map<String, Double> revenueByCategory(List<Order> orders) {
        return orders.stream()
                .filter(order -> !"CANCELLED".equals(order.getStatus()))
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getProduct().getCategory(),
                        Collectors.summingDouble(OrderItem::getValue)
                ));
    }
}