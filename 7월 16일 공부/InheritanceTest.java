package basic;

public class InheritanceTest {
	public static void main(String[] args) {
		Shape shape = new Rectangle();
		shape.print();
	}
	
}

// 일종의 추상클래스, .class로 존재한다. 객체생성되지 않는다. 그러나 타입으로는 생성가능하다.
// 내부에 메서드를 선언하는 용도로 사용한다.
interface Shape {
	public abstract void print(); 
}

class Rectangle implements Shape{
	// 정의란 {} 구간을 만드는 것이고 호출되었을 때 무엇이 실행될지를 기술해 둔다.
	public void print() {
		
	}
}
