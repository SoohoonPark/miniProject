package dragonslayer;

// 게임 내에서 사용하는 몹 데이터들을 담을 객체
public class DSMonsters {
	private String m_name; // 몹 이름
	private int m_hp; // 몹 체력
	private int m_atk; // 몹 공격력
	private int m_def; // 몹 방어력
	private int m_exp; // 몹 경험치
	private String[] m_dropitem; // 몹 드랍아이템(배열)
	
	public DSMonsters(String n, int h, int a, int d, int e, String drop) {
		this.m_name = n;
		this.m_hp = h;
		this.m_atk = a;
		this.m_def = d;
		this.m_exp = e;
		this.m_dropitem = drop.split(","); // 콤마(,)를 기준으로 문자열을 나눠서 배열로 저장하게 됨.
	}

	public String getM_name() {
		return m_name;
	}

	public int getM_hp() {
		return m_hp;
	}

	public int getM_atk() {
		return m_atk;
	}

	public int getM_def() {
		return m_def;
	}

	public int getM_exp() {
		return m_exp;
	}

	public String[] getM_dropitem() {
		return m_dropitem;
	}
}
