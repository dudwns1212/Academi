package myjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpMain {
	public static void main(String[] args) throws Exception {
		//부서번호를 입력받아서 그 부서에 근무하는 사원들의 목록을 출력함
		System.out.println("입사년도 : " );
		Scanner scan = new Scanner(System.in);
		int date = scan.nextInt();
		while(date!=0) {
		System.out.println(date);
		//부서번호를 전달받아서 그 부서의 부서원정보를 리턴함
		List<Emp> empList = EmpDAO.getEmpListByDEptNo(date); // 오류
		for(Emp emp : empList) {
			System.out.println(emp);
		}
		System.out.print("입사년도가 (null값)이면 종료");
		date = scan.nextInt();
		}
	}
}
	
