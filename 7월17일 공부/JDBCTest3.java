package myjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest3 {
	public static void main(String[] args) throws Exception {
		//driver클래스를 메모리로 로딩한다.
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Connection 생성
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newhr", "root", "rootroot");
		//Statement 생성
		Statement stmt = con.createStatement();
		//Query 실행하여 ResultSet을 참조
		String sql = "select * from employees";
		ResultSet rs = stmt.executeQuery(sql);
		//DB를 읽어온다
		while(rs.next()) {
			String lastName = rs.getString("last_name");
			System.out.println(lastName);
		}
		//ResultSet을 닫는다, Statement도 닫는다, Connection도 닫는다
		con.close();
	}
	}
	
