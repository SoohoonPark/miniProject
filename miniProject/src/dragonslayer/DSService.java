package dragonslayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

// DAO에 접근해서 결과값들을 가져오는 Service
public class DSService {
	private Connection conn = null;
	private static DSService service = new DSService(); // 객체 인스턴스화
	
	// Service 생성자 instance화
	private DSService() {
		conn = DSDBConnection.DBConn();
		if(conn != null) {
			System.out.println("[info] conn 객체 생성 완료");
		}
	} 
	
	public static DSService getInstance() {
		return service;
	}
	
	// DAO 로 부터 가져온 몹 정보를 LinkedList에 담아서 GameScreen에 넘겨줌.
	public LinkedList<DSMonsters> monsterData(){
		LinkedList<DSMonsters> monsters = new LinkedList<DSMonsters>();
		ResultSet rs = DSDAO.getMonsterData(conn);
		try {
			while(rs.next()) {
				monsters.add(new DSMonsters(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
			}
		}catch(SQLException sql) {
			System.out.println("[Error] SQL 데이터 가져오기 에러");
			System.out.println(sql.getMessage());
		}finally {
			DBConnectClose.Close(rs);
			DBConnectClose.Close(DSDAO.getPstmt());
			DBConnectClose.Close(conn);
		}
		
		return monsters;
	}
}
