package dragonslayer;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GameScreen extends JFrame{
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/gamescreenmainbackground.png");
	private static String c_name; // ĳ���͸�
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp; // ĳ���� ���� ���� ���� (����â �������� ������)
	private static Boolean battle = false; // ���� �߻��� �˷��ִ� ����. ���� �߻� �� true�� ��ȯ(�⺻�� false)
	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel; // ���� ��� �׵θ� ��
	private static JPanel CharacterPanel,MonsterPanel; // ĳ���� �̹����� ��µǴ� �г�, �� �̹����� ��µǴ� �г�
	
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

		JPanel GameScreenPanel = new JPanel(null);
		GameScreenPanel.setBounds(40, 35, 950, 300);
		GameScreenPanel.setBorder(new LineBorder(Color.WHITE));
				
		// ĳ���� �̹��� ��µǴ� �г�(ü��/���� ���� + ĳ���� �̹���)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(60, 70, 200, 225);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		GameScreenPanel.add(CharacterPanel);
		
		// �� �̹��� ��� �г�(�� ü�� ���� + �� �̹���)
		MonsterPanel = new JPanel(null);
		MonsterPanel.setBounds(550, 45, 350, 250);
		MonsterPanel.setBorder(new LineBorder(Color.RED));
		GameScreenPanel.add(MonsterPanel);
		
		// �α�(log)�� ��µǴ� �г�
		JPanel LogPanel = new JPanel(null);
		LogPanel.setBounds(50, 350, 450, 250);
		LogPanel.setBorder(new LineBorder(Color.WHITE));
		
		// ��ư���� ��µǴ� �г�
		JPanel ButtonPanel = new JPanel(null);
		ButtonPanel.setBounds(520, 380, 450, 190);
		ButtonPanel.setBorder(new LineBorder(Color.GRAY));
		
		// Ž�� ��ư�� ������ ��
		buttonsearch = new JButton("Ž��");
		buttonsearch.setBounds(5, 5, 100, 85);
		buttonsearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(createRandom()) {
				case 1:
					battle = true; // ���� �߻� �� true�� ��ȯ, �ش� ������ ������ ����Ǹ� �ٽ� false�� �ٲ�
					System.out.println("[info] ���� �߻�");
					break;
				case 2:
					System.out.println("[info] ������ ȹ��");
					break;
				case 3:
					System.out.println("[info] Ư�� �̺�Ʈ �߻�");
					break;
				}
			}
		});
		ButtonPanel.add(buttonsearch);
		
		buttonattack = new JButton("����");
		buttonattack.setBounds(115, 5, 100, 85);
		ButtonPanel.add(buttonattack);
		
		buttoninven = new JButton("����");
		buttoninven.setBounds(225, 5, 100, 85);
		ButtonPanel.add(buttoninven);
		
		buttonequip = new JButton("���");
		buttonequip.setBounds(5, 100, 100, 85);
		ButtonPanel.add(buttonequip);
		
		buttonstat = new JButton("����");
		buttonstat.setBounds(115, 100, 100, 85);
		ButtonPanel.add(buttonstat);
		
		buttonskill = new JButton("��ų");
		buttonskill.setBounds(225, 100, 100, 85);
		ButtonPanel.add(buttonskill);
		
		buttonexit = new JButton("��������");
		buttonexit.setBounds(340, 5, 100, 180);
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
}
