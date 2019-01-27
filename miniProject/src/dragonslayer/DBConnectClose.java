package dragonslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// ����,����,��� ��ü�� �ݴ� Ŭ����
public class DBConnectClose {
	public static void Close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {}
		}
	}
	
	public static void Close(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {}
		}
	}
	
	public static void Close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {}
		}
	}
}
