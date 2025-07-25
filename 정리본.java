package project;
// 정리본이라 오류는 무시하셔도 됩니다.
public class 정리본 {
	// 메인을 최소화 하자/ 표기법은 camel 표기법을 사용하자 / 내가 종료하기 전 까지 어떤 오류가 떠도 꺼지지 않게 하자 
	//메인 실행
	public class Db_main {
		public static void main(String[] args) {
			MenuController.setContext(); 
			MenuController.run();
		}
	}
	
	// 메인에서 인터페이스 구현하기 위한 메뉴컨트롤러
	public class MenuController {
		static Scanner scan; //사용하는 메서드가 static이므로 변수도 static으로    
		static Db_method db;
		
		// setContext 함수 실행 -> db생성자로 connection(연결), san 사용
		static public void setContext() { // main이 스태틱메서드니까 setContext나 run메서드도 스태틱으로
		scan = new Scanner(System.in);
		db = new Db_method();
		}
		// run 함수 실행 -> 인터페이스 실행
		static public void run() {
		while(true) { // 숫자 이외의 문자 입력시 오류
				System.out.println("==기능을 선택하세요==");
				System.out.println("1. 직원이름으로 직원정보 검색하기");
				System.out.println("2. 입사년도로 직원정보 검색하기");
				System.out.println("3. 부서번호로 직원정보 검색하기");
				System.out.println("4. 직무로 직원정보 검색하기");
				System.out.println("5. 도시이름으로 직원정보 검색하기");
				System.out.println("6. 통계자료 출력하기");
				System.out.println("7. 부서장 성으로 부서원 정보 검색하기");
				System.out.println("8. 나라이름으로 직원정보 검색하기");
				System.out.println("0. 프로그램 종료하기");
				System.out.println("선택 >>");
				String num = scan.nextLine();
				
				int number=0;
		
				// scan으로 입력한 문자를 숫자로 바꾸는 과정에서 만약 문자를 입력하거나 숫자가 0~8 이외의 숫자라면
				// 각각 다른 오류가 발생하게 try, catch절을 사용하여 만듦
				// 각각의 오류 exception은 클래스로 만들어서 원하는 오류가 뜨게 설정
				try {
				    number = Integer.parseInt(num);
				    if (number < 0 || number > 8) {
				        throw new SelectNumberException();
				    }
				} catch (NumberFormatException e) {
				    System.out.println(new SelectInputException().getMessage());
				    run();
				} catch (SelectNumberException e) {
				    System.out.println(e.getMessage());
				}
				
				// switch문을 이용해 scan을 통해 받은 문자 -> 정수로 바꾼 number를 받아 case문 실행
				switch (number) {
					case 1 -> { 
							System.out.print("직원 이름을 입력하세요 : ");
							String fullName = scan.nextLine();
							db.findEmpInfoByEmpName(fullName);
					}					
					case 2 -> {
							System.out.print("입사년도를 입력하세요 : ");
							int year = scan.nextInt();
							scan.nextLine();
							db.findEmpInfoByHireYear(year);
					}
					case 3 -> {
							System.out.println("부서번호를 입력하세요 : [ex100]");
							int dpId = scan.nextInt();
							scan.nextLine();
							db.findEmpInfoByDeptId(dpId);
					}
					case 4 -> {
							System.out.print("직무를 입력하세요 : ");
							String job = scan.nextLine();
							db.findEmpInfoByJobId(job);
					}
					case 5 -> {
						System.out.print("도시명을 입력하세요 : ");
						String cityName = scan.nextLine();
						db.findEmpInfoByCity(cityName);
					}
					case 6 -> {
						System.out.println("어떤 연봉 통계자료를 출력할까요?");
						System.out.println("1. 부서별");
						System.out.println("2. 직무별");
						System.out.println("3. 도시별");
						System.out.println("4. 나라별");
						System.out.println("5. 대륙별");
						int selectSummry = scan.nextInt();
						db.getEmpSummaryData(selectSummry);	
					}
					case 7 -> {
						System.out.print("부서장 성을 입력하세요 : ");
						String managerLastName = scan.nextLine();
						db.findEmpInfoByManagerLastName(managerLastName);
					}
					case 8 -> {
						System.out.print("나라이름을 입력하세요 : ");
						String countryName = scan.nextLine();
						db.findEmpInfoByCountryName(countryName);
					}
					case 0 -> { 
						db.close();
						System.out.println("프로그램 종료");
							return;
				}
				
			}
			} 
			} 
		
		}
	
	// 숫자말고 문자를 입력했을때의 오류 
	public class SelectInputException extends Exception{
		public SelectInputException() {
			super("//오류가 발생하였습니다 \n오류번호 02: 문자입력 오류 \n문자를 제외하고 숫자를 입력해 주세요//");
		}
	}
	
	// 숫자가 범위를 벗어났을때의 오류
	public class SelectNumberException extends Exception{
		public SelectNumberException() {
			super("//오류가 발생하였습니다 \n오류번호 01: 번호입력 오류 \n범위에 맞는 숫자를 입력해 주세요//");
		}
	}
	
	// 기능과 기능에 중복되는 코드들을 함수로 만들어놓은 클래스
	public class Db_method { 
		// Connection, Query 등 오류발생 상황별로 나누기 위해 
		// Connection은 생성자를 만들어서 생성 시 접속하게 하고 오류가 발생할때 try catch절로 잡아 오류메시지 출력
		public Connection conn = null;
		DBConfig dbc;
		public Db_method() {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newhr", "root", "rootroot");
		// 실행 오류 시 DB 연결 실패 메세지 전송
			} catch (SQLException e) {
					System.out.println("DB 연결 실패: " + e.getMessage());

			}
		}
		
		// 직원정보를 담은 리스트 생성과 sql 실행, 들어오는 값으로 sql과 파라메타값 (object)를 받음
		// 실행오류 시 Query오류라고 뜨게 try catch
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
			public void findEmpInfoByEmpName(String fullName) {
			// main에서 실행 -> db.findEmpInfoByEmpName 실행 -> dbc.set(config)로 쿼리문과 fullName이라는
			// 파라매타를 받고 리스트 로반환 (empList에 저장)
			List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByEmpNameSql"), fullName);
			System.out.println("==이름이 " + fullName + "인 직원정보==");
			if(empList.size()==0) {
				System.out.println("직원이 존재하지 않습니다!");// 만약 입력했는데 아무것도 없다면
														//  -> 리스트의 사이즈가 0이면 존재 x 출력			
			}
			for(EmpInfo emp : empList) { // 리스트 출력 아래도 동일함
				System.out.println(emp);
				}
			}	
			public void findEmpInfoByHireYear(int year) { 
			List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByHireYearSql"), year);
			System.out.println("==" + year + "년 입사 직원정보==");
			if(empList.size()==0) {
				System.out.println("직원이 존재하지 않습니다!");
			}
			for(EmpInfo emp : empList) {
				System.out.println(emp);
				}
			}
			public void findEmpInfoByDeptId(int deptId) { 
				List<EmpInfo> empList = executeQuery(dbc.set("getEmpInfoByDeptIdSql"), deptId);
				System.out.println("==부서번호가 " + deptId + "인 직원정보==");
				if(empList.size()==0) {
					System.out.println("직원이 존재하지 않습니다!");
				}
				for(EmpInfo emp : empList) {
					System.out.println(emp);
				}
			}
			public void findEmpInfoByJobId(String job) {
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
				// switch 안의 swith라고 생각하면 됨 6번을 선택하면 1~5의 선택지가 나오고
				// 거기서 선택한 번호(selectSummaryN)에 맞게 출력
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
	
	
	/*


driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/newhr
userName=root
passWord=rootroot


getEmpInfoByEmpNameSql=select manager_id,job_id,department_id,ifnull(commission_pct,0) as commission_pct ,phone_number, employee_id, last_name, first_name, salary, email, date_format(e.hire_date,'%Y-%m-%d') as hire_date from employees e where lower(concat(last_name, first_name)) = lower(Replace(?,' ',''))
getEmpInfoByHireYearSql=select manager_id,job_id,department_id,ifnull(commission_pct,0) as commission_pct,phone_number,employee_id,last_name, first_name,salary,email,date_format(e.hire_date,'%Y-%m-%d') as hire_date from employees e where year(hire_date) = ?
getEmpInfoByJobIdSql=select manager_id,job_id,department_id,ifnull(commission_pct,0) as commission_pct,phone_number,employee_id,last_name, first_name,salary,email,date_format(e.hire_date,'%Y-%m-%d') as hire_date from employees e where job_id = ?
getEmpInfoByDeptIdSql=select manager_id,job_id,department_id,ifnull(commission_pct,0) as commission_pct,phone_number,employee_id,last_name, first_name,salary,email,date_format(e.hire_date,'%Y-%m-%d') as hire_date from employees e where department_id = ?
getEmpInfoByCityNameSelect=select manager_id,job_id,department_id,ifnull(commission_pct,0) as commission_pct,phone_number,employee_id,last_name, first_name,salary,email,date_format(e.hire_date,'%Y-%m-%d') as hire_date from employees e where e.department_id in (select d.department_id from departments d where d.location_id in (select l.location_id from locations l where city = ?))
getEmpInfoByManagerId=select e.manager_id,e.job_id,e.department_id,ifnull(e.commission_pct,0) as commission_pct,e.phone_number,e.employee_id, e.last_name, e.first_name, e.salary, e.email, date_format(e.hire_date,'%Y-%m-%d') as hire_date, m.last_name from employees e left join employees m on e.manager_id = m.employee_id where m.last_name = ?
getEmpInfoByCountryName=select manager_id,job_id,department_id,ifnull(commission_pct,0) as commission_pct,phone_number,employee_id, last_name, salary, first_name, email,date_format(e.hire_date,'%Y-%m-%d') as hire_date from employees e where e.department_id in (select d.department_id from departments d where d.location_id in(select l.location_id from locations l where l.country_id in (select c.country_id from countries c where country_name = ?)))

sumJob=select j.job_id as id, j.job_title as name, j.job_title as name,format(round(sum(e.salary * (1 + coalesce(e.commission_pct,0)) * 12 * 1390),-3),0) as sum, format(round(avg(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as avg, format(round(max(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as max, format(round(min(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as min, count(*) as count from employees e join jobs j on e.job_id = j.job_id group by j.job_id order by j.job_id
sumDpId=select d.department_id as id, d.department_name as name, format(round(sum(e.salary * (1 + coalesce(e.commission_pct,0)) *12 * 1390),-3),0) as sum, format(round(avg(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as avg, format(round(max(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as max, format(round(min(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as min, count(*) as count from employees e join departments d on e.department_id = d.department_id group by d.department_id order by d.department_id
sumCity=select l.location_id as id, l.city as name, format(round(sum(e.salary * (1 + coalesce(e.commission_pct,0)) *12 * 1390),-3),0) as sum, format(round(avg(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as avg, format(round(max(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as max, format(round(min(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as min, count(*) as count from employees e join departments d on e.department_id = d.department_id join locations l on d.location_id = l.location_id group by l.location_id order by l.location_id
sumCountry=select c.country_id as id, c.country_name as name, format(round(sum(e.salary * (1 + coalesce(e.commission_pct,0)) *12 * 1390),-3),0) as sum, format(round(avg(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as avg, format(round(max(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as max, format(round(min(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as min, count(*) as count from employees e join departments d on e.department_id = d.department_id join locations l on d.location_id = l.location_id join countries c on l.country_id = c.country_id group by c.country_id order by c.country_id
sumRegion=select r.region_id as id, r.region_name as name, format(round(sum(e.salary * (1 + coalesce(e.commission_pct,0)) *12 * 1390),-3),0) as sum, format(round(avg(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as avg, format(round(max(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as max, format(round(min(e.salary * (1 + coalesce(e.commission_pct, 0)) * 12 * 1390),-3),0) as min, count(*) as count from employees e join departments d on e.department_id = d.department_id join locations l on d.location_id = l.location_id join countries c on l.country_id = c.country_id join regions r on c.region_id = r.region_id group by r.region_id order by r.region_id

	 */
		



	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
