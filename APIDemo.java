
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 综合实操：String / StringBuilder / 新旧日期API / 包装类
 * 功能：用户日志文本处理 + 生日倒计时时间计算 + 包装类装箱拆箱演示
 */
public class APIDemo {
    public static void main(String[] args) throws ParseException {
        // ===================== 第一部分：String 字符串常用API实操 =====================
        System.out.println("========== 1. String 字符串处理实操 ==========");
        String originStr = "  Java编程基础 | 字符串API练习 20260812  ";
        System.out.println("原始字符串：【" + originStr + "】");

        // 1.去除首尾空格
        String trimStr = originStr.trim();
        System.out.println("去除首尾空格：【" + trimStr + "】");

        // 2.字符串截取
        String subDate = trimStr.substring(trimStr.length() - 8);
        System.out.println("截取末尾8位日期：" + subDate);

        // 3.分割字符串
        String[] splitArr = trimStr.split("\\|");
        System.out.println("按|分割后第一段：" + splitArr[0]);

        // 4.字符统计：统计大写J、数字总个数
        countChar(trimStr);

        // ===================== 第二部分：StringBuilder 高效拼接 + 字符串反转 =====================
        System.out.println("\n========== 2. StringBuilder 字符串反转、拼接实操 ==========");
        String content = "I love Java Programming";
        // 字符串反转方法
        String reverseStr = reverseString(content);
        System.out.println("原字符串：" + content);
        System.out.println("反转字符串：" + reverseStr);

        // 大量字符串拼接对比（体现StringBuilder优势）
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append("段落").append(i).append(";");
        }
        System.out.println("循环拼接结果：" + sb);

        // ===================== 第三部分：包装类 装箱、拆箱、字符串与数值转换 =====================
        System.out.println("\n========== 3. 包装类实操（装箱/拆箱） ==========");
        // 手动装箱
        Integer num1 = Integer.valueOf(88);
        // 自动装箱
        Integer num2 = 99;
        // 自动拆箱
        int sum = num1 + num2;
        System.out.println("包装类相加结果：" + sum);

        // 字符串转数字、数字转字符串（开发高频用法）
        String numStr = "12345";
        int parseNum = Integer.parseInt(numStr);
        System.out.println("字符串转整数：" + parseNum + 10);
        String intToString = String.valueOf(parseNum);
        System.out.println("数字转回字符串：" + intToString);

        // ===================== 第四部分：旧日期类 Date + SimpleDateFormat =====================
        System.out.println("\n========== 4. 旧版日期API（Date、SimpleDateFormat） ==========");
        // 当前系统时间
        Date oldNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String oldNowStr = sdf.format(oldNow);
        System.out.println("旧Date格式化当前时间：" + oldNowStr);

        // 字符串解析为日期
        String birthdayText = "2005-05-20 10:30:00";
        Date birthdayOld = sdf.parse(birthdayText);
        System.out.println("字符串转为Date生日：" + birthdayOld);

        // 计算两个时间毫秒差值
        long timeDiff = oldNow.getTime() - birthdayOld.getTime();
        System.out.println("当前距离生日总毫秒数：" + timeDiff);

        // ===================== 第五部分：新版Java8日期API（推荐使用，线程安全） =====================
        System.out.println("\n========== 5. Java8新版日期API（LocalDateTime）时间差计算 ==========");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 当前时间
        LocalDateTime newNow = LocalDateTime.now();
        // 生日时间
        LocalDateTime birthdayNew = LocalDateTime.parse(birthdayText, formatter);

        // 格式化输出
        System.out.println("新版当前时间：" + newNow.format(formatter));
        System.out.println("新版生日时间：" + birthdayNew.format(formatter));

        // 计算时间间隔
        Duration duration = Duration.between(birthdayNew, newNow);
        System.out.println("相隔总天数：" + duration.toDays());
        System.out.println("相隔总小时：" + duration.toHours());
        System.out.println("相隔总分钟：" + duration.toMinutes());

        // 计算年月日间隔
        Period period = Period.between(birthdayNew.toLocalDate(), newNow.toLocalDate());
        System.out.printf("年龄：%d年%d月%d天%n", period.getYears(), period.getMonths(), period.getDays());
    }

    /**
     * 自定义方法：统计字符串大写字母、数字字符数量
     */
    public static void countChar(String str) {
        int upperCount = 0;
        int numCount = 0;
        char[] charArr = str.toCharArray();
        for (char c : charArr) {
            if (Character.isUpperCase(c)) {
                upperCount++;
            } else if (Character.isDigit(c)) {
                numCount++;
            }
        }
        System.out.println("大写字母总数：" + upperCount);
        System.out.println("数字字符总数：" + numCount);
    }

    /**
     * 自定义方法：使用StringBuilder实现字符串反转
     */
    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
}
