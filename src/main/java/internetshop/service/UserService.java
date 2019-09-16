package internetshop.service;

import internetshop.model.User;

import java.util.List;

public interface UserService {
    List getOrders(Long userId);

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);

    User deleteByUser(User user);
}
