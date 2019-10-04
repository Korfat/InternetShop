package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddItemToBucketController extends HttpServlet {
    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item_id");
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        bucketService.addItem(bucketService.getByUser(userId).get().getId(), Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/servlet/allItems");
    }
}
