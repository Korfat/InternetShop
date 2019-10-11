package internetshop;

import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.RoleDao;
import internetshop.dao.UserDao;
import internetshop.dao.hibernate.BucketDaoHibernateImpl;
import internetshop.dao.hibernate.ItemDaoHibernateImpl;
import internetshop.dao.hibernate.OrderDaoHibernateImpl;
import internetshop.dao.hibernate.RoleDaoHibernateImpl;
import internetshop.dao.hibernate.UserDaoHibernateImpl;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.RoleService;
import internetshop.service.UserService;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.RoleServiceImpl;
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
    private static RoleDao roleDaoInstanse;
    private static ItemService itemServiceInstanse;
    private static BucketService bucketServiceInstanse;
    private static OrderService orderServiceInstanse;
    private static UserService userServiceInstanse;
    private static RoleService roleServiceInstanse;

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

    public static RoleService getRoleService() {
        if (roleServiceInstanse == null) {
            roleServiceInstanse = new RoleServiceImpl();
        }
        return roleServiceInstanse;
    }

    public static ItemDao getItemDao() {
        if (itemDaoInstanse == null) {
            itemDaoInstanse = new ItemDaoHibernateImpl();
        }
        return itemDaoInstanse;
    }

    public static BucketDao getBucketDao() {
        if (bucketDaoInstanse == null) {
            bucketDaoInstanse = new BucketDaoHibernateImpl();
        }
        return bucketDaoInstanse;
    }

    public static OrderDao getOrderDao() {
        if (orderDaoInstanse == null) {
            orderDaoInstanse = new OrderDaoHibernateImpl();
        }
        return orderDaoInstanse;
    }

    public static UserDao getUserDao() {
        if (userDaoInstanse == null) {
            userDaoInstanse = new UserDaoHibernateImpl();
        }
        return userDaoInstanse;
    }

    public static RoleDao getRoleDao() {
        if (roleDaoInstanse == null) {
            roleDaoInstanse = new RoleDaoHibernateImpl();
        }
        return roleDaoInstanse;
    }
}
