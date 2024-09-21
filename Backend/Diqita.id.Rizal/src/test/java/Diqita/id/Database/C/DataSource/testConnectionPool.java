package Diqita.id.Database.C.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class testConnectionPool {

    @Test
    void testConnectionPoolHikari() {

        HikariConfig testConnectionPool = new HikariConfig();
        testConnectionPool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        testConnectionPool.setJdbcUrl("jdbc:mysql://localhost:3306/photographyservice");
        testConnectionPool.setUsername("root");
        testConnectionPool.setPassword("");
        // Knfigurasi Pool
        testConnectionPool.setMaximumPoolSize(10);
        testConnectionPool.setMinimumIdle(5);
        testConnectionPool.setIdleTimeout(60_000);
        testConnectionPool.setMaxLifetime(10 * 60_000);
        //Membuat COnection Pool
        try {
            HikariDataSource dataSource = new HikariDataSource(testConnectionPool);
            Connection connection = dataSource.getConnection();
            System.out.println("SUKSES MENGAMBIL CONECTION");
            connection.close();
            System.out.println("SUKSES MENGEMBALIKAN KONEKSI");
            dataSource.close();
            System.out.println("SUKSES MENUTUP CONECTION POOL");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }
}
