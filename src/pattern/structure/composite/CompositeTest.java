package pattern.structure.composite;

import java.util.Iterator;
import java.util.Vector;

/**
 * @see IFile 抽象构件	这是一个抽象角色，它给参加组合的对象规定一个接口，规范共有的接口及默认行为。这个接口可以用来管理所有的子对象，要提供一个接口以规范取得和管理下层组件的接口，包括add(), remove()以及getChild()之类的方法。
 * @see File 树叶构件	代表参加组合的树叶对象，定义出参加组合的原始对象的行为。树叶类会给出add(), remove()以及getChild()之类的用来管理子类对象的方法的平庸实现。
 * @see Folder 树枝构件	代表参加组合的有子对象的对象，定义出这样的对象的行为。
 */
public class CompositeTest {
    /**文件层次缩进字符*/
    private static final String INDENT_CHAR = "├───";
    private static final String SPACE_CHAR = "│   ";
    private static final String LAST_CHAR = "└───";

    public static void outTree(Folder folder) {
        System.out.println(folder.getName());
        iterateTree(folder);
    }

    /**
     * 遍历文件夹，输入文件树
     */
    public static void iterateTree(Folder folder) {
        Vector<IFile> list = folder.getAllComponent();
        for (Iterator<IFile> it = list.iterator();it.hasNext();) {
            IFile node = it.next();
            boolean last = it.hasNext();
            if (node instanceof Folder) {
                Folder nodeFolder = (Folder) node;
                System.out.println(getIndents(node.getDeep(),last) + nodeFolder.getName());
                iterateTree(nodeFolder);
            } else {
                System.out.println(getIndents(node.getDeep(),last) + ((File) node).getName());
            }
        }
    }

    public static String getIndents(int deep,boolean last) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < deep; i++) {
            sb.append(SPACE_CHAR);
        }
        if(last){
            sb.append(INDENT_CHAR);
        }
        else {
            sb.append(LAST_CHAR);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //根下文件及文件夹
        Folder root = new Folder("树根");

        Folder b1_1 = new Folder("1_枝1");
        Folder b1_2 = new Folder("1_枝2");
        Folder b1_3 = new Folder("1_枝3");
        File l1_1 = new File("1_叶1");
        File l1_2 = new File("1_叶2");
        File l1_3 = new File("1_叶3");

        //b1_2下的文件及文件夹
        Folder b2_1 = new Folder("2_枝1");
        Folder b2_2 = new Folder("2_枝2");
        File l2_1 = new File("2_叶1");

        root.add(b1_1);
        root.add(b1_2);
        root.add(l1_1);
        root.add(l1_2);

        b1_2.add(b2_1);
        b1_2.add(b2_2);
        b1_2.add(l2_1);
        root.add(l1_3);
        root.add(b1_3);

        outTree(root);
    }

}
