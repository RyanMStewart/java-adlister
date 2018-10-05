import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginCheckServlet", urlPatterns = "/login")
public class LoginCheck extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//            String username = request.getParameter("username");
//                if (username.equalsIgnoreCase("admin")) {
//                    response.sendRedirect("/profile.jsp");
                }
        }
