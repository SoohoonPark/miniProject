package dragonslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

// DAO를 사용해서 DB로부터 넘어온 값들을 처리하는 서비스
public class DSService {
	private Connection conn = null;

	// DAO 로 부터 가져온 몹 정보를 LinkedList에 담아서 GameScreen에 넘겨줌.
	public LinkedList<DSMonsters> monsterData(String grade) {
		conn = DSDBConnection.DBConn();
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
		}
		
		DBConnectClose.Close(mob_rs);
		DBConnectClose.Close(DSDAO.getpstmt());
		DBConnectClose.Close(conn);
		return monsters;
	}
	
	// DAO 로 부터 가져온 아이템 정보를 리스트에 담아서 GameScreen에 넘겨줌
	public LinkedList<DSItems> itemData(){
		conn = DSDBConnection.DBConn();
		LinkedList<DSItems> items = new LinkedList<DSItems>();
		ResultSet item_rs = DSDAO.getItemData(conn);
		try {
			while(item_rs.next()) {
				items.add(new DSItems(item_rs.getString(1), item_rs.getInt(2), item_rs.getInt(3), item_rs.getInt(4), item_rs.getString(5)));
			}
		}catch(SQLException sql) {
			System.out.println("[Error] SQL 데이터 가져오기 에러");
			System.out.println(sql.getMessage());
		}
		DBConnectClose.Close(item_rs);
		DBConnectClose.Close(DSDAO.getpstmt());
		DBConnectClose.Close(conn);
		return items;
	}
}
