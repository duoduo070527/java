
package demo;

// 父类
class Animal {
    public void eat() {
        System.out.println("动物吃东西");
    }
}

// 子类1
class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("小狗啃骨头");
    }

    // 子类独有方法
    public void watchHome() {
        System.out.println("小狗看家");
    }
}

// 子类2
class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("小猫吃鱼");
    }
}
