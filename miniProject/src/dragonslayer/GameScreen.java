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
	/** 이미지 영역 **/
	// 게임아이콘
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit()
			.createImage("resource/images/title/titleicon.png");

	// 게임배경화면(사각형 테두리)
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit()
			.createImage("resource/images/background/GameScreen/MainBackgroundborder.png");

	// 전투배경
	private final static ImageIcon BATTLEBACKGROUND = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/battlebackground.png"));

	// 함정배경
	private final static ImageIcon FIRETRAP = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/fire_trap.png"));
	private final static ImageIcon SPIKETRAP = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/spike_trap.png"));
	private final static ImageIcon WELLTRAP = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/well_trap.png"));

	// 이벤트 배경
	private final static Image EVENTBACKGROUND1 = Toolkit.getDefaultToolkit()
			.createImage("resource/images/background/Event1_resize.png");
	private final static Image EVENTBACKGROUND2 = null;
	private final static Image EVENTBACKGROUND3 = null;

	// 플레이어 이미지(모험가)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit()
			.createImage("resource/images/player/playercharacter_beginner.png");
	private final static Image PLAYERWARRIOR = null;
	private final static Image PLAYERKNIGHT = null;

	// 몹 이미지들(초급)
	private final static Image SKEL = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/low_grade/1_skel_warrior_resize.png");
	private final static Image ORC = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/low_grade/2_orc_warrior_resize.png");
	private final static Image GOLEM = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/low_grade/3_golem_resize.png");

	// 몹 이미지들(중급)
	private final static Image SKELKING = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/middle_grade/1_skel_king_resize.png");
	private final static Image HATCHLING = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/middle_grade/2_Hatchling_resize.png");
	private final static Image LAGIA = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/middle_grade/3_Lagiacrus_resize.png");

	// 몹 이미지들(고급)
	private final static Image DRAKE = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/high_grade/1_Drake_resize.png");
	private final static Image CHIMERA = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/high_grade/2_Chimera_resize.png");
	private final static Image ICEDRAGON = Toolkit.getDefaultToolkit()
			.createImage("resource/images/monsters/high_grade/3_Ice_dragon_resize.png");

	// 버튼 패널 & 로그 패널 배경(테두리)
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit()
			.createImage("resource/images/background/LogPanelBorder.png");
	private final static Image BUTTONBACKGROUND = Toolkit.getDefaultToolkit()
			.createImage("resource/images/background/GameScreen/ButtonPanelBorder.png");

	// 버튼 이미지들(탐색,공격,가방....)
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

	// 스킬 이펙트 이미지들
	private final static Image PLAYERBASICATTACK = Toolkit.getDefaultToolkit()
			.createImage("resource/images/effects/player/player_basic_attack.gif");
	private final static Image MONSTERATTACK = Toolkit.getDefaultToolkit()
			.createImage("resource/images/effects/monster/monster_attack_resized.gif");
	private final static Image BEINGATTACKED = Toolkit.getDefaultToolkit()
			.createImage("resource/images/effects/both sides/Being attacked_resized.gif");

	/** 필드 영역 **/
	private static String c_name, m_name, c_job; // 캐릭터명 & 몬스터이름
	// 캐릭터 스탯 관련 정보 (스탯창 열었을때 보여줌) 레벨, 힘, 민첩, 지능, 체력, 마나, 경험치, 다음 경험치
	private static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp;
	private int current_monster_hp, m_hp, m_exp; // 몹 체력 & 몹 최대체력 & 몹이 주는 경험치
	private static Boolean battle = false; // 전투 발생을 알려주는 변수. 전투 발생 시 true로 전환(기본값 false)
	private Boolean buff = false; // 플레이어 버프 상황(걸려있는지 아닌지)
	private Thread p_check, m_check; // 플레이어, 몹 상태 확인 Thread
	private LinkedList<DSMonsters> lowmonsters = null; // 초급몹정보가 저장돼있는 LinkedList
	private LinkedList<DSMonsters> middlemonsters = null; // 중급몹정보가 저장돼있는 LinkedList
	private LinkedList<DSMonsters> highmonsters = null; // 고급몹정보가 저장돼있는 LinkedList
	private LinkedList<DSItems> iteminfo; // 게임 내 아이템 정보가 담겨 있는 iteminfo
	private ArrayList<String> Rewards = new ArrayList<String>(); // 평범해보이는 상자
	private ArrayList<String> GoodRewards = new ArrayList<String>(); // 괜찮아보이는 상자
	private ArrayList<String> VeryGoodRewards = new ArrayList<String>(); // 화려해보이는 상자
	private HashMap<Integer, Integer> exptable; // 게임 내 경험치 정보가 담겨 있는 exptable;
	private String[] dropitem; // 몹이 드랍하는 아이템이 저장되어있는 String 배열
	private int playeratk, playerdef; // 플레이어 공격력, 방어력
	private int equipdef; // 방어구의 총 방어력
	private int monsteratk, monsterdef; // 몬스터 공격력, 방어력
	private DSService service = new DSService();

	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel,GameScreenimgLabel,monsterimgLabel; // 이미지 라벨들
	private static JLabel playerattackLabel, monsterattackLabel, playerbeingattackedLabel, monsterbeingattackedLabel;
	private static JPanel CharacterPanel, MonsterPanel; // 캐릭터 이미지가 출력되는 패널, 몹 이미지가 출력되는 패널
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	private static JProgressBar playerHpbar, playerMpbar, MonsterHpbar; // 플레이어 체력막대,마나막대, 몹 체력막대

	private static String helmet, armor, glove, boots, weapon; // 캐릭터가 착용하고 있는 아이템명(장비 창으로 넘길 값들)
	private static int def_helmet, def_armor, def_glove, def_boots, atk_weapon; // 캐릭터가 착용하고 있는 아이템의 공&방
	private static LinkedList<DSItems> inven = new LinkedList<DSItems>(); // 플레이어 인벤토리 내용물
	private static int current_user_hp, current_user_mp; // 현재 플레이어 체력 & 마나
	private int tempstr, tempdex, tempint; // 버프 적용받기 전 스텟값 저장(버프 끝나면 원상태로 복구해야하기 때문에)

	public static void main(String[] args) {
		new GameScreen("춘식이",1,"모험가",10,10,10,100,80);
	}

	/** 메소드 영역 **/
	public GameScreen(String name, int l, String job, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() 호출");
		this.c_name = name; // 캐릭터명
		this.c_job = job; // 직업
		this.c_lv = l; // 레벨
		this.c_str = s; // 힘
		this.playeratk = (c_str / 2) + atk_weapon; // 캐릭터 공격력은 (힘/2)+장비공격력
		this.c_dex = d; // 민첩
		this.playerdef = (c_dex / 5) + equipdef; // 캐릭터 방어력은 (민첩/5)+장비방어력
		this.c_int = i; // 지능

		this.c_hp = hp; // 체력
		GameScreen.current_user_hp = c_hp; // 플레이어 현재 체력
		this.c_mp = mp + (c_int * 2); // 마나는 기본 마나값 + 지능*2
		GameScreen.current_user_mp = c_mp; // 플레이어 현재 마나

		this.c_exp = 0; // 초기 경험치 보유량 0
		this.c_next_exp = 50; // 다음 경험치 요구량 50
		
		// 장비 기본값은 "없음"
		weapon = "없음";
		helmet = "없음";
		armor = "없음";
		glove = "없음";
		boots = "없음";
		
		lowmonsters = service.monsterData("초급"); // 초급 몹 정보 저장
		middlemonsters = service.monsterData("중급"); // 중급 몹 정보 저장
		highmonsters = service.monsterData("고급"); // 고급 몹 정보 저장
		iteminfo = service.itemData(); // 아이템 정보 저장
		exptable = service.expData(); // 경험치 정보 저장(Key - 레벨 / Value - 다음 경험치)
		System.out.println("아이템 정보 : " + iteminfo.size());
		System.out.println("[info] GameScreen() 필드 초기화 완료.");
		createGameScreen();
		checkplayerstatus();
		checkmonsterstatus();
	}

	// 화면 생성 메소드
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

		// 레이어 설정
		JLayeredPane layer = getLayeredPane();

		// 게임화면 테두리
		mainbackgroundimgLabel = new JLabel(new ImageIcon(MAINBACKGROUND));
		mainbackgroundimgLabel.setBounds(5, 5, 1020, 638);

		// 게임진행 이미지 라벨
		GameScreenimgLabel = new JLabel(BATTLEBACKGROUND);
		GameScreenimgLabel.setBounds(40, 35, 950, 330);
		GameScreenimgLabel.setBorder(new LineBorder(Color.WHITE));

		// 캐릭터 이미지 출력되는 패널(체력/마나 막대 + 캐릭터 이미지)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(650, 60, 200, 300);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		CharacterPanel.setOpaque(false);

		// 캐릭터 이미지 출력하는 Label
		JLabel characterLabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
		characterLabel.setBounds(30, 70, 150, 226);

		UIManager.put("ProgressBar.selectionBackground", Color.BLACK); // bar가 채워지기 전 글자 색
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK); // bar가 채워진 후 글자 색

		playerHpbar = new JProgressBar(0, c_hp); // 플레이어 캐릭터 체력바
		playerHpbar.setBorderPainted(false);
		playerHpbar.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		playerHpbar.setBackground(Color.WHITE);
		playerHpbar.setForeground(Color.RED);
		playerHpbar.setValue(current_user_hp); // 값은 현재 플레이어 체력
		playerHpbar.setBounds(10, 10, 180, 15);
		playerHpbar.setStringPainted(true);
		playerHpbar.setString(current_user_hp + " / " + c_hp); // JProgressBar 안에 문자열 값 지정(현재 체력 / 총 체력)

		playerMpbar = new JProgressBar(0, c_mp); // 플레이어 캐릭터 마나바
		playerMpbar.setBorderPainted(false);
		playerMpbar.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		playerMpbar.setBackground(Color.WHITE);
		playerMpbar.setForeground(Color.BLUE);
		playerMpbar.setValue(current_user_mp); // 값은 현재 플레이어 마나
		playerMpbar.setBounds(10, 25, 180, 15);
		playerMpbar.setStringPainted(true);
		playerMpbar.setString(current_user_mp + " / " + c_mp); // JProgressBar 안에 문자열 값 지정

		CharacterPanel.add(playerHpbar);
		CharacterPanel.add(playerMpbar);
		CharacterPanel.add(characterLabel); // 캐릭터 패널에 캐릭터 이미지 추가

		// 몹 이미지 출력 패널(몹 체력 막대 + 몹 이미지)
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
		MonsterHpbar.setMinimum(0); // JProgressBar 최소값 0
		MonsterHpbar.setStringPainted(true);
		MonsterHpbar.setVisible(false); // setVisible 기본값은 false, 나중에 몹이 생성되면 그때 다시 true

		MonsterPanel.add(MonsterHpbar);
		MonsterPanel.add(monsterimgLabel);

		// 로그(log)가 출력되는 패널
		BackgroundImagePanel LogPanel = new BackgroundImagePanel(LOGBACKGROUND);
		LogPanel.setBounds(50, 370, 450, 250);
		LogPanel.setOpaque(false);

		logarea = new JTextArea();
		logarea.setEditable(false);
		logarea.setForeground(Color.WHITE);
		logarea.setBackground(Color.BLACK);
		logarea.setText("\n게임 시작");
		logarea.setText("모험이 시작되었습니다!\n사악한 드래곤을 무찌르고 최강의 드래곤 슬레이어가 되십시오.\n");
		logscroll = new JScrollPane(logarea);
		logscroll.setBorder(new LineBorder(Color.BLACK));
		logscroll.setBounds(25, 20, 400, 210);
		LogPanel.add(logscroll);

		// 버튼들이 출력되는 패널
		BackgroundImagePanel ButtonPanel = new BackgroundImagePanel(BUTTONBACKGROUND);
		ButtonPanel.setBounds(520, 400, 440, 190);
		ButtonPanel.setOpaque(false);

		// 탐색 버튼을 눌렸을 때
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
			// 탐색 버튼을 눌렸을 시 3가지의 이벤트 중 무작위로 하나가 발생됨(전투,습득,특수이벤트)
			public void actionPerformed(ActionEvent e) {
				// battle이 true인 경우(전투 발생) 탐색불가. 전투 종료 후 탐색 재개 가능
				if (battle) {
					JLabel message = new JLabel(
							"<html><p style='font-size:14pt; font-family:맑은 고딕;'>전투 중에는 탐색이 불가능합니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "탐색", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				// 1 ~ 3 분기 발생
				int switchnum = createRandom();
				switch (switchnum) {
				case 1: // 전투 발생
					battle = true; // 전투 발생 시 true로 전환, 해당 변수는 전투가 종료되면 다시 false로 바뀜
					System.out.println("[info] 전투 발생");
					createBattle(c_lv); // 플레이어 레벨에 따라서 생성되는 몹의 구간(?)이 달라짐. 1 ~ 10, 11 ~ 20, 21 ~ 30
					break;
				case 2:
					System.out.println("[info] 상자 획득 이벤트 발생");
					// 1 ~ 10 상자 이벤트 (1 ~ 6 일반 상자, 7 ~ 9 괜찮아 보이는 상자, 10 화려한 상자)
					int chestevent = (int) (Math.random() * 10) + 1;
					System.out.println("[info] 발생한 상자 획득 이벤트 : " + chestevent);
					createGetItemEvent(chestevent);
					break;
				case 3:
					System.out.println("[info] 특수 이벤트 발생(버프/함정)");
					int subevent = (int) (Math.random() * 2) + 1; // 1 or 2 랜덤 숫자 (1 : 버프 / 2 : 함정)
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
							"<html><p style='font-size:14pt; font-family:맑은 고딕;'>몬스터가 존재하지 않습니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "공격", JOptionPane.WARNING_MESSAGE, null);
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
				// 중복 클릭을 통한 여러 창 띄우는걸 방지하기 위해 해당 버튼을 클릭하면 버튼 비활성화.
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
				// 중복 클릭을 통한 여러 창 띄우는걸 방지하기 위해 해당 버튼을 클릭하면 버튼 비활성화.
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
				// JOptionPane 버튼 글자 스타일 설정
				UIManager.put("OptionPane.buttonFont", new Font("맑은 고딕", Font.PLAIN, 14));
				JLabel message = new JLabel(
						"<html><p style='font-size:14pt; font-family:맑은 고딕;'>정말 가실꺼에요?<br/>아직 용사님의 도움을 필요로 하는 곳이 많아요!!!</p></html>");
				int a = JOptionPane.showOptionDialog(null, message, "게임종료", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "미안하지만, 지금은 좀 바빠.", "내 도움이 필요한 곳이 어디야??" },
						null);
				if (a == 0) { // 확인 버튼
					System.exit(1);
				} else { // 취소버튼
					return;
				}
			}
		});
		ButtonPanel.add(buttonexit);

		JLabel logoLabel = new JLabel(new ImageIcon(LOGO));
		logoLabel.setBounds(320, 90, 105, 97);
		logoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				// 로고 이미지 누르면 전투 회피 가능.(
				if (battle) {
					battle = false;
				} else {
					JOptionPane.showMessageDialog(null, "전투 중에만 쓸 수 있는 치트", "치트", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
			}
		});

		ButtonPanel.add(logoLabel);

		JPanel skilleffectpanel = new JPanel(null);
		skilleffectpanel.setBounds(150, 70, 670, 280);
		skilleffectpanel.setOpaque(false);
		skilleffectpanel.setBorder(new LineBorder(Color.PINK));

		// 플레이어 기본 공격 이팩트 라벨
		playerattackLabel = new JLabel();
		playerattackLabel.setBounds(150, 0, 380, 280);

		// 몬스터 공격 이팩트 라벨
		monsterattackLabel = new JLabel();
		monsterattackLabel.setBounds(320, 30, 201, 253);

		// 플레이어 피격 이팩트 라벨
		playerbeingattackedLabel = new JLabel();
		playerbeingattackedLabel.setBounds(425, 30, 250, 250);

		// 몬스터 피격 이팩트 라벨
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

	// 1 ~ 3 랜덤 숫자 발생시키는 메소드
	int createRandom() {
		int random = (int) (Math.random() * 3) + 1; // 1 ~ 3 랜덤 숫자 생성
		return random;
	}

	// 로그 작성
	static void writeLog(String text) {
		logarea.append(text + "\n");
		logarea.moveCaretPosition(logarea.getText().length());
	}

	// 가방 버튼 리턴(InventoryScreen 에서 사용)
	public static JButton getInvenbutton() {
		return buttoninven;
	}

	// 장비 버튼 리턴(EquipmentScreen 에서 사용)
	public static JButton getEquipbutton() {
		return buttonequip;
	}

	// 스킬 버튼 리턴(SkillScreen 에서 사용)
	public static JButton getSkillbutton() {
		return buttonskill;
	}

	// 스텟 버튼 리턴(StatScreen 에서 사용)
	public static JButton getStatbutton() {
		return buttonstat;
	}

	// 전투 생성하는 메소드
	public void createBattle(int level) {
		if(buff) {
			System.out.println("[info] 버프 걸려있음");
		}
		int switchnum = createRandom() - 1; // 0 ~ 2 랜덤
		if (level >= 1 && level <= 10) { // 1 ~ 10 레벨은 초급 몹
			createLowMonster(switchnum);
		} else if (level >= 11 && level <= 20) { // 11 ~ 20 레벨은 중급 몹
			createMiddleMonster(switchnum);
		} else { // 둘 다 아니면 21 ~ 부터니까 고급 몹 생성
			createHighMonster(switchnum);
		}
	}

	// 아이템 획득 이벤트(상자 이벤트)
	public void createGetItemEvent(int event) {
		if (event >= 1 && event <= 6) { // 1 ~ 6 : 일반 상자
			writeLog("평범해 보이는 상자를 발견하고 상자를 열었다.");

			// '하급' 키워드가 들어간 아이템 명을 담을 문자열 리스트
			for (int i = 0; i < iteminfo.size(); i++) {
				if (iteminfo.get(i).getI_name().contains("하급")) {
					Rewards.add(iteminfo.get(i).getI_name());
				}
			}
			// 0 ~ 4 까지 중복 없이 숫자 2개 뽑기
			int[] ran = makeRanNums();

			// 뽑은 2개의 숫자가 보상 인덱스
			String[] low = new String[2];
			for (int i = 0; i < 2; i++) {
				low[i] = Rewards.get(ran[i]);
			}
			addInventory(low); // 인벤토리에 추가

		} else if (event >= 7 && event < 10) { // 7 ~ 9 : 괜찮아 보이는 상자
			writeLog("괜찮아 보이는 상자를 발견하고 상자를 열었다.");

			// '중급' 키워드가 들어간 아이템 명을 담을 문자열 리스트
			for (int i = 0; i < iteminfo.size(); i++) {
				if (iteminfo.get(i).getI_name().contains("중급")) {
					GoodRewards.add(iteminfo.get(i).getI_name());
				}
			}
			// 0 ~ 4 까지 중복 없이 숫자 2개 뽑기
			int[] ran = makeRanNums();

			// 뽑은 2개의 숫자가 보상 인덱스
			String[] good = new String[2];
			for (int i = 0; i < 2; i++) {
				good[i] = GoodRewards.get(ran[i]);
			}
			addInventory(good); // 인벤토리에 추가

		} else { // 10 : 화려해 보이는 상자
			writeLog("화려해 보이는 상자를 발견하고 상자를 열었다.");

			// '중급' 키워드가 들어간 아이템 명을 담을 문자열 리스트
			for (int i = 0; i < iteminfo.size(); i++) {
				if (iteminfo.get(i).getI_name().contains("고오급")) {
					VeryGoodRewards.add(iteminfo.get(i).getI_name());
				}
			}
			// 0 ~ 4 까지 중복 없이 숫자 2개 뽑기
			int[] ran = makeRanNums();

			// 뽑은 2개의 숫자가 보상 인덱스
			String[] VeryGood = new String[2];
			for (int i = 0; i < 2; i++) {
				VeryGood[i] = VeryGoodRewards.get(ran[i]);
			}
			addInventory(VeryGood); // 인벤토리에 추가
		}
	}

	// 버프,함정 이벤트
	public void createSubEvent(int event) {
		switch (event) {
		case 1: // 1은 버프
			System.out.println("[info] 버프 전 능력치 : "+"힘 "+c_str+" / "+" 민첩 "+c_dex+" / "+" 지능 "+c_int);
			if(buff) { // 버프 상태가 true 일때 해당 이벤트가 실행되면 중복 버프를 받음. 중복 버프 방지함
				System.out.println("[info] 중복 버프 발생");
				return;
			}
			System.out.println("[info] 버프 이벤트 발생");
			buff = true;
			writeLog("요정의 축복을 받아 능력치가 일시적으로 향상되었습니다.\n(해당 버프는 다음 첫 전투에만 적용됩니다.)");
			// 능력치 버프는 기존 능력치 + 현재 캐릭터 레벨*5 만큼 증가시킴
			c_str+=(c_lv*5);
			c_dex+=(c_lv*5);
			c_int+=(c_lv*5);
			System.out.println("[info] 버프 후 능력치 : 힘 "+c_str+" / "+" 민첩 "+c_dex+" / "+" 지능 "+c_int);
			break;
		case 2: // 2는 함정
			System.out.println("[info] 함정 이벤트 발생");
			int randomtrap = (int) (Math.random() * 3) + 1; // 1 ~ 3 랜덤 트랩 발생
			if (randomtrap == 1) { // 1은 불 함정
				writeLog("벽에서 불이 뿜어져나왔다.");
				current_user_hp -= (c_lv * 2); // 캐릭터 레벨 * 2 수치 만큼 체력 깎임
				setBackgroundimg(FIRETRAP, BATTLEBACKGROUND);
			} else if (randomtrap == 2) { // 2는 가시(바닥) 함정
				writeLog("바닥의 가시를 밟았다.");
				current_user_hp -= (c_lv * 3); // 캐릭터 레벨 * 3 수치 만큼 체력 깎임
				setBackgroundimg(SPIKETRAP, BATTLEBACKGROUND);
			} else { // 3은 우물 함정
				writeLog("우물안의 물을 마셨다");
				current_user_hp -= (c_lv * 4); // 캐릭터 레벨 * 4 수치 만큼 체력 깎임
				setBackgroundimg(WELLTRAP, BATTLEBACKGROUND);
			}
			break;
		}
	}

	// 버프,함정 이벤트 배경화면 재설정 하는 메소드
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

	// 0 ~ 4 랜덤 만드는 메소드(상자 이벤트에서 사용)
	int[] makeRanNums() {
		int[] ran = new int[2];
		for (int i = 0; i < 2; i++) {
			ran[i] = (int) (Math.random() * 5); // 0 ~ 4 랜덤
			for (int j = 0; j < i; j++) {
				if (ran[i] == ran[j]) {
					i--;
				}
			}
		}
		return ran;
	}

	// 플레이어 공격(평타)
	public void attack_player() {
		if (!battle) {
			System.out.println("[info] 전투 중이 아닙니다.");
			return;
		}
		writeLog("'" + c_name + "' 의 공격!\n");

		playerattackLabel.setIcon(new ImageIcon(PLAYERBASICATTACK)); // 플레이어 기본공격 이펙트 출력
		monsterbeingattackedLabel.setIcon(new ImageIcon(BEINGATTACKED)); // 피격 이팩트 출력

		Timer pAttacktimer = new Timer();
		TimerTask pAttackTask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				playerattackLabel.setIcon(null); // 플레이어 기본공격 이펙트 출력
				monsterbeingattackedLabel.setIcon(null); // 피격 이팩트 출력
			}
		};
		pAttacktimer.schedule(pAttackTask, 900);

		int damage = playeratk - monsterdef; // 데미지는 플레이어 공격력 - 몬스터 방어력
		if (damage <= 0) { // 플레이어 공격력 - 몬스터 방어력의 결과가 0보다 작거나 같을 경우 (= 몬스터의 방어력이 플레이어 공격력보다 높을 경우)
			damage = 1; // 최소 데미지 1이 적용되도록 설정함.
			current_monster_hp -= damage; // damage 수치만큼 몬스터 현재 체력 감소
			writeLog("'" + c_name + "' (은/는) " + m_name + " 에게 " + damage + " 의 피해를 입혔다!");
		} else {
			int randomdamage = (int) (Math.random() * damage) + 1; // 1 ~ 플레이어 데미지 사이의 랜덤데미지 결정
			current_monster_hp -= randomdamage; // randomdamage 수치만큼 몹 현재 체력 감소
			writeLog("'" + c_name + "' (은/는) " + m_name + " 에게 " + randomdamage + " 의 피해를 입혔다!");
		}
	}

	// 몬스터 공격(평타)
	public void attack_monster() {
		if (!battle) {
			System.out.println("[info] 전투 중이 아닙니다.");
			return;
		}
		writeLog("'" + m_name + "' 의 공격!\n");
		monsterattackLabel.setIcon(new ImageIcon(MONSTERATTACK)); // 몬스터 공격 이펙트 출력
		playerbeingattackedLabel.setIcon(new ImageIcon(BEINGATTACKED)); // 피격 이팩트 출력
		
		int damage = monsteratk - playerdef; // 데미지는 몬스터 공격력 - 플레이어 방어력
		if (damage <= 0) { // 몬스터 공격력 - 플레이어 방어력의 결과가 0보다 작거나 같을 경우 (= 플레이어의 방어력이 몬스터의 공격력보다 높을 경우)
			damage = 1;
			writeLog("'" + m_name + "' (은/는) " + c_name + " 에게 " + damage + " 의 피해를 입혔다!");
			current_user_hp -= damage; // damage 수치만큼 플레이어 현재 체력 감소
		} else {
			int randomdamage = (int) (Math.random() * damage) + 1; // 1 ~ 몬스터 데미지 사이의 랜덤데미지 결정
			writeLog("'" + m_name + "' (은/는) " + c_name + " 에게 " + randomdamage + " 의 피해를 입혔다!");
			current_user_hp -= randomdamage; // randomdamage 수치만큼 플레이어 현재 체력 감
		}
		Timer mAttackEnd = new Timer();
		TimerTask mAttackEndTask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				monsterattackLabel.setIcon(null); // 몬스터 공격 이펙트 출력
				playerbeingattackedLabel.setIcon(null); // 피격 이팩트 출력
			}
		};
		mAttackEnd.schedule(mAttackEndTask, Calendar.getInstance().get(Calendar.MILLISECOND)+500);
	}

	// 플레이어 상태 확인 Thread 실행 메소드
	public void checkplayerstatus() {
		p_check = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("[info] p_check 쓰레드 실행");

				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(4000);
						System.out.println("[info] 캐릭터 체력 상태 체크..");
						System.out.println("[info] 현재 캐릭터 체력 : " + current_user_hp);
						playerHpbar.setValue(current_user_hp);
						playerHpbar.setString(current_user_hp + " / " + c_hp);
						
						if (current_user_hp <= 0) {
							Thread.currentThread().interrupt();
						}
						
						System.out.println("[info] 캐릭터 마나 상태 체크..");
						System.out.println("[info] 현재 캐릭터 마나 : " + current_user_mp);
						playerMpbar.setValue(current_user_mp);
						playerMpbar.setString(current_user_mp + " / " + c_mp);

						System.out.println("[info] 캐릭터 공격력&방어력 상태 체크..");
						playeratk = (c_str / 2) + atk_weapon; // 플레이어 총 공격력(기본공격력 + 무기공격력)
						equipdef = def_helmet + def_armor + def_glove + def_boots; // 투구,갑옷,장갑,신발 방어력 합계
						playerdef = (c_dex / 5) + equipdef; // 플레이어 총 방어력(기본방어력 + 방어구 총방어력)

						System.out.println("[info] 캐릭터 버프 상태 체크..");
						System.out.println("[info] 버프 상태 : "+buff);
					} catch (Exception e) {
						System.out.println("[Error] p_check 쓰레드 에러");
					}
				}
			}
		});
		p_check.start();
	}

	// 몹 상태 확인 Thread 실행 메소드
	public void checkmonsterstatus() {
		m_check = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("[info] m_check 쓰레드 실행");
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(4000);
						System.out.println("[info] 몹 체력 상태 체크..");
						MonsterHpbar.setValue(current_monster_hp);
						MonsterHpbar.setString(current_monster_hp + " / " + m_hp);
						if (battle) { // 전투 발생 시
							// 몹이 죽으면 경험치 & 아이템 획득(전투 종료)
							if (current_monster_hp <= 0) {
								writeLog(m_name + "(이/가) 쓰러졌다.");
								writeLog("경험치가 " + m_exp + " 올랐습니다.");
								c_exp += m_exp; // 현재 경험치에 몹 경험치를 더함(경험치 획득)
								if(buff) { // 버프가 걸려있는 경우 전투 종료 후 버프를 해제 해야함
									buff = false; // 버프 상태 해제(전투 종료)
									writeLog("요정에게서 받은 버프가 사라졌다.");
									c_str -= (c_lv*5); // 버프 받은 수치만큼 - 해줘서 원래 능력치로 돌아감
									c_dex -= (c_lv*5); // 민첩 수치
									c_int -= (c_lv*5); // 지능 수치
									System.out.println("[info] 버프 받기 전 능력치 : "+c_str+" / "+c_dex+" / "+c_int);
								}
								battle = false; // 전투 종료
								MonsterPanel.setVisible(false); // 몹패널 visible을 false
								playerattackLabel.setIcon(null);
								GameScreenimgLabel.setIcon(BATTLEBACKGROUND);
							}
						}
					} catch (Exception e) {
						System.out.println("[Error] m_check 쓰레드 에러");
					}
				}
			}
		});
		m_check.start();
	}

	// 인벤토리에 아이템 추가하는 메소드
	void addInventory(String[] getitem) {
		try {
			for (int i = 0; i < iteminfo.size(); i++) {
				for (int j = 0; j < getitem.length; j++) {
					if (getitem[j].equals(iteminfo.get(i).getI_name())) {
						inven.add(iteminfo.get(i));
					}
				}
			}
			System.out.println("[info] 현재 인벤토리 사이즈 : " + inven.size());
			for (int i = 0; i < inven.size(); i++) {
				System.out.println("[info] 현재 인벤토리에 들어있는 아이템 : " + inven.get(i).getI_name());
			}
		} catch (Exception e) {
			System.out.println("[Error] 예외 발생");
			e.printStackTrace();
		}
	}

	// InventoryScreen에서 인벤토리 최신화를 위해 사용하는 메소드.
	public static void setInventorydata(LinkedList<DSItems> data) {
		inven = data;
	}

	// InventoryScreen에서 포션을 사용하고 난 후의 체력세팅
	public static void setPlayerhp(int hp) {
		current_user_hp = hp;
	}

	// InventoryScreen에서 포션을 사용하고 난 후의 마나세팅
	public static void setPlayermp(int mp) {
		current_user_mp = mp;
	}

	// InventoryScreen에서 비약을 사용하고 난 후의 캐릭터 스텟 세팅
	public static void setPlayerStr(int up) { // 힘의 비약
		c_str += up; // c_str = c_str+up : up은 증가수치
	}

	public static void setPlayerDex(int up) { // 민첩의 비약
		c_dex += up;
	}

	public static void setPlayerInt(int up) { // 지능의 비약
		c_int += up;
	}

	public static void setPlayerAllstat(int up) { // 능력향상(올스텟)의 비약
		c_str += up;
		c_dex += up;
		c_int += up;
	}

	// InventoryScreen에서 경험의 돌을 사용하고 난 후의 캐릭터 경험치 세팅
	public static void setPlayerExp(int up) {
		c_exp += up;
	}

	// InventoryScreen에서 캐릭터의 장비 명(무기,투구,갑옷,장갑,신발)을 set함
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

	// InventoryScreen에서 넘어온 값들을 현재 GameScreen에 set함
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

	// 초급 몹 기본 정보 저장
	void settingLowMobInfo(int switchnum) {
		m_hp = lowmonsters.get(switchnum).getM_hp(); // 몹 체력 저장
		m_name = lowmonsters.get(switchnum).getM_name(); // 몹 이름 저장
		monsteratk = lowmonsters.get(switchnum).getM_atk(); // 몹 공격력 저장
		monsterdef = lowmonsters.get(switchnum).getM_def(); // 몹 방어력 저장
		m_exp = lowmonsters.get(switchnum).getM_exp(); // 몹 경험치 저장
		dropitem = lowmonsters.get(switchnum).getMobDrop(); // 몹 드랍템
	}

	// 중급 몹 기본 정보 저장
	void settingMiddleMobInfo(int switchnum) {
		m_hp = middlemonsters.get(switchnum).getM_hp(); // 몹 체력 저장
		m_name = middlemonsters.get(switchnum).getM_name(); // 몹 이름 저장
		monsteratk = middlemonsters.get(switchnum).getM_atk(); // 몹 공격력 저장
		monsterdef = middlemonsters.get(switchnum).getM_def(); // 몹 방어력 저장
		m_exp = middlemonsters.get(switchnum).getM_exp(); // 몹 경험치 저장
		dropitem = middlemonsters.get(switchnum).getMobDrop(); // 몹 드랍템
	}

	// 고급 몹 기본 정보 저장
	void settingHighMobInfo(int switchnum) {
		m_hp = highmonsters.get(switchnum).getM_hp(); // 몹 체력 저장
		m_name = highmonsters.get(switchnum).getM_name(); // 몹 이름 저장
		monsteratk = highmonsters.get(switchnum).getM_atk(); // 몹 공격력 저장
		monsterdef = highmonsters.get(switchnum).getM_def(); // 몹 방어력 저장
		m_exp = highmonsters.get(switchnum).getM_exp(); // 몹 경험치 저장
		dropitem = highmonsters.get(switchnum).getMobDrop(); // 몹 드랍템
	}

	// 초급 몹 생성
	public void createLowMonster(int switchnum) {
		MonsterPanel.setVisible(true);
		switch (switchnum) {
		case 0: // Skelwarrior
			monsterimgLabel.setIcon(new ImageIcon(SKEL));
			writeLog(lowmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp); // 현재 몹 체력으로 몹 체력바 값 설정
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp); // 막대 안에 보이는 문자열 설정
			addInventory(dropitem); // Test용
			MonsterHpbar.setVisible(true);
			break;
		case 1: // Orcwarrior
			monsterimgLabel.setIcon(new ImageIcon(ORC));
			writeLog(lowmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			addInventory(dropitem);
			MonsterHpbar.setVisible(true);
			break;
		case 2: // Golem
			monsterimgLabel.setIcon(new ImageIcon(GOLEM));
			writeLog(lowmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			addInventory(dropitem);
			MonsterHpbar.setVisible(true);
			break;
		}
	}

	// 중급 몹 생성
	public void createMiddleMonster(int swtichnum) {
		MonsterPanel.setVisible(true);
		switch (swtichnum) {
		case 0: // SkelKing
			writeLog(middlemonsters.get(swtichnum).getM_name() + " 이/가 나타났다.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(SKELKING));
			break;
		case 1: // Hatchling
			writeLog(middlemonsters.get(swtichnum).getM_name() + " 이/가 나타났다.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(HATCHLING));
			break;
		case 2: // Lagiacrus
			writeLog(middlemonsters.get(swtichnum).getM_name() + " 이/가 나타났다.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(LAGIA));
			break;
		}
	}

	// 고급 몹 생성
	public void createHighMonster(int switchnum) {
		MonsterPanel.setVisible(true);
		switch (switchnum) {
		case 0: // Drake
			writeLog(highmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(DRAKE));
			break;

		case 1: // Chimera
			writeLog(highmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(CHIMERA));
			break;

		case 2: // IceDragon
			writeLog(highmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(ICEDRAGON));
			break;
		}
	}
}