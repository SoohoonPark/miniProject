package dragonslayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GameScreen extends JFrame{
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/gamescreenmainbackground.png");
	// �������
	private final static Image BATTLEBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/battlebackground_resize.png");
	// �̺�Ʈ ���
	private final static Image EVENTBACKGROUND1 = Toolkit.getDefaultToolkit().createImage("resource/images/background/Event1_resize.png");
	// �÷��̾� �̹���(���谡)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_beginner.png");
	// �� �̹�����
	private final static Image HATCHLING = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/2_2_Hatchling_resize.png");
	// �α�(Log) �г� ���(�׵θ�)
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/LogPanelBorder.png");
	// ��ư �г� ���(�׵θ�)
	private final static Image BUTTONBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/ButtonPanelBorder.png");
	// ��ư �̹�����(Ž��,����,����....)
	private final static Image BTNATK = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_attack.png");
	private final static Image BTNATK_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_attack_pressed.png");
	private final static Image BTNSEARCH = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_search.png");
	private final static Image BTNSEARCH_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_search_pressed.png");
	private final static Image BTNEQUIP = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_equip.png");
	private final static Image BTNEQUIP_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_equip_pressed.png");
	private final static Image BTNSKILL = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_skill.png");
	private final static Image BTNSKILL_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_skill_pressed.png");
	private final static Image BTNSTAT = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_stat.png");
	private final static Image BTNSTAT_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_stat_pressed.png");
	private final static Image BTNINVEN = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_inventory.png");
	private final static Image BTNINVEN_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_inventory_pressed.png");
	private final static Image BTNQUIT = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_quitgame.png");
	private final static Image BTNQUIT_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_quitgame_pressed.png");
	private final static Image LOGO = Toolkit.getDefaultToolkit().createImage("resource/images/background/logo.png");
	
	private static String c_name; // ĳ���͸�
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp; // ĳ���� ���� ���� ���� (����â �������� ������)
	private static Boolean battle = false; // ���� �߻��� �˷��ִ� ����. ���� �߻� �� true�� ��ȯ(�⺻�� false)
	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel, GameScreenimgLabel, monsterimgLabel; // �̹��� �󺧵�
	private static JPanel GameScreenPanel,CharacterPanel,MonsterPanel; // ĳ���� �̹����� ��µǴ� �г�, �� �̹����� ��µǴ� �г�
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	private static JProgressBar playerHpbar, playerMpbar, MonsterHpbar;
	
	public static void main(String[] args) {
		new GameScreen("test", 1, 1, 1, 1, 1);
	}
	
	public GameScreen(String name, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() ȣ��");
		GameScreen.c_name = name; // ĳ���͸�
		GameScreen.c_lv = 1; // 1����
		GameScreen.c_str = s; // ��
		GameScreen.c_dex = d; // ��ø
		GameScreen.c_int = i; // ����
		GameScreen.c_hp = hp; // ü��
		GameScreen.c_mp = mp; // ����
		GameScreen.c_exp = 0; // �ʱ� ����ġ ������ 0
		GameScreen.c_next_exp = 50; // ���� ����ġ �䱸�� 50
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
		
		// ���̾� ����
		JLayeredPane layer = getLayeredPane();
		
		// ����ȭ�� �׵θ�
		mainbackgroundimgLabel = new JLabel(new ImageIcon(MAINBACKGROUND));
		mainbackgroundimgLabel.setBounds(5, 5, 1020, 638);
		
		// �������� �̹��� ��
		GameScreenimgLabel = new JLabel(new ImageIcon(BATTLEBACKGROUND));
		GameScreenimgLabel.setBounds(40, 35, 950, 330);
		GameScreenimgLabel.setBorder(new LineBorder(Color.WHITE));
		
		// ĳ���� �̹��� ��µǴ� �г�(ü��/���� ���� + ĳ���� �̹���)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(650,60, 200, 300);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		CharacterPanel.setOpaque(false);
		
		// ĳ���� �̹��� ����ϴ� Label
		JLabel characterLabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
		characterLabel.setBounds(30, 70, 150, 226);
		
		UIManager.put("ProgressBar.selectionBackground", Color.BLACK); // bar�� ä������ �� ���� ��
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK); // bar�� ä���� �� ���� ��
		playerHpbar = new JProgressBar(0,100); // �÷��̾� ĳ���� ü�¹�
		playerHpbar.setBorderPainted(false);
		playerHpbar.setBackground(Color.WHITE);
		playerHpbar.setForeground(Color.RED);
		playerHpbar.setValue(60);
		playerHpbar.setBounds(10, 10, 180, 15);
		playerHpbar.setStringPainted(true);
		playerHpbar.setString(0+"/"+c_hp);
		
		playerMpbar = new JProgressBar(0, 100); // �÷��̾� ĳ���� ������
		playerMpbar.setBorderPainted(false);
		playerMpbar.setBackground(Color.WHITE);
		playerMpbar.setForeground(Color.BLUE);
		playerMpbar.setValue(60);
		playerMpbar.setBounds(10, 26, 180, 15);
		playerMpbar.setStringPainted(true);
		playerMpbar.setString(0+"/"+c_hp);
		
		CharacterPanel.add(playerHpbar);
		CharacterPanel.add(playerMpbar);
		CharacterPanel.add(characterLabel); // ĳ���� �гο� ĳ���� �̹��� �߰�
		
		// �� �̹��� ��� �г�(�� ü�� ���� + �� �̹���)
		MonsterPanel = new JPanel(null);
		MonsterPanel.setBounds(60, 40, 350, 320);
		MonsterPanel.setBorder(new LineBorder(Color.RED));
		MonsterPanel.setOpaque(false);
		
		monsterimgLabel = new JLabel(new ImageIcon(HATCHLING));
		monsterimgLabel.setBounds(0, 40, 280, 300);
		MonsterPanel.add(monsterimgLabel);
		
		// �α�(log)�� ��µǴ� �г�
		BackgroundImagePanel LogPanel = new BackgroundImagePanel(LOGBACKGROUND);
		LogPanel.setBounds(50, 370, 450, 250);
		LogPanel.setOpaque(false);
		
		logarea = new JTextArea();
		logarea.setEditable(false);
		logarea.setForeground(Color.WHITE);
		logarea.setBackground(Color.BLACK);
		logarea.setText("���� ����");
		logscroll = new JScrollPane(logarea);
		logscroll.setBorder(new LineBorder(Color.BLACK));
		logscroll.setBounds(25, 20, 400, 210);
		LogPanel.add(logscroll);
		
		// ��ư���� ��µǴ� �г�
		BackgroundImagePanel ButtonPanel = new BackgroundImagePanel(BUTTONBACKGROUND);
		ButtonPanel.setBounds(520, 400, 440, 190);
		ButtonPanel.setOpaque(false);
		
		// Ž�� ��ư�� ������ ��
		buttonsearch = new JButton(new ImageIcon(BTNSEARCH));
		buttonsearch.setBounds(20, 10, 100, 79);
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
					battle = true; // ���� �߻� �� true�� ��ȯ, �ش� ������ ������ ����Ǹ� �ٽ� false�� �ٲ�
					System.out.println("[info] ���� �߻�");
					CharacterPanel.setVisible(true);
					MonsterPanel.setVisible(true);
					writeLog("\ntest2");
					break;
				case 2:
					System.out.println("[info] ������ ȹ��");
					CharacterPanel.setVisible(true);
					MonsterPanel.setVisible(false);
					writeLog("\ntest1");
					break;
				case 3:
					System.out.println("[info] Ư�� �̺�Ʈ �߻�");
					CharacterPanel.setVisible(false);
					MonsterPanel.setVisible(false);
					writeLog("\ntest3");
					break;
				}
			}
		});
		ButtonPanel.add(buttonsearch);
		
		buttonattack = new JButton(new ImageIcon(BTNATK));
		buttonattack.setBounds(120, 10, 100, 79);
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
		
		buttoninven = new JButton(new ImageIcon(BTNINVEN));
		buttoninven.setBounds(220, 10, 100, 79);
		buttoninven.setBorderPainted(false);
		buttoninven.setFocusPainted(false);
		buttoninven.setContentAreaFilled(false);
		buttoninven.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttoninven.setIcon(new ImageIcon(BTNINVEN_PRESS));
			}
			public void mouseReleased(MouseEvent e) {
				buttoninven.setIcon(new ImageIcon(BTNINVEN));
			}
			public void mouseExited(MouseEvent e) {
				buttoninven.setIcon(new ImageIcon(BTNINVEN));
			}
		});
		buttoninven.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		ButtonPanel.add(buttoninven);
		
		buttonequip = new JButton(new ImageIcon(BTNEQUIP));
		buttonequip.setBounds(20, 100, 100, 79);
		buttonequip.setBorderPainted(false);
		buttonequip.setFocusPainted(false);
		buttonequip.setContentAreaFilled(false);
		buttonequip.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonequip.setIcon(new ImageIcon(BTNEQUIP_PRESS));
			}
			public void mouseReleased(MouseEvent e) {
				buttonequip.setIcon(new ImageIcon(BTNEQUIP));
			}
			public void mouseExited(MouseEvent e) {
				buttonequip.setIcon(new ImageIcon(BTNEQUIP));
			}
		});
		buttonequip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		ButtonPanel.add(buttonequip);
		
		buttonstat = new JButton(new ImageIcon(BTNSTAT));
		buttonstat.setBounds(120, 100, 100, 79);
		buttonstat.setBorderPainted(false);
		buttonstat.setFocusPainted(false);
		buttonstat.setContentAreaFilled(false);
		buttonstat.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonstat.setIcon(new ImageIcon(BTNSTAT_PRESS));
			}
			public void mouseReleased(MouseEvent e) {
				buttonstat.setIcon(new ImageIcon(BTNSTAT));
			}
			public void mouseExited(MouseEvent e) {
				buttonstat.setIcon(new ImageIcon(BTNSTAT));
			}
		});
		buttonstat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		ButtonPanel.add(buttonstat);
		
		buttonskill = new JButton(new ImageIcon(BTNSKILL));
		buttonskill.setBounds(220, 100, 100, 79);
		buttonskill.setBorderPainted(false);
		buttonskill.setFocusPainted(false);
		buttonskill.setContentAreaFilled(false);
		buttonskill.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonskill.setIcon(new ImageIcon(BTNSKILL_PRESS));
			}
			public void mouseReleased(MouseEvent e) {
				buttonskill.setIcon(new ImageIcon(BTNSKILL));
			}
			public void mouseExited(MouseEvent e) {
				buttonskill.setIcon(new ImageIcon(BTNSKILL));
			}
		});
		ButtonPanel.add(buttonskill);
		
		buttonexit = new JButton(new ImageIcon(BTNQUIT));
		buttonexit.setBounds(320, 10, 100, 79);
		buttonexit.setBorderPainted(false);
		buttonexit.setFocusPainted(false);
		buttonexit.setContentAreaFilled(false);
		buttonexit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				buttonexit.setIcon(new ImageIcon(BTNQUIT_PRESS));
			}
			public void mouseReleased(MouseEvent e) {
				buttonexit.setIcon(new ImageIcon(BTNQUIT));
			}
			public void mouseExited(MouseEvent e) {
				buttonexit.setIcon(new ImageIcon(BTNQUIT));
			}
		});
		buttonexit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane ��ư ���� ��Ÿ�� ����
				UIManager.put("OptionPane.buttonFont", new Font("���� ���", Font.PLAIN, 14));
				JLabel message = new JLabel("<html><p style='font-size:14pt; font-family:���� ���;'>���� ���ǲ�����?<br/>���� ������ ������ �ʿ�� �ϴ� ���� ���ƿ�!!!</p></html>");
				int a = JOptionPane.showOptionDialog(null, message, "��������",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] {"�̾�������, ������ �� �ٺ�.","�� ������ �ʿ��� ���� ����??"}, null);
				if(a == 0) { // Ȯ�� ��ư
					System.exit(1);
				} else { // ��ҹ�ư
					return;
				}
			}
		});
		ButtonPanel.add(buttonexit);
		
		JLabel logoLabel = new JLabel(new ImageIcon(LOGO));
		logoLabel.setBounds(320, 90, 105, 97);

		ButtonPanel.add(logoLabel);
		
		layer.add(mainbackgroundimgLabel, new Integer(1));
		layer.add(GameScreenimgLabel, new Integer(2));
		layer.add(CharacterPanel, new Integer(3));
		layer.add(MonsterPanel, new Integer(3));
		layer.add(LogPanel,new Integer(4));
		layer.add(ButtonPanel,new Integer(4));
		
		setVisible(true);
	}
	
	// 1 ~ 3 ���� ���� �߻���Ű�� �޼ҵ�
	int createRandom() {
		int random = (int)(Math.random()*3)+1; // 1 ~ 3 ���� ���� ����
		return random;
	}

	// �α� �ۼ�
	void writeLog(String text) {
		logarea.append(text);
		logarea.moveCaretPosition(logarea.getText().length());
	}
}
