package com.storm.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

public class LambdaTest {
	@Test
	public void test1() {
		double price = 10000.0;

		consumer(price, (p) -> System.out.print("一共消费了:" + p + "元"));
	}

	public void consumer(double price, Consumer<Double> consumer) {
		consumer.accept(price);
	}

	@Test // 利用供给型接口随机生成5个大于50小于100的数
	public void test2() {
		int num = 5;
		@SuppressWarnings("unchecked")
		List<Integer> list = (ArrayList<Integer>) supplier(num, () -> {
			return (int) (Math.random() * 50 + 50);
		});
		list.forEach(System.out::println);
	}

	@SuppressWarnings("unchecked")
	public <T> T supplier(int num, Supplier<Integer> supplier) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; ++i) {
			Integer integer = supplier.get();
			list.add(integer);
		}
		return (T) list;
	}

	@Test // 函数式接口 对字符串进行处理
	public void test3() {
		String s = strHandle("\t\t\t Function<T,R> ", (str) -> str.trim());
		System.out.print(s);

	}

	public String strHandle(String str, Function<String, String> function) {
		return function.apply(str);
	}

	@Test // 断言式接口 将字符个数大于3的字符串输出
	public void test4() {
		List<String> list = Arrays.asList("asdbjkfd", "shdfk", "dfds", "a", "v");
		List<String> newList = filterString(list, (str) -> str.length() > 3);
		newList.forEach(System.out::println);
	}

	public List<String> filterString(List<String> list, Predicate<String> predicate) {
		List<String> newList = new ArrayList<>();
		for (String str : list) {
			if (predicate.test(str)) {
				newList.add(str);
			}
		}
		return newList;
	}

	@Test
	public void myConsumer() {
		String something = "something";
		getSomething(something, x -> System.out.println(x));
		List<String> list = Arrays.asList("abc", "sdd", "gedsg");
		list.stream().forEach(x -> System.out.println(x));
	}

	public void getSomething(String something, Consumer<String> consumer) {
		consumer.accept(something);
	}

	public String supplier(String something, Supplier<String> supplier) {
		return supplier.get();
	}

}
