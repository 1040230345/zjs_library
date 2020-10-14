import Annotatons.util.AllNotNull;
import Annotatons.util.NotNull;

@AllNotNull
public class Users {

    private Integer b;

    private String a;

    private int c;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public Users(){}
    public Users(String a, Integer b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
