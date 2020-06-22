package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    /** Creates a new instance of reference.ReferenceTest */
    public ReferenceTest() {
    }

    // 消耗大量内存
    public static void drainMemory() {
        String[] array = new String[1024 * 10];
        for(int i = 0; i < 1024 * 10; i++) {
            for(int j = 'a'; j <= 'z'; j++) {
                array[i] += (char)j;
            }
        }
    }

    //NoGarbageRetrieve
//    public static void main(String[] args) {
//        reference.MyDate date = new reference.MyDate();
//        date = null;
//    }

    //ExplicitGarbageRetrieve
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        reference.MyDate date = new reference.MyDate();
//        date = null;
//        System.gc();
//    }

    //ImplicitGarbageRetrieve
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        MyDate date = new MyDate();
//        date = null;
//        ReferenceTest.drainMemory();
//    }

    //StrongReferenceTest
//    public static void main(String[] args) {
//        MyDate date = new MyDate();
//        System.gc();
//    }

    //SoftReferenceTest 在内存不足时，软引用被禁止故无输出
//    public static void main(String[] args) {
//        SoftReference ref = new SoftReference(new MyDate());
//        ReferenceTest.drainMemory();
//    }

    //WeakReferenceTest
//    public static void main(String[] args) {
//        WeakReference ref = new WeakReference(new MyDate());
//        System.gc();
//    }

    //PhantomReferenceTest
    public static void main(String[] args) {
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference ref = new PhantomReference(new MyDate(), queue);
        System.gc();
    }
}
