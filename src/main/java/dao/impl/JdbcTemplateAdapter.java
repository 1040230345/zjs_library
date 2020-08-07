package dao.impl;

import dao.IDaoAdapter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

/**
 * 适配器实现类
 */
public abstract class JdbcTemplateAdapter implements IDaoAdapter {

    /**
     * 实现查询功能
     */
    public <K> K queryForObject(String sql, Class<K> requiredType, Object... args){

        Assert.isNull(sql, "sql is not null");

        //处理sql
        sql = sql + " where " + args.toString();


        return null;
    }


    abstract JdbcTemplate getJdbcTemplate();
}
