package dragonslayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class GameScreen extends JFrame{
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/gamescreenmainbackground.png");
	private String c_name;
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp;
	
	public static void main(String[] args) {
		new GameScreen("test", 1, 1, 1, 1, 1);
	}
	
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
		setSize(1040, 680);
		setIconImage(ICONIMAGE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		JLayeredPane layer = getLayeredPane();
		
		// ����ȭ�� �׵θ�
		JLabel mainbackgroundimgLabel = new JLabel(new ImageIcon(MAINBACKGROUND));
		mainbackgroundimgLabel.setBounds(5, 5, 1020, 638);
		
		// �������� ȭ��
		
		
		layer.add(mainbackgroundimgLabel, new Integer(1));
		setVisible(true);
	}
}
