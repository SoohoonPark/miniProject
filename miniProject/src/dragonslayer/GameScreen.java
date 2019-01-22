package dragonslayer;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameScreen extends JFrame{
	private String c_name;
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp;
	
	public GameScreen(String name, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() ȣ��");
		this.c_name = name; // ĳ���͸�
		this.c_lv = 1; // 1����
		this.c_str = s; // ��
		this.c_dex = d; // ��ø
		this.c_int = i; // ����
		this.c_hp = hp; // ü��
		this.c_mp = mp; // ����
		this.c_exp = 0; // �ʱ� ����ġ ������ 0
		this.c_next_exp = 50; // ���� ����ġ �䱸�� 50
		System.out.println("[info] GameScreen() �ʵ� �ʱ�ȭ �Ϸ�.");
		createGameScreen();
	}
	
	void createGameScreen() {
		setTitle("Dragon Slayer");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}
}
