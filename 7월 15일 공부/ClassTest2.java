package myjdbc;

public class ClassTest2 {
	public static void main(String[] args) {
		Util util = new Util();
		util.name = "hahaha";
		System.out.println(util.name);
		Util util2 = new Util();
		System.out.println(util2.name);
	}
}

class Util {
	static String name;
	public static String getName() {
		return name;
	}
	
}
class Sam2 {
	int count; 
	static int amount;
	void print() {
		int localCount;
		try {
			int myCount = 500; 
		}catch(Exception e) {
			
		}
		
	}
}