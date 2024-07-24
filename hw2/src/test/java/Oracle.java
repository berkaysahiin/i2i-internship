import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oracle {
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USERNAME = "sys as sysdba";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            measureTime(connection, 20000);
            measureTime(connection, 100000);
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    private static void measureTime(Connection connection, int count) {
        try {
            // Create table
            try (PreparedStatement stmt = connection.prepareStatement("CREATE TABLE Numbers (num NUMBER)")) {
                stmt.execute();
            }

            // Batch inserting random numbers
            long startTime = System.nanoTime();
            try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Numbers (num) VALUES (?)")) {
                for (int i = 0; i < count; i++) {
                    int randomNum = (int) (Math.random() * 100000);
                    pstmt.setInt(1, randomNum);
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
            long endTime = System.nanoTime();
            System.out.println("ORACLE: Time taken to insert " + count + " numbers: " + (endTime - startTime) + " ns");

            // Selecting numbers
            startTime = System.nanoTime();
            try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Numbers");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int num = rs.getInt("num");
                }
            }
            endTime = System.nanoTime();
            System.out.println("ORACLE: Time taken to select " + count + " numbers: " + (endTime - startTime) + " ns");

            // Drop the table
            try (PreparedStatement stmt = connection.prepareStatement("DROP TABLE Numbers")) {
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
