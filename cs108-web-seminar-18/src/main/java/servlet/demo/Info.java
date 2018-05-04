package servlet.demo;

import java.io.*;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Info
 */
@WebServlet(name = "InfoServlet", value = "/Info")
public class Info extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        out.println("Query String:");
        out.println(req.getQueryString());
        out.println();

        out.println("Request Parameters:");
        Enumeration<String> enumeration = req.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String values[] = req.getParameterValues(name);
            if (values != null) {
                for (int i = 0; i < values.length; ++i) {
                    out.println(name + " (" + i + "): " + values[i]);
                }
            }
        }

        out.println("path \"" + req.getServletPath() + "\"");

        out.println("Request Headers:");
        out.println();
        Enumeration names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            Enumeration values = req.getHeaders(name);  // support multiple values
            if (values != null) {
                while (values.hasMoreElements()) {
                    String value = (String) values.nextElement();
                    out.println(name + ": " + value);
                }
            }
        }
    }

}
