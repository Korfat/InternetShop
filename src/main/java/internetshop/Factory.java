package internetshop;

import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.dao.impl.BucketDaoImpl;
import internetshop.dao.impl.ItemDaoImpl;
import internetshop.dao.impl.OrderDaoImpl;
import internetshop.dao.impl.UserDaoImpl;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.UserServiceImpl;

public class Factory {
    private static ItemDao itemDaoInstanse = new ItemDaoImpl();
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
            itemDaoInstanse = new ItemDaoImpl();
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
