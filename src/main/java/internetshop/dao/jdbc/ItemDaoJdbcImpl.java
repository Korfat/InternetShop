package internetshop.dao.jdbc;

import internetshop.dao.ItemDao;
import internetshop.lib.Dao;
import internetshop.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
        Locale.setDefault(Locale.US);
    }

    @Override
    public Optional<Item> create(Item item) {
        String query = "INSERT INTO items (name, model, price)"
                + " VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getModel());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                    return Optional.of(item);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Can't create order");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Item> get(Long id) {
        String query = "SELECT * FROM items WHERE item_id = ?;";
        Item item = new Item();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                Double price = resultSet.getDouble("price");
                item.setId(itemId);
                item.setName(name);
                item.setModel(model);
                item.setPrice(price);
            }
            return Optional.of(item);
        } catch (SQLException e) {
            logger.error("Can't get order");
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Item>> getAll() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                Double price = resultSet.getDouble("price");
                Item item = new Item();
                item.setId(itemId);
                item.setName(name);
                item.setModel(model);
                item.setPrice(price);
                items.add(item);
            }
            return Optional.of(items);
        } catch (SQLException e) {
            logger.error("Can't get order");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Item> update(Item item) {
        String query = "UPDATE items SET name = ?, model = ?, price = ?"
                + " WHERE item_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getModel());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setLong(4, item.getId());
            preparedStatement.executeUpdate();
            return Optional.of(item);
        } catch (SQLException e) {
            logger.error("Can't update order");
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM items WHERE item_id = ?;";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user");
        }
    }

    @Override
    public Optional<Item> deleteByItem(Item item) {
        return Optional.empty();
    }
}
