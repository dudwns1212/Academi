package 매장관리기능;
import java.util.ArrayList;
public class Store {
	
	String name;
	ArrayList<Product> products = new ArrayList<Product>();     // 제품들 변수
	ArrayList<Customer> customers = new ArrayList<Customer>();  // 고객들 변수
	
	public Store(String name) {
		this.name = name;
	}
	
	void pay(Customer customer, Product product) {
		
		customer.myProducts.add(product);
		product.myPrice.add(product);
		customer.sumPrice += product.price;
		Customer.totalPrice += product.price;
		
		System.out.println(customer.name + "이 구매한 제품명 : " + product.name + ", 가격 : " + product.price);
		System.out.println(customer.name + "님이 총 구매한 금액 : " + customer.sumPrice);
		System.out.println("팔린 전체 금액 : " + Customer.totalPrice);
		
		
	}
	
}
