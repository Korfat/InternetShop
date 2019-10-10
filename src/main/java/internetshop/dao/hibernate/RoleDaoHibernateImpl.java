package internetshop.dao.hibernate;

import internetshop.dao.RoleDao;
import internetshop.lib.Dao;
import internetshop.model.Role;
import internetshop.util.HibernateUtil;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {
    private static Logger logger = Logger.getLogger(RoleDaoHibernateImpl.class);

    @Override
    public Optional<Role> create(Role role) {
        Long roleId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            roleId = (Long) session.save(role);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create role");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        role.setId(roleId);
        return Optional.ofNullable(role);
    }

    @Override
    public Optional<Role> get(Long id) {
        Role role = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            role = session.get(Role.class, id);
            return Optional.of(role);
        } catch (Exception e) {
            logger.error("Can't get role");
        }
        return Optional.ofNullable(role);
    }

    @Override
    public Optional<Role> update(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update role");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(role);
    }

    @Override
    public void delete(Long id) {
        Role role = get(id).get();
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(role);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete role");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
