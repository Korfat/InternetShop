package internetshop.controller;

import internetshop.lib.Inject;
import internetshop.model.User;
import internetshop.service.UserService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllUsersController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> users = userService.getAll();
        req.setAttribute("greeting", "Mates");
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/AllUsers.jsp").forward(req, resp);
    }
}
