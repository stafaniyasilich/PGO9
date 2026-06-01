import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {
        // ==========================================
        // Mock Data Initialization
        // ==========================================

        UniqueCustomers.Customer jan = new UniqueCustomers.Customer("Jan Nowak");
        UniqueCustomers.Customer anna = new UniqueCustomers.Customer("Anna Kowalska");
        UniqueCustomers.Customer tomasz = new UniqueCustomers.Customer("Tomasz Lewandowski");
        UniqueCustomers.Customer maria = new UniqueCustomers.Customer("Maria Wisniewska");
        UniqueCustomers.Customer piotr = new UniqueCustomers.Customer("Piotr Zielinski");

        TopCustomers.Customer cJan = new TopCustomers.Customer("Jan Nowak");
        TopCustomers.Customer cAnna = new TopCustomers.Customer("Anna Kowalska");
        TopCustomers.Customer cTomasz = new TopCustomers.Customer("Tomasz Lewandowski");
        TopCustomers.Customer cPiotr = new TopCustomers.Customer("Piotr Zielinski");

        RevenueByCategory.Product electronics = new RevenueByCategory.Product("Electronics");
        RevenueByCategory.Product books = new RevenueByCategory.Product("Books");
        RevenueByCategory.Product fashion = new RevenueByCategory.Product("Fashion");
        RevenueByCategory.Product grocery = new RevenueByCategory.Product("Grocery");

        ProductsFromActiveOrders.Product laptop = new ProductsFromActiveOrders.Product("Laptop");
        ProductsFromActiveOrders.Product phone = new ProductsFromActiveOrders.Product("Phone");
        ProductsFromActiveOrders.Product headphones = new ProductsFromActiveOrders.Product("Headphones");
        ProductsFromActiveOrders.Product jacket = new ProductsFromActiveOrders.Product("Jacket");
        ProductsFromActiveOrders.Product tShirt = new ProductsFromActiveOrders.Product("T-Shirt");
        ProductsFromActiveOrders.Product coffee = new ProductsFromActiveOrders.Product("Coffee");
        ProductsFromActiveOrders.Product novel = new ProductsFromActiveOrders.Product("Novel");
        ProductsFromActiveOrders.Product textbook = new ProductsFromActiveOrders.Product("Textbook");
        ProductsFromActiveOrders.Product tea = new ProductsFromActiveOrders.Product("Tea");

        System.out.println("--- STREAM API EXECUTION RESULTS ---\n");

        // --- Task 1 ---
        ActiveOrder task1 = new ActiveOrder();
        List<ActiveOrder.Order> orders1 = List.of(
                new ActiveOrder.Order("ORD-001", "NEW"),
                new ActiveOrder.Order("ORD-002", "PROCESSING"),
                new ActiveOrder.Order("ORD-003", "SHIPPED"),
                new ActiveOrder.Order("ORD-004", "DELIVERED"),
                new ActiveOrder.Order("ORD-005", "NEW"),
                new ActiveOrder.Order("ORD-006", "CANCELLED"),
                new ActiveOrder.Order("ORD-007", "PROCESSING"),
                new ActiveOrder.Order("ORD-008", "DELIVERED")
        );
        System.out.println("Task 1 (Active Order IDs): " + task1.activeOrderIds(orders1));

        // --- Task 2 ---
        OrdersAbove task2 = new OrdersAbove();
        List<OrdersAbove.Order> orders2 = List.of(
                new OrdersAbove.Order("ORD-001", 4500.0),
                new OrdersAbove.Order("ORD-002", 1500.0),
                new OrdersAbove.Order("ORD-003", 2900.0),
                new OrdersAbove.Order("ORD-005", 6000.0),
                new OrdersAbove.Order("ORD-008", 3200.0)
        );
        List<String> sortedIds = task2.ordersAbove(orders2, 3000.0).stream()
                .map(OrdersAbove.Order::getId).toList();
        System.out.println("Task 2 (Orders Above 3000 - IDs): " + sortedIds);

        // --- Task 3 ---
        UniqueCustomers task3 = new UniqueCustomers();
        List<UniqueCustomers.Order> orders3 = List.of(
                new UniqueCustomers.Order(jan), new UniqueCustomers.Order(anna),
                new UniqueCustomers.Order(tomasz), new UniqueCustomers.Order(jan),
                new UniqueCustomers.Order(maria), new UniqueCustomers.Order(piotr),
                new UniqueCustomers.Order(anna)
        );
        System.out.println("Task 3 (Unique Customer Names): " + task3.uniqueCustomerNames(orders3));

        // --- Task 4 ---
        ProductsFromActiveOrders task4 = new ProductsFromActiveOrders();
        List<ProductsFromActiveOrders.Order> orders4 = List.of(
                new ProductsFromActiveOrders.Order("DELIVERED", List.of(
                        new ProductsFromActiveOrders.OrderItem(laptop),
                        new ProductsFromActiveOrders.OrderItem(phone),
                        new ProductsFromActiveOrders.OrderItem(headphones))),
                new ProductsFromActiveOrders.Order("PROCESSING", List.of(
                        new ProductsFromActiveOrders.OrderItem(jacket),
                        new ProductsFromActiveOrders.OrderItem(tShirt),
                        new ProductsFromActiveOrders.OrderItem(coffee))),
                new ProductsFromActiveOrders.Order("CANCELLED", List.of(
                        new ProductsFromActiveOrders.OrderItem(new ProductsFromActiveOrders.Product("Car")))),
                new ProductsFromActiveOrders.Order("NEW", List.of(
                        new ProductsFromActiveOrders.OrderItem(novel),
                        new ProductsFromActiveOrders.OrderItem(textbook),
                        new ProductsFromActiveOrders.OrderItem(tea),
                        new ProductsFromActiveOrders.OrderItem(coffee)))
        );
        System.out.println("Task 4 (Products from Active Orders): " + task4.soldProductNames(orders4));

        // --- Task 5 ---
        TotalRevenue task5 = new TotalRevenue();
        List<TotalRevenue.Order> orders5 = List.of(
                new TotalRevenue.Order("DELIVERED", 10000.0),
                new TotalRevenue.Order("PROCESSING", 5480.0),
                new TotalRevenue.Order("CANCELLED", 3500.0),
                new TotalRevenue.Order("NEW", 6000.0)
        );
        System.out.println("Task 5 (Total Revenue): " + task5.totalRevenue(orders5));

        // --- Task 6 ---
        AverageDeliveredValue task6 = new AverageDeliveredValue();
        List<AverageDeliveredValue.Order> orders6 = List.of(
                new AverageDeliveredValue.Order("DELIVERED", 1200.0),
                new AverageDeliveredValue.Order("PROCESSING", 5000.0),
                new AverageDeliveredValue.Order("DELIVERED", 3150.0),
                new AverageDeliveredValue.Order("CANCELLED", 2500.0),
                new AverageDeliveredValue.Order("DELIVERED", 1000.0)
        );
        OptionalDouble avg = task6.averageDeliveredOrderValue(orders6);
        System.out.print("Task 6 (Average Delivered Value): ");
        if (avg.isPresent()) System.out.printf("%.2f%n", avg.getAsDouble());
        else System.out.println("No data");

        // --- Task 7 ---
        CountOrdersByStatus task7 = new CountOrdersByStatus();
        List<CountOrdersByStatus.Order> orders7 = List.of(
                new CountOrdersByStatus.Order("DELIVERED"), new CountOrdersByStatus.Order("DELIVERED"),
                new CountOrdersByStatus.Order("DELIVERED"), new CountOrdersByStatus.Order("SHIPPED"),
                new CountOrdersByStatus.Order("SHIPPED"), new CountOrdersByStatus.Order("NEW"),
                new CountOrdersByStatus.Order("PAID"), new CountOrdersByStatus.Order("CANCELLED")
        );
        System.out.println("Task 7 (Count By Status Map): " + task7.countByStatus(orders7));

        // --- Task 8 ---
        RevenueByCategory task8 = new RevenueByCategory();
        List<RevenueByCategory.Order> orders8 = List.of(
                new RevenueByCategory.Order("DELIVERED", List.of(
                        new RevenueByCategory.OrderItem(electronics, 20000.0),
                        new RevenueByCategory.OrderItem(books, 340.0))),
                new RevenueByCategory.Order("PROCESSING", List.of(
                        new RevenueByCategory.OrderItem(electronics, 100.0),
                        new RevenueByCategory.OrderItem(fashion, 540.0),
                        new RevenueByCategory.OrderItem(grocery, 300.0))),
                new RevenueByCategory.Order("CANCELLED", List.of(
                        new RevenueByCategory.OrderItem(books, 1000.0))),
                new RevenueByCategory.Order("NEW", List.of(
                        new RevenueByCategory.OrderItem(books, 200.0)))
        );
        System.out.println("Task 8 (Revenue By Product Category Map): " + task8.revenueByCategory(orders8));

        // --- Task 9 ---
        TopCustomers task9 = new TopCustomers();
        List<TopCustomers.Order> orders9 = List.of(
                new TopCustomers.Order(cJan, "DELIVERED", 5000.0),
                new TopCustomers.Order(cJan, "PROCESSING", 3655.0),
                new TopCustomers.Order(cAnna, "NEW", 7660.0),
                new TopCustomers.Order(cTomasz, "DELIVERED", 4440.0),
                new TopCustomers.Order(cPiotr, "DELIVERED", 1200.0),
                new TopCustomers.Order(cJan, "CANCELLED", 9999.0)
        );
        System.out.println("Task 9 (Top 3 Customers Map Sorted): " + task9.topCustomers(orders9, 3));

        // --- Task 10 ---
        PartitionOrders task10 = new PartitionOrders();
        List<PartitionOrders.Order> orders10 = List.of(
                new PartitionOrders.Order("DELIVERED", 4500.0),
                new PartitionOrders.Order("NEW", 6000.0),
                new PartitionOrders.Order("PROCESSING", 3200.0),
                new PartitionOrders.Order("PROCESSING", 1500.0),
                new PartitionOrders.Order("NEW", 2900.0),
                new PartitionOrders.Order("DELIVERED", 1000.0),
                new PartitionOrders.Order("NEW", 2500.0),
                new PartitionOrders.Order("CANCELLED", 5000.0)
        );
        Map<Boolean, List<PartitionOrders.Order>> partitioned = task10.partitionActiveOrdersByValue(orders10, 3000.0);
        System.out.println("Task 10 (Partition Active Orders By 3000 Threshold):");
        System.out.println("   -> Group [true] (Expensive) count: " + partitioned.get(true).size());
        System.out.println("   -> Group [false] (Others) count: " + partitioned.get(false).size());

        // --- Task 11 ---
        MostExpensiveDelivered task11 = new MostExpensiveDelivered();
        List<MostExpensiveDelivered.Order> orders11 = List.of(
                new MostExpensiveDelivered.Order("ORD-001", "DELIVERED", 4500.0),
                new MostExpensiveDelivered.Order("ORD-002", "PROCESSING", 6000.0),
                new MostExpensiveDelivered.Order("ORD-003", "DELIVERED", 2900.0),
                new MostExpensiveDelivered.Order("ORD-004", "CANCELLED", 5500.0),
                new MostExpensiveDelivered.Order("ORD-005", "DELIVERED", 1200.0)
        );
        Optional<MostExpensiveDelivered.Order> mostExpensive = task11.mostExpensiveDeliveredOrder(orders11);
        System.out.print("Task 11 (Most Expensive Delivered Order ID): ");
        mostExpensive.ifPresentOrElse(
                order -> System.out.println(order.getId()),
                () -> System.out.println("Not found")
        );
    }
}