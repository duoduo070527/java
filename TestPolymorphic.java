
package demo;

public class TestPolymorphic {
    public static void main(String[] args) {
        // 多态核心写法：父类引用指向子类对象（向上转型，自动转换）
        Animal a1 = new Dog();
        Animal a2 = new Cat();

        // 调用重写方法：执行子类内容（多态核心特点）
        a1.eat();
        a2.eat();

        // a1.watchHome();  ❌ 向上转型后，无法调用子类独有方法

        // 向下强制转型：变回子类，才能调用独有功能
        Dog dog = (Dog) a1;
        dog.watchHome();

        // 健壮判断：instanceof 避免转型报错
        if (a1 instanceof Dog) {
            Dog d = (Dog) a1;
            d.watchHome();
        }
    }
}