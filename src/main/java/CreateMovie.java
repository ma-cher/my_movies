import logic.ConnectionDataBaseMovies;
import logic.TestMain;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateMovie extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        getServletContext().getRequestDispatcher("/jsp/receiveWant.jsp").forward(req, resp); // при гет запросе, т.е. переходе на страницу /want, сервелт будет перенаправлять на jsp receiveWant для отображения формы заполнения
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        int year = 0;

        String director = req.getParameter("director");

        PrintWriter pw = resp.getWriter();

        Connection connection = ConnectionDataBaseMovies.createConnection();

        try {
            if(connection != null){
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO `my_movies`.`movies` (`name`, `year`, `director`) VALUES ('" +
                        name +
                        "', '" +
                        year +
                        "', '" +
                        director +
                        "');");
                statement.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
           pw.println(throwables.getMessage());
        }

       getServletContext().getRequestDispatcher("/jsp/receiveWant.jsp").forward(req, resp);

    }
}
