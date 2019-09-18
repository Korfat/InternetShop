package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.service.OrderService;
import internetshop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    private static final Long USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        userService.getOrders(USER_ID).remove(orderService.get(Long.valueOf(orderId)));
        resp.sendRedirect(req.getContextPath() + "/allOrders");
    }
}
