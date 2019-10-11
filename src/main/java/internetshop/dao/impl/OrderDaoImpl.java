package internetshop.dao.impl;

import internetshop.dao.OrderDao;
import internetshop.dao.Storage;
import internetshop.lib.Dao;
import internetshop.model.Order;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Optional<Order> create(Order order) {
        Storage.orders.add(order);
        return Optional.of(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Optional<Order> update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Storage.orders.set(i, order);
                return Optional.of(order);
            }
        }
        throw new NoSuchElementException("Can't find item" + order.getId());
    }

    @Override
    public void delete(Long id) {
        Storage.orders.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public Optional<Order> deleteByOrder(Order order) {
        Storage.orders.removeIf(s -> s.equals(order));
        return Optional.of(order);
    }

    @Override
    public Optional<Order> addItem(Long orderId, Long itemId) {
        return Optional.empty();
    }
}
