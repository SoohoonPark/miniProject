package dragonslayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

// DAO�� ����ؼ� DB�κ��� �Ѿ�� ������ ó���ϴ� ����
public class DSService {
	private Connection conn = null;
	private static DSService service = new DSService(); // ��ü �ν��Ͻ�ȭ

	// Service ������ (private ���� �����ڸ� ���� �ν��Ͻ�ȭ ��Ŵ)
	private DSService() {
		conn = DSDBConnection.DBConn();
		if (conn != null) {
			System.out.println("[info] conn ��ü ���� �Ϸ�");
		}
	}

	public static DSService getInstance() {
		return service;
	}

	// DAO �� ���� ������ �� ������ LinkedList�� ��Ƽ� GameScreen�� �Ѱ���. (�ʱ� �� ����)
	public LinkedList<DSMonsters> monsterData(String grade) {
		LinkedList<DSMonsters> monsters = new LinkedList<DSMonsters>();
		ResultSet mob_rs = DSDAO.getMonsterData(conn, grade);
		try {
			while (mob_rs.next()) {
				monsters.add(new DSMonsters(mob_rs.getString(1), mob_rs.getString(2), mob_rs.getInt(3),
						mob_rs.getInt(4), mob_rs.getInt(5), mob_rs.getInt(6), mob_rs.getString(7)));
			}
		} catch (SQLException sql) {
			System.out.println("[Error] SQL ������ �������� ����");
			System.out.println(sql.getMessage());
		} finally {
			DBConnectClose.Close(mob_rs);
			DBConnectClose.Close(DSDAO.getPstmt());
			DBConnectClose.Close(conn);
		}
		return monsters;
	}
}
