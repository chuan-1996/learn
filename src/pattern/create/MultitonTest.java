package pattern.create;

class Multiton {
    public static final Multiton INSTANCE_01 = new Multiton("instance_01");
    public static final Multiton INSTANCE_02 = new Multiton("instance_02");

    private Multiton(String name) {}
}

class Country {
    public static final Country CHINA   = new Country("chinae");
    public static final Country ENGLAND = new Country("england");
    public static final Country FRANCE  = new Country("france");

    private String name;
    private Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/**
 * @author qq491
 * 多例
 */
public class MultitonTest {

    public static void main(String[] args) {
        Country c1 = Country.CHINA;
        Country c2 = Country.ENGLAND;
        Country c3 = Country.FRANCE;

        System.out.println("c1 is: "+c1.getName());
        System.out.println("c2 is: "+c2.getName());
        System.out.println("c3 is: "+c3.getName());
    }
}
