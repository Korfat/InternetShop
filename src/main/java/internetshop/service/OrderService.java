package internetshop.service;

import internetshop.model.Item;
import internetshop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> completeOrder(List<Item> items, Long userId);

    Optional<Order> create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    List<Item> getAllItems(Long orderId);

    Optional<Order> update(Order order);

    void delete(Long id);

    Optional<Order> deleteByOrder(Order order);
}
