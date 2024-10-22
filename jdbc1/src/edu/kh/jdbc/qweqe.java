import java.sql.Connection;
import java.sql.Date;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.User;

public User selectId(Connection conn, String input) {
		
		User user = null; // 결과 저장용 변수
		
		try {
			
			// SQL 작성
			String sql = "SELECT * FROM TB_USER WHERE USER_ID = ?";
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ?(위치 홀더)에 알맞은 값 대입
			pstmt.setString(1, input);
			
			// SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			
			// 조회 결과가 있을 경우
			// -> 중복되는 아이디가 없을 경우
			// 1행만 조회되기 때문에 while문 보다는 if를 사용하는게 효과적
			if(rs.next()) {
				// 첫 행에 데이터가 존재한다면
				
				// 각 컬럼의 값 얻어오기
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				
				// java.sql.Date 활용
				Date enrollDate = rs.getDate("ENROLL_DATE");
				
				// 조회된 컬럼값을 이용해서 user 개체 생성
				user = new User( userNo,userId,userPw,userName,enrollDate.toString());
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 JDBC 객체 자원 반환
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			// Connection 객체는 생성된 Service에서 close!!!
		}
           
		return user; // 결과 반환( 생성된 User 또는 null )
	}