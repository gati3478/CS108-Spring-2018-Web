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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        out.println("Query String:");
        out.println(request.getQueryString());
        out.println();

        out.println("Request Parameters:");
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String[] values = request.getParameterValues(name);
            if (values != null) {
                for (int i = 0; i < values.length; ++i) {
                    out.println(name + " (" + i + "): " + values[i]);
                }
            }
        }

        out.println("path \"" + request.getServletPath() + "\"");

        out.println("Request Headers:");
        out.println();
        Enumeration names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            Enumeration values = request.getHeaders(name);  // support multiple values
            if (values != null) {
                while (values.hasMoreElements()) {
                    String value = (String) values.nextElement();
                    out.println(name + ": " + value);
                }
            }
        }
    }

}
