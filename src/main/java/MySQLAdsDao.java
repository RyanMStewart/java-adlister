import java.sql.*;
import com.mysql.cj.jdbc.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySQLAdsDao implements Ads {
    private Connection connection;
    private List<Ad> ads;

    public MySQLAdsDao(Config config) throws SQLException {
        this.ads = new ArrayList<>();
        DriverManager.registerDriver(new Driver());
        this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
        );
    }

    public List<Ad> all() throws SQLException {
//        Scanner getText = new Scanner(System.in);
//        System.out.println("Enter the SELECT query: ");
        List<Ad> adHistory = new ArrayList<>();
        String query = "SELECT * FROM ads";
        Statement stmt = connection.createStatement();
//        stmt.execute("")
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            Ad ad = new Ad(rs.getLong("user_id"),
                    rs.getString("title"),
                    rs.getString("description")
            );
            adHistory.add(ad);
        }
        return adHistory;
    }

    public Long insert(Ad ad) throws SQLException {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter the query to insert a new record: ");
//        String query = scan.nextLine();

        String query = "INSERT INTO ads(user_id, title, description) VALUES ('" + ad.getUserId() + "', '" + ad.getTitle() + "', '" + ad.getDescription() + "');";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query , Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
//        if (rs.next()) {
//            return rs.getLong(1);
//        }
            if (rs.next()) {
                System.out.println("Inserted a new record. New id: " + rs.getLong(1));
            }
            return rs.getLong(1);
    }
}
