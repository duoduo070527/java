
import java.util.*;

// 学生实体类：用于ArrayList、HashSet存储
class Student {
    private Integer id;    // 学号
    private String name;   // 姓名
    private Integer age;   // 年龄

    // 构造方法
    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // getter、setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // 重写toString，打印集合直接看内容
    @Override
    public String toString() {
        return "Student{学号=" + id + ",姓名='" + name + "',年龄=" + age + "}";
    }

    // HashSet去重核心：学号相同就判定为同一个学生，必须重写equals + hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class CollectionAllDemo {
    public static void main(String[] args) {
        // ===================== 第一部分：ArrayList 增删改查 + 四种遍历 =====================
        System.out.println("============ 一、ArrayList 学生集合操作 ============");
        List<Student> stuList = new ArrayList<>();

        // 1.新增元素
        stuList.add(new Student(1001, "张三", 19));
        stuList.add(new Student(1002, "李四", 20));
        stuList.add(new Student(1003, "王五", 19));
        System.out.println("新增全部学生：" + stuList);

        // 2.修改：将索引1的李四年龄改为21
        stuList.get(1).setAge(21);
        System.out.println("修改李四年龄后：" + stuList);

        // 3.删除：删除学号1003的王五
        stuList.removeIf(stu -> stu.getId() == 1003);
        System.out.println("删除王五后：" + stuList);

        // 4.查询：获取学号1001的学生
        Student target = null;
        for (Student s : stuList) {
            if (s.getId() == 1001) {
                target = s;
                break;
            }
        }
        System.out.println("查询学号1001：" + target);

        System.out.println("\n----- ArrayList四种遍历方式 -----");
        // 方式1：普通for循环（带索引）
        System.out.println("1.普通for循环：");
        for (int i = 0; i < stuList.size(); i++) {
            System.out.print(stuList.get(i) + "  ");
        }
        System.out.println();

        // 方式2：增强for循环
        System.out.println("2.增强for循环：");
        for (Student s : stuList) {
            System.out.print(s + "  ");
        }
        System.out.println();

        // 方式3：迭代器Iterator
        System.out.println("3.迭代器遍历：");
        Iterator<Student> it = stuList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "  ");
        }
        System.out.println();

        // 方式4：Lambda forEach遍历
        System.out.println("4.Lambda遍历：");
        stuList.forEach(s -> System.out.print(s + "  "));
        System.out.println("\n");

        // ===================== 第二部分：HashSet 自定义对象去重（重难点） =====================
        System.out.println("============ 二、HashSet 学生去重测试 ============");
        Set<Student> stuSet = new HashSet<>();
        // 放入两个学号完全一致的学生（重复数据）
        stuSet.add(new Student(1001, "张三", 19));
        stuSet.add(new Student(1001, "张三", 19));
        stuSet.add(new Student(1002, "李四", 21));

        System.out.println("存入重复学生后集合：" + stuSet);
        // 结果只会存在两个学生，重复学号自动去除，验证equals+hashCode生效

        // HashSet遍历（三种常用方式）
        System.out.println("\nHashSet遍历：");
        // 增强for
        for (Student s : stuSet) {
            System.out.print(s + "  ");
        }
        System.out.println();
        // 迭代器
        Iterator<Student> setIt = stuSet.iterator();
        while (setIt.hasNext()) {
            System.out.print(setIt.next() + "  ");
        }
        System.out.println();
        // Lambda
        stuSet.forEach(System.out::print);
        System.out.println("\n");

        // ===================== 第三部分：HashMap 统计文字字符出现次数（笔试高频题） =====================
        System.out.println("============ 三、HashMap 统计字符出现频次 ============");
        String text = "java集合ArrayList HashSet HashMap 面向对象编程";
        Map<Character, Integer> charCountMap = new HashMap<>();

        // 遍历字符串每一个字符
        for (char ch : text.toCharArray()) {
            // 如果map已存在该字符，次数+1；不存在则存入，次数初始为1
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        // HashMap四种遍历方式
        System.out.println("字符统计结果：");
        // 方式1：遍历键集合
        System.out.println("1.遍历key集合：");
        for (Character ch : charCountMap.keySet()) {
            System.out.print(ch + "=" + charCountMap.get(ch) + "  ");
        }
        System.out.println();

        // 方式2：遍历键值对entry
        System.out.println("2.遍历Entry键值对：");
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.print(entry.getKey() + "：" + entry.getValue() + "次  ");
        }
        System.out.println();

        // 方式3：迭代器遍历entry
        System.out.println("3.迭代器遍历：");
        Iterator<Map.Entry<Character, Integer>> mapIt = charCountMap.entrySet().iterator();
        while (mapIt.hasNext()) {
            Map.Entry<Character, Integer> entry = mapIt.next();
            System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
        }
        System.out.println();

        // 方式4：Lambda遍历
        System.out.println("4.Lambda遍历：");
        charCountMap.forEach((k, v) -> System.out.print(k + "[" + v + "] "));
    }
}
