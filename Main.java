
import java.util.Random;
import java.util.Scanner;

// 1-100猜数字小游戏，最多5次机会
public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        // 生成1~100随机数字
        int target = r.nextInt(100) + 1;
        int count = 0; // 记录猜测次数

        System.out.println("====猜数字游戏开始====");
        System.out.println("系统生成了1-100之间的数字，你一共有5次机会！");

        while (true) {
            count++;
            // 超过5次直接结束游戏
            if (count > 5) {
                System.out.println("5次机会用完，游戏失败！正确数字是：" + target);
                break;
            }
            System.out.print("请输入你猜的数字：");
            int num = sc.nextInt();

            if (num > target) {
                System.out.println("猜大了！还剩" + (5 - count) + "次机会");
            } else if (num < target) {
                System.out.println("猜小了！还剩" + (5 - count) + "次机会");
            } else {
                System.out.println("恭喜你猜对了！一共猜了" + count + "次");
                break;
            }
        }
        sc.close();
    }
}