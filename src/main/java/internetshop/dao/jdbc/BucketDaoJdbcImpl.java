package internetshop.dao.jdbc;

import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.lib.Dao;
import internetshop.lib.Inject;
import internetshop.model.Bucket;
import internetshop.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    @Inject
    private static ItemDao itemDao;

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Bucket> create(Bucket bucket) {
        String query = "INSERT INTO `buckets` (`user_id`) VALUES (?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, bucket.getUserId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bucket.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating bucket failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Can't create order");
        }

        for (Item item : bucket.getItems()) {
            addItem(bucket.getId(), item.getId());
        }
        return Optional.of(bucket);
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        String query = "SELECT bucket_id, user_id, item_id FROM buckets INNER JOIN buckets_items"
                + " USING (bucket_id) WHERE bucket_id = ?;";
        Bucket bucket = new Bucket();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long bucketIdFromDb = resultSet.getLong("bucket_id");
                long userId = resultSet.getLong("user_id");
                long itemId = resultSet.getLong("item_id");
                bucket.setId(bucketIdFromDb);
                bucket.setUserId(userId);
                Optional<Item> item = itemDao.get(itemId);
                bucket.getItems().add(item.get());
            }
            return Optional.of(bucket);
        } catch (SQLException e) {
            logger.error("Can't get bucket");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Bucket> getByUser(Long userId) {
        String query = "SELECT * FROM buckets WHERE user_id = ?;";
        Bucket bucket = new Bucket();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long bucketIdFromDb = resultSet.getLong("bucket_id");
                long userIdFromDb = resultSet.getLong("user_id");
                bucket.setId(bucketIdFromDb);
                bucket.setUserId(userIdFromDb);
            }
            return Optional.of(bucket);
        } catch (SQLException e) {
            logger.error("Can't get bucket");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {
        String query = "UPDATE buckets SET user_id = ? WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucket.getUserId());
            preparedStatement.setLong(2, bucket.getId());
            preparedStatement.executeUpdate();
            return Optional.of(bucket);
        } catch (SQLException e) {
            logger.error("Can't update bucket");
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete bucket");
        }
    }

    public Optional<Bucket> addItem(Long bucketId, Long itemId) {
        String query = "INSERT INTO `buckets_items` (`bucket_id`, `item_id`) VALUES (?, ?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            preparedStatement.setLong(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add item");
        }
        return get(bucketId);
    }

    @Override
    public Optional<Bucket> deleteItem(Long bucketId, Long itemId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ? AND item_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            preparedStatement.setLong(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete item");
        }
        return get(bucketId);
    }

    @Override
    public Optional<Bucket> clear(Long bucketId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete item");
        }
        return get(bucketId);
    }
}
