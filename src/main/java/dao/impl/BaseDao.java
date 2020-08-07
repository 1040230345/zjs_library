package dao.impl;

import Annotatons.Table;
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
    public int count() {
        //获取传进来的对象
        tClass = getTClass();
        String sql = " select count(*) from " +getTableName(tClass);

        System.out.println(sql);
        return 0;
    }

    /**
     * 从注解中获取表名
     */
    private String getTableName(Class<T> tClass){
        Table table = tClass.getAnnotation(Table.class);
        if(table==null){
            throw new RuntimeException("该实体类必须包含表名才能使用该方法  tClazz=" + tClass.getName());
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
}
