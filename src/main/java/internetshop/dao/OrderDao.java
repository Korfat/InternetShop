package internetshop.dao;

import internetshop.model.Order;

public interface OrderDao {
    Order create(Order item);

    Order get(Long id);

    Order update(Order item);

    Order delete(Long id);

    Order deleteByOrder(Order order);
}
