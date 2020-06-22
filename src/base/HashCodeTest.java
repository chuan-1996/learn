package base;

class Test{
    @Override
    public boolean equals(Object obj)    {
        if (this.getClass()==obj.getClass())System.out.println("getclass和instanceof不同");
        if (obj instanceof Test)System.out.println("instanceof显示父类");
        return false;
    }

}

class Test2 extends Test{

}

public class HashCodeTest {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = s1+s2;
        String s5 = new String("helloworld");
        String s6 = "hello"+"world";
        String s7 = s1+"world";

        //s1 s2 s3 s4 s5是串内容的引用存在stack中
        //s3位于常量池
        //s5位于heap
        //为什么s6等于s3而s4和s7不等于s3，因为编译器的优化，String s6 = "hello"+"world" 对于编译器来说其实就是String s6 = "helloworld"，压根就不算拼接
        // 而S3 和S7属于拼接，所以其内存地址不同
        System.out.println("类的相等");
        Test as = new Test();
        Test2 asd = new Test2();
        System.out.println(as.equals(asd));

        System.out.println("String 的不变性指在heap中的内容不变，而其名字只是一个引用(reference variable)");
        System.out.println(s3==s4);
        System.out.println(s3==s5);
        System.out.println(s3==s6);
        System.out.println(s3==s7);
        System.out.println(s4==s7);
        System.out.println("s3 s4 s5 的 hashcode");
        System.out.println(s3.hashCode());
        System.out.println(s4.hashCode());
        System.out.println(s5.hashCode());
        System.out.println("s3 s4 s5 的 identityhashcode");
        System.out.println(System.identityHashCode(s3));
        System.out.println(System.identityHashCode(s4));
        System.out.println(System.identityHashCode(s5));

        System.out.println("==表示两个对象完全相等，不是内容上的相等，而是identityhashcode相等,即物理地址相同");
        s4="hello";
        s5="hello";
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s4));
        System.out.println(System.identityHashCode(s5));
        System.out.println(s1==s4);
        System.out.println(s1==s5);
        System.out.println("此时s5指向改变，gc回收heap中的healworld");
        s5="helloworld";
        System.out.println(s6==s5);
        System.out.println(System.identityHashCode(s5));
        System.out.println("hashcode相等例子但不equal的例子");
        String h1 = "Ma";
        String h2 = "NB";
        System.out.println(h1.hashCode());
        System.out.println(h2.hashCode());
        System.out.println(System.identityHashCode(h1));
        System.out.println(System.identityHashCode(h2));

        System.out.println("相等的严格度排行: identityhashcode(==), equals, hashcode");

    }
}
