package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.Driver;
import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // this class is made for creating new database connections
/*
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                AppProperties.DB_URL,
                AppProperties.DB_USER,
                AppProperties.DB_PASSWORD
        );
    }

    -- Deprecated version only for small databases
 */

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(AppProperties.DB_URL);
        config.setUsername(AppProperties.DB_USER);
        config.setPassword(AppProperties.DB_PASSWORD);
        config.setMaximumPoolSize(AppProperties.DB_POOL_MAX_SIZE);
        config.setMinimumIdle(AppProperties.DB_POOL_MIN_IDLE);

        dataSource = new HikariDataSource(config);
        System.out.println("Hikari Pool created!");
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Connection returned!");
        return dataSource.getConnection();
    }

    public static void closePool() {
        dataSource.close();
    }

    static void main() throws SQLException {

        System.out.println(DatabaseConfig.getConnection());

    }
}
