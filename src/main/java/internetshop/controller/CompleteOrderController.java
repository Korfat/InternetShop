package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.service.BucketService;
import internetshop.service.OrderService;
import internetshop.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    private static final Long USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> items = new ArrayList<>(bucketService
                .getAllItems(bucketService.getByUser(USER_ID).getId()));
        Order order = new Order(USER_ID, items);
        orderService.create(order);
        userService.get(USER_ID).getOrders().add(order);
        bucketService.clear(bucketService.getByUser(USER_ID).getId());
        req.setAttribute("items", items);
        req.getRequestDispatcher("WEB-INF/views/completeOrder.jsp").forward(req, resp);
    }
}
