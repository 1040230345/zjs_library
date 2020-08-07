package test.java;

import Annotatons.Table;

@Table(name = "users")
public class People {
    private String sex;
    private String name;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
