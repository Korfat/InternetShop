package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Bucket;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectData extends HttpServlet {
    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
