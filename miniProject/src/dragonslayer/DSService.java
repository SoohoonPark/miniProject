package dragonslayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

// DAO�� ����ؼ� DB�κ��� �Ѿ�� ������ ó���ϴ� ����
public class DSService {
	private Connection conn = null;

	// DAO �� ���� ������ �� ������ LinkedList�� ��Ƽ� GameScreen�� �Ѱ���.
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
			System.out.println("[Error] SQL ������ �������� ����");
			System.out.println(sql.getMessage());
		}
		
		DBConnectClose.Close(mob_rs);
		DBConnectClose.Close(DSDAO.getpstmt());
		DBConnectClose.Close(conn);
		return monsters;
	}
	
	// DAO �� ���� ������ ������ ������ ����Ʈ�� ��Ƽ� GameScreen�� �Ѱ���
	public LinkedList<DSItems> itemData(){
		conn = DSDBConnection.DBConn();
		LinkedList<DSItems> items = new LinkedList<DSItems>();
		ResultSet item_rs = DSDAO.getItemData(conn);
		try {
			while(item_rs.next()) {
				items.add(new DSItems(item_rs.getString(1),item_rs.getInt(2),item_rs.getInt(3),item_rs.getInt(4),item_rs.getString(5),item_rs.getString(6)));
			}
			for(int i=0; i<items.size(); i++) {
				System.out.println((i+1)+" ��° ������ : "+items.get(i).getI_name());
			}
		}catch(SQLException sql) {
			System.out.println("[Error] SQL ������ �������� ����");
			System.out.println(sql.getMessage());
		}
		DBConnectClose.Close(item_rs);
		DBConnectClose.Close(DSDAO.getpstmt());
		DBConnectClose.Close(conn);
		return items;
	}
	
	// DAO �κ��� ������ ����ġ ������ �ʿ� ��Ƽ� GameScreen�� �Ѱ���
	public HashMap<Integer, Integer> expData(){
		conn = DSDBConnection.DBConn();
		HashMap<Integer,Integer> exp = new HashMap<Integer,Integer>();
		ResultSet exp_rs = DSDAO.getExpData(conn);
		try {
			while(exp_rs.next()) {
				exp.put(exp_rs.getInt(1), exp_rs.getInt(2));
			}
		}catch(SQLException sql) {
			System.out.println("[Error] SQL ������ �������� ����");
			System.out.println(sql.getMessage());
		}
		DBConnectClose.Close(exp_rs);
		DBConnectClose.Close(DSDAO.getpstmt());
		DBConnectClose.Close(conn);
		return exp;
	}
}
