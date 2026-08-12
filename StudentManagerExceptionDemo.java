
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// ===================== 第一步：自定义3种业务异常（继承Exception 编译期受检异常） =====================
// 学号重复异常：新增学生时学号已存在
class IdRepeatException extends Exception {
    public IdRepeatException(String message) {
        super(message);
    }
}

// 学生不存在异常：删除/修改/查询时找不到对应学号学生
class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}

// 年龄非法异常：年龄小于0或者大于60不符合学生范围
class AgeIllegalException extends Exception {
    public AgeIllegalException(String message) {
        super(message);
    }
}

// ===================== 第二步：学生实体类 =====================
class Student {
    private Integer sid;
    private String name;
    private Integer age;

    public Student(Integer sid, String name, Integer age) {
        this.sid = sid;
        this.name = name;
        this.age = age;
    }

    // getter setter
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    @Override
    public String toString() {
        return "学生[学号：" + sid + "，姓名：" + name + "，年龄：" + age + "]";
    }
}

// ===================== 第三步：学生管理业务层（集合存储 + throws声明抛出异常） =====================
class StudentManager {
    // 使用ArrayList存储全体学生数据
    private final List<Student> studentList = new ArrayList<>();

    // 添加学生：校验学号重复、年龄合法性，异常throws抛出给调用者处理
    public void addStudent(Student stu) throws IdRepeatException, AgeIllegalException {
        // 校验年龄范围
        if (stu.getAge() < 0 || stu.getAge() > 60) {
            throw new AgeIllegalException("添加失败：年龄" + stu.getAge() + "超出合法范围(0~60)");
        }

        // 校验学号是否重复
        boolean isRepeat = studentList.stream()
                .anyMatch(s -> s.getSid().equals(stu.getSid()));
        if (isRepeat) {
            throw new IdRepeatException("添加失败：学号" + stu.getSid() + "已存在，不可重复录入");
        }

        studentList.add(stu);
        System.out.println(stu.getName() + " 录入成功");
    }

    // 根据学号删除学生
    public void deleteStudent(Integer sid) throws StudentNotFoundException {
        Optional<Student> target = studentList.stream()
                .filter(s -> s.getSid().equals(sid))
                .findFirst();

        if (target.isEmpty()) {
            throw new StudentNotFoundException("删除失败：不存在学号为" + sid + "的学生");
        }
        studentList.remove(target.get());
        System.out.println("学号" + sid + " 删除成功");
    }

    // 查询所有学生
    public void showAll() {
        if (studentList.isEmpty()) {
            System.out.println("暂无学生数据");
            return;
        }
        System.out.println("=======全部学生列表=======");
        studentList.forEach(System.out::println);
    }
}

// ===================== 第四步：测试主程序 try-catch-finally捕获异常 =====================
public class StudentManagerExceptionDemo {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        System.out.println("========= 场景1：正常添加学生 =========");
        try {
            manager.addStudent(new Student(2026001, "张明", 19));
            manager.addStudent(new Student(2026002, "李华", 20));
            manager.showAll();
        } catch (IdRepeatException | AgeIllegalException e) {
            System.out.println("捕获异常：" + e.getMessage());
        } finally {
            System.out.println("场景1执行完毕，执行finally收尾代码\n");
        }

        System.out.println("========= 场景2：重复学号，触发学号重复异常 =========");
        try {
            manager.addStudent(new Student(2026001, "张明", 19));
        } catch (IdRepeatException e) {
            System.out.println("捕获业务异常：" + e.getMessage());
        } catch (AgeIllegalException e) {
            System.out.println("捕获业务异常：" + e.getMessage());
        } finally {
            System.out.println("场景2执行完毕，finally固定执行\n");
        }

        System.out.println("========= 场景3：填写负数年龄，触发年龄非法异常 =========");
        try {
            manager.addStudent(new Student(2026003, "王浩", -5));
        } catch (IdRepeatException | AgeIllegalException e) {
            System.out.println("捕获异常：" + e.getMessage());
        } finally {
            System.out.println("场景3执行完毕\n");
        }

        System.out.println("========= 场景4：删除不存在学号，触发学生不存在异常 =========");
        try {
            manager.deleteStudent(999999);
        } catch (StudentNotFoundException e) {
            System.out.println("捕获异常：" + e.getMessage());
        } finally {
            System.out.println("所有业务操作结束，finally常用于关闭资源、日志记录");
        }
    }
}
