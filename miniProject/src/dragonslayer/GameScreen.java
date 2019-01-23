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
		
		buttonsearch = new JButton("Ž��");
		buttonsearch.setBounds(5, 5, 100, 85);
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
				int a = JOptionPane.showOptionDialog(null, "���� ���ǲ�����? ���� ������ ������ �ʿ�� �ϴ� ���� ���ƿ�!!!", "��������",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] {"�̾�������, ������ �� �ٺ�.",  "�� ������ �ʿ��� ���� ����??"}, null);
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
