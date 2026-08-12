
// 行为接口
interface AnimalBehavior {
    void breathe();
    void shout();
}

// 猫实现接口
class Cat implements AnimalBehavior {
    private String name;
    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void breathe() {
        System.out.println(name + " 在呼吸氧气");
    }

    @Override
    public void shout() {
        System.out.println(name + "：喵喵喵");
    }
}

// 狗实现接口
class Dog implements AnimalBehavior {
    private String name;
    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void breathe() {
        System.out.println(name + " 在呼吸氧气");
    }

    @Override
    public void shout() {
        System.out.println(name + "：汪汪汪");
    }
}

// 测试入口
public class TestAnimalByInterface {
    public static void main(String[] args) {
        AnimalBehavior cat = new Cat("橘猫");
        AnimalBehavior dog = new Dog("柴犬");

        cat.breathe();
        cat.shout();
        dog.breathe();
        dog.shout();
    }
}