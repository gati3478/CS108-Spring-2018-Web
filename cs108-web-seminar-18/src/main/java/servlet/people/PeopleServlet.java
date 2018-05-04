package servlet.people;

import model.Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PeopleServlet", urlPatterns = {"/PeopleServlet", "/index.html"})
public class PeopleServlet extends HttpServlet {

    /**
     * The list keeps information about added people. DO NOT do this in real
     * applications  as the servlet is not thread-safe. Servlet container usually
     * uses one instance of a servlet class to serve multiple requests.
     */
    private List<Person> list = new ArrayList<>();


    /**
     * Handles the HTTP
     * <code>GET</code> method.
     * <p>
     * Shows form for adding people and lists existing ones.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set content type as html and encoding as unicode.
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Person Registration Form</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Person Registration Form</h1>");
            out.println("<form action=\"PeopleServlet\" method=\"post\">");
            out.println("   First name: <input type=\"text\" name=\"first_name\">");
            out.println("   <br />");
            out.println("   Last name: <input type=\"text\" name=\"last_name\">");
            out.println("   <br />");
            out.println("   Phone: <input type=\"text\" name=\"phone\">");
            out.println("   <br />");
            out.println("   <input type=\"radio\" name=\"phone_type\" value=\"Mobile\"> Mobile");
            out.println("   <input type=\"radio\" name=\"phone_type\" value=\"Home\"> Home");
            out.println("   <br />");
            out.println("   <br />");
            out.println("   <input type=\"submit\" value=\"Submit Order\">");
            out.println("</form>");

            out.println("<br />");
            out.println("<br />");

            out.println("<table border=\"1px\">");

            for (Person person : list) {
                out.println("<tr>");
                out.println("   <td>" + person.getFirstName() + "</td>");
                out.println("   <td>" + person.getLastName() + "</td>");
                out.println("   <td>" + person.getPhone() + "</td>");
                out.println("   <td>" + person.getPhoneType() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     * <p>
     * Receives person information from http request, creates person instance
     * and saves it in the list.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Person person = new Person();
        person.setFirstName(request.getParameter("first_name"));
        person.setLastName(request.getParameter("last_name"));
        person.setPhone(request.getParameter("phone"));
        person.setPhoneType(request.getParameter("phone_type"));

        list.add(person);

        doGet(request, response);
    }

}
