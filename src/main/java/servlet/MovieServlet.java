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

public class MovieServlet  extends HttpServlet {
    private final BaseServiceImpl<Long,Movie> movieService = new MovieService();
    MovieBuilder movieBuilder = new MovieBuilder();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String genre = req.getParameter("genre");
        Integer duration = Integer.parseInt(req.getParameter("duration"));

        try {
            Movie movie = movieBuilder.reset()
                    .setTitle(title)
                    .setGenre(genre)
                    .setDuration(duration).result();
            movieService.save(movie);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("movies");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>new-movie</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<header style='width:100% ;height: 100px; background-color: lightcyan;align';text-align: center;>");
        writer.println("<h2>add movie </h2>");
        writer.println("</header>");
        writer.println("<div style='height: 500px'>");
        writer.println("<h3>NEW MOVIE</h3>");
        writer.println("<br>");
        writer.println("<form method=post>");
        writer.println("<lable for=title>title</lable>");
        writer.println("<input type=text name=title placeholder=enter the title>");
        writer.println("<lable for=genre>genre</lable>");
        writer.println("<input type=text name=genre placeholder=enter the genre>");
        writer.println("<lable for=duration>duration</lable>");
        writer.println("<input type=text name=duration placeholder=enter the duration>");
        writer.println("<input type=submit>");
        writer.println("</form>");
        writer.println("</div>");
        writer.println("<footer style=' background-color: lightcyan;width: 100%; height: 80px;'>");
        writer.println("<h2>movie page</h2>");
        writer.println("</footer>");
        writer.println("</body>");
        writer.println("</html>");

        writer.close();

    }



}
