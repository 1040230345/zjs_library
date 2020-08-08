package dao;

import dao.impl.BaseDao;
import dao.impl.JdbcTemplateAdapter;
import dao.myJdbcFactory.ConnectionFactory;
import java.sql.Connection;

public  class AbstractNewDao<T> extends BaseDao<T> {

    //适配器
    public IDaoAdapter getAdapter() {
        return new JdbcTemplateAdapter() {
            protected Connection getConnection() {
                return ConnectionFactory.getConnection();
            }
        };
    }
}
