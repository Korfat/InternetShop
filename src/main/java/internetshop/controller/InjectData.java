package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Item;
import internetshop.service.ItemService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectData extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item newItem1 = new Item();
        newItem1.setName("Samsung");
        newItem1.setModel(req.getParameter("S10"));
        newItem1.setPrice(1000.99);
        Item newItem2 = new Item();
        newItem2.setName("IPhone");
        newItem2.setModel(req.getParameter("XS"));
        newItem2.setPrice(1199.99);
        Item newItem3 = new Item();
        newItem3.setName("Meizu");
        newItem3.setModel(req.getParameter("M3"));
        newItem3.setPrice(100.01);
        itemService.create(newItem1);
        itemService.create(newItem2);
        itemService.create(newItem3);
        resp.sendRedirect(req.getContextPath() + "/allItems");
    }
}
