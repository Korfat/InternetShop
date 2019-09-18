package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.service.BucketService;
import internetshop.service.ItemService;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item_id");
        bucketService.addItem(bucketService.getCurrent(), Long.valueOf(itemId));
        itemService.delete(Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/allItems");
    }
}
