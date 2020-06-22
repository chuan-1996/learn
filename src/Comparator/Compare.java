package Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 重写toString以直接输出一个类
 */
public class Compare {
    private static class Person implements Comparable<Person>{
        int age;
        String name;

        public Person( String name,int age) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * 实现 “Comparable<String>” 的接口，即重写compareTo<T t>函数。
         *  这里是通过“person的名字”进行比较的
         */
        @Override
        public int compareTo(Person person) {
            return name.compareTo(person.name);
            //return this.name - person.name;
        }

    }
    /**
     *  它是“Person的age的升序比较器”
     */
    private static class AscAgeComparator implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            return p1.getAge() - p2.getAge();
        }
    }

    /**
     *       它是“Person的age的升序比较器”
     */
    private static class DescAgeComparator implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            return p2.getAge() - p1.getAge();
        }
    }

    public static void main(String[] str){
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(new Person("ccc", 20));
        list.add(new Person("AAA", 30));
        list.add(new Person("bbb", 10));
        list.add(new Person("ddd", 40));
        System.out.printf("Original sort, list: %s\n",list);

        Collections.sort(list);
        System.out.printf("Name sort, list:%s\n", list);

        Collections.sort(list,new AscAgeComparator());
        System.out.printf("Asc(age) sort, list:%s\n", list);

        Collections.sort(list,new DescAgeComparator());
        System.out.printf("Desc(age) sort, list:%s\n", list);

        System.out.print("以下使用流式操作\n");
        System.out.printf("Name sort, list:%s\n", list.stream().sorted().collect(Collectors.toList()));
        System.out.printf("Asc(age) sort, list:%s\n", list.stream().sorted(new AscAgeComparator()).collect(Collectors.toList()));
        System.out.printf("Desc(age) sort, list:%s\n", list.stream().sorted(new DescAgeComparator()).collect(Collectors.toList()));
    }
}
