package internetshop.service;

import internetshop.model.Item;
import internetshop.model.Order;

import java.util.List;

public interface OrderService {
    Order completeOrder(List<Item> items, Long userId);

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);

    Order deleteByOrder(Order order);
}
