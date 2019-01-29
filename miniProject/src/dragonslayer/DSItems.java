package dragonslayer;

public class DSItems {
	private String i_name; // 아이템명
	private int i_atk; // 아이템 공격력
	private int i_def; // 아이템 방어력
	private int i_regen; // 아이템 회복력(물약만 해당)
	private String i_equip; // 장비 여부(장비템인지 아닌지 Y or N);
	
	public DSItems(String n, int a, int d, int r, String e) {
		this.i_name = n;
		this.i_atk = a;
		this.i_def = d;
		this.i_regen = r;
		this.i_equip = e;
		System.out.println("[info] DB로부터 아이템정보 객체에 저장 완료.");
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
