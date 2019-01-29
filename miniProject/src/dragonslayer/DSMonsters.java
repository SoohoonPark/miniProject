package dragonslayer;

// ���� ������ ����ϴ� �� �����͵��� ���� ��ü
public class DSMonsters {
	private String m_name; // �� �̸�
	private String m_grade; // �� ���
	private int m_hp; // �� ü��
	private int m_atk; // �� ���ݷ�
	private int m_def; // �� ����
	private int m_exp; // �� ����ġ
	private String[] m_dropitem; // �� ���������(�迭)
	
	// Ŭ���� ������
	public DSMonsters(String n, String g, int h, int a, int d, int e, String drop) {
		this.m_name = n;
		this.m_grade = g;
		this.m_hp = h;
		this.m_atk = a;
		this.m_def = d;
		this.m_exp = e;
		this.m_dropitem = drop.split(","); // �޸�(,)�� �������� ���ڿ��� ������ �迭�� �����ϰ� ��.
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

	// �� ��������� ����(����Ǵ� �������� 2����, �ߺ� �Ұ���)
	public String[] getMobDrop() {
		String[] drop = new String[2]; // ��� �Ǵ� �������� 2��
		for(int i=0; i<drop.length; i++) { // ���� ��������� ������ŭ �ݺ��� ����
			int dropindex = (int)(Math.random() * m_dropitem.length); // 0 ~ m_dropitem.length ����
			drop[i] = m_dropitem[dropindex];
			for(int j=0; j<i; j++) {
				if(drop[i] == drop[j]) { // ��������� �ߺ� ���� (�Ȱ����� 2�� �����°� ����)
					i--;
				}
			}
		}
		return drop;
	}
}
