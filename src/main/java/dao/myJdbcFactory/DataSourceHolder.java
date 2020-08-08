package dao.myJdbcFactory;


import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * 创建数据源即加载驱动的名称与连接数据库的信息
 */
public class DataSourceHolder {

    private static BasicDataSource ds;

    public static DataSource getDataSource(){
        if(ds == null){
            ds = new BasicDataSource();
            //链接数据库的4个最基本信息，通过对象的set方法进行设置如下：
            ds.setDriverClassName(GetDbProperties.DRIVERNAME);                    //设置数据库驱动
            ds.setUrl(GetDbProperties.URL);                                        //设置访问数据库的路径
            ds.setUsername(GetDbProperties.USERNAME);                            //设置登录数据库的用户名
            ds.setPassword(GetDbProperties.PASSWORD);                            //设置登录数据库的密码
            //对象连接池中的常见配置项，以下的四个配置可以不配置(因为有默认配置)，但是上面的四个是必须要配置的！
            ds.setInitialSize(GetDbProperties.INITIALSIZE);            //指定初始化的连接数
            ds.setMaxIdle(GetDbProperties.MAXIDLE);                  //指定最大链接数量
            ds.setMaxIdle(GetDbProperties.MINLDEL);                    //指定最大空闲数
            ds.setMinIdle(GetDbProperties.MAXACTIVE);               //指定最小空闲数
            return ds;
        }
        return ds;
    }

}
