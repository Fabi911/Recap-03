import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo{
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order getOrderById(String id) {
        return orders.get(id);
    }

    @Override
    public Order addOrder(Order newOrder) {
        orders.put(newOrder.id(), newOrder);
        return newOrder;
    }

    @Override
    public void removeOrder(String id) {
        orders.remove(id);
    }

    @Override
    public void changeOrderStatus(String orderId, String newStatus) {
        Order order = getOrderById(orderId);
        if (order == null) {
            System.out.println("Order mit der Id: " + orderId + " konnte nicht gefunden werden!");
            return;
        }
        removeOrder(orderId);
        addOrder(new Order(order.id(), order.products(), newStatus));

    }
}
