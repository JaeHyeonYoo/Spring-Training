package hello.core.order;

public interface OrderService {

    void order(Order order);

    Order discount(Long orderId);
}
