import java.util.List;
import java.util.stream.Collectors;

public class ProductsFromActiveOrders {
    public static class Product {
        private String name;
        public Product(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static class OrderItem {
        private Product product;
        public OrderItem(Product product) { this.product = product; }
        public Product getProduct() { return product; }
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

    public List<String> soldProductNames(List<Order> orders) {
        return orders.stream()
                .filter(order -> !"CANCELLED".equals(order.getStatus()))
                .flatMap(order -> order.getItems().stream())
                .map(orderItem -> orderItem.getProduct().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}