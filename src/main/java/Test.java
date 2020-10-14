import utils.sentencedEmpty.MySentencedEmptyUtils;
import utils.sentencedEmpty.UserCallBack;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws Exception {
//        DAO dao = new DAO();
//        System.out.println(dao.count());
        Users st = new Users();
        st.setA("123");
        st.setB(1);
//        MySentencedEmptyUtils.isNotBlank(st, new UserCallBack() {
//            public void callBack() {
//                System.out.println("可以的");
//            }
//        });
        MySentencedEmptyUtils.isNotBlank(st,null);
    }
}
