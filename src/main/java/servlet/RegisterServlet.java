package servlet;

import builder.UserBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Role;
import model.User;
import service.BaseServiceImpl;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class RegisterServlet extends HttpServlet {
    private final BaseServiceImpl<Long,User> userService = new UserService();
    private final UserBuilder userBuilder = new UserBuilder();

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (username==null || password==null || email==null || role==null){
            req.getRequestDispatcher("front/register.html").forward(req, resp);
        }
        Role role1 = Role.valueOf(role);
        User user = new User(username,email,password,new HashSet<>(),role1);
        try {
            userService.save(user);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>new-user</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h3 style='color: green'> user is registered successfully</h3>");
        writer.println("</body>");
        writer.println("</html>");

        writer.close();

    }

}
