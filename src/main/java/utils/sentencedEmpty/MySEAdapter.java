package utils.sentencedEmpty;

import Annotatons.util.AllNotNull;
import Annotatons.util.NotNull;

import java.lang.reflect.Field;

/**
 * 判空适配器
 * 屏蔽底层接口
 */
public abstract class MySEAdapter implements BaseImp{



    /**
     * 判空思路
     * 1、通过反射获取class
     * 2、获取所有变量
     * 3、判断是否有notnull
     * 4、如果有并且类型是String 去掉空格  !='' && !=null &&!=' ' 其他类型如果是包装类 !=null
     * @param date
     */
    public static <T> void isNotBlank(T date,UserCallBack userCallBack) throws Exception {
        //获取传入对象的所有变量
        Field[] fields = date.getClass().getDeclaredFields();
        AllNotNull allNotNull = date.getClass().getAnnotation(AllNotNull.class);
        if(allNotNull!=null){
            for (Field field : fields) {
                //赋予权限
                field.setAccessible(true);
                judge(field,userCallBack,date,null);
            }
        }
        for (Field field : fields) {
            //赋予权限
            field.setAccessible(true);
            //获取注解
            NotNull notNull = field.getAnnotation(NotNull.class);
            //注解为空
            if(notNull==null){
                continue;
            }
            judge(field,userCallBack,date,notNull);
        }
    }

    /**
     * 判断
     */
    private static  <T>void judge(Field field,UserCallBack userCallBack,T date,NotNull notNull) throws Exception {
        //获取对象类型
        Class<?> type = field.getType();

        //如果是String类型
        if(type==String.class){
            if(field.get(date)!=null){
                if (!field.get(date).toString().replaceAll(" +","").equals("")) {
                    return;
                }
            }

        }
        //如果是int
        if(type==Integer.class){
            if(field.get(date)!=null){
                return;
            }
        }
        //如果是int
        if(type == int.class){
            if((Integer)field.get(date)!=0){
                return;
            }
        }

        //回调或者异常
        if(userCallBack!=null){
            userCallBack.callBack();
        }else{
            if(notNull==null){
                throw new Exception("存在null值:"+field.getName());
            }
            throw new Exception(notNull.error());
        }
    }


}
