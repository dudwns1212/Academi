package project4;

public class Animal {
	
	String name;
	int age;
	String mobile;
	
	public static int count=0;
	
	public Animal() {
		count = count + 1;
	}
	
	public String getNmae() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void standup(){
		System.out.println(this.name + "가 일어납니다.");
	}
	
	public void sitdown() {
		System.out.println(this.name + "가 앉습니다.");
	}
	
	public void run() {
		System.out.println(this.name + "가 뛰어갑니다.");
	}
}
