package dao.impl;

import dao.IDaoAdapter;
import dao.myJdbcFactory.DBClose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 适配器实现类
 */
public abstract class JdbcTemplateAdapter implements IDaoAdapter {

    /**
     * 实现查询功能
     */
    public <K> K queryForObject(String sql, Class<K> requiredType, Object... args){
        Connection conn = getConnection();//获取数据库连接
        Statement stmt = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                return (K) rs.getObject(1);
            }
            throw new RuntimeException("查询异常");
        } catch (SQLException e) {
            throw new RuntimeException("查询数据库失败{}",e);
        }finally {
            DBClose.close(conn);//关闭数据库
        }
    }


    protected abstract Connection getConnection();
}
