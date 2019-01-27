package dragonslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB�� �����ϰ� ó���ϴ� DAO Ŭ����
public class DSDAO {
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	// �� ������ �����ͼ� ���(rs)�� Service�� �Ѱ���.
	public static ResultSet getMonsterData(Connection conn, String grade) {	
		try {
			String query = "SELECT * FROM MONSTERS"+" WHERE M_GRADE = '"+grade+"'";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		}catch(SQLException sql) {
			System.out.println("[Error] SQL ó�� ���� �߻�");
			System.out.println(sql.getMessage());
		}
		
		return rs;
	}
	
	public static PreparedStatement getPstmt() {
		return pstmt;
	}
}
