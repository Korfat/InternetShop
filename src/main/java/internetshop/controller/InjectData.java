package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectData extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

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

        User user = new User("Dima");
        user.setSurname("Shum");
        user.setLogin("Korfat");
        user.setPassword("12");
        user.addRole(Role.of("USER"));
        Bucket newBucketUser = new Bucket(user);
        bucketService.create(newBucketUser);
        user.setBucket(newBucketUser);
        userService.create(user);

        User admin = new User("Super");
        admin.setSurname("Admin");
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.addRole(Role.of("ADMIN"));
        userService.create(admin);
        resp.sendRedirect(req.getContextPath() + "/servlet/allItems");
    }
}
