package com.atguigu.exer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.atguigu.java8.Employee;

public class TestLambda {
	
	List<Employee> emps = Arrays.asList(
			new Employee(101, "张三", 18, 9999.99),
			new Employee(102, "李四", 59, 6666.66),
			new Employee(103, "王五", 28, 3333.33),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(105, "田七", 38, 5555.55)
	);

	/**
	 * 需求：调用 Collection.sort() 方法，通过定制排序比较两个 Employee（先按年龄比，年龄相同按姓名比），使用 Lambda 作用参数传递。
	 */
	@Test
	public void test1(){
		Collections.sort(emps, (e1, e2) -> {
			if(e1.getAge() == e2.getAge()){
					return e1.getName().compareTo(e2.getName());
			}else{
				return -Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		
		for (Employee emp : emps) {
			System.out.println(emp);
		}
	}

	/**
	 * 1、声明函数式接口，接口中声明抽象方法，public String getValue(String str);
	 * 2、使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。
	 * 3、再将一个字符串进行截取子串的操作。
	 */
	@Test
	public void test2(){
		String trimStr = strHandler("\t\t\t 我大尚硅谷威武   ", (str) -> str.trim());
		System.out.println(trimStr);
		
		String upper = strHandler("abcdef", (str) -> str.toUpperCase());
		System.out.println(upper);
		
		String newStr = strHandler("我大尚硅谷威武", (str) -> str.substring(2, 5));
		System.out.println(newStr);
	}
	
	//需求：用于处理字符串
	public String strHandler(String str, MyFunction mf){
		return mf.getValue(str);
	}

	/**
	 * 1、声明一个带两个泛型的函数式接口，泛型类型为 <T,R> T为参数，R为返回值。
	 * 2、接口中声明对应的抽象方法。
	 * 3、使用接口作为参数，计算两个 long 型参数的和。
	 * 4、再计算两个 long 型参数的乘积。
	 */
	@Test
	public void test3(){
		op(100L, 200L, (x, y) -> x + y);
		
		op(100L, 200L, (x, y) -> x * y);
	}
	
	//需求：对于两个 Long 型数据进行处理
	public void op(Long l1, Long l2, MyFunction2<Long, Long> mf){
		System.out.println(mf.getValue(l1, l2));
	}

}
