package pattern.structure.facade;

/**
 * @author qq491
 * 门面SecurityFade承担了与保安系统内部各个对象打交道的任务，而客户对象只需要与门面对象打交道即可。
 * SecurityFade是客户端与保安系统之间的一个门户，它使得客户端与子系统之间的关系变得简单和易于管理。
 */
public class FacadeTest{

    public static void main(String[] args) {
        SecurityFacade security = new SecurityFacade();
        security.activate();
    }

}
