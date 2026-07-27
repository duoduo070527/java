
public class Main {
    public static void main(String[] args) {
        // 定义测试数组
        int[] nums = {33, 44, 55, 66, 77, 88};

        System.out.print("数组全部元素：");
        ArrayUtil.printArr(nums);
        System.out.println("数组最大值：" + ArrayUtil.getMax(nums));
        System.out.println("数组所有数字总和：" + ArrayUtil.getSum(nums));
    }
}

// 数组工具类，存放操作数组的静态方法
class ArrayUtil {
    // 获取数组最大值
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // 计算数组总和
    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // 格式化打印数组
    public static void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
        System.out.println("]");
    }
}