package servlet;

import db.dao.MetropolisDAO;
import db.bean.Metropolis;
import listener.ContextKey;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Metropolises", urlPatterns = {"/Metropolises", "/index.html"})
public class Metropolises extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Getting session
        HttpSession session = request.getSession();
        String cityName = (String) session.getAttribute("city");

        // Getting context
        ServletContext sc = getServletContext();
        MetropolisDAO dao = (MetropolisDAO) sc.getAttribute(ContextKey.DAO);

        // Getting lists from DAO
        List<Metropolis> metropolisesEurope = dao.getMetropolises("Europe");
        List<Metropolis> metropolises = dao.getMetropolises();

        // Writing response
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<title>Metropolises DataSource Example</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>You are from " + cityName + "</h3>");
        out.println("<br />");
        out.println("<br />");
        out.println("<h3>Here are other metropolises from Europe:</h3>");
        out.println("<table border=\"1px\">");
        printTable(out, metropolisesEurope);
        out.println("</table>");
        out.println("<br />");
        out.println("<h3>Here are all metropolises:</h3>");
        out.println("<table border=\"1px\">");
        printTable(out, metropolises);
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    private void printTable(PrintWriter out, List<Metropolis> metropolises) {
        for (Metropolis city : metropolises) {
            out.println("<tr>");
            out.println("   <td>" + city.getName() + "</td>");
            out.println("   <td>" + city.getPopulation() + "</td>");
            out.println("   <td>" + city.getContinent() + "</td>");
            out.println("</tr>");
        }
    }

}
