package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.service.BucketService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFromBucket extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    private static final Long USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item_id");
        bucketService.deleteItem(bucketService.getByUser(USER_ID).getId(), Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/bucket");
    }
}
