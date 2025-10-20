package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movie;
import model.User;
import service.BaseServiceImpl;
import service.MovieService;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class UserMovies extends HttpServlet {

    private final BaseServiceImpl<Long, Movie> movieService = new MovieService();
    private final BaseServiceImpl<Long, User> userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdStr = req.getParameter("userId");
        String movieIdStr = req.getParameter("movieId");

        if (userIdStr == null || movieIdStr == null) {
            resp.getWriter().println("<p style='color:red;'>invalid userId or movieId!</p>");
            return;
        }

        Long userId = Long.parseLong(userIdStr);
        Long movieId = Long.parseLong(movieIdStr);
        User user = userService.findById(userId).orElse(null);
        Movie movie = movieService.findById(movieId).orElse(null);

        if (user != null && movie != null) {
            Set<Movie> movies = user.getMovies();
            if (movies == null) {
                movies = new HashSet<>();

            }
            movies.add(movie);
            user.setMovies(movies);
            userService.update(userId, user);

            resp.sendRedirect("user-movies?userId=" + userId);
        } else {
            resp.getWriter().println("<p style='color:red;'>enter user or movie correctly!</p>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userIdStr = req.getParameter("userId");
        if (userIdStr == null) {
            resp.getWriter().println("<p style='color:red;'>invalid userId!</p>");
            return;
        }

        Long userId = Long.parseLong(userIdStr);
        User user = userService.findById(userId).orElse(null);
        if (user == null) {
            resp.getWriter().println("<p style='color:red;'>User is not found!</p>");
            return;
        }
        String contextPath = req.getContextPath();
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<link rel='stylesheet' href='" + contextPath + "/front/style/bootstrap.min.css'>");
        out.println("</head>");
        out.println("<body class='bg-light'>");
        out.println("<nav class='navbar bg-white shadow-sm fixed-top'>");
        out.println("<div class='container d-flex justify-content-between align-items-center'>");
        out.println("<div class='d-flex align-items-center'>");
        out.println("<h4 class='ms-3 mb-0 text-primary'>Watchlist</h4>");
        out.println("</div>");
        out.println("</form>");
        out.println("</div>");
        out.println("</nav>");
        out.println("<div class='container mt-5 pt-5'>");
        out.println("<h3>Logged in as " + user.getUsername() + "</h3>");
        out.println("<div class='row g-3'>");

        for (Movie movie : user.getMovies()) {
            out.println("<div class='col-md-4 col-lg-4'>");
            out.println("<figure class='movie-card'>");
            out.println("<figcaption class='text-center'>");
            out.println("<h6 class='fw-bold mb-1'>" + movie.getTitle() + "</h6>");
            out.println("<p class='mb-1 text-muted'>" + movie.getGenre() + " | Rating: " + movie.getRating() + "</p>");
            out.println("<p class='mb-1 text-muted'>" + movie.getDescription() + "</p>");

            out.println("<form action='" + contextPath + "/front/Admin-MovieLibrary' method='post' style='display:inline;'>");
            out.println("<input type='hidden' name='action' value='delete'>");
            out.println("<input type='hidden' name='id' value='" + movie.getId() + "'>");
            out.println("<input type='submit' value='Delete' class='btn btn-danger'>");
            out.println("</form>");

            out.println("</figcaption>");
            out.println("</figure>");
            out.println("</div>");
        }
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
