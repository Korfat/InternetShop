package internetshop.service;

import internetshop.exceptions.AuthenticationException;
import internetshop.model.Order;
import internetshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Order> getOrders(Long userId);

    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    User delete(Long id);

    User deleteByUser(User user);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
