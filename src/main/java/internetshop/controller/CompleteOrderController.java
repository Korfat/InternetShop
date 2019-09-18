package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.service.BucketService;
import internetshop.service.OrderService;

import java.io.IOException;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Order order = orderService.completeOrder(bucketService
                .getAllItems(bucketService.getCurrent()), 0L);
        List<Item> items = orderService.getAllItems(order.getId());
        Bucket bucket = new Bucket(0L);
        bucketService.create(bucket);
        req.setAttribute("items", items);
        req.getRequestDispatcher("WEB-INF/views/completeOrder.jsp").forward(req, resp);
    }
}
