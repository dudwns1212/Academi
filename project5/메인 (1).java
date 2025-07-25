package 매장관리기능;

public class 메인 {

	public static void main(String[] args) {
		//고객 생성
		Customer customer1 = new Customer("고객1", 20, "010-1111-1111");
		Customer customer2 = new Customer("고객2", 21, "010-2222-2222");
		Customer customer3 = new Customer("고객3", 22, "010-3333-3333");
		Customer customer4 = new Customer("고객4", 23, "010-4444-4444");
		Customer customer5 = new Customer("고객5", 24, "010-5555-5555");
		Customer customer6 = new Customer("고객6", 25, "010-6666-6666");
		//제품 생성
		Product product1 = new Product("제품1", 1000);
		Product product2 = new Product("제품2", 2000);
		Product product3 = new Product("제품3", 3000);
		Product product4 = new Product("제품4", 4000);
		Product product5 = new Product("제품5", 5000);
		Product product6 = new Product("제품6", 6000);
		//매장 생성
		Store store1 = new Store("매장1");
		Store store2 = new Store("매장2");
		//매장1에 제품들과 고객 넣어주기
		store1.products.add(product1);
		store1.products.add(product2);
		store1.products.add(product3);
		store1.customers.add(customer1);
		store1.customers.add(customer2);
		store1.customers.add(customer3);
		//매장2에 제품들과 고객 넣어주기
		store2.products.add(product4);
		store2.products.add(product5);
		store2.products.add(product6);
		store2.customers.add(customer4);
		store2.customers.add(customer5);
		store2.customers.add(customer6);
		//매장에 있는 고객과 제품의 숫자
		System.out.println("매장1 고객의 수 : " + store1.customers.size());
		System.out.println("매장1 제품의 수 : " + store1.products.size());
		System.out.println("매장2 고객의 수 : " + store2.customers.size());
		System.out.println("매장2 제품의 수 : " + store2.products.size());
		
		store1.pay(customer1, product1);
		store1.pay(customer1, product2);
		store1.pay(customer2, product3);
		store2.pay(customer3, product4);
		store2.pay(customer4, product5);
		store2.pay(customer3, product6);
		
		System.out.println("-------------계산대--------------");
		System.out.println(customer1.name + "님의 총 결제 금액 : " + customer1.sumPrice);
		System.out.println(customer2.name + "님의 총 결제 금액 : " + customer2.sumPrice);
		System.out.println(customer3.name + "님의 총 결제 금액 : " + customer3.sumPrice);
		System.out.println(customer4.name + "님의 총 결제 금액 : " + customer4.sumPrice);
		System.out.println(customer5.name + "님의 총 결제 금액 : " + customer5.sumPrice);
		System.out.println(customer6.name + "님의 총 결제 금액 : " + customer6.sumPrice);
		
	}

	
	
}
