package dragonslayer;

public class DSItems {
	private String i_name; // �����۸�
	private int i_atk; // ������ ���ݷ�
	private int i_def; // ������ ����
	private int i_regen; // ������ ȸ����(���ุ �ش�)
	private String i_equip; // ��� ����(��������� �ƴ��� Y or N);
	
	public DSItems(String n, int a, int d, int r, String e) {
		this.i_name = n;
		this.i_atk = a;
		this.i_def = d;
		this.i_regen = r;
		this.i_equip = e;
		System.out.println("[info] DB�κ��� ���������� ��ü�� ���� �Ϸ�.");
	}

	public String getI_name() {
		return i_name;
	}

	public int getI_atk() {
		return i_atk;
	}

	public int getI_def() {
		return i_def;
	}

	public int getI_regen() {
		return i_regen;
	}

	public String getI_equip() {
		return i_equip;
	}
}
