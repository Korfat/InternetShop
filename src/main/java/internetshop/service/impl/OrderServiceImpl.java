package internetshop.service.impl;

import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;

    @Inject
    private static UserDao userDao;

    @Override
    public Order completeOrder(List<Item> items, Long userId) {
        Order order = new Order(userId, items);
        orderDao.create(order);
        userDao.get(userId).getOrders().add(order);
        userDao.get(userId).getBucket().clearItems();
        return order;
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public List<Item> getAllItems(Long orderId) {
        Order order = orderDao.get(orderId);
        return order.getItems();
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Order delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public Order deleteByOrder(Order order) {
        return orderDao.deleteByOrder(order);
    }
}
