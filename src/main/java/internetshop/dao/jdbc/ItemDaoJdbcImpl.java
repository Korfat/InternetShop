package internetshop.dao.jdbc;

import internetshop.dao.ItemDao;
import internetshop.lib.Dao;
import internetshop.model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static String DB_NAME = "internetshop";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
        Locale.setDefault(Locale.US);
    }

    @Override
    public Item create(Item item) {
        Statement stmt = null;
        String query = String.format("INSERT INTO %s.items (name, model, price)"
                        + " VALUES ('%s', '%s', %.2f) ;", DB_NAME, item.getName(),
                item.getModel(), item.getPrice());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.warn("Can't create item");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item get(Long id) {
        Statement stmt = null;
        String query = String.format("SELECT * FROM %s.items where item_id = %d ;", DB_NAME, id);
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long itemId = rs.getLong("item_id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                double price = rs.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setModel(model);
                item.setPrice(price);
                return item;
            }
        } catch (SQLException e) {
            logger.warn("Can't get item by id=" + id);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        Statement stmt = null;
        String query = String.format("SELECT * FROM %s.items ;", DB_NAME);
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long itemId = rs.getLong("item_id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                double price = rs.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setModel(model);
                item.setPrice(price);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            logger.warn("Can't get all items");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        Statement stmt = null;
        String query = String.format("UPDATE %s.items SET name = '%s', model = '%s',"
                        + " price = '%.2f' WHERE item_id = %d ;", DB_NAME, item.getName(),
                item.getModel(), item.getPrice(), item.getId());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.warn("Can't update item");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Statement stmt = null;
        String query = String.format("DELETE FROM %s.items where item_id = %d ;", DB_NAME, id);
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn("Can't update item");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
    }

    @Override
    public Item deleteByItem(Item item) {
        return null;
    }
}
