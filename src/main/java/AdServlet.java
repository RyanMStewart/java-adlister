
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "adServlet", urlPatterns = "/ads")
public class AdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//        Ads adsDoa = DaoFactory.getAdsDoa();
        Ads adsDao = DaoFactory.getAdsDao();
        List<Ad> ads = adsDao.all();

        req.setAttribute("ads", ads);
        req.getRequestDispatcher("/ads/index.jsp").forward(req, res);
    }
}
