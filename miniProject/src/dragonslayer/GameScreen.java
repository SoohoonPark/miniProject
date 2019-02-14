package dragonslayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
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

@SuppressWarnings("serial")
public class GameScreen extends JFrame {
	/** �̹��� ���� **/
	// ���Ӿ�����
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");

	// ���ӹ��ȭ��(�簢�� �׵θ�)
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/background_border.png");
	// �������
	private final static ImageIcon BATTLEBACKGROUND = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/background_battle.png"));
	// �������
	private final static ImageIcon FIRETRAP = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/fire_trap.png"));
	private final static ImageIcon SPIKETRAP = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/spike_trap.png"));
	private final static ImageIcon WELLTRAP = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/well_trap.png"));

	// �÷��̾� �̹���(���谡)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_beginner.png");
	private final static Image PLAYERWARRIOR = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_warrior.png");
	private final static Image PLAYERKNIGHT = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_knight.png");
	private final static Image PLAYERKNIGHT_BOSS = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_knight_boss.png");

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

	// ������ �̹�����(�ֻ��)
	private final static Image BOSSPOLYMORPH = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/boss/bosspolymorph_resized.png");
	private final static Image BOSSORIGINAL = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/boss/bossoriginal_resized.png");

	// ��ư �г� & �α� �г� ���(�׵θ�)
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/background_border_log.png");
	private final static Image BUTTONBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/background_border_buttons.png");

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
	private final static Image LOGO = Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/logo.png");

	// ��ų ����Ʈ �̹�����
	private final static Image PLAYERBASICATTACK = Toolkit.getDefaultToolkit().createImage("resource/images/effects/player/player_basic_attack.gif");
	private final static Image MONSTERATTACK = Toolkit.getDefaultToolkit().createImage("resource/images/effects/monster/monster_attack_resized.gif");
	private final static Image BEINGATTACKED = Toolkit.getDefaultToolkit().createImage("resource/images/effects/both sides/Being attacked_resized.gif");
	private final static Image BOSSNORMALATTACK = Toolkit.getDefaultToolkit().createImage("resource/images/effects/monster/boss/Boss Normal attack_resized.gif");
	private final static Image BOSSSKILLATTACK = Toolkit.getDefaultToolkit().createImage("resource/images/effects/monster/boss/Boss Skill_resized.gif");

	/** �ʵ� ���� **/
	private static String c_name, m_name, c_job; // ĳ���͸� & �����̸�
	// ĳ���� ���� ���� ���� (����â �������� ������) ����, ��, ��ø, ����, ü��, ����, ����ġ, ���� ����ġ
	public static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp;
	public static int current_monster_hp, m_hp, m_exp; // �� ü�� & �� �ִ�ü�� & ���� �ִ� ����ġ
	public static Boolean battle = false; // ���� �߻��� �˷��ִ� ����. ���� �߻� �� true�� ��ȯ(�⺻�� false)
	private static Boolean bossphase2 = false; // ������ 2 ���� ����
	private static Boolean bossfight = false; // ������ ���� ����
	private static Boolean skillused = false; // ���� ��ų�� �ѹ��� ���� �� �ֵ��� �����ϴ� ����

	private Boolean buff = false; // �÷��̾� ���� ��Ȳ(�ɷ��ִ��� �ƴ���)
	private Thread p_check, m_check; // �÷��̾�, �� ���� Ȯ�� Thread
	private LinkedList<DSMonsters> lowmonsters = null; // �ʱ޸������� ������ִ� LinkedList
	private LinkedList<DSMonsters> middlemonsters = null; // �߱޸������� ������ִ� LinkedList
	private LinkedList<DSMonsters> highmonsters = null; // ��޸������� ������ִ� LinkedList
	private LinkedList<DSMonsters> bossmonsters = null; // ������������ ������ִ� LinkedList
	private LinkedList<DSItems> iteminfo; // ���� �� ������ ������ ��� �ִ� iteminfo
	private ArrayList<String> Rewards = new ArrayList<String>(); // ����غ��̴� ����
	private ArrayList<String> GoodRewards = new ArrayList<String>(); // �����ƺ��̴� ����
	private ArrayList<String> VeryGoodRewards = new ArrayList<String>(); // ȭ���غ��̴� ����
	private HashMap<Integer, Integer> exptable; // ���� �� ����ġ ������ ��� �ִ� exptable;
	private String[] dropitem; // ���� ����ϴ� �������� ����Ǿ��ִ� String �迭
	private static int playeratk; // �÷��̾� ���ݷ�
	private static int playerdef; // �÷��̾� ����

	private static int equipdef; // ���� �� ����
	private static int monsteratk, monsterdef; // ���� ���ݷ�, ����

	private int prevlv; // ���� ����(���� �����ؼ� ����ϰ� �Ǵ� ����)
	private DSService service = new DSService(); // DB ���� ����

	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel, GameScreenimgLabel, characterLabel, monsterimgLabel; // �̹��� �󺧵�
	private static JLabel playerattackLabel, monsterattackLabel, playerbeingattackedLabel, monsterbeingattackedLabel;
	private static JLabel bossskillLabel;
	private static JLabel message;
	public static JLabel SkillEffectLabel1, SkillEffectLabel2, SkillEffectLabel3, SkillEffectLabel4_1,SkillEffectLabel4_2;

	private static JPanel CharacterPanel, MonsterPanel; // ĳ���� �̹����� ��µǴ� �г�, �� �̹����� ��µǴ� �г�
	private static JPanel skilleffectpanel; // ���� �� ��ų ����Ʈ�� ��µǴ� �г�
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	private static JProgressBar playerHpbar, playerMpbar, MonsterHpbar; // �÷��̾� ü�¸���,��������, �� ü�¸���

	private static String helmet, armor, glove, boots, weapon; // ĳ���Ͱ� �����ϰ� �ִ� �����۸�(��� â���� �ѱ� ����)
	private static int def_helmet, def_armor, def_glove, def_boots, atk_weapon; // ĳ���Ͱ� �����ϰ� �ִ� �������� ��&��
	private static LinkedList<DSItems> inven = new LinkedList<DSItems>(); // �÷��̾� �κ��丮 ���빰
	private static int current_user_hp, current_user_mp; // ���� �÷��̾� ü�� & ����

//	public static void main(String[] args) {
//		new GameScreen("�����", 30, "���谡", 1000, 1000, 1000, 1400, 880);
//	}

	/** �޼ҵ� ���� **/
	public GameScreen(String name, int l, String job, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() ȣ��");
		// MainScreen ~ LoadingScreen ���� ���Ǵ� bgm�� �����ϰ� GameScreen���� ���ο� bgm ���
		DSAudio audio = DSAudio.getInstance();
//		audio.offTitle();
		audio.playGame();

		lowmonsters = service.monsterData("�ʱ�"); // �ʱ� �� ���� ����
		middlemonsters = service.monsterData("�߱�"); // �߱� �� ���� ����
		highmonsters = service.monsterData("���"); // ��� �� ���� ����
		bossmonsters = service.monsterData("����"); // ���� �� ���� ����
		iteminfo = service.itemData(); // ������ ���� ����
		exptable = service.expData(); // ����ġ ���� ����(Key - ���� / Value - ���� ����ġ)

		GameScreen.c_name = name; // ĳ���͸�
		GameScreen.c_job = job; // ����
		GameScreen.c_lv = l; // ����
		GameScreen.c_str = s; // ��
		GameScreen.playeratk = (c_str / 2) + atk_weapon; // ĳ���� ���ݷ��� (��/2)+�����ݷ�
		GameScreen.c_dex = d; // ��ø
		GameScreen.playerdef = (c_dex / 5) + equipdef; // ĳ���� ������ (��ø/5)+������
		GameScreen.c_int = i; // ����

		GameScreen.c_hp = hp; // ü��
		GameScreen.current_user_hp = c_hp; // �÷��̾� ���� ü��
		GameScreen.c_mp = mp + (c_int * 2); // ������ �⺻ ������ + ����*2
		GameScreen.current_user_mp = c_mp; // �÷��̾� ���� ����

		GameScreen.c_exp = 0; // �ʱ� ����ġ ������ 0
		GameScreen.c_next_exp = exptable.get(c_lv); // ���� ����ġ �䱸�� 50

		// ��� �⺻���� "����"
		weapon = "����";
		helmet = "����";
		armor = "����";
		glove = "����";
		boots = "����";
		System.out.println("[info] GameScreen() �ʵ� �ʱ�ȭ �Ϸ�.");


		addInventory(new String[] { "�ܰ�", "ü�� ����", "���� ����", "ü�� ����", "���� ����" }); // �⺻ ��																													// ����
//		addInventory(new String[] { "�Ⱓƽ �׽�", "�̽��� ����", "�̽��� ����", "�̽��� �尩", "�̽��� �Ź�" }); // �׽��ÿ�

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

		// ĳ���� �̹��� ��µǴ� �г�(ü��/���� ���� + ĳ���� �̹���)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(650, 60, 210, 300);
		CharacterPanel.setOpaque(false);

		// ĳ���� �̹��� ����ϴ� Label
		characterLabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
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
		MonsterPanel.setOpaque(false);

		monsterimgLabel = new JLabel("", SwingConstants.CENTER);
		monsterimgLabel.setBounds(10, 25, 330, 290);

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
		logarea.setFocusable(false);
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
			// Ž�� ��ư�� ������ �� 3������ �̺�Ʈ �� �������� �ϳ��� �߻���(����,����ȹ��,�����̺�Ʈ)
			public void actionPerformed(ActionEvent e) {
				// battle�� true�� ���(���� �߻�) Ž���Ұ�. ���� ���� �� Ž�� �簳 ����
				if (battle) {
					JLabel message = new JLabel("<html><p style='font-family:���� ���;'>���� �߿��� Ž���� �Ұ����մϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "Ž��", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				// 1 ~ 3 �б� �߻�
				int switchnum = (int) (Math.random() * 10) + 1; // 1 ~ 10
				System.out.println("���� Ž�� �б� : " + switchnum);
				switch (switchnum) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:// ���� �߻�
					battle = true; // ���� �߻� �� true�� ��ȯ, �ش� ������ ������ ����Ǹ� �ٽ� false�� �ٲ�
					System.out.println("[info] ���� �߻�");
					createBattle(c_lv); // �÷��̾� ������ ���� �����Ǵ� ���� ����(?)�� �޶���. 1 ~ 10, 11 ~ 20, 21 ~ 30
					break;
				case 8:
					System.out.println("[info] ���� ȹ�� �̺�Ʈ �߻�");
					// 1 ~ 10 ���� �̺�Ʈ (1 ~ 6 �Ϲ� ����, 7 ~ 9 ������ ���̴� ����, 10 ȭ���� ����)
					int chestevent = (int) (Math.random() * 10) + 1;
					System.out.println("[info] �߻��� ���� ȹ�� �̺�Ʈ	 : " + chestevent);
					createGetItemEvent(chestevent);
					break;
				case 9: case 10:
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
					JLabel message = new JLabel("<html><p style='font-family:���� ���;'>���Ͱ� �������� �ʽ��ϴ�.</p></html>");
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
				mAttackTimer.schedule(mAttackTimerTask, Calendar.getInstance().get(Calendar.MILLISECOND) + 500);
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
				new SkillScreen();
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
						"<html><p style='font-family:���� ���;'>���� ���ǲ�����?<br/>���� ������ ������ �ʿ�� �ϴ� ���� ���ƿ�!!</p></html>");
				int a = JOptionPane.showOptionDialog(null, message, "��������", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "�̾�������, ������ �� �ٺ�.", "�׷�? �� ������ �ʿ��� ���� ����??" },
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
		logoLabel.setBounds(325, 85, 90, 90);
		logoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				if (c_lv == 30) {
					createFinalBossBattle_Phase1();
				} else {
					JLabel message = new JLabel("<html><p style='font-family:���� ���;'>"
							+ "������ �����Ͽ� ������ �濡 ������ �� �����ϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "������ ����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		ButtonPanel.add(logoLabel);

		skilleffectpanel = new JPanel(null);
		skilleffectpanel.setBounds(150, 70, 670, 280);
		skilleffectpanel.setOpaque(false);

		// �÷��̾� �⺻ ���� ����Ʈ ��
		playerattackLabel = new JLabel();
		playerattackLabel.setBounds(150, 0, 380, 280);

		// ���� ���� ����Ʈ ��
		monsterattackLabel = new JLabel();
		monsterattackLabel.setBounds(320, 30, 201, 253);

		// ���� ��ų ����Ʈ ��
		bossskillLabel = new JLabel();
		bossskillLabel.setBounds(320, 30, 950, 330);

		// �÷��̾� �ǰ� ����Ʈ ��
		playerbeingattackedLabel = new JLabel();
		playerbeingattackedLabel.setBounds(460, 30, 250, 250);

		// ���� �ǰ� ����Ʈ ��
		monsterbeingattackedLabel = new JLabel();
		monsterbeingattackedLabel.setBounds(0, 30, 250, 250);

		// ��ų ����Ʈ ��
		SkillEffectLabel1 = new JLabel();
		SkillEffectLabel1.setBounds(150, 0, 380, 280);

		SkillEffectLabel2 = new JLabel();
		SkillEffectLabel2.setBounds(150, 0, 380, 280);

		SkillEffectLabel3 = new JLabel();
		SkillEffectLabel3.setBounds(150, 0, 380, 280);

		SkillEffectLabel4_1 = new JLabel();
		SkillEffectLabel4_1.setBounds(40, 35, 950, 330);

		SkillEffectLabel4_2 = new JLabel();
		SkillEffectLabel4_2.setBounds(40, 35, 950, 330);

		skilleffectpanel.add(playerattackLabel);
		skilleffectpanel.add(monsterattackLabel);
		skilleffectpanel.add(playerbeingattackedLabel);
		skilleffectpanel.add(monsterbeingattackedLabel);
		skilleffectpanel.add(SkillEffectLabel1);
		skilleffectpanel.add(SkillEffectLabel2);
		skilleffectpanel.add(SkillEffectLabel3);
		skilleffectpanel.add(bossskillLabel);

		layer.add(mainbackgroundimgLabel, new Integer(1));
		layer.add(GameScreenimgLabel, new Integer(2));
		layer.add(SkillEffectLabel4_1, new Integer(4));
		layer.add(SkillEffectLabel4_2, new Integer(3));
		layer.add(CharacterPanel, new Integer(3));
		layer.add(MonsterPanel, new Integer(3));
		layer.add(LogPanel, new Integer(4));
		layer.add(ButtonPanel, new Integer(4));
		layer.add(skilleffectpanel, new Integer(4));

		setVisible(true);
	}

	// 30 ������ �� �� ���������� ����
	void createFinalBossBattle_Phase1() { // ���� �� Phase 1
		UIManager.put("OptionPane.buttonFont", new Font("���� ���", Font.PLAIN, 12));
		JLabel message = new JLabel(
				"<html><p style='font-family:���� ���;'>���� ����� �巡���� ����� �ϸ� ���ҽ��ϴ�.<br/>������ ������ ���� �غ� �Ǿ����ϱ�?</p></html>");
		int select = JOptionPane.showOptionDialog(this, message, "������ ����", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "��������!", "������ ���� �η���..." }, null);
		switch (select) {
		case 0: // 'YES'
			battle = true;
			bossfight = true;
			// Phase 1 ����
			writeLog("\n\n������ ������ ���۵Ǿ����ϴ�.\n�� ������ ����� ������ �տ� �޷��ֽ��ϴ�!");
			System.out.println("[info] ���� �߻�");
			createBossMonster(0);
			System.out.println("[info] ���� �� - Phase 1 ����");
			writeLog("�巡���� �ΰ��� ������ �ϰ� �ִٴ� �� \n������ ����ƺ��� �ִٴ� ���Դϴ�.\n����� ƴŸ ��ȸ�� �븮����!");
			break;
		case 1: // 'NO'
			break;
		}

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
		if (buff) {
			System.out.println("[info] ���� �ɷ�����");
		}
		int switchnum = (int) (Math.random() * 3); // 0 ~ 2 ����
		if (level >= 1 && level <= 10) { // 1 ~ 10 ������ �ʱ� ��
			if (c_lv <= 5) { // ĳ���� ������ 5 ���� �� ���
				switchnum = (int) (Math.random() * 2); // 0 ~ 1 ����(�� ���� ����)
				createLowMonster(switchnum);
			} else {
				createLowMonster(switchnum);
			}
		} else if (level >= 11 && level <= 20) { // 11 ~ 20 ������ �߱� ��
			if (c_lv <= 15) { // ĳ���� ������ 15 ���� �� ���
				switchnum = (int) (Math.random() * 2); // 0 ~ 1 ����(��ũ�� ���ñ� ���� ����)
				createMiddleMonster(switchnum);
			} else {
				createMiddleMonster(switchnum);
			}
		} else { // �� �� �ƴϸ� 21 ~ ���ʹϱ� ��� �� ����
			if (c_lv != 25) {
				switchnum = (int) (Math.random() * 2); // 0 ~ 1 ����(���̽� �巡�� ���� ����)
				createHighMonster(switchnum);
			} else {
				createHighMonster(switchnum);
			}
		}
	}

	// ������ ȹ�� �̺�Ʈ(���� �̺�Ʈ)
	public void createGetItemEvent(int event) {
		try {
			if (event >= 1 && event <= 6) { // 1 ~ 6 : �Ϲ� ����
				writeLog("\n����� ���̴� ���ڸ� �߰��ߴ�.");
				// '�ϱ�' Ű���尡 �� ������ ���� ���� ���ڿ� ����Ʈ
				for (int i = 0; i < iteminfo.size(); i++) {
					if (iteminfo.get(i).getI_name().contains("�ϱ�")) {
						Rewards.add(iteminfo.get(i).getI_name());
					}
				}
				System.out.println("[info] �ϱ� ���� ���� ������ : " + Rewards.size());
				// 0 ~ 4 ���� �ߺ� ���� ���� 2�� �̱�
				int[] ran = makeRanNums();

				// ���� 2���� ���ڰ� ���� �ε���
				String[] low = new String[2];
				for (int i = 0; i < 2; i++) {
					low[i] = Rewards.get(ran[i]);
				}
				addInventory(low); // �κ��丮�� �߰�
				Rewards.clear(); // ���� ȹ�� �� ���� ����Ʈ ���� �ʱ�ȭ

			} else if (event >= 7 && event < 10) { // 7 ~ 9 : ������ ���̴� ����
				writeLog("\n������ ���̴� ���ڸ� �߰��ߴ�.");
				// '�߱�' Ű���尡 �� ������ ���� ���� ���ڿ� ����Ʈ
				for (int i = 0; i < iteminfo.size(); i++) {
					if (iteminfo.get(i).getI_name().contains("�߱�")) {
						GoodRewards.add(iteminfo.get(i).getI_name());
					}
				}
				System.out.println("[info] �߱� ���� ���� ������ : " + GoodRewards.size());
				// 0 ~ 4 ���� �ߺ� ���� ���� 2�� �̱�
				int[] ran = makeRanNums();

				// ���� 2���� ���ڰ� ���� �ε���
				String[] good = new String[2];
				for (int i = 0; i < 2; i++) {
					good[i] = GoodRewards.get(ran[i]);
				}
				addInventory(good); // �κ��丮�� �߰�
				GoodRewards.clear();

			} else { // 10 : ȭ���� ���̴� ����
				writeLog("\nȭ���� ���̴� ���ڸ� �߰��ߴ�.");
				// '�����' Ű���尡 �� ������ ���� ���� ���ڿ� ����Ʈ
				for (int i = 0; i < iteminfo.size(); i++) {
					if (iteminfo.get(i).getI_name().contains("�����")) {
						VeryGoodRewards.add(iteminfo.get(i).getI_name());
					}
				}
				System.out.println("[info] ����� ���� ���� ������ : " + VeryGoodRewards.size());
				// 0 ~ 4 ���� �ߺ� ���� ���� 2�� �̱�
				int[] ran = makeRanNums();

				// ���� 2���� ���ڰ� ���� �ε���
				String[] VeryGood = new String[2];
				for (int i = 0; i < 2; i++) {
					VeryGood[i] = VeryGoodRewards.get(ran[i]);
				}
				addInventory(VeryGood); // �κ��丮�� �߰�
				VeryGoodRewards.clear();
			}
		} catch (Exception e) {
			System.out.println("[Error] ���� �̺�Ʈ ���� �߻�");
			System.out.println(e.getMessage());
		}
	}

	// ����,���� �̺�Ʈ
	public void createSubEvent(int event) {
		switch (event) {
		case 1: // 1�� ����
			System.out.println("[info] ���� �� �ɷ�ġ : " + "�� " + c_str + " / " + " ��ø " + c_dex + " / " + " ���� " + c_int);
			if (buff) { // ���� ���°� true �϶� �ش� �̺�Ʈ�� ����Ǹ� �ߺ� ������ ����. �ߺ� ���� ������
				System.out.println("[info] �ߺ� ���� �߻�");
				return;
			}
			System.out.println("[info] ���� �̺�Ʈ �߻�");
			buff = true;
			prevlv = c_lv; // ������ ���� ���� ��ġ�� ������ ���� ���� ��ġ�� �޶����°��� �����ϱ� ���� ����.
			writeLog("\n������ �ູ�� �޾� �ɷ�ġ�� �Ͻ������� ���Ǿ����ϴ�.\n(�ش� ������ ���� ù �������� ����˴ϴ�.)");
			// �ɷ�ġ ������ ���� �ɷ�ġ + ���� ĳ���� ����*5 ��ŭ ������Ŵ
			c_str += (c_lv * 5);
			c_dex += (c_lv * 5);
			c_int += (c_lv * 5);
			System.out.println("[info] ���� �� �ɷ�ġ : �� " + c_str + " / " + " ��ø " + c_dex + " / " + " ���� " + c_int);
			break;
		case 2: // 2�� ����
			System.out.println("[info] ���� �̺�Ʈ �߻�");
			int randomtrap = (int) (Math.random() * 3) + 1; // 1 ~ 3 ���� Ʈ�� �߻�
			DSAudio trap = DSAudio.getInstance();
			trap.playTrapScream();
			if (randomtrap == 1) { // 1�� �� ����
				writeLog("\n���� �ߵ� : �ܺ����� �߰ſ� ���� �վ��� ���Դ�.\nü���� �����ߴ�.");
				current_user_hp -= (c_lv * 20); // ĳ���� ���� * 2 ��ġ ��ŭ ü�� ����
				setBackgroundimg(FIRETRAP, BATTLEBACKGROUND);
			} else if (randomtrap == 2) { // 2�� ����(�ٴ�) ����
				writeLog("\n���� �ߵ� : �ٴڿ��� ��ī�ο� ���ð� �ڱ��� �ö�Դ�.\nü���� �����ߴ�.");
				current_user_hp -= (c_lv * 30); // ĳ���� ���� * 3 ��ġ ��ŭ ü�� ����
				setBackgroundimg(SPIKETRAP, BATTLEBACKGROUND);
			} else { // 3�� �칰 ����
				writeLog("\n�����ߵ� : ������ �칰���� ���� ���̴�.\nü���� �����ߴ�.");
				current_user_hp -= (c_lv * 40); // ĳ���� ���� * 4 ��ġ ��ŭ ü�� ����
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
		recoverimg.schedule(recovertask, 2000);
	}

	// 0 ~ 4 ���� ����� �޼ҵ�(���� �̺�Ʈ���� ���)
	int[] makeRanNums() {
		int[] ran = new int[2];
		for (int i = 0; i < 2; i++) {
			ran[i] = (int) (Math.random() * 4); // 0 ~ 3 ���� (0,1,2,3)
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
		DSAudio playerhit = DSAudio.getInstance();
		if (!battle) {
			System.out.println("[info] ���� ���� �ƴմϴ�.");
			return;
		}
		writeLog("'" + c_name + "' �� ����!\n");

		playerattackLabel.setIcon(new ImageIcon(PLAYERBASICATTACK)); // �÷��̾� �⺻���� ����Ʈ ���
		playerhit.playAtk_P();
		monsterbeingattackedLabel.setIcon(new ImageIcon(BEINGATTACKED)); // �ǰ� ����Ʈ ���
		playerhit.playBeinghit();

		int damage = playeratk - monsterdef; // �������� �÷��̾� ���ݷ� - ���� ����
		System.out.println("���� ������ : " + damage);
		if (damage <= 0) { // �÷��̾� ���ݷ� - ���� ������ ����� 0���� �۰ų� ���� ��� (= ������ ������ �÷��̾� ���ݷº��� ���� ���)
			damage = 1; // �ּ� ������ 1�� ����ǵ��� ������.
			current_monster_hp -= damage; // damage ��ġ��ŭ ���� ���� ü�� ����
			writeLog("'" + c_name + "' (��/��) " + m_name + " ���� " + damage + " �� ���ظ� ������!");
		} else {
			int mindam = damage / 2; // �ּҵ������� ������ / 2
			int maxdam = damage; // �ִ뵥����
			System.out.println("�ּҵ����� : " + mindam);
			System.out.println("�ִ뵥���� : " + maxdam);
			if (mindam == 1 || maxdam == 1) {
				int randomdamage = 1;
				current_monster_hp -= randomdamage; // randomdamage ��ġ��ŭ �� ���� ü�� ����
				writeLog("'" + c_name + "' (��/��) " + m_name + " ���� " + randomdamage + " �� ���ظ� ������!");
			} else {
				int randomdamage = (int) (Math.random() * maxdam) + mindam; // �ּҵ����� ~ �÷��̾� ������ ������ ���������� ����
				if (randomdamage > maxdam) {
					randomdamage = maxdam;
				}
				System.out.println("���� ���������� : " + randomdamage);
				current_monster_hp -= randomdamage; // randomdamage ��ġ��ŭ �� ���� ü�� ����
				writeLog("'" + c_name + "' (��/��) " + m_name + " ���� " + randomdamage + " �� ���ظ� ������!");
			}
		}

		Timer pAttacktimer = new Timer();
		TimerTask pAttackTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				playerattackLabel.setIcon(null); // �÷��̾� �⺻���� ����Ʈ ���
				monsterbeingattackedLabel.setIcon(null); // �ǰ� ����Ʈ ���
			}
		};
		pAttacktimer.schedule(pAttackTask, 800);
	}

	// ���� ����(��Ÿ)
	public static void attack_monster() {
		DSAudio monsterhit = DSAudio.getInstance();
		if (!battle) {
			System.out.println("[info] ���� ���� �ƴմϴ�.");
			return;
		}

		writeLog("'" + m_name + "' �� ����!");
		if (battle == true && bossphase2 == true) { // ���� ���̸�, ������ phase 2�� ���� ���̸�
			System.out.println("[Info] ������ 2 ������");
			DSAudio playdragonattack = DSAudio.getInstance();
			playdragonattack.playDragonAttack();
			monsterattackLabel.setIcon(new ImageIcon(BOSSNORMALATTACK)); // ���� �⺻ ���� ����Ʈ ���
			monsterattackLabel.setBounds(340, 30, 380, 280);
		} else {
			monsterattackLabel.setIcon(new ImageIcon(MONSTERATTACK)); // ���� ���� ����Ʈ ���
			monsterhit.playAtk_M();
			playerbeingattackedLabel.setIcon(new ImageIcon(BEINGATTACKED)); // �ǰ� ����Ʈ ���
			monsterhit.playBeinghit();
		}

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
		if (current_monster_hp <= 0) {
			return;
		} else {
			mAttackEnd.schedule(mAttackEndTask, Calendar.getInstance().get(Calendar.MILLISECOND) + 300);
		}
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
						Thread.sleep(300);
						System.out.println("[info] ĳ���� ���� üũ..");
						if (c_lv > 10 && c_lv < 30) { // 11 ~ 29 ���� ���� �̹���
							c_job = "����"; // 11 ~ 29 ���� ������ ����
							characterLabel.setIcon(new ImageIcon(PLAYERWARRIOR));
						} else if (c_lv >= 30) { // ��� �̹���
							c_job = "���"; // ����(30) ������ ���
							if (bossphase2) {
								characterLabel.setIcon(new ImageIcon(PLAYERKNIGHT_BOSS));
							} else {
								characterLabel.setIcon(new ImageIcon(PLAYERKNIGHT));
							}
						}
						System.out.println("[info] ĳ���� ü�� & ���� ���� üũ..");
						playerHpbar.setValue(current_user_hp);
						playerHpbar.setString(current_user_hp + " / " + c_hp);
						playerMpbar.setValue(current_user_mp);
						playerMpbar.setString(current_user_mp + " / " + c_mp);

						if (current_user_hp <= 0) {
							JLabel message = new JLabel("<html><body style='background-color:black;'>"
									+ "<p style='font-family:���� ���; font-size:13px; color:white; text-align:center;'>"
									+ "ĳ���Ͱ� �����Ͽ����ϴ�.<br/><br/>������ ���ƿ��� ������ ���� ���Ƴ��� �ְڽ��ϴ�!!"
									+ "</p></body></html>");

							UIManager.put("OptionPane.background", Color.BLACK);
							UIManager.put("Panel.background", Color.BLACK);

							JOptionPane.showMessageDialog(null, message, "ĳ���� ���", JOptionPane.DEFAULT_OPTION, null);
							System.exit(0);
						}

						System.out.println("[info] ĳ���� ���ݷ�&���� ���� üũ..");
						playeratk = (c_str / 2) + atk_weapon; // �÷��̾� �� ���ݷ�(�⺻���ݷ� + ������ݷ�)
						equipdef = def_helmet + def_armor + def_glove + def_boots; // ����,����,�尩,�Ź� ���� �հ�
						playerdef = (c_dex / 5) + equipdef; // �÷��̾� �� ����(�⺻���� + �� �ѹ���)

						System.out.println("[info] ĳ���� ���� ����ġ & ���� ����ġ ���� üũ..");
						c_next_exp = exptable.get(c_lv);
						System.out.println("[info] ���� ����ġ : " + c_exp);
						System.out.println("[info] ���� ����ġ : " + c_next_exp);
						// ������(���� ����ġ�� ���� ����ġ���� ũ�ų� ���� ��)
						if (c_exp >= c_next_exp) {
							c_lv++;
							writeLog("\n---------- ���� �� ----------\nĳ���� ������ " + c_lv + " �� �Ǿ���.\n");
							int strup, dexup, intup;

							if (c_lv >= 1 && c_lv <= 10) { // ������ 1 ~ 10�� ��� ���� ����ġ (5 ~ 10 ����), ü�� +40 /���� +10
								strup = (int) (Math.random() * 6) + 5;
								c_str += strup;
								writeLog("���� " + strup + " �ö���.");
								dexup = (int) (Math.random() * 6) + 5;
								c_dex += dexup;
								writeLog("��ø�� " + dexup + " �ö���.");
								intup = (int) (Math.random() * 6) + 5;
								c_int += intup;
								writeLog("������ " + intup + " �ö���.");
								c_hp += 40;
								c_mp += 10;
								writeLog("ü���� 40 �ö���.\n������ 10 �ö���.");

								current_user_hp = c_hp;
								current_user_mp = c_mp;
								playerHpbar.setValue(current_user_hp);
								playerMpbar.setValue(current_user_mp);
								playerHpbar.setMaximum(current_user_hp);
								playerMpbar.setMaximum(current_user_mp);
								playerHpbar.setString(current_user_hp + " / " + c_hp);
								playerMpbar.setString(current_user_mp + " / " + c_mp);

							} else if (c_lv >= 11 && c_lv <= 20) { // ������ 11 ~ 20�� ��� ���� ����ġ (10 ~ 15 ����), ü�� + 70 /���� +
																	// 30
								strup = (int) (Math.random() * 6) + 10;
								c_str += strup;
								writeLog("���� " + strup + " �ö���.");
								dexup = (int) (Math.random() * 6) + 10;
								c_dex += dexup;
								writeLog("��ø�� " + dexup + " �ö���.");
								intup = (int) (Math.random() * 6) + 10;
								c_int += intup;
								writeLog("������ " + intup + " �ö���.");
								c_hp += 70;
								c_mp += 20;
								writeLog("ü���� 70 �ö���.\n������ 20 �ö���.");

								current_user_hp = c_hp;
								current_user_mp = c_mp;
								playerHpbar.setValue(current_user_hp);
								playerMpbar.setValue(current_user_mp);
								playerHpbar.setMaximum(current_user_hp);
								playerMpbar.setMaximum(current_user_mp);
								playerHpbar.setString(current_user_hp + " / " + c_hp);
								playerMpbar.setString(current_user_mp + " / " + c_mp);

							} else if (c_lv >= 21 && c_lv <= 29) { // 21 ~ 29 ���� ����ġ (15 ~ 20 ����), ü�� + 100 /���� + 30
								strup = (int) (Math.random() * 6) + 15;
								c_str += strup;
								writeLog("���� " + strup + " �ö���.");
								dexup = (int) (Math.random() * 6) + 15;
								c_dex += dexup;
								writeLog("��ø�� " + dexup + " �ö���.");
								intup = (int) (Math.random() * 6) + 15;
								c_int += intup;
								writeLog("������ " + intup + " �ö���.");
								c_hp += 100;
								c_mp += 30;
								writeLog("ü���� 100 �ö���.\n������ 30 �ö���.");

								current_user_hp = c_hp;
								current_user_mp = c_mp;
								playerHpbar.setValue(current_user_hp);
								playerMpbar.setValue(current_user_mp);
								playerHpbar.setMaximum(current_user_hp);
								playerMpbar.setMaximum(current_user_mp);
								playerHpbar.setString(current_user_hp + " / " + c_hp);
								playerMpbar.setString(current_user_mp + " / " + c_mp);

							} else { // 30 ���� �޼�(����) �ý���+80, ü,�� + 500
								c_hp += 300;
								c_mp += 300;
								c_str += 80;
								c_dex += 80;
								c_int += 80;
								writeLog("���� 30�� �Ǿ���.\nü���� 300 �ö���.\n������ 300 �ö���.\n��� �ɷ�ġ�� 80 �ö���.");

								current_user_hp = c_hp;
								current_user_mp = c_mp;
								playerHpbar.setValue(current_user_hp);
								playerMpbar.setValue(current_user_mp);
								playerHpbar.setMaximum(current_user_hp);
								playerMpbar.setMaximum(current_user_mp);
								playerHpbar.setString(current_user_hp + " / " + c_hp);
								playerMpbar.setString(current_user_mp + " / " + c_mp);
							}
						}

						System.out.println("[info] ĳ���� ���� ���� üũ..");
						System.out.println("[info] ���� ���� : " + buff);
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
							Thread.sleep(300);
							System.out.println("[info] �� ü�� ���� üũ..");
							MonsterHpbar.setValue(current_monster_hp);
							MonsterHpbar.setString(current_monster_hp + " / " + m_hp);
							// ������ 2 ����
							if (battle == true && bossphase2 == true) {
								System.out.println("[Info] ������ ������ 2 ������..");
								if (current_monster_hp >= 1200 && current_monster_hp <= 2400) { // ���� ü���� 60% ������ �� ��ų ���� 1ȸ
																								// ���
									if (!skillused) {
										useDragonSkill();
									}
								}

								if (current_monster_hp >= 1 && current_monster_hp <= 1199) { // ���� ü���� 30% ������ �� ��ų ���� 1ȸ ���
									if (skillused) {
										useDragonSkill();
										skillused = false;
									}
								}

								if (current_monster_hp <= 0) {
									bossfight = false;
									bossphase2 = false;
									battle = false;
									System.out.println("[Info] ������ ������ 2 ����, ���� ���");
									GameScreenimgLabel.setIcon(BATTLEBACKGROUND);
									MonsterPanel.setVisible(false);
									JLabel message = new JLabel("<html><body style='background-color:black;'>"
											+ "<p style='font-family:���� ���; font-size:13px; color:white; text-align:center;'>"
											+ "�巡���� �е����� ���� ���� �������⸦ �� ����...<br/>���� �پ���� ���� ������ ����  ��Ƴ��� �巡���� ���忡 ���� �ھƳ־���."
											+ "<br/>���� ������ ��ó ���̷� �˺��� �ǰ� �վ��� ���԰�,"
											+ "<br/>ö�˼� ���⸸ �ϴ� �Ŵ��� �巡���� ���� ������ ������ ���ȴ�."
											+ "<br/><br/>'ì�׶�!!!!!'<br/>����� �տ� �ٵ�� �ִ� ���� ������ �������� ���� �Ҹ��� ��ȴ�."
											+ "<br/>'����! ���������ʽÿ�!'<br/>�ڴʰ� ������ �������� �޷����� �������� ��縦 ����ȾҴ�."
											+ "<br/>���� ����� ���� �ǽ� ���̷� ����� �̼Ҹ� ������ �������� �߾�ŷȴ�.<br/>'���� �Ǿ���...'<br/>"
											+ "<br/>. . . . .<br/>. . .<br/>."
											+ "<br/><br/><br/><br/><br/><br/>�����մϴ�! �賭�ϱ⸸ �Ͽ��� ��� �� ������ ���� ���� ������."
											+ "<br/>��ħ�� ����� �巡���� ������� ������ ��ȭ�� ��ã�ҽ��ϴ�."
											+ "<br/><br/><font color=purple>�巡�� �����̾�</font>&nbsp;&nbsp;<font color=red>"
											+ c_name + "</font>���� ������ �������� �Ĵ뿡�� �θ� ���� ���Դϴ�!</p>"
											+ "<p style='margin-top:50px; margin-bottom:50px; font-family:���� ���; font-size:16px; color:blue; text-align:center;'>���ݱ��� �巡�� �����̾�� �Բ� ���ּż� �����մϴ�.</p></body></html>");

									UIManager.put("OptionPane.background", Color.BLACK);
									UIManager.put("Panel.background", Color.BLACK);

									JOptionPane.showMessageDialog(null, message, "Ending", JOptionPane.DEFAULT_OPTION,
											null);
									System.exit(0);
								}
							}
							if (battle) { // ���� �߻� ��
								// ���� ������ ����ġ & ������ ȹ��(���� ����)
								if (current_monster_hp <= 0 && battle == true) {
									monsterattackLabel.setIcon(null);
									playerbeingattackedLabel.setIcon(null);
									playerattackLabel.setIcon(null);
									if (bossfight) {
										System.out.println("[Info] ������ ������ 1 ����");
										writeLog("\n" + m_name + "(��/��) ��������.");
										bossphase2 = true;
										createBossMonster(1); // 2������ ����
									} else {
										writeLog(m_name + "(��/��) ��������.");
										writeLog("����ġ�� " + m_exp + " �ö����ϴ�.");
										if (c_lv == 30) {
											m_exp = 0; // ������ �� ����ġ ȹ�� X
										}
										c_exp += m_exp; // ���� ����ġ�� �� ����ġ�� ����(����ġ ȹ��)
										addInventory(dropitem); // ���� ����� ������ �κ��丮�� �߰�
										for (int i = 0; i < dropitem.length; i++) {
											writeLog(dropitem[i] + " (��/��) �����.");
										}
										battle = false; // ���� ����
										if (buff) { // ������ �ɷ��ִ� ��� ���� ���� �� ������ ���� �ؾ���
											buff = false; // ���� ���� ����(���� ����)
											if (c_lv != prevlv) { // ���� ������ �����ޱ��� ������ ���� ���� ���(��, ���� �ް� ������ �� ���)
												writeLog("\n�������Լ� ���� ������ �������.");
												c_str -= (prevlv * 5); // ���� ���� ��ġ��ŭ - ���༭ ���� �ɷ�ġ�� ���ư�
												c_dex -= (prevlv * 5); // ��ø ��ġ
												c_int -= (prevlv * 5); // ���� ��ġ
											}
											writeLog("\n�������Լ� ���� ������ �������.");
											c_str -= (c_lv * 5); // ���� ���� ��ġ��ŭ - ���༭ ���� �ɷ�ġ�� ���ư�
											c_dex -= (c_lv * 5); // ��ø ��ġ
											c_int -= (c_lv * 5); // ���� ��ġ
											System.out.println(
													"[info] ���� �ޱ� �� �ɷ�ġ : " + c_str + " / " + c_dex + " / " + c_int);
										}
										MonsterPanel.setVisible(false); // ���г� visible�� false
									}
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
	
	// ������ 2 ���� ��ų ��� �޼ҵ�
	void useDragonSkill() {
		skillused = true;
		GameScreenimgLabel.setOpaque(true);
		playerattackLabel.setIcon(null);
		monsterattackLabel.setIcon(null);

		DSAudio playdragon = DSAudio.getInstance();
		playdragon.playDragonSkill();
		GameScreenimgLabel.setBackground(Color.BLACK);
		MonsterPanel.setVisible(false);
		GameScreenimgLabel.setIcon(new ImageIcon(BOSSSKILLATTACK));
		CharacterPanel.setLocation(380, 40);
		writeLog("'" + m_name + "' �� ������ �극���� ����ߴ�.");
		current_user_hp -= 700;
		writeLog("'" + c_name + "' �� ü���� 700 �����ߴ�.");
		Timer resetbackground = new Timer();
		TimerTask bgresettask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CharacterPanel.setLocation(775, 78);
				GameScreenimgLabel.setIcon(new ImageIcon(BOSSORIGINAL));
				MonsterPanel.setVisible(true);
			}
		};
		resetbackground.schedule(bgresettask, 4800);
	}

	// SkillScreen���� ȣ��Ǵ� ��ų �޼ҵ��
	static void skill_DragonSlasher() {
		int requiredLv = 6;
		int requiredMp = 70;
		int skilldamage = 0;
		Timer removeEffectTimer;
		TimerTask removeEffectTask;
		try {
			if (battle) {
				if (c_lv < requiredLv) {
					message = new JLabel("<html><p style='font-family:���� ���;'>������ �����մϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "�巡�� ������", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (current_user_mp < requiredMp) {
						message = new JLabel("<html><p style='font-family:���� ���;'>������ �����մϴ�.</p></html>");
						JOptionPane.showMessageDialog(null, message, "�巡�� ������", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						DSAudio dragonsound = DSAudio.getInstance();
						dragonsound.playDragonSlasher();
						SkillEffectLabel1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
								.createImage("resource/images/effects/player/skill1_Dragon_Slasher_resize.gif")));
						skilldamage = (playeratk * 2) - monsterdef; // ��ų������ = ��ų���*2 - �� ����
						if (skilldamage <= 0) {
							skilldamage = 1; // �ּҵ������� ������ 1
							current_user_mp -= requiredMp;
							System.out.println("[info] ���� ���� : " + current_user_mp);
							current_monster_hp -= skilldamage;
						} else {
							int mindam = skilldamage / 2;
							int maxdam = skilldamage;
							System.out.println("�巡�� ������ �ּ� ������ : " + mindam + " �ִ� ������ : " + maxdam);
							if (mindam == 1 || maxdam == 1) {
								skilldamage = 1;
								current_user_mp -= requiredMp;
								System.out.println("[info] ���� ���� : " + current_user_mp);
								current_monster_hp -= skilldamage;
							} else {
								skilldamage = (int) (Math.random() * maxdam) + mindam; // �ּҵ����� ~ ��ų������ ���� ���� ������
								if (skilldamage > maxdam) {
									skilldamage = maxdam;
								}
								current_user_mp -= requiredMp;
								System.out.println("[info] ���� ���� : " + current_user_mp);
								current_monster_hp -= skilldamage;
							}
						}
			
					writeLog("\n��ų - �巡�� ������ ���\n" + m_name + " ���� ���ظ� " + skilldamage + " ������.");
					removeEffectTimer = new Timer();
					removeEffectTask = new TimerTask() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								SkillEffectLabel1.setIcon(null);
								attack_monster();
							}
						};
						removeEffectTimer.schedule(removeEffectTask, 1000);
					}
				}
			} else {
				message = new JLabel("<html><p style='font-family:���� ���;'>��ų ����� ���� �߿��� �����մϴ�.</p></html>");
				JOptionPane.showMessageDialog(null, message, "�巡�� ������", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static void skill_AuraBlade() {
		int requiredLv = 12;
		int requiredMp = 120;
		int skilldamage = 0;
		Timer removeEffectTimer;
		TimerTask removeEffectTask;
		try {
			if (battle) {
				if (c_lv < requiredLv) {
					message = new JLabel("<html><p style='font-family:���� ���;'>������ �����մϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "���� ���̵�", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (current_user_mp < requiredMp) {
						message = new JLabel("<html><p style='font-family:���� ���;'>������ �����մϴ�.</p></html>");
						JOptionPane.showMessageDialog(null, message, "���� ���̵�", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						DSAudio aurablade = DSAudio.getInstance();
						aurablade.playAuraBlade();
						SkillEffectLabel2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
								.createImage("resource/images/effects/player/skill2_Aura_Blade_resize.gif")));
						skilldamage = (playeratk * 3) - monsterdef; // ��ų������ = ��ų���*3 - �� ����
						if (skilldamage <= 0) {
							skilldamage = 1; // �ּҵ������� ������ 1
							current_user_mp -= requiredMp;
							System.out.println("[info] ���� ���� : " + current_user_mp);
							current_monster_hp -= skilldamage;
						} else {
							int mindam = skilldamage / 2;
							int maxdam = skilldamage;
							System.out.println("���� ���̵� �ּ� ������ : " + mindam + " �ִ� ������ : " + maxdam);
							if (mindam == 1 || maxdam == 1) {
								skilldamage = 1;
								current_user_mp -= requiredMp;
								System.out.println("[info] ���� ���� : " + current_user_mp);
								current_monster_hp -= skilldamage;
							} else {
								skilldamage = (int) (Math.random() * maxdam) + mindam; // �ּҵ����� ~ ��ų������ ���� ���� ������
								if (skilldamage > maxdam) {
									skilldamage = maxdam;
								}
								current_user_mp -= requiredMp;
								System.out.println("[info] ���� ���� : " + current_user_mp);
								current_monster_hp -= skilldamage;
							}
						}

					writeLog("\n��ų - ���� ���̵� ���\n" + m_name + " ���� ���ظ� " + skilldamage + " ������.");
					removeEffectTimer = new Timer();
					removeEffectTask = new TimerTask() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								SkillEffectLabel2.setIcon(null);
								attack_monster();
							}
						};
						removeEffectTimer.schedule(removeEffectTask, 1000);
					}
				}
			} else {
				message = new JLabel("<html><p style='font-family:���� ���;'>��ų ����� ���� �߿��� �����մϴ�.</p></html>");
				JOptionPane.showMessageDialog(null, message, "���� ���̵�", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	static void skill_DemonicSword() {
		int requiredLv = 18;
		int requiredMp = 170;
		int skilldamage = 0;
		Timer removeEffectTimer;
		TimerTask removeEffectTask;
		try {
			if (battle) {
				if (c_lv < requiredLv) {
					message = new JLabel("<html><p style='font-family:���� ���;'>������ �����մϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "����� �ҵ�", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (current_user_mp < requiredMp) {
						message = new JLabel("<html><p style='font-family:���� ���;'>������ �����մϴ�.</p></html>");
						JOptionPane.showMessageDialog(null, message, "����� �ҵ�", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						DSAudio demonic = DSAudio.getInstance();
						demonic.playDemonicSword();
						SkillEffectLabel3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
								.createImage("resource/images/effects/player/skill3_Demonic_Swords_resize.gif")));
						skilldamage = (playeratk * 4) - monsterdef; // ��ų������ = ��ų���*6 - �� ����
						if (skilldamage <= 0) {
							skilldamage = 1; // �ּҵ������� ������ 1
							current_user_mp -= requiredMp;
							System.out.println("[info] ���� ���� : " + current_user_mp);
							current_monster_hp -= skilldamage;
						} else {
							int mindam = skilldamage / 2;
							int maxdam = skilldamage;
							System.out.println("����� �ҵ� �ּ� ������ : " + mindam + " �ִ� ������ : " + maxdam);
							if (mindam == 1 || maxdam == 1) {
								skilldamage = 1;
								current_user_mp -= requiredMp;
								System.out.println("[info] ���� ���� : " + current_user_mp);
								current_monster_hp -= skilldamage;
							} else {
								skilldamage = (int) (Math.random() * maxdam) + mindam; // �ּҵ����� ~ ��ų������ ���� ���� ������
								if (skilldamage > maxdam) {
									skilldamage = maxdam;
								}
								current_user_mp -= requiredMp;
								System.out.println("[info] ���� ���� : " + current_user_mp);
								current_monster_hp -= skilldamage;
							}
						}

					writeLog("\n��ų - ����� �ҵ� ���\n" + m_name + " ���� ���ظ� " + skilldamage + " ������.");
					removeEffectTimer = new Timer();
					removeEffectTask = new TimerTask() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								SkillEffectLabel3.setIcon(null);
								attack_monster();
							}
						};
						removeEffectTimer.schedule(removeEffectTask, 1200);
					}
				}
			} else {
				message = new JLabel("<html><p style='font-family:���� ���;'>��ų ����� ���� �߿��� �����մϴ�.</p></html>");
				JOptionPane.showMessageDialog(null, message, "����� �ҵ�", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	static void skill_DimensionBreaker() {
		int requiredLv = 27;
		int requiredMp = 400;
		int skilldamage = 0;
		Timer removeEffectTimer;
		TimerTask removeEffectTask;
		try {
			if (battle) {
				if (c_lv < requiredLv) {
					message = new JLabel("<html><p style='font-family:���� ���;'>������ �����մϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "���� �극��Ŀ", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (current_user_mp < requiredMp) {
						message = new JLabel("<html><p style='font-family:���� ���;'>������ �����մϴ�.</p></html>");
						JOptionPane.showMessageDialog(null, message, "���� �극��Ŀ", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						showUltimateSkill();
						skilldamage = (playeratk * 8) - monsterdef; // ��ų������ = ��ų���*8 - �� ����
						if (skilldamage <= 0) {
							skilldamage = 1; // �ּҵ������� ������ 1
							current_user_mp -= requiredMp;
							System.out.println("[info] ���� ���� : " + current_user_mp);
							current_monster_hp -= skilldamage;
						} else {
							int mindam = skilldamage / 2;
							int maxdam = skilldamage;
							System.out.println("���� �극��Ŀ �ּ� ������ : " + mindam + " �ִ� ������ : " + maxdam);
							if (mindam == 1 || maxdam == 1) {
								skilldamage = 1;
								current_user_mp -= requiredMp;
								System.out.println("[info] ���� ���� : " + current_user_mp);
								current_monster_hp -= skilldamage;
							} else {
								skilldamage = (int) (Math.random() * maxdam) + mindam; // �ּҵ����� ~ ��ų������ ���� ���� ������
								if (skilldamage > maxdam) {
									skilldamage = maxdam;
								}
								current_user_mp -= requiredMp;
								System.out.println("[info] ���� ���� : " + current_user_mp);
								current_monster_hp -= skilldamage;
							}
						}
						writeLog("��ų - ���� �극��Ŀ ���\n" + m_name + " ���� ���ظ� " + skilldamage + " ������.");
						removeEffectTimer = new Timer();
						removeEffectTask = new TimerTask() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								attack_monster();
							}
						};
						removeEffectTimer.schedule(removeEffectTask, 7000);
					}

					writeLog("\n��ų - ���� �극��Ŀ ���\n" + m_name + " ���� ���ظ� " + skilldamage + " ������.");
					removeEffectTimer = new Timer();
					removeEffectTask = new TimerTask() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							attack_monster();
						}
					};
					removeEffectTimer.schedule(removeEffectTask, 7000);
				}
			} else {
				message = new JLabel("<html><p style='font-family:���� ���;'>��ų ����� ���� �߿��� �����մϴ�.</p></html>");
				JOptionPane.showMessageDialog(null, message, "���� �극��Ŀ", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// �ñر� ���� �޼ҵ�
	static void showUltimateSkill() {
		GameScreenimgLabel.setOpaque(true);
		GameScreenimgLabel.setBackground(Color.WHITE);
		CharacterPanel.setVisible(false);
		MonsterPanel.setLocation(350, 40);
		GameScreenimgLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage("resource/images/effects/player/skill4_Dimension_Breaker_main_resize.gif")));
		DSAudio ultimateskill = DSAudio.getInstance();
		ultimateskill.playDimensionBreaker();
		Timer changeImg1 = new Timer();
		Timer changeImg2 = new Timer();
		TimerTask changeImgTask2 = new TimerTask() { // �ñر� ���� �� �� ȭ������ ����
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CharacterPanel.setVisible(true);
				MonsterPanel.setLocation(60, 40);
				if (bossphase2) { // ������ 2������ ���� �� �� ���
					GameScreenimgLabel.setIcon(new ImageIcon(BOSSORIGINAL));
				} else {
					GameScreenimgLabel.setIcon(BATTLEBACKGROUND);
				}
			}
		};

		TimerTask changeImgTask1 = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameScreenimgLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
						.createImage("resource/images/effects/player/skill4_Dimension_Breaker_sub_resize.gif")));
				changeImg2.schedule(changeImgTask2, 2100); // 2.1�� �� ���� ȭ������ ���ư���

			}
		};
		changeImg1.schedule(changeImgTask1, 4200); // ù ��° �ñر� ��ų ����Ʈ ���� �� 4.2�� �� �ι�° ��ų ����Ʈ ����(ù ��° ��ų ����Ʈ ���̰� 4�ʶ�..)
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
		System.out.println("�� �̸� : " + m_name);
		System.out.println("�� ü�� : " + m_hp);
		System.out.println("�� ���ݷ� : " + monsteratk);
		System.out.println("�� ���� : " + monsterdef);
		System.out.println("�� ����ġ : " + m_exp);
		System.out.println("�� ��� : " + Arrays.toString(dropitem));
	}

	// �߱� �� �⺻ ���� ����
	void settingMiddleMobInfo(int switchnum) {
		m_hp = middlemonsters.get(switchnum).getM_hp(); // �� ü�� ����
		m_name = middlemonsters.get(switchnum).getM_name(); // �� �̸� ����
		monsteratk = middlemonsters.get(switchnum).getM_atk(); // �� ���ݷ� ����
		monsterdef = middlemonsters.get(switchnum).getM_def(); // �� ���� ����
		m_exp = middlemonsters.get(switchnum).getM_exp(); // �� ����ġ ����
		dropitem = middlemonsters.get(switchnum).getMobDrop(); // �� �����
		System.out.println("�� �̸� : " + m_name);
		System.out.println("�� ü�� : " + m_hp);
		System.out.println("�� ���ݷ� : " + monsteratk);
		System.out.println("�� ���� : " + monsterdef);
		System.out.println("�� ����ġ : " + m_exp);
		System.out.println("�� ��� : " + Arrays.toString(dropitem));
	}

	// ��� �� �⺻ ���� ����
	void settingHighMobInfo(int switchnum) {
		m_hp = highmonsters.get(switchnum).getM_hp(); // �� ü�� ����
		m_name = highmonsters.get(switchnum).getM_name(); // �� �̸� ����
		monsteratk = highmonsters.get(switchnum).getM_atk(); // �� ���ݷ� ����
		monsterdef = highmonsters.get(switchnum).getM_def(); // �� ���� ����
		m_exp = highmonsters.get(switchnum).getM_exp(); // �� ����ġ ����
		dropitem = highmonsters.get(switchnum).getMobDrop(); // �� �����
		System.out.println("�� �̸� : " + m_name);
		System.out.println("�� ü�� : " + m_hp);
		System.out.println("�� ���ݷ� : " + monsteratk);
		System.out.println("�� ���� : " + monsterdef);
		System.out.println("�� ����ġ : " + m_exp);
		System.out.println("�� ��� : " + Arrays.toString(dropitem));
	}

	// ���� �� �⺻ ���� ����
	void settingBossMobInfo(int switchnum) {
		m_hp = bossmonsters.get(switchnum).getM_hp(); // �� ü�� ����
		m_name = bossmonsters.get(switchnum).getM_name(); // �� �̸� ����
		monsteratk = bossmonsters.get(switchnum).getM_atk(); // �� ���ݷ� ����
		monsterdef = bossmonsters.get(switchnum).getM_def(); // �� ���� ����
		m_exp = bossmonsters.get(switchnum).getM_exp(); // �� ����ġ ����
		dropitem = bossmonsters.get(switchnum).getMobDrop(); // �� �����
		System.out.println("�� �̸� : " + m_name);
		System.out.println("�� ü�� : " + m_hp);
		System.out.println("�� ���ݷ� : " + monsteratk);
		System.out.println("�� ���� : " + monsterdef);
		System.out.println("�� ����ġ : " + m_exp);
		System.out.println("�� ��� : " + Arrays.toString(dropitem));
	}

	// �ʱ� �� ����
	public void createLowMonster(int switchnum) {
		MonsterPanel.setVisible(true);
		switch (switchnum) {
		case 0: // Skelwarrior
			monsterimgLabel.setIcon(new ImageIcon(SKEL));
			writeLog("\n" + lowmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp); // ���� �� ü������ �� ü�¹� �� ����
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp); // ���� �ȿ� ���̴� ���ڿ� ����
			MonsterHpbar.setVisible(true);
			break;
		case 1: // Orcwarrior
			monsterimgLabel.setIcon(new ImageIcon(ORC));
			writeLog("\n" + lowmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			break;
		case 2: // Golem
			monsterimgLabel.setIcon(new ImageIcon(GOLEM));
			writeLog("\n" + lowmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			break;
		}
	}

	// �߱� �� ����
	public void createMiddleMonster(int swtichnum) {
		MonsterPanel.setVisible(true);
		switch (swtichnum) {
		case 0: // SkelKing
			writeLog("\n" + middlemonsters.get(swtichnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(SKELKING));
			break;
		case 1: // Hatchling
			writeLog("\n" + middlemonsters.get(swtichnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(HATCHLING));
			break;
		case 2: // Lagiacrus
			writeLog("\n" + middlemonsters.get(swtichnum).getM_name() + " ��/�� ��Ÿ����.\n");
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
			writeLog("\n" + highmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(DRAKE));
			break;

		case 1: // Chimera
			writeLog("\n" + highmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(CHIMERA));
			break;

		case 2: // IceDragon
			writeLog("\n" + highmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
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

	// ���� �� ����
	public void createBossMonster(int switchnum) {
		MonsterPanel.setVisible(true);
		switch (switchnum) {
		case 0: // Boss Phase1 - Boss Polymorph mode
			writeLog("\n" + bossmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			settingBossMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(BOSSPOLYMORPH));
			break;

		case 1: // Boss Phase2 - Boss Original mode
			System.out.println("[info] ���� �� - Phase 2 ����");
			writeLog("\n �г밡 �ؿ� ���� �巡���� �� ����� �巯�������ϴ�.\n�ε� �����Ͻñ� �ٶ��ϴ�!");
			writeLog(bossmonsters.get(switchnum).getM_name() + " ��/�� ��Ÿ����.\n");
			DSAudio roar = DSAudio.getInstance();
			roar.playDragonRoar();
			settingBossMobInfo(switchnum);
			current_monster_hp = m_hp; // ���� �� ü�¿� ���� ������ �� ü�� ����(����)
			MonsterHpbar.setMaximum(m_hp); // ü�¹��� �ִ��ġ�� �� ü������ ����
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(null);
			CharacterPanel.setLocation(775, 78);
			playerHpbar.setLocation(10, 60);
			playerMpbar.setLocation(10, 75);
			skilleffectpanel.setLocation(300, 70);
			characterLabel.setIcon(new ImageIcon(PLAYERKNIGHT_BOSS));
			GameScreenimgLabel.setIcon(new ImageIcon(BOSSORIGINAL));
			break;
		}
	}
}