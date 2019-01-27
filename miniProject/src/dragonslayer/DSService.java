package dragonslayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

// DAO�� �����ؼ� ��������� �������� Service
public class DSService {
	private Connection conn = null;
	private static DSService service = new DSService(); // ��ü �ν��Ͻ�ȭ
	
	// Service ������ instanceȭ
	private DSService() {
		conn = DSDBConnection.DBConn();
		if(conn != null) {
			System.out.println("[info] conn ��ü ���� �Ϸ�");
		}
	} 
	
	public static DSService getInstance() {
		return service;
	}
	
	// DAO �� ���� ������ �� ������ LinkedList�� ��Ƽ� GameScreen�� �Ѱ���.
	public LinkedList<DSMonsters> monsterData(){
		LinkedList<DSMonsters> monsters = new LinkedList<DSMonsters>();
		ResultSet rs = DSDAO.getMonsterData(conn);
		try {
			while(rs.next()) {
				monsters.add(new DSMonsters(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
			}
		}catch(SQLException sql) {
			System.out.println("[Error] SQL ������ �������� ����");
			System.out.println(sql.getMessage());
		}finally {
			DBConnectClose.Close(rs);
			DBConnectClose.Close(DSDAO.getPstmt());
			DBConnectClose.Close(conn);
		}
		
		return monsters;
	}
}
