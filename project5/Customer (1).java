package 매장관리기능;

import java.util.ArrayList;

public class Customer {
	
	public String name;
	public int age;
	public String mobile;
	public ArrayList <Product> myProducts = new ArrayList <Product> ();  // 내가 산 제품들 변수
	public int sumPrice = 0;
	static int totalPrice;
	
	public Customer(String name, int age, String mobile) {
		this.name = name;
		this.age = age;
		this.mobile = mobile;
	}
	
}
