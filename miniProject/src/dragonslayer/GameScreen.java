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
	// �������
	private final static Image BATTLEBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/battlebackground.png");
	// �÷��̾� �̹���(���谡)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_beginner.png");
	// �α�(Log) ���
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/LogBorderimage_1_resize.png");
	// ��ư �̹�����(Ž��,����,����....)
	private final static Image BTNATK = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_attack.png");
	private final static Image BTNATK_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_attack_pressed.png");
	private final static Image BTNSEARCH = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_search.png");
	private final static Image BTNSEARCH_PRESS = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_search_pressed.png");
	private final static Image BTNEQUIP = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_equip.png");
	private final static Image BTNSKILL = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_skill.png");
	private final static Image BTNSTAT = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_stat.png");
	private final static Image BTNINVEN = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_inventory.png");
	private final static Image BTNQUIT = Toolkit.getDefaultToolkit().createImage("resource/images/button/GameScreen/button_quitgame.png");
	
	private static String c_name; // ĳ���͸�
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp; // ĳ���� ���� ���� ���� (����â �������� ������)
	private static Boolean battle = false; // ���� �߻��� �˷��ִ� ����. ���� �߻� �� true�� ��ȯ(�⺻�� false)
	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel; // ���� ��� �׵θ� ��
	private static JPanel CharacterPanel,MonsterPanel; // ĳ���� �̹����� ��µǴ� �г�, �� �̹����� ��µǴ� �г�
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	
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
		
		// �������� ȭ��
		BackgroundImagePanel GameScreenPanel = new BackgroundImagePanel(BATTLEBACKGROUND);
		GameScreenPanel.setBounds(40, 35, 950, 300);
		GameScreenPanel.setBorder(new LineBorder(Color.WHITE));
				
		// ĳ���� �̹��� ��µǴ� �г�(ü��/���� ���� + ĳ���� �̹���)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(650,5, 200, 290);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		CharacterPanel.setOpaque(false);
		
		JLabel characterLabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
		characterLabel.setBounds(30, 60, 150, 226);
		
		CharacterPanel.add(characterLabel);
		GameScreenPanel.add(CharacterPanel);
		
		// �� �̹��� ��� �г�(�� ü�� ���� + �� �̹���)
		MonsterPanel = new JPanel(null);
		MonsterPanel.setBounds(60, 10, 350, 285);
		MonsterPanel.setBorder(new LineBorder(Color.RED));
		MonsterPanel.setOpaque(false);
		GameScreenPanel.add(MonsterPanel);
		
		// �α�(log)�� ��µǴ� �г�
		BackgroundImagePanel LogPanel = new BackgroundImagePanel(LOGBACKGROUND);
		LogPanel.setBounds(50, 350, 450, 250);
		
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
		JPanel ButtonPanel = new JPanel(null);
		ButtonPanel.setBounds(520, 380, 440, 190);
		ButtonPanel.setBorder(new LineBorder(Color.GRAY));
		
		// Ž�� ��ư�� ������ ��
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
					battle = true; // ���� �߻� �� true�� ��ȯ, �ش� ������ ������ ����Ǹ� �ٽ� false�� �ٲ�
					System.out.println("[info] ���� �߻�");
					writeLog("\ntest2");
					break;
				case 2:
					System.out.println("[info] ������ ȹ��");
					writeLog("\ntest1");
					break;
				case 3:
					System.out.println("[info] Ư�� �̺�Ʈ �߻�");
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
		
		buttoninven = new JButton("����");
		buttoninven.setBounds(225, 5, 100, 79);
		ButtonPanel.add(buttoninven);
		
		buttonequip = new JButton("���");
		buttonequip.setBounds(5, 100, 100, 79);
		ButtonPanel.add(buttonequip);
		
		buttonstat = new JButton("����");
		buttonstat.setBounds(115, 100, 100, 79);
		ButtonPanel.add(buttonstat);
		
		buttonskill = new JButton("��ų");
		buttonskill.setBounds(225, 100, 100, 79);
		ButtonPanel.add(buttonskill);
		
		buttonexit = new JButton("��������");
		buttonexit.setBounds(335, 5, 100, 79);
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
		
		layer.add(mainbackgroundimgLabel, new Integer(1));
		layer.add(GameScreenPanel, new Integer(2));
		layer.add(LogPanel,new Integer(3));
		layer.add(ButtonPanel,new Integer(3));
		
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
