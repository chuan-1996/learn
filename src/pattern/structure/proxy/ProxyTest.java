package pattern.structure.proxy;

/**
 * @author qq491
 * 悟空代理了高小姐
 * @see AbstractAppearance 抽象主体
 * @see MissGao 真实主体
 * @see WuKong 代理主体
 */
public class ProxyTest {
    public static void main(String[] args) {

        AbstractAppearance appearance = new WuKong();
        appearance.smile();
    }
}
