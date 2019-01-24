package dragonslayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GameScreen extends JFrame{
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/gamescreenmainbackground.png");
	// 전투배경
	private final static Image BATTLEBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/battlebackground.png");
	// 플레이어 이미지(모험가)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_beginner.png");
	// 로그(Log) 배경
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/LogBorderimage_1_resize.png");
	// 버튼 이미지들(탐색,공격,가방....)
	private final static Image BTNATK = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_attack.png");
	private final static Image BTNATK_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_attack_pressed.png");
	private final static Image BTNSEARCH = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_search.png");
	private final static Image BTNSEARCH_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_search_pressed.png");
	private final static Image BTNEQUIP = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_equip.png");
	private final static Image BTNSKILL = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_skill.png");
	private final static Image BTNSTAT = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_stat.png");
	private final static Image BTNINVEN = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_inventory.png");
	private final static Image BTNQUIT = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_quitgame.png");
	
	private static String c_name; // 캐릭터명
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp; // 캐릭터 스탯 관련 정보 (스탯창 열었을때 보여줌)
	private static Boolean battle = false; // 전투 발생을 알려주는 변수. 전투 발생 시 true로 전환(기본값 false)
	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel; // 메인 배경 테두리 라벨
	private static JPanel CharacterPanel,MonsterPanel; // 캐릭터 이미지가 출력되는 패널, 몹 이미지가 출력되는 패널
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	
	public static void main(String[] args) {
		new GameScreen("test", 1, 1, 1, 1, 1);
	}
	
	public GameScreen(String name, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() 호출");
		GameScreen.c_name = name; // 캐릭터명
		GameScreen.c_lv = 1; // 1레벨
		GameScreen.c_str = s; // 힘
		GameScreen.c_dex = d; // 민첩
		GameScreen.c_int = i; // 지능
		GameScreen.c_hp = hp; // 체력
		GameScreen.c_mp = mp; // 마나
		GameScreen.c_exp = 0; // 초기 경험치 보유량 0
		GameScreen.c_next_exp = 50; // 다음 경험치 요구량 50
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
		
		// 레이어 설정
		JLayeredPane layer = getLayeredPane();
		
		// 게임화면 테두리
		mainbackgroundimgLabel = new JLabel(new ImageIcon(MAINBACKGROUND));
		mainbackgroundimgLabel.setBounds(5, 5, 1020, 638);
		
		// 게임진행 화면
		BackgroundImagePanel GameScreenPanel = new BackgroundImagePanel(BATTLEBACKGROUND);
		GameScreenPanel.setBounds(40, 35, 950, 300);
		GameScreenPanel.setBorder(new LineBorder(Color.WHITE));
				
		// 캐릭터 이미지 출력되는 패널(체력/마나 막대 + 캐릭터 이미지)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(650,5, 200, 290);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		CharacterPanel.setOpaque(false);
		
		JLabel characterLabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
		characterLabel.setBounds(30, 60, 150, 226);
		
		CharacterPanel.add(characterLabel);
		GameScreenPanel.add(CharacterPanel);
		
		// 몹 이미지 출력 패널(몹 체력 막대 + 몹 이미지)
		MonsterPanel = new JPanel(null);
		MonsterPanel.setBounds(60, 10, 350, 285);
		MonsterPanel.setBorder(new LineBorder(Color.RED));
		MonsterPanel.setOpaque(false);
		GameScreenPanel.add(MonsterPanel);
		
		// 로그(log)가 출력되는 패널
		BackgroundImagePanel LogPanel = new BackgroundImagePanel(LOGBACKGROUND);
		LogPanel.setBounds(50, 350, 450, 250);
		
		logarea = new JTextArea();
		logarea.setEditable(false);
		logarea.setForeground(Color.WHITE);
		logarea.setBackground(Color.BLACK);
		logarea.setText("게임 시작");
		logscroll = new JScrollPane(logarea);
		logscroll.setBorder(new LineBorder(Color.BLACK));
		logscroll.setBounds(25, 20, 400, 210);
		LogPanel.add(logscroll);
		
		// 버튼들이 출력되는 패널
		JPanel ButtonPanel = new JPanel(null);
		ButtonPanel.setBounds(520, 380, 440, 190);
		ButtonPanel.setBorder(new LineBorder(Color.GRAY));
		
		// 탐색 버튼을 눌렸을 때
		buttonsearch = new JButton(new ImageIcon(BTNSEARCH));
		buttonsearch.setBounds(5, 5, 100, 79);
		buttonsearch.setBorderPainted(false);
		buttonsearch.setFocusPainted(false);
		buttonsearch.setContentAreaFilled(false);
		buttonsearch.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonsearch.setIcon(new ImageIcon(BTNSEARCH_PRESS));
			}
			public void mouseReleased(MouseEvent e) {
				buttonsearch.setIcon(new ImageIcon(BTNSEARCH));
			}
			public void mouseExited(MouseEvent e) {
				buttonsearch.setIcon(new ImageIcon(BTNSEARCH));
			}
		});
		buttonsearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(createRandom()) {
				case 1:
					battle = true; // 전투 발생 시 true로 전환, 해당 변수는 전투가 종료되면 다시 false로 바뀜
					System.out.println("[info] 전투 발생");
					writeLog("\ntest2");
					break;
				case 2:
					System.out.println("[info] 아이템 획득");
					writeLog("\ntest1");
					break;
				case 3:
					System.out.println("[info] 특수 이벤트 발생");
					writeLog("\ntest3");
					break;
				}
			}
		});
		ButtonPanel.add(buttonsearch);
		
		buttonattack = new JButton(new ImageIcon(BTNATK));
		buttonattack.setBounds(115, 5, 100, 79);
		buttonattack.setBorderPainted(false);
		buttonattack.setFocusPainted(false);
		buttonattack.setContentAreaFilled(false);
		buttonattack.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonattack.setIcon(new ImageIcon(BTNATK_PRESS));
			}
			public void mouseReleased(MouseEvent e) {
				buttonattack.setIcon(new ImageIcon(BTNATK));
			}
			public void mouseExited(MouseEvent e) {
				buttonattack.setIcon(new ImageIcon(BTNATK));
			}
		});
		buttonattack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		ButtonPanel.add(buttonattack);
		
		buttoninven = new JButton("가방");
		buttoninven.setBounds(225, 5, 100, 79);
		ButtonPanel.add(buttoninven);
		
		buttonequip = new JButton("장비");
		buttonequip.setBounds(5, 100, 100, 79);
		ButtonPanel.add(buttonequip);
		
		buttonstat = new JButton("스텟");
		buttonstat.setBounds(115, 100, 100, 79);
		ButtonPanel.add(buttonstat);
		
		buttonskill = new JButton("스킬");
		buttonskill.setBounds(225, 100, 100, 79);
		ButtonPanel.add(buttonskill);
		
		buttonexit = new JButton("게임종료");
		buttonexit.setBounds(335, 5, 100, 79);
		buttonexit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane 버튼 글자 스타일 설정
				UIManager.put("OptionPane.buttonFont", new Font("맑은 고딕", Font.PLAIN, 14));
				JLabel message = new JLabel("<html><p style='font-size:14pt; font-family:맑은 고딕;'>정말 가실꺼에요?<br/>아직 용사님의 도움을 필요로 하는 곳이 많아요!!!</p></html>");
				int a = JOptionPane.showOptionDialog(null, message, "게임종료",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] {"미안하지만, 지금은 좀 바빠.","내 도움이 필요한 곳이 어디야??"}, null);
				if(a == 0) { // 확인 버튼
					System.exit(1);
				} else { // 취소버튼
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
	
	// 1 ~ 3 랜덤 숫자 발생시키는 메소드
	int createRandom() {
		int random = (int)(Math.random()*3)+1; // 1 ~ 3 랜덤 숫자 생성
		return random;
	}

	// 로그 작성
	void writeLog(String text) {
		logarea.append(text);
		logarea.moveCaretPosition(logarea.getText().length());
	}
}
