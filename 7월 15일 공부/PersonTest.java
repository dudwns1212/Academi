package Ex04;

public class PersonTest {
	public static void main(String[] args) {
		Person person1 = new Person();
		person1.name = "Anonymous";
		person1.age = 12;
		
		Person person2 = new Person("철수",3);
		
		person1.selfIntroduce();
		person2.selfIntroduce();
		
		Person.getPopulation();
	}
}

class Person {
	int age;
	String name;
	static int numberOfPerson;
	
	Person(){
		numberOfPerson++;
	}
	Person(String name, int age){
		this.name=name;
		this.age=age;
		numberOfPerson++;
	}
	
	void selfIntroduce(){
		System.out.println("내 이름은 " + name + "이며, 나이는 " + age + "살 입니다.");
	}
	
	static void getPopulation() {
		System.out.println(numberOfPerson);
	}
	
}