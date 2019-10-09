package internetshop.dao.hibernate;

import internetshop.dao.RoleDao;
import internetshop.lib.Dao;
import internetshop.model.Role;
import internetshop.util.HibernateUtil;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {
    @Override
    public Optional<Role> create(Role role) {
        Long roleId = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            roleId = (Long) session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        role.setId(roleId);
        return Optional.ofNullable(role);
    }

    @Override
    public Optional<Role> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Role role = session.get(Role.class, id);
            return Optional.of(role);
        }
    }

    @Override
    public Optional<Role> update(Role role) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.ofNullable(role);
    }

    @Override
    public void delete(Long id) {
        Role role = get(id).get();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
