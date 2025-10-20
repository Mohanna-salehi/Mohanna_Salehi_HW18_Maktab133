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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

