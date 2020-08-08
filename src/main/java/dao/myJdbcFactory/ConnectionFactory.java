package dao.myJdbcFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 创建连接的工厂类
 */
public class ConnectionFactory {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DataSourceHolder.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
