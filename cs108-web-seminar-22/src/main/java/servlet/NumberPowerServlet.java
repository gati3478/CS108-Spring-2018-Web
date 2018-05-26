package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("WeakerAccess")
@WebServlet(name = "NumberPowerServlet", value = "/NumberPower")
public class NumberPowerServlet extends HttpServlet {
    public static final String NUMBER = "number";
    public static final String POWER = "pow";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberParam = request.getParameter(NUMBER);
        String powerParam = request.getParameter(POWER);

        PrintWriter out = response.getWriter();

        try {
            Double number = Double.parseDouble(numberParam);
            Double power = Double.parseDouble(powerParam);

            Double result = Math.pow(number, power);

            out.append(result.toString());
        } catch (NumberFormatException e) {
            out.append("NaN");
        }

    }

}
