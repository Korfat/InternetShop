package internetshop.dao.hibernate;

import internetshop.dao.ItemDao;
import internetshop.lib.Dao;
import internetshop.model.Item;
import internetshop.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoHibernateImpl.class);

    @Override
    public Optional<Item> create(Item item) {
        Long itemId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            itemId = (Long) session.save(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create item");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        item.setId(itemId);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<Item> get(Long id) {
        Item item = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            item = session.get(Item.class, id);
        } catch (Exception e) {
            logger.error("Can't get user");
        }
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            items = session.createCriteria(Item.class).list();
        } catch (Exception e) {
            logger.error("Can't get user");
        }
        return items;
    }

    @Override
    public Optional<Item> update(Item item) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update item");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(item);
    }

    @Override
    public void delete(Long id) {
        Item item = get(id).get();
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete item");
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
    public Optional<Item> deleteByItem(Item item) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete item");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(item);
    }
}
