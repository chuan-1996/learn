package pattern.structure.composite;

/**
 * 抽象构件
 */
interface IFile {

    IFile getComposite();

    void sampleOperation();

    int getDeep();

    void setDeep(int x);
}
