package internetshop.service.impl;

import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserDao userDao;

    @Override
    public Optional<Order> completeOrder(List<Item> items, Long userId) {
        Order order = new Order();
        order.setItems(items);
        order.setUser(userDao.get(userId).get());
        orderDao.create(order);
        return Optional.of(order);
    }

    @Override
    public Optional<Order> create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public List<Item> getAllItems(Long orderId) {
        Optional<Order> order = get(orderId);
        return order.get().getItems();
    }

    @Override
    public Optional<Order> update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Optional<Order> deleteByOrder(Order order) {
        return orderDao.deleteByOrder(order);
    }
}
