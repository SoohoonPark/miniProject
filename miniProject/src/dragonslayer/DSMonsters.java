package dragonslayer;

// ���� ������ ����ϴ� �� �����͵��� ���� ��ü
public class DSMonsters {
	private String m_name; // �� �̸�
	private int m_hp; // �� ü��
	private int m_atk; // �� ���ݷ�
	private int m_def; // �� ����
	private int m_exp; // �� ����ġ
	private String[] m_dropitem; // �� ���������(�迭)
	
	public DSMonsters(String n, int h, int a, int d, int e, String drop) {
		this.m_name = n;
		this.m_hp = h;
		this.m_atk = a;
		this.m_def = d;
		this.m_exp = e;
		this.m_dropitem = drop.split(","); // �޸�(,)�� �������� ���ڿ��� ������ �迭�� �����ϰ� ��.
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
