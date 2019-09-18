package internetshop.service;

import internetshop.model.Order;
import internetshop.model.User;

import java.util.List;

public interface UserService {
    List<Order> getOrders(Long userId);

    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    User delete(Long id);

    User deleteByUser(User user);
}
