package dragonslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB�� �����ϰ� ó���ϴ� DAO Ŭ����
public class DSDAO {
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	
	// �� ������ �����ͼ� ���(rs)�� Service�� �Ѱ���.
	public static ResultSet getMonsterData(Connection conn, String grade) {	
		try {
			System.out.println("[info] �Ѱܹ��� grade : "+grade);
			String query = "SELECT * FROM MONSTERS"+" WHERE M_GRADE = '"+grade+"'";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		}catch(SQLException sql) {
			System.out.println("[Error] SQL ó�� ���� �߻�(getMonster)");
			System.out.println(sql.getMessage());
		}
		return rs;
	}
	
	// ������ ������ �����ͼ� ���(rs)�� Service�� �Ѱ���
	public static ResultSet getItemData(Connection conn) {
		try {
			String query = "SELECT * FROM ITEMS";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		}catch(SQLException sql) {
			System.out.println("[Error] SQL ó�� ���� �߻�(getItem)");
			System.out.println(sql.getMessage());
		}
		return rs;
	}
	
	// ����ġ ������ �����ͼ� ���(rs)�� Service�� �Ѱ���
	public static ResultSet getExpData(Connection conn) {
		try {
			String query = "SELECT * FROM EXPTABLE";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		}catch(SQLException sql) {
			System.out.println("[Error] SQL ó�� ���� �߻�(getExp)");
			System.out.println(sql.getMessage());
		}
		return rs;
	}
	
	// pstmt �ݱ� ���� �޼ҵ�(Service���� ����)
	public static PreparedStatement getpstmt() {
		return pstmt;
	}
}
