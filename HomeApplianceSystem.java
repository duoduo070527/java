
// 接口1：智能联网功能
interface Smart {
    // 连接WiFi
    void connectWifi(String wifiName);
    // 语音控制
    void voiceControl(String order);
}

// 接口2：定时功能
interface Timing {
    // 设置定时时长
    void setTime(int hour);
}

// 抽象类：家电通用模板
abstract class Appliance {
    protected String brand;  // 品牌
    protected double price;  // 售价

    // 构造方法
    public Appliance(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    // 所有家电共用：开机
    public void powerOn() {
        System.out.println(brand + " 已开机");
    }

    // 所有家电共用：关机
    public void powerOff() {
        System.out.println(brand + " 已关机");
    }

    // 抽象工作方法：子类必须各自实现运行逻辑
    public abstract void work();

    // 展示家电信息
    public void showInfo() {
        System.out.printf("品牌：%s，售价：%.2f元%n", brand, price);
    }
}

// 1.普通电风扇：仅继承抽象类，无智能、定时功能
class Fan extends Appliance {
    public Fan(String brand, double price) {
        super(brand, price);
    }

    @Override
    public void work() {
        System.out.println(brand + "电风扇正在吹风，循环室内空气");
    }
}

// 2.智能电视：继承家电 + 实现智能接口
class TV extends Appliance implements Smart {
    public TV(String brand, double price) {
        super(brand, price);
    }

    @Override
    public void work() {
        System.out.println(brand + "电视正在播放影视节目");
    }

    // 实现智能接口
    @Override
    public void connectWifi(String wifiName) {
        System.out.println(brand + "电视已连接WiFi：" + wifiName);
    }

    @Override
    public void voiceControl(String order) {
        System.out.println("收到语音指令：" + order + "，正在切换画面");
    }
}

// 3.变频空调：继承家电 + 同时实现Smart、Timing双接口（接口多实现重点）
class AirConditioner extends Appliance implements Smart, Timing {
    public AirConditioner(String brand, double price) {
        super(brand, price);
    }

    @Override
    public void work() {
        System.out.println(brand + "空调正在调节室温，变频运行省电");
    }

    // 智能接口实现
    @Override
    public void connectWifi(String wifiName) {
        System.out.println(brand + "空调已接入家庭WiFi：" + wifiName);
    }

    @Override
    public void voiceControl(String order) {
        System.out.println("语音操控：" + order + "，调整温度");
    }

    // 定时接口实现
    @Override
    public void setTime(int hour) {
        System.out.println("已设置" + hour + "小时后自动关机");
    }
}

// 测试主类
public class HomeApplianceSystem {
    public static void main(String[] args) {
        // 向上转型：父类数组存放所有家电（多态数组）
        Appliance[] homeAppliances = {
                new Fan("美的", 129.0),
                new TV("海信", 2499.0),
                new AirConditioner("格力", 3599.0)
        };

        System.out.println("========== 全屋家电统一运行测试 ==========\n");
        // 循环遍历数组
        for (Appliance app : homeAppliances) {
            app.showInfo();
            app.powerOn();
            app.work();

            // 判断实际对象类型，向下转型调用接口独有方法
            if (app instanceof TV) {
                TV smartTv = (TV) app;
                smartTv.connectWifi("HomeWiFi_2026");
                smartTv.voiceControl("打开动漫频道");
            } else if (app instanceof AirConditioner) {
                AirConditioner ac = (AirConditioner) app;
                ac.connectWifi("HomeWiFi_2026");
                ac.voiceControl("温度调到26度");
                ac.setTime(6);
            }

            app.powerOff();
            System.out.println("-----------------------------------------\n");
        }
    }
}
