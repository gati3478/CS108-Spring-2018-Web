package servlet.demo;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class RefreshDemo
 */
@WebServlet(name = "RefreshDemoServlet", value = "/RefreshDemo")
public class RefreshDemo extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        res.setHeader("Refresh", "1");
        out.println(new Date().toString());
    }

}
