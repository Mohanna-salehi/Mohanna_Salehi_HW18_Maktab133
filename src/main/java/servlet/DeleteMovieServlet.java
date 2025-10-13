package servlet;

import builder.MovieBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movie;

import service.MovieService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DeleteMovieServlet extends HttpServlet {
    MovieService m = new MovieService();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long deleteID = Long.parseLong(req.getParameter("movieID"));
            m.delete(deleteID);
            resp.getWriter().println(deleteID+ " is just deleted successfully");
            resp.sendRedirect("delete-movie");
           // doGet(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<head><title>MovieList</title></head>");
        writer.println("<body>");
        writer.println("<h3>DELETE MOVIE</h3>");
        writer.println("<br>");
        writer.println("<form method='post'>");
        writer.println("<label for='movieID'>Movie ID:</label>");
        writer.println("<input type='text' name='movieID' placeholder='enter the movieID'>");
        writer.println("<input type='submit' value='delete Movie'>");
        writer.println("</form>");
        writer.println("<br>");


        List<Movie> movies = m.findAll();

            writer.println("<table style='border=1>");
            writer.println("<tr>");
            writer.println("<th>ID</th>");
            writer.println("<th>Title</th>");
            writer.println("<th>Genre</th>");
            writer.println("<th>Duration</th>");
            writer.println("</tr>");

            for (Movie movie : movies) {
                writer.println("<tr>");
                writer.println("<td>" + movie.getId() + "</td>");
                writer.println("<td>" + movie.getTitle() + "</td>");
                writer.println("<td>" + movie.getGenre() + "</td>");
                writer.println("<td>" + movie.getDuration() + "</td>");
                writer.println("</tr>");
            }

            writer.println("</table>");

        writer.println("</body>");
        writer.println("</html>");
    }
}

