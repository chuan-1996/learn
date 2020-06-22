package pattern.structure.composite;

/**
 * 树叶构件
 */
public class File implements IFile {
    private String name;
    private int deep;

    public File(String name) {
        this.name = name;
    }

    @Override
    public IFile getComposite() {
        return this;
    }

    @Override
    public void sampleOperation() {
        System.out.println("执行了某个方法！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getDeep() {
        return deep;
    }

    @Override
    public void setDeep(int deep) {
        this.deep = deep;
    }
}
