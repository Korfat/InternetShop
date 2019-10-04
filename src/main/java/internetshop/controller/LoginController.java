package internetshop.controller;

import internetshop.exceptions.AuthenticationException;
import internetshop.lib.Inject;
import internetshop.model.User;
import internetshop.service.UserService;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("psw");
        try {
            Optional<User> user = userService.login(login, password);

            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.get().getId());

            Cookie cookie = new Cookie("Mate", user.get().getToken());
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/servlet/allItems");
        } catch (AuthenticationException e) {
            req.setAttribute("errorMessage", "Incorrect login or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
