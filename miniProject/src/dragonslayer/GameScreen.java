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
		System.out.println("[info] GameScreen() 호출");
		this.c_name = name; // 캐릭터명
		this.c_lv = 1; // 1레벨
		this.c_str = s; // 힘
		this.c_dex = d; // 민첩
		this.c_int = i; // 지능
		this.c_hp = hp; // 체력
		this.c_mp = mp; // 마나
		this.c_exp = 0; // 초기 경험치 보유량 0
		this.c_next_exp = 50; // 다음 경험치 요구량 50
		System.out.println("[info] GameScreen() 필드 초기화 완료.");
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
		
		// 게임화면 테두리
		JLabel mainbackgroundimgLabel = new JLabel(new ImageIcon(MAINBACKGROUND));
		mainbackgroundimgLabel.setBounds(5, 5, 1020, 638);
		
		// 게임진행 화면
		
		
		layer.add(mainbackgroundimgLabel, new Integer(1));
		setVisible(true);
	}
}
