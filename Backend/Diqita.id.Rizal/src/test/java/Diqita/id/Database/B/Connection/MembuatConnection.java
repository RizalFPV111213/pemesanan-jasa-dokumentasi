package Diqita.id.Database.B.Connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MembuatConnection {

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/photographyservice";
        String username = "root";
        String password = "";
        try {
            Connection testConection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("SUKSES");
            testConection.close();
            System.out.println("SUKSES MENUTUP");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }
}
