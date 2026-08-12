
// 接口1：加班能力
interface Overtime {
    // 计算加班工资
    double calcOvertimePay(int hour);
}

// 接口2：绩效考核能力
interface Performance {
    // 根据绩效等级发放奖金
    double getBonus(String level);
}

// 抽象父类：员工（抽取所有员工公共属性与通用逻辑）
abstract class Staff {
    // 公共属性
    protected String id;     // 工号
    protected String name;   // 姓名
    protected double basePay;// 基本工资

    // 构造方法
    public Staff(String id, String name, double basePay) {
        this.id = id;
        this.name = name;
        this.basePay = basePay;
    }

    // 所有员工通用方法：上下班打卡
    public void clockIn() {
        System.out.println(name + "【" + id + "】打卡上班");
    }

    public void clockOut() {
        System.out.println(name + "【" + id + "】打卡下班");
    }

    // 抽象方法：计算当月总薪资（不同员工计算规则不一样，强制子类重写）
    public abstract double calcTotalPay();

    // 打印员工信息
    public void showInfo() {
        System.out.printf("工号：%s 姓名：%s 基础薪资：%.2f%n", id, name, basePay);
    }
}

// 1.普通员工：只继承抽象类，不实现任何接口
class OrdinaryStaff extends Staff {
    public OrdinaryStaff(String id, String name, double basePay) {
        super(id, name, basePay);
    }

    @Override
    public double calcTotalPay() {
        // 普通员工只有基本工资
        return basePay;
    }
}

// 2.程序员：继承员工类 + 实现加班接口
class Programmer extends Staff implements Overtime {
    private int overtimeHour; // 当月加班时长

    public Programmer(String id, String name, double basePay, int overtimeHour) {
        super(id, name, basePay);
        this.overtimeHour = overtimeHour;
    }

    // 实现加班接口方法
    @Override
    public double calcOvertimePay(int hour) {
        return hour * 50; // 加班时薪50元
    }

    @Override
    public double calcTotalPay() {
        // 基本工资 + 加班费
        return basePay + calcOvertimePay(overtimeHour);
    }
}

// 3.部门主管：继承员工类 + 同时实现加班、绩效两个接口（接口多实现核心考点）
class Manager extends Staff implements Overtime, Performance {
    private int overtimeHour;
    private String perfLevel;

    public Manager(String id, String name, double basePay, int overtimeHour, String perfLevel) {
        super(id, name, basePay);
        this.overtimeHour = overtimeHour;
        this.perfLevel = perfLevel;
    }

    // 实现加班接口
    @Override
    public double calcOvertimePay(int hour) {
        return hour * 80; // 主管加班时薪更高：80元
    }

    // 实现绩效接口
    @Override
    public double getBonus(String level) {
        return switch (level) {
            case "A" -> 3000;
            case "B" -> 1500;
            case "C" -> 500;
            default -> 0;
        };
    }

    @Override
    public double calcTotalPay() {
        // 基本工资 + 加班费 + 绩效奖金
        return basePay + calcOvertimePay(overtimeHour) + getBonus(perfLevel);
    }
}

// 测试主类
public class StaffSystem {
    public static void main(String[] args) {
        // 向上转型写法
        Staff staff1 = new OrdinaryStaff("001", "小张", 4500);
        Staff staff2 = new Programmer("002", "小李", 7000, 22);
        Staff staff3 = new Manager("003", "王经理", 12000, 15, "A");

        System.out.println("=====普通员工薪资核算=====");
        staff1.showInfo();
        staff1.clockIn();
        System.out.println("当月总薪资：" + staff1.calcTotalPay());
        staff1.clockOut();

        System.out.println("\n=====程序员薪资核算=====");
        staff2.showInfo();
        staff2.clockIn();
        System.out.println("当月总薪资：" + staff2.calcTotalPay());
        staff2.clockOut();

        System.out.println("\n=====部门主管薪资核算=====");
        staff3.showInfo();
        staff3.clockIn();
        System.out.println("当月总薪资：" + staff3.calcTotalPay());
        staff3.clockOut();
    }
}