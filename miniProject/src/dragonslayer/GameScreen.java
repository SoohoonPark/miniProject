package dragonslayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

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
	/** �̹��� ���� **/
	// ���Ӿ�����
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	
	// ���ӹ��ȭ��(�簢�� �׵θ�)
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/gamescreenmainbackground.png");
	
	// �������
	private final static Image BATTLEBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/battlebackground_resize.png");
	
	// �̺�Ʈ ���
	private final static Image EVENTBACKGROUND1 = Toolkit.getDefaultToolkit().createImage("resource/images/background/Event1_resize.png");
	private final static Image EVENTBACKGROUND2 = null;
	private final static Image EVENTBACKGROUND3 = null;
	
	// �÷��̾� �̹���(���谡)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_beginner.png");
	private final static Image PLAYERWARRIOR = null;
	private final static Image PLAYERKNIGHT = null;
	
	// �� �̹�����(�ʱ�)
	private final static Image SKEL = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/low_grade/1_skel_warrior_resize.png");
	private final static Image ORC = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/low_grade/2_orc_warrior_resize.png");
	private final static Image GOLEM = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/low_grade/3_golem_resize.png");
	
	// �� �̹�����(�߱�)
	private final static Image SKELKING = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/middle_grade/1_skel_king_resize.png");
	private final static Image HATCHLING = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/middle_grade/2_Hatchling_resize.png");
	private final static Image LAGIA = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/middle_grade/3_Lagiacrus_resize.png");
	
	// �� �̹�����(���)
	private final static Image DRAKE = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/high_grade/1_Drake_resize.png");
	private final static Image CHIMERA = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/high_grade/2_Chimera_resize.png");
	private final static Image ICEDRAGON = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/high_grade/3_Ice_dragon_resize.png");
	
	// ��ư �г� & �α� �г� ���(�׵θ�)
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/LogPanelBorder.png");
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
	
	/** �ʵ� ���� **/
	private String c_name, m_name, c_job; // ĳ���͸� & �����̸�
	private int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp; // ĳ���� ���� ���� ���� (����â �������� ������)
	private int c_atk, c_def, m_Atk, m_Def; // ĳ���� ���ݷ�&����, �� ���ݷ�&����
	private int current_user_hp, current_user_mp, current_monster_hp, m_hp; // ���� �÷��̾� ü�� & �� ü�� & �� �ִ�ü��
	private Boolean battle = false; // ���� �߻��� �˷��ִ� ����. ���� �߻� �� true�� ��ȯ(�⺻�� false)
	private LinkedList<DSMonsters> lowmonsters = null; // �ʱ޸������� ������ִ� LinkedList
	private LinkedList<DSMonsters> middlemonsters = null; // �߱޸������� ������ִ� LinkedList
	private LinkedList<DSMonsters> highmonsters = null; // ��޸������� ������ִ� LinkedList
	
	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel, GameScreenimgLabel, monsterimgLabel; // �̹��� �󺧵�
	private static JPanel CharacterPanel,MonsterPanel; // ĳ���� �̹����� ��µǴ� �г�, �� �̹����� ��µǴ� �г�
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	private static JProgressBar playerHpbar, playerMpbar, MonsterHpbar; // �÷��̾� ü�¸���,��������, �� ü�¸���
	
	private static int playeratk, playerdef;	// �÷��̾� ���ݷ�, ����
	private static int monsteratk, monsterdef;	// ���� ���ݷ�, ����
	private DSService service = DSService.getInstance();
	
	/** �޼ҵ� ���� **/
	public GameScreen(String name, String job, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() ȣ��");
		this.c_name = name; // ĳ���͸�
		this.c_job = job; // ����
		this.c_lv = 1; // 1����
		this.c_str = s; // ��
		this.c_atk = c_str/2; // ĳ���� ���ݷ��� ��/2
		this.c_dex = d; // ��ø
		this.c_def = c_dex/5; // ĳ���� ������ ��ø/5
		this.c_int = i; // ����
		
		this.c_hp = hp; // ü��
		this.current_user_hp = c_hp; // �÷��̾� ���� ü��
		this.c_mp = mp+(c_int*2); // ������ �⺻ ������ + ����*2
		this.current_user_mp = c_mp; // �÷��̾� ���� ����
		
		this.c_exp = 0; // �ʱ� ����ġ ������ 0
		this.c_next_exp = 50; // ���� ����ġ �䱸�� 50
		lowmonsters = service.monsterData("�ʱ�"); // �ʱ� �� ���� ����
//		middlemonsters = service.monsterData("�߱�"); // �߱� �� ���� ����
//		highmonsters = service.monsterData("���"); // ��� �� ���� ����
		if(!lowmonsters.isEmpty()) {
			System.out.println("[info] �ʱ� �� ���� �������� �Ϸ�");
		}
//		if(!middlemonsters.isEmpty()) {
//			System.out.println("[info] �߱� �� ���� �������� �Ϸ�");
//		}
//		if(!highmonsters.isEmpty()) {
//			System.out.println("[info] ��� �� ���� �������� �Ϸ�");
//		}
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
		System.out.println(getLocation().x);
		System.out.println(getLocation().y);
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
		
		playerHpbar = new JProgressBar(0,c_hp); // �÷��̾� ĳ���� ü�¹�
		playerHpbar.setBorderPainted(false);
		playerHpbar.setFont(new Font("���� ���",Font.BOLD, 11));
		playerHpbar.setBackground(Color.WHITE);
		playerHpbar.setForeground(Color.RED);
		playerHpbar.setValue(current_user_hp); // ���� ���� �÷��̾� ü��
		playerHpbar.setBounds(10, 10, 180, 15);
		playerHpbar.setStringPainted(true);
		playerHpbar.setString(current_user_hp+" / "+c_hp); // JProgressBar �ȿ� ���ڿ� �� ����(���� ü�� / �� ü��)
		
		playerMpbar = new JProgressBar(0, c_mp); // �÷��̾� ĳ���� ������
		playerMpbar.setBorderPainted(false);
		playerMpbar.setFont(new Font("���� ���",Font.BOLD, 11));
		playerMpbar.setBackground(Color.WHITE);
		playerMpbar.setForeground(Color.BLUE);
		playerMpbar.setValue(current_user_mp); // ���� ���� �÷��̾� ����
		playerMpbar.setBounds(10, 25, 180, 15);
		playerMpbar.setStringPainted(true);
		playerMpbar.setString(current_user_mp+" / "+c_mp); // JProgressBar �ȿ� ���ڿ� �� ����
		
		CharacterPanel.add(playerHpbar);
		CharacterPanel.add(playerMpbar);
		CharacterPanel.add(characterLabel); // ĳ���� �гο� ĳ���� �̹��� �߰�
		
		// �� �̹��� ��� �г�(�� ü�� ���� + �� �̹���)
		MonsterPanel = new JPanel(null);
		MonsterPanel.setBounds(60, 40, 350, 320);
		MonsterPanel.setBorder(new LineBorder(Color.RED));
		MonsterPanel.setOpaque(false);
		
		monsterimgLabel = new JLabel();
		monsterimgLabel.setBounds(10, 25, 330, 290);
		monsterimgLabel.setBorder(new LineBorder(Color.CYAN));
		
		MonsterHpbar = new JProgressBar();
		MonsterHpbar.setBounds(50, 2, 250, 20);
		MonsterHpbar.setBorderPainted(false);
		MonsterHpbar.setBackground(Color.WHITE);
		MonsterHpbar.setForeground(Color.RED);
		MonsterHpbar.setMinimum(0); // JProgressBar �ּҰ� 0
		MonsterHpbar.setStringPainted(true);
		MonsterHpbar.setVisible(false); // setVisible �⺻���� false, ���߿� ���� �����Ǹ� �׶� �ٽ� true
		
		MonsterPanel.add(MonsterHpbar);
		MonsterPanel.add(monsterimgLabel);
		
		// �α�(log)�� ��µǴ� �г�
		BackgroundImagePanel LogPanel = new BackgroundImagePanel(LOGBACKGROUND);
		LogPanel.setBounds(50, 370, 450, 250);
		LogPanel.setOpaque(false);
		
		logarea = new JTextArea();
		logarea.setEditable(false);
		logarea.setForeground(Color.WHITE);
		logarea.setBackground(Color.BLACK);
		logarea.setText("���� ����\n");
		logarea.setText("\n������ ���۵Ǿ����ϴ�!\n����� �巡���� ����� �ְ��� �巡�� �����̾ �ǽʽÿ�.\n\n");
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
		});
		buttonsearch.addActionListener(new ActionListener() {
			// Ž�� ��ư�� ������ �� 3������ �̺�Ʈ �� �������� �ϳ��� �߻���(����,����,Ư���̺�Ʈ)
			public void actionPerformed(ActionEvent e) {
				// battle�� true�� ���(���� �߻�) Ž���Ұ�. ���� ���� �� Ž�� �簳 ����
				if(battle) {
					JLabel message = new JLabel("<html><p style='font-size:14pt; font-family:���� ���;'>���� �߿��� Ž���� �Ұ����մϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "Ž��", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				// 1 ~ 3 �б� �߻�
				switch(createRandom()) {
				case 1: // ���� �߻�
					battle = true; // ���� �߻� �� true�� ��ȯ, �ش� ������ ������ ����Ǹ� �ٽ� false�� �ٲ�
					System.out.println("[info] ���� �߻�");
					createBattle(c_lv);
					break;
				case 2:
					System.out.println("[info] ������ ȹ��");
					writeLog("test1\n");
					break;
				case 3:
					System.out.println("[info] Ư�� �̺�Ʈ �߻�");
					writeLog("test3\n");
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
		});
		buttonattack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!battle) {
					JLabel message = new JLabel("<html><p style='font-size:14pt; font-family:���� ���;'>���� ������ ������ �Ұ����մϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "����", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				attack_player();
				attack_monster();
				
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
		});
		buttoninven.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �ߺ� Ŭ���� ���� ���� â ���°� �����ϱ� ���� �ش� ��ư�� Ŭ���ϸ� ��ư ��Ȱ��ȭ.
				buttoninven.setEnabled(false);
				new InventoryScreen();
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
		});
		buttonequip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonequip.setEnabled(false);
				new EquipmentScreen();
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
		});
		buttonstat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StatScreen(c_name, c_job, c_lv, c_str, c_dex, c_int, c_atk, c_def, c_exp, c_next_exp);
				buttonstat.setEnabled(false);
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
	
	// ���� ��ư ����(InventoryScreen ���� ���)
	public static JButton getInvenbutton() {
		return buttoninven;
	}
	
	// ��� ��ư ����(EquipmentScreen ���� ���)
	public static JButton getEquipbutton() {
		return buttonequip;
	}
	
	// ��ų ��ư ����(SkillScreen ���� ���)
	public static JButton getSkillbutton() {
		return buttonskill;
	}
	
	// ���� ��ư ����(StatScreen ���� ���)
	public static JButton getStatbutton() {
		return buttonstat;
	}

	// ���� �����ϴ� �޼ҵ�
	public void createBattle(int level) {
		int switchnum = createRandom()-1; // 0 ~ 2 ����
		if(level >= 1 && level <= 10) { // 1 ~ 10 ������ �ʱ� ��
			createLowMonster(switchnum);
		}else if(level >=11 && level <= 20) { // 11 ~ 20 ������ �߱� ��
			createMiddleMonster(switchnum);
		}else { // �� �� �ƴϸ� 21 ~ ���ʹϱ� ��� �� ����
			createHighMonster(switchnum);
		}
	}
	
	// �ʱ� �� ����
	public void createLowMonster(int swtichnum) {
		switch(swtichnum) {
		case 0: // Skelwarrior
			writeLog(lowmonsters.get(0).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(0).getM_hp();
			m_name = lowmonsters.get(0).getM_name();
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(0).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(SKEL));
			break;
		case 1: // Orcwarrior
			writeLog(lowmonsters.get(1).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(1).getM_hp();
			m_name = lowmonsters.get(1).getM_name();
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(1).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(ORC));
			break;
		case 2: // Golem
			writeLog(lowmonsters.get(2).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(2).getM_hp();
			m_name = lowmonsters.get(2).getM_name();
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(2).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(GOLEM));
			break;
		}
	}
	
	// �߱� �� ����
	public void createMiddleMonster(int swtichnum) {
		switch(swtichnum) {
		case 0: // SkelKing
			writeLog(lowmonsters.get(0).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(0).getM_hp();
			m_name = lowmonsters.get(0).getM_name();
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(0).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(SKELKING));
			break;
		case 1: // Hatchling
			writeLog(lowmonsters.get(1).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(1).getM_hp();
			m_name = lowmonsters.get(1).getM_name();
			current_monster_hp = lowmonsters.get(1).getM_hp(); // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(1).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(HATCHLING));
			break;
		case 2: // Lagiacrus
			writeLog(lowmonsters.get(2).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(2).getM_hp();
			m_name = lowmonsters.get(2).getM_name();
			current_monster_hp = lowmonsters.get(2).getM_hp(); // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(2).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(LAGIA));
			break;
		}
	}
	
	// ��� �� ����
	public void createHighMonster(int swtichnum) {
		switch(swtichnum) {
		case 0: // Drake
			writeLog(lowmonsters.get(0).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(0).getM_hp();
			m_name = lowmonsters.get(0).getM_name();
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(0).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(DRAKE));
			break;
		case 1: // Chimera
			writeLog(lowmonsters.get(1).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(1).getM_hp();
			m_name = lowmonsters.get(1).getM_name();
			current_monster_hp = lowmonsters.get(1).getM_hp(); // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(1).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(CHIMERA));
			break;
		case 2: // IceDragon
			writeLog(lowmonsters.get(2).getM_name()+" ��/�� ��Ÿ����.\n");
			m_hp = lowmonsters.get(2).getM_hp();
			m_name = lowmonsters.get(2).getM_name();
			current_monster_hp = lowmonsters.get(2).getM_hp(); // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(lowmonsters.get(2).getM_hp()); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(ICEDRAGON));
			break;
		}
	}
	
	// �÷��̾� ���� �޼ҵ�
	public void attack_player() {
		writeLog("'" + c_name + "' �� ����!\n");
		int damage = playeratk - monsterdef; // �������� �÷��̾� ���ݷ� - ���� ����
		if(damage <= 0) { // �÷��̾� ���ݷ� - ���� ������ ����� 0���� �۰ų� ���� ��� (= ������ ������ �÷��̾� ���ݷº��� ���� ���)
			damage = 1; // �ּ� ������ 1�� ����ǵ��� ������.
			current_monster_hp -= damage; // damage ��ġ��ŭ ���� ���� ü�� ����
			writeLog("'" + c_name + "' (��/��) " + m_name + " ���� " + damage + " �� ���ظ� ������!\n");
		} else {
			int randomdamage = (int) (Math.random() * damage) + 1; // 1 ~ �÷��̾� ������ ������ ���������� ����
			current_monster_hp -= randomdamage; // randomdamage ��ġ��ŭ �� ���� ü�� ����
			writeLog("'" + c_name + "' (��/��) " + m_name + " ���� " + randomdamage + " �� ���ظ� ������!\n");
		}
	}
	
	// ���� ���� �޼ҵ�
	public void attack_monster() {
		writeLog("'" + m_name + "' �� ����!\n");
		int damage = monsteratk - playerdef; // �������� ���� ���ݷ� - �÷��̾� ����
		if(damage <= 0) { // ���� ���ݷ� - �÷��̾� ������ ����� 0���� �۰ų� ���� ��� (= �÷��̾��� ������ ������ ���ݷº��� ���� ���)
			damage = 1;
			current_user_hp -= damage; // damage ��ġ��ŭ �÷��̾� ���� ü�� ����
			writeLog("'" + m_name + "' (��/��) " + c_name + " ���� " + damage + " �� ���ظ� ������!\n");
		} else {
			int randomdamage = (int) (Math.random() * damage) + 1; // 1 ~ ���� ������ ������ ���������� ����
			current_user_hp -= randomdamage; // randomdamage ��ġ��ŭ �÷��̾� ���� ü�� ��
			writeLog("'" + m_name + "' (��/��) " + c_name + " ���� " + randomdamage + " �� ���ظ� ������!\n");
		}
	}
}