import logic.ConnectionDataBaseMovies;

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

public class MyMovies extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        Connection connection = ConnectionDataBaseMovies.createConnection();
        pw.println("<html>");
        pw.println("<link href=\"style.css\" rel=\"stylesheet\">");
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM movies");
                while (rs.next()) {
                    pw.println("<h2>"
                            + rs.getString("id") + " "
                            + rs.getString("name") + " "
                            + rs.getString("year") + " "
                            + rs.getString("director")
                            + "</h2>");
                }
                statement.close();
            }
            else pw.println("Connection failed");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            pw.println("SQLException: " + throwables.getMessage());
        }

        pw.println("<form>\n" +
                "<input type=\"button\" value=\" What do you want to watch? \" onClick='location.href=\"/want_to_watch\"'>\n" +
                "</form>");

        pw.println("<html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
