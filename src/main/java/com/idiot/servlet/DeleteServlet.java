package com.idiot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
    PrintWriter printWriter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        PrintWriter printWriter = res.getWriter();
        res.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task", "root", "akshay@123");

            PreparedStatement preparedStatement = connection.prepareStatement(" DELETE FROM Taskdata WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            printWriter.println("<h2>Record is Deleted  Succesfully</h2>");


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        printWriter.println("<a href='home.html'>Home</a>");
        printWriter.println("<br>");
        printWriter.println("<a href='TaskList'>Task List List</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}

