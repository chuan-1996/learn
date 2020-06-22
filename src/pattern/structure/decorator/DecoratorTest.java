package pattern.structure.decorator;

/**
 * @see GreatSage 抽象构件
 * @see Monkey 具体构件
 * @see Change 装饰
 */
public class DecoratorTest {
    public static void main(String[] args) {
        GreatSage sage = new Monkey();

        // 1. "齐天大圣"
        sage.move();

        // 2. "齐天大圣"变成"鱼"之后，再变成"鸟"
        GreatSage fish = new Fish(sage);
        GreatSage bird = new Bird(fish);
        bird.move();

        // 3. "齐天大圣"变成"昆虫"之后，再变成"花"
        GreatSage flower = new Flower(new Insect(sage));
        flower.move();
    }
}
