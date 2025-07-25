package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import myjdbc.Emp;

public class Db_method { 
	
	public Connection conn = null;
	DBConfig dbc;
	// 생성자에 커넥션을 넣어서 실행 -> main에 db객체 생성
	public Db_method() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newhr", "root", "rootroot");
	// 실행 오류 시 DB 연결 실패 메세지 전송
		} catch (SQLException e) {
				System.out.println("DB 연결 실패: " + e.getMessage());

		}
	}
	
	// 직원정보를 담은 리스트 생성과 sql 실행, 들어오는 값으로 sql과 파라메타값 (object)를 받음
	public List<EmpInfo> executeQuery(String sql, Object... params) {// int와 String을 모두 받음
		List<EmpInfo> empList = new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				for (int i = 0; i < params.length; i++) {// setObject(i,params)로 2개 이상의 변수도 담을 수 있음
            stmt.setObject(i + 1, params[i]);
				}
				ResultSet rs = stmt.executeQuery(); 
				// while문으로 직원정보를 리스트에 담음 -> empinfo클래스에 변수가 정의되어 있음
				while(rs.next()) {
					EmpInfo emp = new EmpInfo();
					emp.employeeId = rs.getInt("employee_id");
					emp.lastName = rs.getString("last_name");
					emp.salary = rs.getInt("salary");
					emp.email = rs.getString("email");
					emp.hireDate = rs.getString("hire_date");
					emp.firstName = rs.getString("first_name");
					emp.phoneNumber = rs.getString("phone_number");
					emp.jobId = rs.getString("job_id");
					emp.managerId = rs.getString("manager_id");
					emp.departmentId = rs.getString("department_id");
					emp.commissionPct = rs.getDouble("commission_pct");
					
					empList.add(emp);
			}
		} catch (SQLException e) { // 쿼리 실행에 오류 발생 시 실패 메세지 전송
				 System.out.println("쿼리 실행 오류: " + e.getMessage());
		}
		return empList;
		}
	
	// 위와 같은 메서드로 집계함수 정보를 담은 리스트 생성과 sql 실행
	public List<SummryData> executeQueryS(String sql, Object... params) {
		List<SummryData> sumDataList = new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
				}
				ResultSet rs = stmt.executeQuery();
		
				while(rs.next()) {
					SummryData sum = new SummryData();
					sum.id = rs.getString("id");
					sum.name = rs.getString("name");
					sum.avg = rs.getString("avg");
					sum.max = rs.getString("max");
					sum.min = rs.getString("min");
					sum.count = rs.getInt("count");
					sum.sum = rs.getString("sum");
					sumDataList.add(sum);
			}
		} catch (SQLException e) { 
				 System.out.println("쿼리 실행 오류: " + e.getMessage());
		}
		return sumDataList;
	}
	
		//기능들 메서드
		public void findEmpInfoByEmpName(String fullName) { // 직원이름
		List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByEmpNameSql"), fullName);
		System.out.println("==이름이 " + fullName + "인 직원정보==");
		if(empList.size()==0) {
			System.out.println("직원이 존재하지 않습니다!");
		}
		for(EmpInfo emp : empList) {
			System.out.println(emp);
			}
		}	
		public void findEmpInfoByHireYear(int year) { // 입사년도 작동 ㅇ
		List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByHireYearSql"), year);
		System.out.println("==" + year + "년 입사 직원정보==");
		if(empList.size()==0) {
			System.out.println("직원이 존재하지 않습니다!");
		}
		for(EmpInfo emp : empList) {
			System.out.println(emp);
			}
		}
		public void findEmpInfoByDeptId(int deptId) { // 부서번호 작동 o
			List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByDeptIdSql"), deptId);
			System.out.println("==부서번호가 " + deptId + "인 직원정보==");
			if(empList.size()==0) {
				System.out.println("직원이 존재하지 않습니다!");
			}
			for(EmpInfo emp : empList) {
				System.out.println(emp);
			}
		}
		public void findEmpInfoByJobId(String job) { // 직무 작동 ㅇ
			List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByJobIdSql"), job);
			System.out.println("==" + job + "직군인 직원정보==");
			if(empList.size()==0) {
				System.out.println("직원이 존재하지 않습니다!");
			}
			for(EmpInfo emp : empList) {
				System.out.println(emp);
			}
		}	
		public void findEmpInfoByCity(String cityName) {
			List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByCityNameSelect"), cityName);
			System.out.println("==" + cityName + "에서 근무하는 직원정보==");
			if(empList.size()==0) {
				System.out.println("직원이 존재하지 않습니다!");
			}
			for(EmpInfo emp : empList) {
				System.out.println(emp);
			}
		}
		public void getEmpSummaryData(int selectSummaryN) {
			List<SummryData> sumDataList;
			
			if(selectSummaryN == 1) {
				sumDataList = executeQueryS(dbc.set("sumDpId"));
				System.out.println("==부서별 연봉 통계입니다==");
			}else if(selectSummaryN == 2) {
				sumDataList = executeQueryS(dbc.set("sumJob"));
				System.out.println("==직무별 연봉 통계입니다==");
			}else if(selectSummaryN == 3){
				sumDataList = executeQueryS(dbc.set("sumCity"));
				System.out.println("==도시별 연봉 통계입니다==");
			}else if(selectSummaryN == 4){
				sumDataList = executeQueryS(dbc.set("sumCountry"));
				System.out.println("==나라별 연봉 통계입니다==");
			}else if(selectSummaryN == 5){
				sumDataList = executeQueryS(dbc.set("sumRegion"));
				System.out.println("==대륙별 연봉 통계입니다==");
			}else {
				System.out.println("==잘못입력하셨습니다==");
				return;
			}
			
			for(SummryData sum : sumDataList) {
				System.out.println(sum);
			}
		}
		public void findEmpInfoByManagerLastName(String managerLastName) { 
			List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByManagerId"), managerLastName);
			System.out.println("==매니저 성이 " + managerLastName + "인 부서원정보==");
			if(empList.size()==0) {
				System.out.println("직원이 존재하지 않습니다!");
			}
			for(EmpInfo emp : empList) {
				System.out.println(emp);
			}
		}
		public void findEmpInfoByCountryName(String countryName) { // 나라이름
			List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByCountryName"), countryName);
			System.out.println("==" + countryName + "에서 근무하는 직원정보==");
			if(empList.size()==0) {
				System.out.println("직원이 존재하지 않습니다!");
			}
			for(EmpInfo emp : empList) {
				System.out.println(emp);
			}
		}
		// 콘솔 닫는 메서드 
		public void close() {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
					System.out.println("DB 연결 해제");
				}
			} catch (SQLException e) {
					
			}
		}

	
}
	
