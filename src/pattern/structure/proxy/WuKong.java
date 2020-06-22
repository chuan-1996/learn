package pattern.structure.proxy;

public class WuKong extends AbstractAppearance {
    AbstractAppearance missGao = new MissGao();

    @Override
    public void smile() {
        missGao.smile();
        System.out.println("Actual, it's wukong smiles.");
    }
}
