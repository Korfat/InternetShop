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
        Item item = new Item(1L);
        item.setName("Meizu");
        item.setModel("M3");
        item.setPrice(100.11);
        Item itemFromBd = itemService.create(item);
        itemService.get(itemFromBd.getId());
        itemFromBd.setModel("M6");
        Item itemAfterUpdate = itemService.update(itemFromBd);
        itemService.get(itemAfterUpdate.getId());
        itemService.delete(itemAfterUpdate.getId());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
