package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {

	public static void main(String[] args) {
		
		// 입력 받은 급여보다 초과해서 받는 사원의
		// 사번, 이름, 급여 조회
		
		// 1. JDBC 객체 참조용 변수 선언
		Connection conn = null; // DB연결정보 저장 객체
		Statement stmt = null;  // SQL 수행, 결과 반환용 객체
		ResultSet rs = null;	// SELECT 수행 결과 저장 객체
	


		
		try {
			// 2. DriverManager 객체를 이용해서 Connection 객체 생성
			// 2-1) Oracle JDBC Driver 객체 메모리 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2-2) DB 연결 정보 작성
			String type = "jdbc:oracle:thin:@"; 
			String host = "localhost"; 
			String port = ":1521"; 
			String dbName = ":XE"; 	
			String userName = "kh_kwh";
			String password = "kh1234";
			
			// 2-3) DB 연결 정보와 DriverManager 를 이용해서 Connection 객체 생성
			conn = DriverManager.getConnection(type+host+port+dbName,userName,password );
			
			// 3. SQL 작성
			// 입력받은 급여 -> Scanner 필요
			// int input 여기에 급여 담기
			Scanner sc = new Scanner(System.in);
			System.out.print("급여 입력 : ");
			int input = sc.nextInt();
			
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE WHERE SALARY > " + input;
			
			// 4. Statment 객체 생성 
			stmt = conn.createStatement();
			
			// 5. Statment 객체를 이용하여 SQL 수행 후 결과 반환 받기
			// excuteQuery() : SELECT 실행, ResultSet 반환
			// executeUpdate() : DML 실행, 결과 행의 개수 반환(int)
			rs = stmt.executeQuery(sql);
			
			// 6. 조회 결과가 담겨있는 ResultSet 을
			// 커서 이용해 1행씩 접근해
			// 각 행에 작성된 컬럼값 얻어오기
			// -> while 안에서 꺼낸 데이터 출력
			
			// 201 / 송중기 / 6000000원
			// 202 / 노옹철 / 6000000원
			// 203 / 송은희 / 6000000원
			// ...
			while(rs.next()) {
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				System.out.printf(" %s / %s / %d원 \n", empId, empName, salary);
			}
		
		} catch (Exception e) { 
			// 최상위 예외인 Exception을 이용해서 모든 예외를 처리
			// -> 다형성 업캐스팅 적용
			e.printStackTrace();
		} finally {
			// 7. 사용 완료된 JDVC 객체 자원 반환(close)
			// -> 생성된 역순으로 close!
			try {
				if( rs != null ) rs.close();
				if( stmt != null ) stmt.close();
				if( conn != null ) conn.close();
			} catch (Exception e) { e.printStackTrace();}
		}
		
		
	}
	
	
}