package com.zovlanik.restapi.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DatabaseDemo extends HttpServlet {

    static final String GET_ALL_DEVELOPERS_RECORDS = "SELECT * FROM developers";
    private final MySQLDBWorker dbWorker = new MySQLDBWorker();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Database Demo";
        String docType = "<!DOCTYPE html>";

        try {
            Connection connection = dbWorker.getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(GET_ALL_DEVELOPERS_RECORDS);

            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>DEVELOPERS DATA</h1>");
            writer.println("<br/>");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String specialty = resultSet.getString(4);
                int experience = resultSet.getInt(5);
                int salary = resultSet.getInt(6);


                writer.println("ID: " + id);
                writer.println("First name: " + firstName + "<br/>");
                writer.println("Last name: " + lastName + "<br/>");
                writer.println("Specialty: " + specialty + "<br/>");
                writer.println("Experience: " + experience + " years<br/>");
                writer.println("Salary: $" + salary + "<br/>");
                writer.println("<br/>");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
