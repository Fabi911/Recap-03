import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, "PROCESSING"); // default value of orderStatus is PROCESSING

        return orderRepo.addOrder(newOrder);
    }
    public void changeOrderStatus(String orderId, String newStatus) {
        Order order = orderRepo.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order mit der Id: " + orderId + " konnte nicht gefunden werden!");
            return;
        }
        orderRepo.removeOrder(orderId);
        orderRepo.addOrder(new Order(order.id(), order.products(), newStatus));
    }
}
