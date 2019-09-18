package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.Bucket;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        if (req.getParameter("psw").equals(req.getParameter("psw-repeat"))) {
            newUser.setPassword(req.getParameter("psw-repeat"));
        } else {
            req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        newUser.setLogin(req.getParameter("login"));
        newUser.setName(req.getParameter("user_name"));
        newUser.setSurname(req.getParameter("user_surname"));
        userService.create(newUser);
        //
        Bucket newBuckec = new Bucket(newUser);
        bucketService.create(newBuckec);
        //
        resp.sendRedirect(req.getContextPath() + "/allItems");
    }
}
