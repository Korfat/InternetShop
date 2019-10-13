package internetshop.dao.hibernate;

import internetshop.dao.UserDao;
import internetshop.exceptions.AuthenticationException;
import internetshop.lib.Dao;
import internetshop.model.Order;
import internetshop.model.User;
import internetshop.util.HashUtil;
import internetshop.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public Optional<User> create(User user) {
        Long userId = null;
        byte[] salt = HashUtil.getSalt();
        user.setPassword(HashUtil.hashPassword(user.getPassword(), salt));
        user.setSalt(salt);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            userId = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create user");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        user.setId(userId);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> get(Long id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        } catch (Exception e) {
            logger.error("Can't get user");
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            users = session.createCriteria(User.class).list();
        } catch (Exception e) {
            logger.error("Can't get users");
        }
        return users;
    }

    @Override
    public Optional<User> update(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update user");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(Long id) {
        User user = get(id).get();
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
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
    public Optional<User> login(String login, String password) throws AuthenticationException {
        List<User> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where login=:login");
            query.setParameter("login", login);
            list = query.list();
        } catch (Exception e) {
            logger.error("Can't login users");
        }
        return list.stream()
                .filter(u -> u.getPassword()
                        .equals(HashUtil.hashPassword(password, u.getSalt())))
                .findFirst();
    }

    @Override
    public Optional<User> getByToken(String token) {
        List<User> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where token=:token");
            query.setParameter("token", token);
            list = query.list();
        } catch (Exception e) {
            logger.error("Can't login users");
        }
        return list.stream().findFirst();
    }

    @Override
    public List<Order> getOrders(Long userId) {
        List<Order> orders = get(userId).get().getOrders();
        return orders;
    }

    @Override
    public Optional<User> addRole(Long userId, Long roleId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "INSERT INTO users_roles(user_id, role_id) VALUES(?, ?);");
            query.setParameter(1, userId);
            query.setParameter(2, roleId);
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
        return get(userId);
    }
}
