package dragonslayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.tools.Tool;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

@SuppressWarnings("serial")
public class GameScreen extends JFrame {
	/** �̹��� ���� **/
	// ���Ӿ�����
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit()
			.createImage("resource/images/title/titleicon.png");

	// ���ӹ��ȭ��(�簢�� �׵θ�)
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit()
			.createImage("resource/images/background/GameScreen/MainBackgroundborder.png");

	// �������
	private final static ImageIcon BATTLEBACKGROUND = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/battlebackground.png"));

	// �������
	private final static ImageIcon FIRETRAP = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/fire_trap.png"));
	private final static ImageIcon SPIKETRAP = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/spike_trap.png"));
	private final static ImageIcon WELLTRAP = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/well_trap.png"));

	// �̺�Ʈ ���
	private final static Image EVENTBACKGROUND1 = Toolkit.getDefaultToolkit()
			.createImage("resource/images/background/Event1_resize.png");
	private final static Image EVENTBACKGROUND2 = null;
	private final static Image EVENTBACKGROUND3 = null;

	// �÷��̾� �̹���(���谡)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit()
			.createImage("resource/images/player/playercharacter_beginner.png");
	private final static Image PLAYERWARRIOR = null;
	private final static Image PLAYERKNIGHT = null;

	// �� �̹�����(�ʱ�)
	private final static Image SKEL = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/low_grade/1_skel_warrior_resize.png");
	private final static Image ORC = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/low_grade/2_orc_warrior_resize.png");
	private final static Image GOLEM = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/low_grade/3_golem_resize.png");

	// �� �̹�����(�߱�)
	private final static Image SKELKING = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/middle_grade/1_skel_king_resize.png");
	private final static Image HATCHLING = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/middle_grade/2_Hatchling_resize.png");
	private final static Image LAGIA = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/middle_grade/3_Lagiacrus_resize.png");

	// �� �̹�����(���)
	private final static Image DRAKE = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/high_grade/1_Drake_resize.png");
	private final static Image CHIMERA = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/high_grade/2_Chimera_resize.png");
	private final static Image ICEDRAGON = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/high_grade/3_Ice_dragon_resize.png");

	// ��ư �г� & �α� �г� ���(�׵θ�)
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit()
			.createImage("resource/images/background/LogPanelBorder.png");
	private final static Image BUTTONBACKGROUND = Toolkit.getDefaultToolkit()
			.createImage("resource/images/background/GameScreen/ButtonPanelBorder.png");

	// ��ư �̹�����(Ž��,����,����....)
	private final static Image BTNATK = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_attack.png");
	private final static Image BTNATK_PRESS = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_attack_pressed.png");
	private final static Image BTNSEARCH = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_search.png");
	private final static Image BTNSEARCH_PRESS = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_search_pressed.png");
	private final static Image BTNEQUIP = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_equip.png");
	private final static Image BTNEQUIP_PRESS = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_equip_pressed.png");
	private final static Image BTNSKILL = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_skill.png");
	private final static Image BTNSKILL_PRESS = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_skill_pressed.png");
	private final static Image BTNSTAT = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_stat.png");
	private final static Image BTNSTAT_PRESS = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_stat_pressed.png");
	private final static Image BTNINVEN = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_inventory.png");
	private final static Image BTNINVEN_PRESS = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_inventory_pressed.png");
	private final static Image BTNQUIT = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_quitgame.png");
	private final static Image BTNQUIT_PRESS = Toolkit.getDefaultToolkit()
			.createImage("resource/images/button/GameScreen/button_quitgame_pressed.png");
	private final static Image LOGO = Toolkit.getDefaultToolkit().createImage("resource/images/background/logo.png");

	// ��ų ����Ʈ �̹�����
	private final static Image PLAYERBASICATTACK = Toolkit.getDefaultToolkit()
			.createImage("resource/images/effects/player/player_basic_attack.gif");
	private final static Image MONSTERATTACK = Toolkit.getDefaultToolkit()
			.createImage("resource/images/effects/monster/monster_attack_resized.gif");
	private final static Image BEINGATTACKED = Toolkit.getDefaultToolkit()
			.createImage("resource/images/effects/both sides/Being attacked_resized.gif");

	/** �ʵ� ���� **/
	private static String c_name, m_name, c_job; // ĳ���͸� & �����̸�
	// ĳ���� ���� ���� ���� (����â �������� ������) ����, ��, ��ø, ����, ü��, ����, ����ġ, ���� ����ġ
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp;
	private int current_monster_hp, m_hp, m_exp; // �� ü�� & �� �ִ�ü�� & ���� �ִ� ����ġ
	private static Boolean battle = false; // ���� �߻��� �˷��ִ� ����. ���� �߻� �� true�� ��ȯ(�⺻�� false)
	private Boolean buff = false; // �÷��̾� ���� ��Ȳ(�ɷ��ִ��� �ƴ���)
	private Thread p_check, m_check; // �÷��̾�, �� ���� Ȯ�� Thread
	private LinkedList<DSMonsters> lowmonsters = null; // �ʱ޸������� ������ִ� LinkedList
	private LinkedList<DSMonsters> middlemonsters = null; // �߱޸������� ������ִ� LinkedList
	private LinkedList<DSMonsters> highmonsters = null; // ��޸������� ������ִ� LinkedList
	private LinkedList<DSItems> iteminfo; // ���� �� ������ ������ ��� �ִ� iteminfo
	private ArrayList<String> Rewards = new ArrayList<String>(); // ����غ��̴� ����
	private ArrayList<String> GoodRewards = new ArrayList<String>(); // �����ƺ��̴� ����
	private ArrayList<String> VeryGoodRewards = new ArrayList<String>(); // ȭ���غ��̴� ����
	private HashMap<Integer, Integer> exptable; // ���� �� ����ġ ������ ��� �ִ� exptable;
	private String[] dropitem; // ���� ����ϴ� �������� ����Ǿ��ִ� String �迭
	private int playeratk, playerdef; // �÷��̾� ���ݷ�, ����
	private int equipdef; // ���� �� ����
	private int monsteratk, monsterdef; // ���� ���ݷ�, ����
	private DSService service = new DSService();

	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel,GameScreenimgLabel,monsterimgLabel; // �̹��� �󺧵�
	private static JLabel playerattackLabel, monsterattackLabel, playerbeingattackedLabel, monsterbeingattackedLabel;
	private static JPanel CharacterPanel, MonsterPanel; // ĳ���� �̹����� ��µǴ� �г�, �� �̹����� ��µǴ� �г�
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	private static JProgressBar playerHpbar, playerMpbar, MonsterHpbar; // �÷��̾� ü�¸���,��������, �� ü�¸���

	private static String helmet, armor, glove, boots, weapon; // ĳ���Ͱ� �����ϰ� �ִ� �����۸�(��� â���� �ѱ� ����)
	private static int def_helmet, def_armor, def_glove, def_boots, atk_weapon; // ĳ���Ͱ� �����ϰ� �ִ� �������� ��&��
	private static LinkedList<DSItems> inven = new LinkedList<DSItems>(); // �÷��̾� �κ��丮 ���빰
	private static int current_user_hp, current_user_mp; // ���� �÷��̾� ü�� & ����
	private int tempstr, tempdex, tempint; // ���� ����ޱ� �� ���ݰ� ����(���� ������ �����·� �����ؾ��ϱ� ������)

	public static void main(String[] args) {
		new GameScreen("�����",1,"���谡",10,10,10,100,80);
	}

	/** �޼ҵ� ���� **/
	public GameScreen(String name, int l, String job, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() ȣ��");
		this.c_name = name; // ĳ���͸�
		this.c_job = job; // ����
		this.c_lv = l; // ����
		this.c_str = s; // ��
		this.playeratk = (c_str / 2) + atk_weapon; // ĳ���� ���ݷ��� (��/2)+�����ݷ�
		this.c_dex = d; // ��ø
		this.playerdef = (c_dex / 5) + equipdef; // ĳ���� ������ (��ø/5)+������
		this.c_int = i; // ����

		this.c_hp = hp; // ü��
		GameScreen.current_user_hp = c_hp; // �÷��̾� ���� ü��
		this.c_mp = mp + (c_int * 2); // ������ �⺻ ������ + ����*2
		GameScreen.current_user_mp = c_mp; // �÷��̾� ���� ����

		this.c_exp = 0; // �ʱ� ����ġ ������ 0
		this.c_next_exp = 50; // ���� ����ġ �䱸�� 50
		
		// ��� �⺻���� "����"
		weapon = "����";
		helmet = "����";
		armor = "����";
		glove = "����";
		boots = "����";
		
		lowmonsters = service.monsterData("�ʱ�"); // �ʱ� �� ���� ����
		middlemonsters = service.monsterData("�߱�"); // �߱� �� ���� ����
		highmonsters = service.monsterData("���"); // ��� �� ���� ����
		iteminfo = service.itemData(); // ������ ���� ����
		exptable = service.expData(); // ����ġ ���� ����(Key - ���� / Value - ���� ����ġ)
		System.out.println("������ ���� : " + iteminfo.size());
		System.out.println("[info] GameScreen() �ʵ� �ʱ�ȭ �Ϸ�.");
		createGameScreen();
		checkplayerstatus();
		checkmonsterstatus();
	}

	// ȭ�� ���� �޼ҵ�
	void createGameScreen() {
		setTitle("Dragon Slayer");
		setSize(1040, 680);
		setIconImage(ICONIMAGE);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		// ���̾� ����
		JLayeredPane layer = getLayeredPane();

		// ����ȭ�� �׵θ�
		mainbackgroundimgLabel = new JLabel(new ImageIcon(MAINBACKGROUND));
		mainbackgroundimgLabel.setBounds(5, 5, 1020, 638);

		// �������� �̹��� ��
		GameScreenimgLabel = new JLabel(BATTLEBACKGROUND);
		GameScreenimgLabel.setBounds(40, 35, 950, 330);
		GameScreenimgLabel.setBorder(new LineBorder(Color.WHITE));

		// ĳ���� �̹��� ��µǴ� �г�(ü��/���� ���� + ĳ���� �̹���)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(650, 60, 200, 300);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		CharacterPanel.setOpaque(false);

		// ĳ���� �̹��� ����ϴ� Label
		JLabel characterLabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
		characterLabel.setBounds(30, 70, 150, 226);

		UIManager.put("ProgressBar.selectionBackground", Color.BLACK); // bar�� ä������ �� ���� ��
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK); // bar�� ä���� �� ���� ��

		playerHpbar = new JProgressBar(0, c_hp); // �÷��̾� ĳ���� ü�¹�
		playerHpbar.setBorderPainted(false);
		playerHpbar.setFont(new Font("���� ���", Font.BOLD, 11));
		playerHpbar.setBackground(Color.WHITE);
		playerHpbar.setForeground(Color.RED);
		playerHpbar.setValue(current_user_hp); // ���� ���� �÷��̾� ü��
		playerHpbar.setBounds(10, 10, 180, 15);
		playerHpbar.setStringPainted(true);
		playerHpbar.setString(current_user_hp + " / " + c_hp); // JProgressBar �ȿ� ���ڿ� �� ����(���� ü�� / �� ü��)

		playerMpbar = new JProgressBar(0, c_mp); // �÷��̾� ĳ���� ������
		playerMpbar.setBorderPainted(false);
		playerMpbar.setFont(new Font("���� ���", Font.BOLD, 11));
		playerMpbar.setBackground(Color.WHITE);
		playerMpbar.setForeground(Color.BLUE);
		playerMpbar.setValue(current_user_mp); // ���� ���� �÷��̾� ����
		playerMpbar.setBounds(10, 25, 180, 15);
		playerMpbar.setStringPainted(true);
		playerMpbar.setString(current_user_mp + " / " + c_mp); // JProgressBar �ȿ� ���ڿ� �� ����

		CharacterPanel.add(playerHpbar);
		CharacterPanel.add(playerMpbar);
		CharacterPanel.add(characterLabel); // ĳ���� �гο� ĳ���� �̹��� �߰�

		// �� �̹��� ��� �г�(�� ü�� ���� + �� �̹���)
		MonsterPanel = new JPanel(null);
		MonsterPanel.setBounds(60, 40, 350, 320);
		MonsterPanel.setBorder(new LineBorder(Color.RED));
		MonsterPanel.setOpaque(false);

		monsterimgLabel = new JLabel("", SwingConstants.CENTER);
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
		logarea.setText("\n���� ����");
		logarea.setText("������ ���۵Ǿ����ϴ�!\n����� �巡���� ����� �ְ��� �巡�� �����̾ �ǽʽÿ�.\n");
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
				if (battle) {
					JLabel message = new JLabel(
							"<html><p style='font-size:14pt; font-family:���� ���;'>���� �߿��� Ž���� �Ұ����մϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "Ž��", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				// 1 ~ 3 �б� �߻�
				int switchnum = createRandom();
				switch (switchnum) {
				case 1: // ���� �߻�
					battle = true; // ���� �߻� �� true�� ��ȯ, �ش� ������ ������ ����Ǹ� �ٽ� false�� �ٲ�
					System.out.println("[info] ���� �߻�");
					createBattle(c_lv); // �÷��̾� ������ ���� �����Ǵ� ���� ����(?)�� �޶���. 1 ~ 10, 11 ~ 20, 21 ~ 30
					break;
				case 2:
					System.out.println("[info] ���� ȹ�� �̺�Ʈ �߻�");
					// 1 ~ 10 ���� �̺�Ʈ (1 ~ 6 �Ϲ� ����, 7 ~ 9 ������ ���̴� ����, 10 ȭ���� ����)
					int chestevent = (int) (Math.random() * 10) + 1;
					System.out.println("[info] �߻��� ���� ȹ�� �̺�Ʈ : " + chestevent);
					createGetItemEvent(chestevent);
					break;
				case 3:
					System.out.println("[info] Ư�� �̺�Ʈ �߻�(����/����)");
					int subevent = (int) (Math.random() * 2) + 1; // 1 or 2 ���� ���� (1 : ���� / 2 : ����)
					System.out.println(subevent);
					createSubEvent(subevent);
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
				if (!battle) {
					JLabel message = new JLabel(
							"<html><p style='font-size:14pt; font-family:���� ���;'>���Ͱ� �������� �ʽ��ϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "����", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				attack_player();
				Timer mAttackTimer = new Timer();
				TimerTask mAttackTimerTask = new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						attack_monster();
					}
				};
				mAttackTimer.schedule(mAttackTimerTask, Calendar.getInstance().get(Calendar.MILLISECOND)+2000);
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
				new InventoryScreen(inven, current_user_hp, current_user_mp, c_hp, c_mp);
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
				new EquipmentScreen((ImageIcon) characterLabel.getIcon(), weapon, helmet, armor, glove, boots);
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

				new StatScreen(c_name, c_job, c_lv, c_str, c_dex, c_int, playeratk, playerdef, c_exp, c_next_exp);
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
		buttonskill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �ߺ� Ŭ���� ���� ���� â ���°� �����ϱ� ���� �ش� ��ư�� Ŭ���ϸ� ��ư ��Ȱ��ȭ.
				buttonskill.setEnabled(false);
				new SkillScreen(current_user_mp);
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
				JLabel message = new JLabel(
						"<html><p style='font-size:14pt; font-family:���� ���;'>���� ���ǲ�����?<br/>���� ������ ������ �ʿ�� �ϴ� ���� ���ƿ�!!!</p></html>");
				int a = JOptionPane.showOptionDialog(null, message, "��������", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "�̾�������, ������ �� �ٺ�.", "�� ������ �ʿ��� ���� ����??" },
						null);
				if (a == 0) { // Ȯ�� ��ư
					System.exit(1);
				} else { // ��ҹ�ư
					return;
				}
			}
		});
		ButtonPanel.add(buttonexit);

		JLabel logoLabel = new JLabel(new ImageIcon(LOGO));
		logoLabel.setBounds(320, 90, 105, 97);
		logoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				// �ΰ� �̹��� ������ ���� ȸ�� ����.(
				if (battle) {
					battle = false;
				} else {
					JOptionPane.showMessageDialog(null, "���� �߿��� �� �� �ִ� ġƮ", "ġƮ", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
			}
		});

		ButtonPanel.add(logoLabel);

		JPanel skilleffectpanel = new JPanel(null);
		skilleffectpanel.setBounds(150, 70, 670, 280);
		skilleffectpanel.setOpaque(false);
		skilleffectpanel.setBorder(new LineBorder(Color.PINK));

		// �÷��̾� �⺻ ���� ����Ʈ ��
		playerattackLabel = new JLabel();
		playerattackLabel.setBounds(150, 0, 380, 280);

		// ���� ���� ����Ʈ ��
		monsterattackLabel = new JLabel();
		monsterattackLabel.setBounds(320, 30, 201, 253);

		// �÷��̾� �ǰ� ����Ʈ ��
		playerbeingattackedLabel = new JLabel();
		playerbeingattackedLabel.setBounds(425, 30, 250, 250);

		// ���� �ǰ� ����Ʈ ��
		monsterbeingattackedLabel = new JLabel();
		monsterbeingattackedLabel.setBounds(0, 30, 250, 250);

		skilleffectpanel.add(playerattackLabel);
		skilleffectpanel.add(monsterattackLabel);
		skilleffectpanel.add(playerbeingattackedLabel);
		skilleffectpanel.add(monsterbeingattackedLabel);	

		layer.add(mainbackgroundimgLabel, new Integer(1));
		layer.add(GameScreenimgLabel, new Integer(2));
		layer.add(CharacterPanel, new Integer(3));
		layer.add(MonsterPanel, new Integer(3));
		layer.add(LogPanel, new Integer(4));
		layer.add(ButtonPanel, new Integer(4));
		layer.add(skilleffectpanel, new Integer(4));

		setVisible(true);
	}

	// 1 ~ 3 ���� ���� �߻���Ű�� �޼ҵ�
	int createRandom() {
		int random = (int) (Math.random() * 3) + 1; // 1 ~ 3 ���� ���� ����
		return random;
	}

	// �α� �ۼ�
	static void writeLog(String text) {
		logarea.append(text + "\n");
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
		if(buff) {
			System.out.println("[info] ���� �ɷ�����");
		}
		int switchnum = createRandom() - 1; // 0 ~ 2 ����
		if (level >= 1 && level <= 10) { // 1 ~ 10 ������ �ʱ� ��
			createLowMonster(switchnum);
		} else if (level >= 11 && level <= 20) { // 11 ~ 20 ������ �߱� ��
			createMiddleMonster(switchnum);
		} else { // �� �� �ƴϸ� 21 ~ ���ʹϱ� ��� �� ����
			createHighMonster(switchnum);
		}
	}

	// ������ ȹ�� �̺�Ʈ(���� �̺�Ʈ)
	public void createGetItemEvent(int event) {
		if (event >= 1 && event <= 6) { // 1 ~ 6 : �Ϲ� ����
			writeLog("����� ���̴� ���ڸ� �߰��ϰ� ���ڸ� ������.");

			// '�ϱ�' Ű���尡 �� ������ ���� ���� ���ڿ� ����Ʈ
			for (int i = 0; i < iteminfo.size(); i++) {
				if (iteminfo.get(i).getI_name().contains("�ϱ�")) {
					Rewards.add(iteminfo.get(i).getI_name());
				}
			}
			// 0 ~ 4 ���� �ߺ� ���� ���� 2�� �̱�
			int[] ran = makeRanNums();

			// ���� 2���� ���ڰ� ���� �ε���
			String[] low = new String[2];
			for (int i = 0; i < 2; i++) {
				low[i] = Rewards.get(ran[i]);
			}
			addInventory(low); // �κ��丮�� �߰�

		} else if (event >= 7 && event < 10) { // 7 ~ 9 : ������ ���̴� ����
			writeLog("������ ���̴� ���ڸ� �߰��ϰ� ���ڸ� ������.");

			// '�߱�' Ű���尡 �� ������ ���� ���� ���ڿ� ����Ʈ
			for (int i = 0; i < iteminfo.size(); i++) {
				if (iteminfo.get(i).getI_name().contains("�߱�")) {
					GoodRewards.add(iteminfo.get(i).getI_name());
				}
			}
			// 0 ~ 4 ���� �ߺ� ���� ���� 2�� �̱�
			int[] ran = makeRanNums();

			// ���� 2���� ���ڰ� ���� �ε���
			String[] good = new String[2];
			for (int i = 0; i < 2; i++) {
				good[i] = GoodRewards.get(ran[i]);
			}
			addInventory(good); // �κ��丮�� �߰�

		} else { // 10 : ȭ���� ���̴� ����
			writeLog("ȭ���� ���̴� ���ڸ� �߰��ϰ� ���ڸ� ������.");

			// '�߱�' Ű���尡 �� ������ ���� ���� ���ڿ� ����Ʈ
			for (int i = 0; i < iteminfo.size(); i++) {
				if (iteminfo.get(i).getI_name().contains("�����")) {
					VeryGoodRewards.add(iteminfo.get(i).getI_name());
				}
			}
			// 0 ~ 4 ���� �ߺ� ���� ���� 2�� �̱�
			int[] ran = makeRanNums();

			// ���� 2���� ���ڰ� ���� �ε���
			String[] VeryGood = new String[2];
			for (int i = 0; i < 2; i++) {
				VeryGood[i] = VeryGoodRewards.get(ran[i]);
			}
			addInventory(VeryGood); // �κ��丮�� �߰�
		}
	}

	// ����,���� �̺�Ʈ
	public void createSubEvent(int event) {
		switch (event) {
		case 1: // 1�� ����
			System.out.println("[info] ���� �� �ɷ�ġ : "+"�� "+c_str+" / "+" ��ø "+c_dex+" / "+" ���� "+c_int);
			if(buff) { // ���� ���°� true �϶� �ش� �̺�Ʈ�� ����Ǹ� �ߺ� ������ ����. �ߺ� ���� ������
				System.out.println("[info] �ߺ� ���� �߻�");
				return;
			}
			System.out.println("[info] ���� �̺�Ʈ �߻�");
			buff = true;
			writeLog("������ �ູ�� �޾� �ɷ�ġ�� �Ͻ������� ���Ǿ����ϴ�.\n(�ش� ������ ���� ù �������� ����˴ϴ�.)");
			// �ɷ�ġ ������ ���� �ɷ�ġ + ���� ĳ���� ����*5 ��ŭ ������Ŵ
			c_str+=(c_lv*5);
			c_dex+=(c_lv*5);
			c_int+=(c_lv*5);
			System.out.println("[info] ���� �� �ɷ�ġ : �� "+c_str+" / "+" ��ø "+c_dex+" / "+" ���� "+c_int);
			break;
		case 2: // 2�� ����
			System.out.println("[info] ���� �̺�Ʈ �߻�");
			int randomtrap = (int) (Math.random() * 3) + 1; // 1 ~ 3 ���� Ʈ�� �߻�
			if (randomtrap == 1) { // 1�� �� ����
				writeLog("������ ���� �վ������Դ�.");
				current_user_hp -= (c_lv * 2); // ĳ���� ���� * 2 ��ġ ��ŭ ü�� ����
				setBackgroundimg(FIRETRAP, BATTLEBACKGROUND);
			} else if (randomtrap == 2) { // 2�� ����(�ٴ�) ����
				writeLog("�ٴ��� ���ø� ��Ҵ�.");
				current_user_hp -= (c_lv * 3); // ĳ���� ���� * 3 ��ġ ��ŭ ü�� ����
				setBackgroundimg(SPIKETRAP, BATTLEBACKGROUND);
			} else { // 3�� �칰 ����
				writeLog("�칰���� ���� ���̴�");
				current_user_hp -= (c_lv * 4); // ĳ���� ���� * 4 ��ġ ��ŭ ü�� ����
				setBackgroundimg(WELLTRAP, BATTLEBACKGROUND);
			}
			break;
		}
	}

	// ����,���� �̺�Ʈ ���ȭ�� �缳�� �ϴ� �޼ҵ�
	void setBackgroundimg(ImageIcon trapimg, ImageIcon originalimg) {
		CharacterPanel.setVisible(false);
		MonsterPanel.setVisible(false);
		GameScreenimgLabel.setIcon(trapimg);
		Timer recoverimg = new Timer();
		TimerTask recovertask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameScreenimgLabel.setIcon(originalimg);
				CharacterPanel.setVisible(true);
			}
		};
		recoverimg.schedule(recovertask, 1000);
	}

	// 0 ~ 4 ���� ����� �޼ҵ�(���� �̺�Ʈ���� ���)
	int[] makeRanNums() {
		int[] ran = new int[2];
		for (int i = 0; i < 2; i++) {
			ran[i] = (int) (Math.random() * 5); // 0 ~ 4 ����
			for (int j = 0; j < i; j++) {
				if (ran[i] == ran[j]) {
					i--;
				}
			}
		}
		return ran;
	}

	// �÷��̾� ����(��Ÿ)
	public void attack_player() {
		if (!battle) {
			System.out.println("[info] ���� ���� �ƴմϴ�.");
			return;
		}
		writeLog("'" + c_name + "' �� ����!\n");

		playerattackLabel.setIcon(new ImageIcon(PLAYERBASICATTACK)); // �÷��̾� �⺻���� ����Ʈ ���
		monsterbeingattackedLabel.setIcon(new ImageIcon(BEINGATTACKED)); // �ǰ� ����Ʈ ���

		Timer pAttacktimer = new Timer();
		TimerTask pAttackTask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				playerattackLabel.setIcon(null); // �÷��̾� �⺻���� ����Ʈ ���
				monsterbeingattackedLabel.setIcon(null); // �ǰ� ����Ʈ ���
			}
		};
		pAttacktimer.schedule(pAttackTask, 900);

		int damage = playeratk - monsterdef; // �������� �÷��̾� ���ݷ� - ���� ����
		if (damage <= 0) { // �÷��̾� ���ݷ� - ���� ������ ����� 0���� �۰ų� ���� ��� (= ������ ������ �÷��̾� ���ݷº��� ���� ���)
			damage = 1; // �ּ� ������ 1�� ����ǵ��� ������.
			current_monster_hp -= damage; // damage ��ġ��ŭ ���� ���� ü�� ����
			writeLog("'" + c_name + "' (��/��) " + m_name + " ���� " + damage + " �� ���ظ� ������!");
		} else {
			int randomdamage = (int) (Math.random() * damage) + 1; // 1 ~ �÷��̾� ������ ������ ���������� ����
			current_monster_hp -= randomdamage; // randomdamage ��ġ��ŭ �� ���� ü�� ����
			writeLog("'" + c_name + "' (��/��) " + m_name + " ���� " + randomdamage + " �� ���ظ� ������!");
		}
	}

	// ���� ����(��Ÿ)
	public void attack_monster() {
		if (!battle) {
			System.out.println("[info] ���� ���� �ƴմϴ�.");
			return;
		}
		writeLog("'" + m_name + "' �� ����!\n");
		monsterattackLabel.setIcon(new ImageIcon(MONSTERATTACK)); // ���� ���� ����Ʈ ���
		playerbeingattackedLabel.setIcon(new ImageIcon(BEINGATTACKED)); // �ǰ� ����Ʈ ���
		
		int damage = monsteratk - playerdef; // �������� ���� ���ݷ� - �÷��̾� ����
		if (damage <= 0) { // ���� ���ݷ� - �÷��̾� ������ ����� 0���� �۰ų� ���� ��� (= �÷��̾��� ������ ������ ���ݷº��� ���� ���)
			damage = 1;
			writeLog("'" + m_name + "' (��/��) " + c_name + " ���� " + damage + " �� ���ظ� ������!");
			current_user_hp -= damage; // damage ��ġ��ŭ �÷��̾� ���� ü�� ����
		} else {
			int randomdamage = (int) (Math.random() * damage) + 1; // 1 ~ ���� ������ ������ ���������� ����
			writeLog("'" + m_name + "' (��/��) " + c_name + " ���� " + randomdamage + " �� ���ظ� ������!");
			current_user_hp -= randomdamage; // randomdamage ��ġ��ŭ �÷��̾� ���� ü�� ��
		}
		Timer mAttackEnd = new Timer();
		TimerTask mAttackEndTask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				monsterattackLabel.setIcon(null); // ���� ���� ����Ʈ ���
				playerbeingattackedLabel.setIcon(null); // �ǰ� ����Ʈ ���
			}
		};
		mAttackEnd.schedule(mAttackEndTask, Calendar.getInstance().get(Calendar.MILLISECOND)+500);
	}

	// �÷��̾� ���� Ȯ�� Thread ���� �޼ҵ�
	public void checkplayerstatus() {
		p_check = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("[info] p_check ������ ����");

				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(4000);
						System.out.println("[info] ĳ���� ü�� ���� üũ..");
						System.out.println("[info] ���� ĳ���� ü�� : " + current_user_hp);
						playerHpbar.setValue(current_user_hp);
						playerHpbar.setString(current_user_hp + " / " + c_hp);
						
						if (current_user_hp <= 0) {
							Thread.currentThread().interrupt();
						}
						
						System.out.println("[info] ĳ���� ���� ���� üũ..");
						System.out.println("[info] ���� ĳ���� ���� : " + current_user_mp);
						playerMpbar.setValue(current_user_mp);
						playerMpbar.setString(current_user_mp + " / " + c_mp);

						System.out.println("[info] ĳ���� ���ݷ�&���� ���� üũ..");
						playeratk = (c_str / 2) + atk_weapon; // �÷��̾� �� ���ݷ�(�⺻���ݷ� + ������ݷ�)
						equipdef = def_helmet + def_armor + def_glove + def_boots; // ����,����,�尩,�Ź� ���� �հ�
						playerdef = (c_dex / 5) + equipdef; // �÷��̾� �� ����(�⺻���� + �� �ѹ���)

						System.out.println("[info] ĳ���� ���� ���� üũ..");
						System.out.println("[info] ���� ���� : "+buff);
					} catch (Exception e) {
						System.out.println("[Error] p_check ������ ����");
					}
				}
			}
		});
		p_check.start();
	}

	// �� ���� Ȯ�� Thread ���� �޼ҵ�
	public void checkmonsterstatus() {
		m_check = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("[info] m_check ������ ����");
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(4000);
						System.out.println("[info] �� ü�� ���� üũ..");
						MonsterHpbar.setValue(current_monster_hp);
						MonsterHpbar.setString(current_monster_hp + " / " + m_hp);
						if (battle) { // ���� �߻� ��
							// ���� ������ ����ġ & ������ ȹ��(���� ����)
							if (current_monster_hp <= 0) {
								writeLog(m_name + "(��/��) ��������.");
								writeLog("����ġ�� " + m_exp + " �ö����ϴ�.");
								c_exp += m_exp; // ���� ����ġ�� �� ����ġ�� ����(����ġ ȹ��)
								if(buff) { // ������ �ɷ��ִ� ��� ���� ���� �� ������ ���� �ؾ���
									buff = false; // ���� ���� ����(���� ����)
									writeLog("�������Լ� ���� ������ �������.");
									c_str -= (c_lv*5); // ���� ���� ��ġ��ŭ - ���༭ ���� �ɷ�ġ�� ���ư�
									c_dex -= (c_lv*5); // ��ø ��ġ
									c_int -= (c_lv*5); // ���� ��ġ
									System.out.println("[info] ���� �ޱ� �� �ɷ�ġ : "+c_str+" / "+c_dex+" / "+c_int);
								}
								battle = false; // ���� ����
								MonsterPanel.setVisible(false); // ���г� visible�� false
								playerattackLabel.setIcon(null);
								GameScreenimgLabel.setIcon(BATTLEBACKGROUND);
							}
						}
					} catch (Exception e) {
						System.out.println("[Error] m_check ������ ����");
					}
				}
			}
		});
		m_check.start();
	}

	// �κ��丮�� ������ �߰��ϴ� �޼ҵ�
	void addInventory(String[] getitem) {
		try {
			for (int i = 0; i < iteminfo.size(); i++) {
				for (int j = 0; j < getitem.length; j++) {
					if (getitem[j].equals(iteminfo.get(i).getI_name())) {
						inven.add(iteminfo.get(i));
					}
				}
			}
			System.out.println("[info] ���� �κ��丮 ������ : " + inven.size());
			for (int i = 0; i < inven.size(); i++) {
				System.out.println("[info] ���� �κ��丮�� ����ִ� ������ : " + inven.get(i).getI_name());
			}
		} catch (Exception e) {
			System.out.println("[Error] ���� �߻�");
			e.printStackTrace();
		}
	}

	// InventoryScreen���� �κ��丮 �ֽ�ȭ�� ���� ����ϴ� �޼ҵ�.
	public static void setInventorydata(LinkedList<DSItems> data) {
		inven = data;
	}

	// InventoryScreen���� ������ ����ϰ� �� ���� ü�¼���
	public static void setPlayerhp(int hp) {
		current_user_hp = hp;
	}

	// InventoryScreen���� ������ ����ϰ� �� ���� ��������
	public static void setPlayermp(int mp) {
		current_user_mp = mp;
	}

	// InventoryScreen���� ����� ����ϰ� �� ���� ĳ���� ���� ����
	public static void setPlayerStr(int up) { // ���� ���
		c_str += up; // c_str = c_str+up : up�� ������ġ
	}

	public static void setPlayerDex(int up) { // ��ø�� ���
		c_dex += up;
	}

	public static void setPlayerInt(int up) { // ������ ���
		c_int += up;
	}

	public static void setPlayerAllstat(int up) { // �ɷ����(�ý���)�� ���
		c_str += up;
		c_dex += up;
		c_int += up;
	}

	// InventoryScreen���� ������ ���� ����ϰ� �� ���� ĳ���� ����ġ ����
	public static void setPlayerExp(int up) {
		c_exp += up;
	}

	// InventoryScreen���� ĳ������ ��� ��(����,����,����,�尩,�Ź�)�� set��
	public static void setPlayerEquipNameWeapon(String w) {
		if (w != null) {
			weapon = w;
		}
	}

	public static void setPlayerEquipNameHelmet(String h) {
		if (h != null) {
			helmet = h;
		}
	}

	public static void setPlayerEquipNameArmor(String a) {
		if (a != null) {
			armor = a;
		}
	}

	public static void setPlayerEquipNameGlove(String g) {
		if (g != null) {
			glove = g;
		}
	}

	public static void setPlayerEquipNameBoots(String b) {
		if (b != null) {
			boots = b;
		}
	}

	// InventoryScreen���� �Ѿ�� ������ ���� GameScreen�� set��
	public static void setEquipAtk(int w) {
		if (w > 0) {
			atk_weapon = w;
		}
	}

	public static void setEquipDef_Helmet(int h) {
		if (h > 0) {
			def_helmet = h;
		}
	}

	public static void setEquipDef_Armor(int a) {
		if (a > 0) {
			def_armor = a;
		}
	}

	public static void setEquipDef_Glove(int g) {
		if (g > 0) {
			def_glove = g;
		}
	}

	public static void setEquipDef_Boots(int b) {
		if (b > 0) {
			def_boots = b;
		}
	}

	// �ʱ� �� �⺻ ���� ����
	void settingLowMobInfo(int switchnum) {
		m_hp = lowmonsters.get(switchnum).getM_hp(); // �� ü�� ����
		m_name = lowmonsters.get(switchnum).getM_name(); // �� �̸� ����
		monsteratk = lowmonsters.get(switchnum).getM_atk(); // �� ���ݷ� ����
		monsterdef = lowmonsters.get(switchnum).getM_def(); // �� ���� ����
		m_exp = lowmonsters.get(switchnum).getM_exp(); // �� ����ġ ����
		dropitem = lowmonsters.get(switchnum).getMobDrop(); // �� �����
	}

	// �߱� �� �⺻ ���� ����
	void settingMiddleMobInfo(int switchnum) {
		m_hp = middlemonsters.get(switchnum).getM_hp(); // �� ü�� ����
		m_name = middlemonsters.get(switchnum).getM_name(); // �� �̸� ����
		monsteratk = middlemonsters.get(switchnum).getM_atk(); // �� ���ݷ� ����
		monsterdef = middlemonsters.get(switchnum).getM_def(); // �� ���� ����
		m_exp = middlemonsters.get(switchnum).getM_exp(); // �� ����ġ ����
		dropitem = middlemonsters.get(switchnum).getMobDrop(); // �� �����
	}

	// ��� �� �⺻ ���� ����
	void settingHighMobInfo(int switchnum) {
		m_hp = highmonsters.get(switchnum).getM_hp(); // �� ü�� ����
		m_name = highmonsters.get(switchnum).getM_name(); // �� �̸� ����
		monsteratk = highmonsters.get(switchnum).getM_atk(); // �� ���ݷ� ����
		monsterdef = highmonsters.get(switchnum).getM_def(); // �� ���� ����
		m_exp = highmonsters.get(switchnum).getM_exp(); // �� ����ġ ����
		dropitem = highmonsters.get(switchnum).getMobDrop(); // �� �����
	}

	// �ʱ� �� ����
	public void createLowMonster(int switchnum) {
		MonsterPanel.setVisible(true);
		switch (switchnum) {
		case 0: // Skelwarrior
			monsterimgLabel.setIcon(new ImageIcon(SKEL));
			writeLog(lowmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp); // ���� �� ü������ �� ü�¹� �� ����
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp); // ���� �ȿ� ���̴� ���ڿ� ����
			addInventory(dropitem); // Test��
			MonsterHpbar.setVisible(true);
			break;
		case 1: // Orcwarrior
			monsterimgLabel.setIcon(new ImageIcon(ORC));
			writeLog(lowmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			addInventory(dropitem);
			MonsterHpbar.setVisible(true);
			break;
		case 2: // Golem
			monsterimgLabel.setIcon(new ImageIcon(GOLEM));
			writeLog(lowmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			addInventory(dropitem);
			MonsterHpbar.setVisible(true);
			break;
		}
	}

	// �߱� �� ����
	public void createMiddleMonster(int swtichnum) {
		MonsterPanel.setVisible(true);
		switch (swtichnum) {
		case 0: // SkelKing
			writeLog(middlemonsters.get(swtichnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(SKELKING));
			break;
		case 1: // Hatchling
			writeLog(middlemonsters.get(swtichnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(HATCHLING));
			break;
		case 2: // Lagiacrus
			writeLog(middlemonsters.get(swtichnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(LAGIA));
			break;
		}
	}

	// ��� �� ����
	public void createHighMonster(int switchnum) {
		MonsterPanel.setVisible(true);
		switch (switchnum) {
		case 0: // Drake
			writeLog(highmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(DRAKE));
			break;

		case 1: // Chimera
			writeLog(highmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(CHIMERA));
			break;

		case 2: // IceDragon
			writeLog(highmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(ICEDRAGON));
			break;
		}
	}
}