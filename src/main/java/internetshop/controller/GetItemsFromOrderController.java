package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Item;
import internetshop.service.OrderService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetItemsFromOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        List<Item> itemsInOrder = orderService.getAllItems(Long.valueOf(orderId)).get();
        req.setAttribute("itemsInOrder", itemsInOrder);
        req.getRequestDispatcher("/WEB-INF/views/getOrder.jsp").forward(req, resp);
    }
}
