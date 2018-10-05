import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

@WebServlet(name = "CountServlet", urlPatterns = "/count")
public class CountServlet extends HttpServlet {
    static int count = 0;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            res.setContentType("text/html");
            Boolean reset = parseBoolean(req.getParameter("reset"));

//            count++;
//            if(req.getParameterMap().containsValue("reset")) {
//                count++;
//            }
//
            if (reset) {
//                int resetNum = Integer.valueOf(reset);
                count = 0;
            } else {
                count++;
            }

//            String who = req.getParameter("name");
            PrintWriter out = res.getWriter();
            out.println("<h1>The current count is " + count + "!</h1>");
        }
    }

