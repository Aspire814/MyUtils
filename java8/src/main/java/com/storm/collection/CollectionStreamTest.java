package com.storm.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.storm.common.Employee;


public class CollectionStreamTest {
    // 获取Stream方式
    //   在java8中的Collection接口被扩展，提供了两个获取流的方法：
    // list.stream();//通过创建串行流
    // list.parallelStream();//创建并行流
    // 当然Java8的Arrays的静态方法stream()也可以获得流
    // Arrays.stream(T[] array);//java8的Arrays的静态方法stream可以获取数组流
    //   可以通过静态方法获取流
    // Stream.of(T...values)//通过Stream.of方式创建流
    //   由函数创建流
    // Stream.interate()和Stream.generate()创建无限流
    @Test
    public void test1() {
        List<Employee> lists = Arrays.asList(new Employee("张三", 18, 5555.55), new Employee("李四", 20, 6666.66), new Employee("王二", 5, 10000.55),
                new Employee("麻子", 40, 50000.55));

        Collections.sort(lists, (x, y) -> Integer.compare(x.getAge(), y.getAge()));

        for (Employee employee : lists) {
            // System.out.println(employee);
        }

        String[] strArray = {"12", "23", "34"};
        Stream<String> strStream = Arrays.stream(strArray);
        strStream.forEach(System.out::println);

        Stream<String> strStream1 = Stream.of(strArray);

        Stream<Employee> stream = lists.stream()
                .filter(emp -> emp.getSalary() > 6000)// 筛选出工资大于6000的员工
                .distinct()// 元素去重
                .limit(2)// 使元素不超过2个
                ;// .skip(2);// 跳过2个元素

        stream.forEach(System.out::println);

        Stream<String> stream1 = lists.stream().map(e -> e.getName())// 将每一个Employee对象的姓名替换原来的Employee
                .sorted()// 自然排序
                .distinct();
        stream1.forEach(System.out::println);


    }

    @Test
    public void test2() {
        List<Employee> lists = Arrays.asList(new Employee("张三", 18, 5555.55), new Employee("李四", 20, 6666.66), new Employee("王二", 5, 10000.55),
                new Employee("麻子", 40, 50000.55));

        Collections.sort(lists, (x, y) -> Integer.compare(x.getAge(), y.getAge()));

        for (Employee employee : lists) {
            // System.out.println(employee);
        }
        // 返回流中元素总数
        long num = lists.stream().count();
        System.out.println(num);

        // 返回流中最大值
        Optional<Double> maxSalary = lists.stream().map(Employee::getSalary).max(Double::compareTo);

        System.out.println(maxSalary.get());

        // 是否匹配所有元素
        boolean bool = lists.stream().allMatch(e -> e.getName().equals("张三"));
        System.out.println(bool);

        // 是否至少匹配一个元素
        boolean bool2 = lists.stream().anyMatch(e -> e.getName().equals("张三"));
        System.out.println(bool2);

        // 检查是否没有匹配到所有元素
        boolean bool3 = lists.stream().noneMatch(e -> e.getName().equals("张三"));
        System.out.println(bool3);

        // 将Employee映射为Employee的名字，并且迭代输出
        lists.stream().map(Employee::getName).forEach(System.out::println);

        // 利用归约，求出所有员工的工资之和
        Double salarySum = lists.stream().map(Employee::getSalary).reduce(0.0, (x, y) -> x + y);
        System.out.println(salarySum);
        // 第二种，利用reduce规约，求出所有员工工资之和，Optional容器的出现，是为了解决空指针异常
        Optional<Double> salarySum2 = lists.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(salarySum2.get());

        // 收集的功能很强大，该函数需要一个Collector接口，用来实现对流执行收集操作，例如收集到List、Set、Map中去。
        // 并且Collectors工具类提供了很多静态方法，能够方便的创建常见地方收集器，需要的话，自行查询API文档
        HashSet<Employee> set = lists.stream().collect(Collectors.toCollection(HashSet::new));

        System.out.println(set);

    }

    @Test
    public void testMyFilter() {
        List<Employee> lists = Arrays.asList(new Employee("张三", 18, 5555.55), new Employee("李四", 20, 6666.66), new Employee("王二", 5, 10000.55),
                new Employee("麻子", 40, 50000.55));

        Collections.sort(lists, (x, y) -> Integer.compare(x.getAge(), y.getAge()));
        filter(lists, x -> x.getAge() > 18);
        Predicate<Employee> startsWithJ = (employee) -> employee.getName().startsWith("王");
        Predicate<Employee> fourLetterLong = (employee) -> employee.getAge() == 5;
        lists.stream().filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("Name, which starts with '王' and age is 5 : " + n));
    }

    public static void filter(List<Employee> employees, Predicate<Employee> condition) {
        employees.stream().filter((employee) -> (condition.test(employee))).forEach((employee) -> {
            System.out.println(employee.getName() + " ");
        });
    }
}
