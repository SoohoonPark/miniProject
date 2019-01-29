package dragonslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DBConnection 클래스
public class DSDBConnection {
	private final static String DBID = "itbank";
	private final static String DBPW = "PAGODA";

//	private final static String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final static String DBURL = "jdbc:oracle:thin:@175.200.206.7:1521:orcl";
	private static Connection conn = null;
	
	public static Connection DBConn() {
		System.out.println("[info] DB Connect 시작");
		try { // 드라이버 로드 예외 처리
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try { // SQL connect 예외 처리
				conn = DriverManager.getConnection(DBURL, DBID, DBPW);
			}catch(SQLException sql) {
				System.out.println(sql.getMessage());
				System.out.println("[Error] SQL Connect 에러");
			}
		}catch(Exception e) {
			System.out.println("[Error] 드라이버 로드 에러");
		}
		System.out.println("[info] DB Connect 완료");
		return conn;
	}
}
