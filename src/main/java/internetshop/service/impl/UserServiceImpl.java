package internetshop.service.impl;

import internetshop.dao.UserDao;
import internetshop.exceptions.AuthenticationException;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Order;
import internetshop.model.User;
import internetshop.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        user.setToken(getToken());
        return userDao.create(user);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
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

    @Override
    public User login(String login, String password) throws AuthenticationException {
        return userDao.login(login, password);
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }
}
