package pattern.structure.composite;

import java.util.Vector;

/**
 * 树枝构件
 */
public class Folder implements IFile {
    private String name;
    private int deep;
    private Vector<IFile> componentVector = new Vector<IFile>();

    public Folder(String name) {
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

    public void add(IFile IFile) {
        componentVector.addElement(IFile);
        IFile.setDeep(this.deep + 1);
    }

    public void remove(IFile IFile) {
        componentVector.removeElement(IFile);
    }

    public Vector<IFile> getAllComponent() {
        return componentVector;
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
