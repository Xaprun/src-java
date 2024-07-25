package com.example;

import java.io.IOException;
import java.util.Set;
import java.util.Map;

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
            String pong = jedis.ping();
            if ("PONG".equals(pong)) {
                response.getWriter().println("<li>Connected to Redis successfully: PONG</li>");
                // Pobranie wszystkich kluczy
                Set<String> keys = jedis.keys("*");
                for (String key : keys) {
                    String type = jedis.type(key);
                    response.getWriter().println("<li>" + key + " (Type: " + type + "): ");

                    switch (type) {
                        case "string":
                            response.getWriter().println(jedis.get(key));
                            break;
                        case "list":
                            response.getWriter().println(jedis.lrange(key, 0, -1).toString());
                            break;
                        case "set":
                            response.getWriter().println(jedis.smembers(key).toString());
                            break;
                        case "hash":
                            response.getWriter().println(jedis.hgetAll(key).toString());
                            break;
                        case "zset":
                            response.getWriter().println(jedis.zrange(key, 0, -1).toString());
                            break;
                        default:
                            response.getWriter().println("(unknown type)");
                    }
                    response.getWriter().println("</li>");
                }
            } else {
                response.getWriter().println("<li>Could not connect to Redis: Unexpected response: " + pong + "</li>");
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
