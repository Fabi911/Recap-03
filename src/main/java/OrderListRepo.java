import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo{
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public Order addOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder;
    }

    public void removeOrder(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                orders.remove(order);
                return;
            }
        }
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
