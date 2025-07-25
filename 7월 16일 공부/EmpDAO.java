package myjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	public static List<Emp> getEmpListByDEptNo(int date) throws Exception {
		List<Emp> result = new ArrayList<Emp>();

		Connection conn =
				DriverManager.getConnection("jdbc:mysql://localhost:3306/newhr", "root", "rootroot");

		Statement stmt = conn.createStatement();
		
		String sql = "select * from employees where year(hire_date) = " + date;
		ResultSet rs = stmt.executeQuery(sql); // 오류
		
		while(rs.next()) {
			Emp emp = new Emp();
			emp.id = rs.getInt("employee_id");
			emp.last_name = rs.getString("last_name");
			emp.salary = rs.getInt("salary");
			result.add(emp);
		}
		
		return result;
	}

}

