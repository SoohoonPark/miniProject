package dragonslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DBConnection Ŭ����
public class DSDBConnection {
	private final static String DBID = "itbank";
	private final static String DBPW = "PAGODA";

//	private final static String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final static String DBURL = "jdbc:oracle:thin:@175.200.206.7:1521:orcl";
	private static Connection conn = null;
	
	public static Connection DBConn() {
		System.out.println("[info] DB Connect ����");
		try { // ����̹� �ε� ���� ó��
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try { // SQL connect ���� ó��
				conn = DriverManager.getConnection(DBURL, DBID, DBPW);
			}catch(SQLException sql) {
				System.out.println(sql.getMessage());
				System.out.println("[Error] SQL Connect ����");
			}
		}catch(Exception e) {
			System.out.println("[Error] ����̹� �ε� ����");
		}
		System.out.println("[info] DB Connect �Ϸ�");
		return conn;
	}
}
