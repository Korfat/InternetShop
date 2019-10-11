package internetshop.dao.hibernate;

import internetshop.dao.OrderDao;
import internetshop.lib.Dao;
import internetshop.model.Order;
import internetshop.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoHibernateImpl.class);

    @Override
    public Optional<Order> create(Order order) {
        Long orderId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            orderId = (Long) session.save(order);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create order");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        order.setId(orderId);
        return Optional.of(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        Order order = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            order = session.get(Order.class, id);
        } catch (Exception e) {
            logger.error("Can't get order");
        }
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orders = session.createCriteria(Order.class).list();
        } catch (Exception e) {
            logger.error("Can't get orders");
        }
        return orders;
    }

    @Override
    public Optional<Order> update(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update order");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(order);
    }

    @Override
    public void delete(Long id) {
        Order order = get(id).get();
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete user");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Order> deleteByOrder(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete order");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(order);
    }

    @Override
    public Optional<Order> addItem(Long orderId, Long itemId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "INSERT INTO orders_items(order_id, item_id) VALUES(?, ?);");
            query.setParameter(1, orderId);
            query.setParameter(2, itemId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            logger.error("Failed to add the item into the bucket");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return get(orderId);
    }
}
