package POJOS;

public class Condition {

    private String name;
    private Float value1;
    private Float value2;
    private String sign;

    public Condition(String name, Float value1, Float value2, String sign) {
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
        this.sign = sign;
    }

    public Condition(String name, Float value1) {
        this.name = name;
        this.value1 = value1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue1() {
        return value1;
    }

    public void setValue1(Float value1) {
        this.value1 = value1;
    }

    public Float getValue2() {
        return value2;
    }

    public void setValue2(Float value2) {
        this.value2 = value2;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "\nCondition{" +
                "name='" + name + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                ", sign='" + sign + '\'' +
                '}';
    }
}
