package dao;

/**
 * 主要包含对应表的增删改查的一些默认操作。
 * find**:查询类
 * count**:统计类
 * update**:更新类
 * delete**:删除类
 * insert**:插入类
 * handle**:其他的自定义原始sql操作类
 * 参数中没声明的默认都是Condition.Equal,AndOr.And的规则
 * 表bean中的排序 请通过Sorts注解来标识排序，数字越大，排的越前
 *
 * 注意事项：
 * 如果要增加方法，一定要按上面的规则增加！！！而且不要加那种业务耦合的方法进来
 * @author zj
 *
 * @param <T>	与表中的字段一一对应，且包含表名、主键、注释等信息
 */
public interface IDao<T> {

    /**
     * 统计总数
     * @return
     */
    Long count();

    /**
     * 按条件统计总数
     */
    Long count(String conditions,Object... args);

    /**
     * 获取适配器
     * 这个由子类提供
     */
    public IDaoAdapter getAdapter();





}
