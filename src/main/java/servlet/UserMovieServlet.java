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
import service.BaseServiceImpl;
import service.MovieService;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class UserMovieServlet extends HttpServlet {

    private final BaseServiceImpl<Long, Movie> movieService = new MovieService();
    private final BaseServiceImpl<Long, User> userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userID = Long.parseLong(req.getParameter("userID"));
        Long movieID = Long.parseLong(req.getParameter("movieID"));

        User user = userService.findById(userID).orElse(null);
        Movie movie = movieService.findById(movieID).orElse(null);

        if (user != null && movie != null) {
            Set<Movie> movies = user.getMovies();
            if (movies == null) {
                movies = new HashSet<>();
            }
            movies.add(movie);
            user.setMovies(movies);
            userService.update(userID, user);

            resp.sendRedirect("user-movies?userID=" + userID);
        } else {
            resp.getWriter().println("<p style='color:red;'>enter user or movie correctly!</p>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<head><title>WatchList</title></head>");
        writer.println("<body>");
        writer.println("<h3>USER'S WATCHLIST</h3>");
        writer.println("<br>");
        writer.println("<form method='post'>");
        writer.println("<label for='userID'>User ID:</label>");
        writer.println("<input type='text' name='userID' placeholder='enter the userID'>");
        writer.println("<label for='movieID'>Movie ID:</label>");
        writer.println("<input type='text' name='movieID' placeholder='enter the movieID'>");
        writer.println("<input type='submit' value='Add Movie'>");
        writer.println("</form>");
        writer.println("<br>");

         Long userID = Long.parseLong(req.getParameter("userID"));
         User user = userService.findById(userID).orElse(null);

            if (user != null && user.getMovies() != null && !user.getMovies().isEmpty()) {

                writer.println("<table style=border-collapse: collapse; border=1>");
                writer.println("<tr>");
                writer.println("<th>ID</th>");
                writer.println("<th>Title</th>");
                writer.println("<th>Genre</th>");
                writer.println("<th>Duration</th>");
                writer.println("</tr>");

                for (Movie movie : user.getMovies()) {
                    writer.println("<tr>");
                    writer.println("<td>" + movie.getId() + "</td>");
                    writer.println("<td>" + movie.getTitle() + "</td>");
                    writer.println("<td>" + movie.getGenre() + "</td>");
                    writer.println("<td>" + movie.getDuration() + "</td>");
                    writer.println("</tr>");
                }

                writer.println("</table>");
            } else {
                writer.println("<p style='color:red;'>There are no movies in the user's watchlist.</p>");
            }

        writer.println("</body>");
        writer.println("</html>");
    }
}
