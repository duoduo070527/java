
// 抽象动物父类
abstract class Animal {
    // 共同属性：所有动物都有名字
    protected String name;

    // 构造方法：抽象类可以有构造，给子类初始化调用
    public Animal(String name) {
        this.name = name;
    }

    // 普通通用方法：所有动物呼吸逻辑一致，不用子类重写
    public void breathe() {
        System.out.println(name + " 在呼吸氧气");
    }

    // 抽象方法：没有方法体，强制子类必须重写
    public abstract void shout();
}

// 猫 子类 继承抽象类
class Cat extends Animal {
    public Cat(String name) {
        super(name); // 调用父类构造
    }

    // 必须实现抽象叫声方法
    @Override
    public void shout() {
        System.out.println(name + "：喵喵喵");
    }
}

// 狗 子类 继承抽象类
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void shout() {
        System.out.println(name + "：汪汪汪");
    }
}

// 测试运行
public class TestAnimal {
    public static void main(String[] args) {
        Animal cat = new Cat("橘猫");
        Animal dog = new Dog("柴犬");

        cat.breathe();
        cat.shout();

        dog.breathe();
        dog.shout();
    }
}
