package servlet;

import builder.MovieBuilder;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Movie;

import service.BaseServiceImpl;
import service.MovieService;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

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
        String description = req.getParameter("duration");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        Double rating = Double.parseDouble(req.getParameter("rating"));

        try {
            Movie movie = movieBuilder.reset()
                    .setTitle(title)
                    .setGenre(genre)
                    .setDescription(description).setDate(date).setRating(rating).result();
            movieService.save(movie);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("front/addMovie.html").forward(req, resp);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>new-movie</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h3 style='color: green'> movie is added successfully</h3>");
        writer.println("</body>");
        writer.println("</html>");

        writer.close();

    }

}

