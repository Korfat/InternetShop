package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Item;
import internetshop.service.BucketService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    private static final Long USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> itemsInBucket = bucketService
                .getAllItems(bucketService.getByUser(USER_ID).getId());
        req.setAttribute("itemsInBucket", itemsInBucket);
        req.getRequestDispatcher("WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
