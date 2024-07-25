package com.example;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<style>");
        response.getWriter().println("body { background-color: green; color: white; font-family: Arial, sans-serif; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Redis Data</h1>");
        response.getWriter().println("<ul>");

        // Połączenie z Redis
        try (Jedis jedis = new Jedis("redis-container", 6379)) {
            response.getWriter().println("<li>Connected to Redis</li>");
            // Pobranie wszystkich kluczy
            Set<String> keys = jedis.keys("*");
            for (String key : keys) {
                String value = jedis.get(key);
                response.getWriter().println("<li>" + key + ": " + value + "</li>");
            }
        } catch (Exception e) {
            response.getWriter().println("<li>Could not connect to Redis: " + e.getMessage() + "</li>");
            e.printStackTrace(response.getWriter());
        }

        response.getWriter().println("</ul>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
