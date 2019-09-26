package internetshop;

import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.dao.impl.BucketDaoImpl;
import internetshop.dao.impl.OrderDaoImpl;
import internetshop.dao.impl.UserDaoImpl;
import internetshop.dao.jdbc.ItemDaoJdbcImpl;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Factory {
    private static Logger logger = Logger.getLogger(Factory.class);
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/internetshop?"
                    + "user=root&password=qpalzmrfvQ1&serverTimezone=UTC");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Can't establish connection to our DB", e);
        }
    }

    private static ItemDao itemDaoInstanse;
    private static BucketDao bucketDaoInstanse;
    private static OrderDao orderDaoInstanse;
    private static UserDao userDaoInstanse;
    private static ItemService itemServiceInstanse;
    private static BucketService bucketServiceInstanse;
    private static OrderService orderServiceInstanse;
    private static UserService userServiceInstanse;

    public static ItemService getItemService() {
        if (itemServiceInstanse == null) {
            itemServiceInstanse = new ItemServiceImpl();
        }
        return itemServiceInstanse;
    }

    public static BucketService getBucketService() {
        if (bucketServiceInstanse == null) {
            bucketServiceInstanse = new BucketServiceImpl();
        }
        return bucketServiceInstanse;
    }

    public static OrderService getOrderService() {
        if (orderServiceInstanse == null) {
            orderServiceInstanse = new OrderServiceImpl();
        }
        return orderServiceInstanse;
    }

    public static UserService getUserService() {
        if (userServiceInstanse == null) {
            userServiceInstanse = new UserServiceImpl();
        }
        return userServiceInstanse;
    }

    public static ItemDao getItemDao() {
        if (itemDaoInstanse == null) {
            itemDaoInstanse = new ItemDaoJdbcImpl(connection);
        }
        return itemDaoInstanse;
    }

    public static BucketDao getBucketDao() {
        if (bucketDaoInstanse == null) {
            bucketDaoInstanse = new BucketDaoImpl();
        }
        return bucketDaoInstanse;
    }

    public static OrderDao getOrderDao() {
        if (orderDaoInstanse == null) {
            orderDaoInstanse = new OrderDaoImpl();
        }
        return orderDaoInstanse;
    }

    public static UserDao getUserDao() {
        if (userDaoInstanse == null) {
            userDaoInstanse = new UserDaoImpl();
        }
        return userDaoInstanse;
    }
}
