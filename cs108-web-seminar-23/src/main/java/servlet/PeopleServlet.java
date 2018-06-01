package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PeopleServlet", value = "/PeopleServlet")
public class PeopleServlet extends HttpServlet {

    /**
     * The list keeps information about added people. DO NOT do this in real
     * applications  as the servlet is not thread-safe. Servlet container usually
     * uses one instance of a servlet class to serve multiple requests.
     */
    private List<Person> people = new ArrayList<>();


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
        String json = new Gson().toJson(people);

        // Set content type as html and encoding as unicode.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(json);
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

        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

        String firstName = data.get("first_name").getAsString();
        String lastName = data.get("last_name").getAsString();
        String phoneNumber = data.get("phone").getAsString();
        String phoneType = data.get("phone_type").getAsString();

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhone(phoneNumber);
        person.setPhoneType(phoneType);

        people.add(person);

        doGet(request, response);
    }

}
