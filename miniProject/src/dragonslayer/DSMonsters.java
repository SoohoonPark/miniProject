package dragonslayer;

// 게임 내에서 사용하는 몹 데이터들을 담을 객체
public class DSMonsters {
	private String m_name; // 몹 이름
	private String m_grade; // 몹 등급
	private int m_hp; // 몹 체력
	private int m_atk; // 몹 공격력
	private int m_def; // 몹 방어력
	private int m_exp; // 몹 경험치
	private String[] m_dropitem; // 몹 드랍아이템(배열)
	
	// 클래스 생성자
	public DSMonsters(String n, String g, int h, int a, int d, int e, String drop) {
		this.m_name = n;
		this.m_grade = g;
		this.m_hp = h;
		this.m_atk = a;
		this.m_def = d;
		this.m_exp = e;
		this.m_dropitem = drop.split(","); // 콤마(,)를 기준으로 문자열을 나눠서 배열로 저장하게 됨.
	}

	public String getM_name() {
		return m_name;
	}
	
	public String getM_grade() {
		return m_grade;
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

	// 몹 드랍아이템 결정(드랍되는 아이템은 2종류, 중복 불가능)
	public String[] getMobDrop() {
		String[] drop = new String[2]; // 드랍 되는 아이템은 2개
		for(int i=0; i<drop.length; i++) { // 몹의 드랍아이템 개수만큼 반복문 실행
			int dropindex = (int)(Math.random() * m_dropitem.length); // 0 ~ m_dropitem.length 까지
			drop[i] = m_dropitem[dropindex];
			for(int j=0; j<i; j++) {
				if(drop[i] == drop[j]) { // 드랍아이템 중복 제거 (똑같은거 2개 나오는거 방지)
					i--;
				}
			}
		}
		return drop;
	}
}
