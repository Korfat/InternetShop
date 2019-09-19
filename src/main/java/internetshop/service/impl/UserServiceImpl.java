package internetshop.service.impl;

import internetshop.dao.UserDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Order;
import internetshop.model.User;
import internetshop.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public List<Order> getOrders(Long userId) {
        return userDao.get(userId).getOrders();
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public User deleteByUser(User user) {
        return userDao.deleteByUser(user);
    }
}
