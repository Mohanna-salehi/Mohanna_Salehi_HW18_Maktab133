package servlet;

import builder.MovieBuilder;
import builder.UserBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movie;
import model.User;
import service.BaseService;
import service.BaseServiceImpl;
import service.MovieService;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {
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

        try {
            User user = userBuilder.reset()
                    .setName(username)
                    .setEmail(email)
                    .setPassword(password).result();
            userService.save(user);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("users");
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
        writer.println("<h3>NEW USER</h3>");
        writer.println("<br>");
        writer.println("<form method=post>");
        writer.println("<lable for=username>username</lable>");
        writer.println("<input type=text name=username placeholder=enter the username>");
        writer.println("<lable for=email>email</lable>");
        writer.println("<input type=text name=email placeholder=enter the email>");
        writer.println("<lable for=password>password</lable>");
        writer.println("<input type=text name=password placeholder=enter the password>");
        writer.println("<input type=submit>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");

        writer.close();

    }

}
