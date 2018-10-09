import java.sql.SQLException;
import java.util.List;

public interface Ads {
    // get a list of all the ads
    void all() throws SQLException;
    // insert a new ad and return the new ad's id
//    Long insert(Ad ad) throws SQLException;
    Long insert() throws SQLException;
}
