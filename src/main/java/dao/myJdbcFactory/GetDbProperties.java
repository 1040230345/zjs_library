package dao.myJdbcFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取db.properties的值
 */
public class GetDbProperties {

    static final String DRIVERNAME;
    static final String URL;
    static final String USERNAME;
    static final String PASSWORD;
    static final int INITIALSIZE;
    static final int MAXIDLE;
    static final int MINLDEL;
    static final int MAXACTIVE;


    static {
        Properties prop = new Properties();
        try{
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream("src/main/resources/application.properties"));
            prop.load(in); //加载属性列表
            in.close(); //关闭

            //赋值(必须)
            if((String) prop.get("zjs.DriverName")==null||(String) prop.get("zjs.url")==null
                    ||(String) prop.get("zjs.username")==null||(String) prop.get("zjs.password")==null)
                throw new RuntimeException("数据源配置出错，application.properties是否符合规范");

            DRIVERNAME = (String) prop.get("zjs.DriverName");
            URL = (String) prop.get("zjs.url");
            USERNAME = (String) prop.get("zjs.username");
            PASSWORD = (String) prop.get("zjs.password");

            //非必须
            if (Integer.valueOf(prop.get("zjs.initialSize").toString())!=null)
                INITIALSIZE = Integer.valueOf(prop.get("zjs.initialSize").toString());
            else
                INITIALSIZE = 10;
            if (Integer.valueOf(prop.get("zjs.maxIdle").toString())!=null)
                MAXIDLE = Integer.valueOf(prop.get("zjs.maxIdle").toString());
            else
                MAXIDLE = 5;
            if (Integer.valueOf(prop.get("zjs.minIdle").toString())!=null)
                MINLDEL = Integer.valueOf(prop.get("zjs.minIdle").toString());
            else MINLDEL = 3;
            if (Integer.valueOf(prop.get("zjs.maxActive").toString())!=null)
                MAXACTIVE = Integer.valueOf(prop.get("zjs.maxActive").toString());
            else MAXACTIVE = 1 ;

        }catch(Exception e){
            throw new RuntimeException("读取application失败，请检查文件是否存在resources下");
        }
    }

}
