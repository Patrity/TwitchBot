package sql;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public class Database {

    public final String host, user, pass;
    public static HikariDataSource connection;

    public void connect() {
        System.out.println("Database Connected");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(host);
        config.setUsername(user);
        config.setPassword(pass);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        connection = new HikariDataSource(config);
    }

    public Connection get() throws SQLException {
        return connection.getConnection();
    }
}
