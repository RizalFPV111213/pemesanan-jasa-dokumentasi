package Diqita.id.Database.D.ConnectionUtil;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {  private static HikariDataSource dataSource;

    static {
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

        dataSource = new HikariDataSource(testConnectionPool);

    }

    public static HikariDataSource getDataSource(){
        return dataSource;
    }

}