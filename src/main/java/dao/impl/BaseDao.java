package dao.impl;

import Annotatons.dao.Table;
import dao.IDao;

import java.lang.reflect.ParameterizedType;

/**
 * 实现类，暴露接口
 * 同时屏蔽了持久层的实现，持久层放到了适配器层去处理
 * 抽象类的原因是因为允许存在抽象方法不用去实现
 * IDao 中的 getAdapter（获取适配器）留给使用者去实现，实现解耦
 */
public abstract class BaseDao<T> implements IDao<T> {

    private Class<T> tClass;

    /**
     * 统计总条数
     * @return
     */
    public Long count() {
        //获取传进来的对象
        tClass = getTClass();
        String sql = " select count(*) from " +getTableName(tClass);
        return handleQueryForObject(sql,Long.class,null);
    }

    /**
     * 按条件统计条数
     */
    public Long count(String conditions,Object... args){
        //获取传进来的对象
        tClass = getTClass();
        String sql = " select count(*) from " +getTableName(tClass)+" where "+conditions;
        return handleQueryForObject(sql,Long.class,args);
    }

    /**
     * 从注解中获取表名
     */
    private String getTableName(Class<T> tClass){
        //获取注解
        Table table = tClass.getAnnotation(Table.class);
        if(table==null){
            return getClassName(tClass);
//            throw new RuntimeException("该实体类必须包含表名才能使用该方法  tClazz=" + tClass.getName());
        }
        return table.name();
    }

    /**
     * 判断 tClass 是否为空
     */
    private Class<T> getTClass(){
        if(tClass==null){
            return tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return tClass;
    }

    /**
     * 获取类名并进行转化
     */
    private String getClassName(Class<T> tClass){
        char[] nameC =  tClass.getSimpleName().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nameC.length; i++) {
            if((int)nameC[i]>=65&&(int)nameC[i]<97){
                nameC[i] = (char) ((char) 97+((int)nameC[i]-65));
                if(i!=0)
                    sb.append("_"+nameC[i]);
                else sb.append(nameC[i]);
            }else
                sb.append(nameC[i]);
        }
        return sb.toString();
    }

    /**
     * 执行一条sql语句
     * @param sql
     * @param requiredType
     * @param args
     * @param <T>
     * @return
     */
    public <T> T handleQueryForObject(String sql, Class<T> requiredType, Object... args){
        return getAdapter().queryForObject(sql,requiredType,args);
    }
}
