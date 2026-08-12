
package demo;

public class Person {
    public String name = "张三";
    protected int age = 20;
    String sex = "男";
    private double money = 1000.0;

    public void showPublic() {
        System.out.println("公共方法：所有人都能访问");
    }

    protected void showProtected() {
        System.out.println("受保护方法：同包 + 子类可用");
    }

    void showDefault() {
        System.out.println("默认权限方法：仅同一个包内可用");
    }

    private void showPrivate() {
        System.out.println("私有方法：只能本类内部调用");
    }

    public void innerTest() {
        System.out.println(money);
        showPrivate();
    }
}