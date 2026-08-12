
import java.util.*;
import java.util.stream.Collectors;

public class LambdaStreamDemo {
    public static void main(String[] args) {
        List<Integer> numList = Arrays.asList(12, 5, 28, 19, 5, 33, 9, 28);
        List<String> nameList = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu", "qianqi");

        //=========1.传统匿名内部类 VS Lambda写法对比=========
        System.out.println("====匿名内部类遍历====");
        numList.forEach(new java.util.function.Consumer<Integer>() {
            @Override
            public void accept(Integer n) {
                System.out.print(n + " ");
            }
        });
        System.out.println("\n====Lambda简写遍历====");
        numList.forEach(n -> System.out.print(n + " "));
        System.out.println("\n====方法引用最简写法====");
        numList.forEach(System.out::print);
        System.out.println("\n");

        //=========2.Stream流式常用运算=========
        //1）去重
        List<Integer> distinctNums = numList.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("去重后：" + distinctNums);

        //2）筛选：保留大于10的数字
        List<Integer> filterList = numList.stream()
                .filter(n -> n > 10)
                .collect(Collectors.toList());
        System.out.println("大于10数字：" + filterList);

        //3）排序：升序、降序
        List<Integer> ascList = numList.stream().sorted().collect(Collectors.toList());
        List<Integer> descList = numList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("升序：" + ascList);
        System.out.println("降序：" + descList);

        //4）截取前3个元素
        List<Integer> limitList = numList.stream().limit(3).collect(Collectors.toList());
        System.out.println("截取前3位：" + limitList);

        //5）求和
        int sum = numList.stream().mapToInt(Integer::intValue).sum();
        System.out.println("数字总和：" + sum);

        //6）映射：全部转为大写
        List<String> upperNames = nameList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("姓名大写：" + upperNames);

        //7）收集转为Set集合
        Set<Integer> numSet = numList.stream().collect(Collectors.toSet());
        System.out.println("转为Set：" + numSet);
    }
}
