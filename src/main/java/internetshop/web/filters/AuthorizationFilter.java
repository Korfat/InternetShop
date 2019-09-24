package internetshop.web.filters;

import internetshop.lib.Inject;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static internetshop.model.Role.RoleName.ADMIN;
import static internetshop.model.Role.RoleName.USER;

public class AuthorizationFilter implements Filter {
    private static final String EMPTY_STRING = "";

    @Inject
    private static UserService userService;

    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/allUsers", ADMIN);
        protectedUrls.put("/servlet/deleteUser", ADMIN);
        protectedUrls.put("/servlet/addItem", ADMIN);
        protectedUrls.put("/servlet/addItemToBucket", USER);
        protectedUrls.put("/servlet/bucket", USER);
        protectedUrls.put("/servlet/deleteFromBucket", USER);
        protectedUrls.put("/servlet/completeOrder", USER);
        protectedUrls.put("/servlet/allOrders", USER);
        protectedUrls.put("/servlet/deleteOrder", USER);
        protectedUrls.put("/servlet/getItemsFromOrder", USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            processUnAuthenticated(req, resp);
            return;
        }

        String requestedUrl = req.getRequestURI().replace(req.getContextPath(), EMPTY_STRING);
        Role.RoleName roleName = protectedUrls.get(requestedUrl);
        if (roleName == null) {
            processAuthenticated(filterChain, req, resp);
            return;
        }
        String token = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("Mate")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null) {
            processUnAuthenticated(req, resp);
        } else {
            Optional<User> user = userService.getByToken(token);
            if (user.isPresent()) {
                if (verifyRole(user.get(), roleName)) {
                    processAuthenticated(filterChain, req, resp);
                } else {
                    processDenied(req, resp);
                }
            } else {
                processUnAuthenticated(req, resp);
            }
        }
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles()
                .stream()
                .anyMatch(r -> r.getRoleName().equals(roleName));
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    private void processAuthenticated(FilterChain chain,HttpServletRequest req,
                            HttpServletResponse resp) throws IOException, ServletException {
        chain.doFilter(req, resp);
    }

    private void processUnAuthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {

    }
}
