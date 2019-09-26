package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Item;
import internetshop.service.ItemService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item item = new Item();
        item.setName("Meizu");
        item.setModel("M3");
        item.setPrice(100.11);
        itemService.create(item);
        itemService.get(item.getId());
        item.setModel("M6");
        itemService.update(item);
        itemService.get(item.getId());
        itemService.delete(item.getId());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
