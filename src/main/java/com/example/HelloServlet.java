package com.example;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<style>");
        response.getWriter().println("body { background-color: blue; color: white; font-family: Arial, sans-serif; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>System Information</h1>");
        response.getWriter().println("<ul>");
        response.getWriter().println("<li>OS: " + System.getProperty("os.name") + "</li>");
        response.getWriter().println("<li>OS Version: " + System.getProperty("os.version") + "</li>");
        response.getWriter().println("<li>OS Architecture: " + System.getProperty("os.arch") + "</li>");
        response.getWriter().println("<li>Available processors (cores): " + Runtime.getRuntime().availableProcessors() + "</li>");
        response.getWriter().println("<li>Free memory (bytes): " + Runtime.getRuntime().freeMemory() + "</li>");
        response.getWriter().println("<li>Total memory (bytes): " + Runtime.getRuntime().totalMemory() + "</li>");
        response.getWriter().println("<li>Max memory (bytes): " + Runtime.getRuntime().maxMemory() + "</li>");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            response.getWriter().println("<li>IP Address: " + ip.getHostAddress() + "</li>");
            response.getWriter().println("<li>Host Name: " + ip.getHostName() + "</li>");
        } catch (UnknownHostException e) {
            response.getWriter().println("<li>IP Address: Unable to get IP address</li>");
        }
        response.getWriter().println("</ul>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
