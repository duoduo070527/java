
import java.util.Scanner;
import java.util.Random;

// 猜周杰伦歌名小游戏，一共有5次猜测机会
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        // 周杰伦经典歌名单
        String[] songList = {"七里香", "青花瓷", "稻香", "晴天", "搁浅", "夜曲", "枫", "退后"};
        // 随机选中一首歌
        String targetSong = songList[r.nextInt(songList.length)];
        int count = 0; // 记录猜测次数

        System.out.println("====猜周杰伦歌名游戏开始====");
        System.out.println("系统随机挑选了一首周杰伦歌曲，你一共有5次机会！");

        while (true) {
            count++;
            if (count > 5) {
                System.out.println("5次机会用完，游戏失败！正确歌名是：" + targetSong);
                break;
            }

            System.out.print("请输入你猜测的歌名：");
            String inputName = sc.next();

            if (inputName.equals(targetSong)) {
                System.out.println("恭喜你猜对了！一共猜了" + count + "次");
                break;
            } else {
                System.out.println("猜错啦！还剩" + (5 - count) + "次机会");
            }
        }
        sc.close();
    }
}