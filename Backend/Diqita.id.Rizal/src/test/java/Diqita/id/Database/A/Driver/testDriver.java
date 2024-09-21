package Diqita.id.Database.A.Driver;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class testDriver {

    @Test
    void testingKoneksi() throws SQLException {

        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);

    }
}
