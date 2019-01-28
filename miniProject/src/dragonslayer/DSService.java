package dragonslayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

// DAO를 사용해서 DB로부터 넘어온 값들을 처리하는 서비스
public class DSService {
	private Connection conn = null;
	private static DSService service = new DSService(); // 객체 인스턴스화

	// Service 생성자 (private 접근 제한자를 통해 인스턴스화 시킴)
	private DSService() {
		conn = DSDBConnection.DBConn();
		if (conn != null) {
			System.out.println("[info] conn 객체 생성 완료");
		}
	}

	public static DSService getInstance() {
		return service;
	}

	// DAO 로 부터 가져온 몹 정보를 LinkedList에 담아서 GameScreen에 넘겨줌. (초급 몹 정보)
	public LinkedList<DSMonsters> monsterData(String grade) {
		LinkedList<DSMonsters> monsters = new LinkedList<DSMonsters>();
		ResultSet mob_rs = DSDAO.getMonsterData(conn, grade);
		try {
			while (mob_rs.next()) {
				monsters.add(new DSMonsters(mob_rs.getString(1), mob_rs.getString(2), mob_rs.getInt(3),
						mob_rs.getInt(4), mob_rs.getInt(5), mob_rs.getInt(6), mob_rs.getString(7)));
			}
		} catch (SQLException sql) {
			System.out.println("[Error] SQL 데이터 가져오기 에러");
			System.out.println(sql.getMessage());
		} finally {
			DBConnectClose.Close(mob_rs);
			DBConnectClose.Close(DSDAO.getPstmt());
			DBConnectClose.Close(conn);
		}
		return monsters;
	}
}
