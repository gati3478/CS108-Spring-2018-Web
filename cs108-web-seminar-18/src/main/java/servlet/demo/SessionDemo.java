package servlet.demo;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class SessionDemo
 */
@WebServlet(name = "SessionDemoServlet", value = "/SessionDemo")
public class SessionDemo extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Get the current session object, create one if necessary
        HttpSession session = req.getSession();

        // Increment the hit count for this page. The value is saved
        // in this client's session under the name "tracker.count".
        Integer count = (Integer) session.getAttribute("tracker.count");
        if (count == null) {
            count = 1;
        } else {
            ++count;
        }
        session.setAttribute("tracker.count", count);

        out.println("<HTML><HEAD><TITLE>SessionTracker</TITLE></HEAD>");
        out.println("<BODY><H1>Session Tracking Demo</H1>");

        // Display the hit count for this page
        out.println("You've visited this page " + count +
                ((count.equals(1)) ? " time." : " times."));

        out.println("<P>");

        out.println("<H2>Here is your session data:</H2>");
        Enumeration<String> enumerator = session.getAttributeNames();
        while (enumerator.hasMoreElements()) {
            String name = enumerator.nextElement();
            out.println(name + ": " + session.getAttribute(name) + "<BR>");
        }

        out.println("<H3>Here are some vital stats on your session:</H3>");
        out.println("Session id: " + session.getId() +
                " <I>(keep it secret)</I><BR>");
        out.println("New session: " + session.isNew() + "<BR>");
        out.println("Timeout: " + session.getMaxInactiveInterval());
        out.println("<I>(" + session.getMaxInactiveInterval() / 60 +
                " minutes)</I><BR>");
        out.println("Creation time: " + session.getCreationTime());
        out.println("<I>(" + new Date(session.getCreationTime()) + ")</I><BR>");
        out.println("Last access time: " + session.getLastAccessedTime());
        out.println("<I>(" + new Date(session.getLastAccessedTime()) +
                ")</I><BR>");

        out.println("Requested session ID from cookie: " +
                req.isRequestedSessionIdFromCookie() + "<BR>");
        out.println("Requested session ID from URL: " +
                req.isRequestedSessionIdFromURL() + "<BR>");
        out.println("Requested session ID valid: " +
                req.isRequestedSessionIdValid() + "<BR>");

        out.println("<H3>Test URL Rewriting</H3>");
        out.println("Click <A HREF=\"" +
                res.encodeURL(req.getRequestURI()) + "\">here</A>");
        out.println("to test that session tracking works via URL");
        out.println("rewriting even when cookies aren't supported.");

        out.println("</BODY></HTML>");
    }

}
