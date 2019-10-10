package internetshop.dao.hibernate;

import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.lib.Dao;
import internetshop.lib.Inject;
import internetshop.model.Bucket;
import internetshop.util.HibernateUtil;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Inject
    private static ItemDao itemDao;

    @Override
    public Optional<Bucket> create(Bucket bucket) {
        Long bucketId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            bucketId = (Long) session.save(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create bucket");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        bucket.setId(bucketId);
        return Optional.ofNullable(bucket);
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE bucket_id=:bucketId");
            query.setParameter("bucketId", bucketId);
            Optional<Bucket> bucket = query.list().stream().findFirst();
            if (bucket.isPresent()) {
                return bucket;
            }
        } catch (HibernateException e) {
            logger.error("Can't get bucket. ", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Bucket> getByUser(Long userId) {
        Bucket bucket = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE user_id=:userId");
            query.setParameter("userId", userId);
            bucket = (Bucket) query.uniqueResult();
        } catch (Exception e) {
            logger.error("Can't get bucket");
        }
        return Optional.ofNullable(bucket);
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update bucket");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(bucket);
    }

    @Override
    public void delete(Long id) {
        Bucket bucket = get(id).get();
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete bucket");
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
    public Optional<Bucket> addItem(Long bucketId, Long itemId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "INSERT INTO buckets_items(bucket_id, item_id) VALUES(?, ?);");
            query.setParameter(1, bucketId);
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
        return get(bucketId);
    }

    @Override
    public Optional<Bucket> deleteItem(Long bucketId, Long itemId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "DELETE FROM buckets_items WHERE bucket_id = ? AND item_id = ?;");
            query.setParameter(1, bucketId);
            query.setParameter(2, itemId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            logger.error("Failed to delete the item into the bucket");
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return get(bucketId);
    }

    @Override
    public Optional<Bucket> clear(Long bucketId) {
        Bucket bucket = null;
        try {
            bucket = get(bucketId).get();
            bucket.clearItems();
            update(bucket);
        } catch (Exception e) {
            logger.error("Can't clear bucket");
        }
        return Optional.ofNullable(bucket);
    }
}
