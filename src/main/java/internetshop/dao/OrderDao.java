package internetshop.dao;

import internetshop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    Optional<Order> update(Order order);

    void delete(Long id);

    Optional<Order> deleteByOrder(Order order);

    Optional<Order> addItem(Long orderId, Long itemId);
}
