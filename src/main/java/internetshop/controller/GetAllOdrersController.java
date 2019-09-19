package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Order;
import internetshop.service.OrderService;
import internetshop.service.UserService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllOdrersController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    private static final Long USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Order> orders = userService.getOrders(USER_ID);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("WEB-INF/views/allOrders.jsp").forward(req, resp);
    }
}
