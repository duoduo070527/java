
package demo;

public class TestPermission {
    public static void main(String[] args) {
        Person p = new Person();
        // 同包下访问权限
        System.out.println(p.name);    // public ✅
        System.out.println(p.age);     // protected ✅
        System.out.println(p.sex);     // 默认 ✅
        // System.out.println(p.money); // private ❌ 报错，外部无法访问

        p.showPublic();    // ✅
        p.showProtected(); // ✅
        p.showDefault();   // ✅
        // p.showPrivate(); // ❌

        p.innerTest(); // 通过公共方法间接调用私有内容
    }
}
