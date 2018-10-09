import java.sql.*;
import com.mysql.cj.jdbc.Driver;
import java.util.List;
import java.util.Scanner;

public class MySQLAdsDao implements Ads {
    private Connection connection;
    private static List<Ad> ads;

    public MySQLAdsDao(Config config) throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
        );
    }

    public void all() throws SQLException {
        Scanner getText = new Scanner(System.in);
        System.out.println("Enter the SELECT query: ");
        String query = getText.nextLine();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
//        while(rs.next()) {
//            MySQLAdsDao.ads.add(rs.getString("title"));
//        }
        while (rs.next()) {
            ads.add(rs);
        }
        while(rs.next()) {
            System.out.println("Here's an ad: ");
            System.out.println(" id: " + rs.getLong("id"));
            System.out.println(" title: " + rs.getString("title"));
        }

    }

    public Long insert() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the query to insert a new record: ");
        String query = scan.nextLine();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Inserted a new record. New id: " + rs.getLong(1));
            }
            return rs.getLong(1);
    }
}
