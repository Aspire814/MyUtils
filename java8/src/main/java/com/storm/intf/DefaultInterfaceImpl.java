package com.storm.intf;

public interface DefaultInterfaceImpl<T> {
	public T fun(int a);

	default String getName() {

		return "Hello Java8!";
	}

	public static void testInterfaceStacticFunc() {
		System.out.println("testInterfaceStacticFunc");
	}

	public static void main(String[] args) {
		testInterfaceStacticFunc();
	}

}

