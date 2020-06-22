package base;

public class Abs {
    public static void main(String[] args){
        int hash ="abcgggggdddddddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffffffffffffg".hashCode();
        System.out.println(hash & 0x7fffffff);
        System.out.println(hash);
    }
}
