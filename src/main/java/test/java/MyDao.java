package test.java;

import dao.IDaoAdapter;
import dao.impl.BaseDao;

public class MyDao extends BaseDao<People> {


    public IDaoAdapter getAdapter() {
        return null;
    }



}
