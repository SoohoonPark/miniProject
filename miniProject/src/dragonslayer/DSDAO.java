package dragonslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB에 접근하고 처리하는 DAO 클래스
public class DSDAO {
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	
	// 몹 정보를 가져와서 결과(rs)를 Service로 넘겨줌.
	public static ResultSet getMonsterData(Connection conn, String grade) {	
		try {
			System.out.println("[info] 넘겨받은 grade : "+grade);
			String query = "SELECT * FROM MONSTERS"+" WHERE M_GRADE = '"+grade+"'";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		}catch(SQLException sql) {
			System.out.println("[Error] SQL 처리 예외 발생(getMonster)");
			System.out.println(sql.getMessage());
		}
		return rs;
	}
	
	// 아이템 정보를 가져와서 결과(rs)를 Service로 넘겨줌
	public static ResultSet getItemData(Connection conn) {
		try {
			String query = "SELECT * FROM ITEMS";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		}catch(SQLException sql) {
			System.out.println("[Error] SQL 처리 예외 발생(getItem)");
			System.out.println(sql.getMessage());
		}
		return rs;
	}
	
	// 경험치 정보를 가져와서 결과(rs)를 Service로 넘겨줌
	public static ResultSet getExpData(Connection conn) {
		try {
			String query = "SELECT * FROM EXPTABLE";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		}catch(SQLException sql) {
			System.out.println("[Error] SQL 처리 예외 발생(getExp)");
			System.out.println(sql.getMessage());
		}
		return rs;
	}
	
	// pstmt 닫기 위한 메소드(Service에서 닫음)
	public static PreparedStatement getpstmt() {
		return pstmt;
	}
}
