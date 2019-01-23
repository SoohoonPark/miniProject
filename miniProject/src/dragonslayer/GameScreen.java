package dragonslayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GameScreen extends JFrame{
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/gamescreenmainbackground.png");
	private String c_name;
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp;
	private JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	
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

		JPanel GameScreenPanel = new JPanel(null);
		GameScreenPanel.setBounds(40, 35, 950, 300);
		GameScreenPanel.setBorder(new LineBorder(Color.WHITE));
				
		JPanel CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(60, 70, 200, 225);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		GameScreenPanel.add(CharacterPanel);
		
		JPanel MonsterPanel = new JPanel(null);
		MonsterPanel.setBounds(550, 45, 350, 250);
		MonsterPanel.setBorder(new LineBorder(Color.RED));
		GameScreenPanel.add(MonsterPanel);
		
		JPanel LogPanel = new JPanel(null);
		LogPanel.setBounds(50, 350, 450, 250);
		LogPanel.setBorder(new LineBorder(Color.WHITE));
		
		JPanel ButtonPanel = new JPanel(null);
		ButtonPanel.setBounds(520, 380, 450, 190);
		ButtonPanel.setBorder(new LineBorder(Color.GRAY));
		
		buttonsearch = new JButton("탐색");
		buttonsearch.setBounds(5, 5, 100, 85);
		ButtonPanel.add(buttonsearch);
		
		buttonattack = new JButton("공격");
		buttonattack.setBounds(115, 5, 100, 85);
		ButtonPanel.add(buttonattack);
		
		buttoninven = new JButton("가방");
		buttoninven.setBounds(225, 5, 100, 85);
		ButtonPanel.add(buttoninven);
		
		buttonequip = new JButton("장비");
		buttonequip.setBounds(5, 100, 100, 85);
		ButtonPanel.add(buttonequip);
		
		buttonstat = new JButton("스텟");
		buttonstat.setBounds(115, 100, 100, 85);
		ButtonPanel.add(buttonstat);
		
		buttonskill = new JButton("스킬");
		buttonskill.setBounds(225, 100, 100, 85);
		ButtonPanel.add(buttonskill);
		
		buttonexit = new JButton("게임종료");
		buttonexit.setBounds(340, 5, 100, 180);
		buttonexit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showOptionDialog(null, "정말 가실꺼에요? 아직 용사님의 도움을 필요로 하는 곳이 많아요!!!", "게임종료",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] {"미안하지만, 지금은 좀 바빠.",  "내 도움이 필요한 곳이 어디야??"}, null);
				if(a == 0) {
					System.exit(1);
				} else {
					return;
				}
				
			}
		});
		ButtonPanel.add(buttonexit);
		
		layer.add(mainbackgroundimgLabel, new Integer(1));
		layer.add(GameScreenPanel, new Integer(2));
		layer.add(LogPanel,new Integer(3));
		layer.add(ButtonPanel,new Integer(3));
		setVisible(true);
	}
}
