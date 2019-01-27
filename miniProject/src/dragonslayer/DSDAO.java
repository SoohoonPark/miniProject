package dragonslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB에 접근하고 처리하는 DAO 클래스
public class DSDAO {
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	// 몹 정보를 가져와서 결과(rs)를 Service로 넘겨줌.
	public static ResultSet getMonsterData(Connection conn, String grade) {	
		try {
			String query = "SELECT * FROM MONSTERS"+" WHERE M_GRADE = '"+grade+"'";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		}catch(SQLException sql) {
			System.out.println("[Error] SQL 처리 예외 발생");
			System.out.println(sql.getMessage());
		}
		
		return rs;
	}
	
	public static PreparedStatement getPstmt() {
		return pstmt;
	}
}
