package cn.cincout.black.hole.javaconcurrent.module;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-18
 * @sine 1.8
 */
public class Animal {
    static {
        System.out.println("animal static");
    }

    public Animal() {
        System.out.println("animal constructor");
    }
    private String name;


    public static void printDog() {
        System.out.println("print dog");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();

        new Animal().printDog();

        String a = "a";
        String b = "a";
        System.out.println(a == b);
    }
}

class Dog extends Animal {
    static {
        System.out.println("dog static");
    }

    public Dog() {
        System.out.println("dog constructor");
    }
}


