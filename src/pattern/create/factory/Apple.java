package pattern.create.factory;

public class Apple extends Fruit {
    private int age;

    @Override
    public void grow() {
        log("Apple grow()");
    }
    @Override
    public void harvest() {
        log("Apple harvest()");
    }
    @Override
    public void plant() {
        log("Apple plant()");
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    private void log(String msg) {
        System.out.println(msg);
    }
}
