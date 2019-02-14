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
	/** 이미지 영역 **/
	// 게임아이콘
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");

	// 게임배경화면(사각형 테두리)
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/background_border.png");
	// 전투배경
	private final static ImageIcon BATTLEBACKGROUND = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/background_battle.png"));
	// 함정배경
	private final static ImageIcon FIRETRAP = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/fire_trap.png"));
	private final static ImageIcon SPIKETRAP = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/spike_trap.png"));
	private final static ImageIcon WELLTRAP = new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/trap/well_trap.png"));

	// 플레이어 이미지(모험가)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_beginner.png");
	private final static Image PLAYERWARRIOR = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_warrior.png");
	private final static Image PLAYERKNIGHT = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_knight.png");
	private final static Image PLAYERKNIGHT_BOSS = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_knight_boss.png");

	// 몹 이미지들(초급)
	private final static Image SKEL = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/low_grade/1_skel_warrior_resize.png");
	private final static Image ORC = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/low_grade/2_orc_warrior_resize.png");
	private final static Image GOLEM = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/low_grade/3_golem_resize.png");

	// 몹 이미지들(중급)
	private final static Image SKELKING = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/middle_grade/1_skel_king_resize.png");
	private final static Image HATCHLING = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/middle_grade/2_Hatchling_resize.png");
	private final static Image LAGIA = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/middle_grade/3_Lagiacrus_resize.png");

	// 몹 이미지들(고급)
	private final static Image DRAKE = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/high_grade/1_Drake_resize.png");
	private final static Image CHIMERA = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/high_grade/2_Chimera_resize.png");
	private final static Image ICEDRAGON = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/high_grade/3_Ice_dragon_resize.png");

	// 보스몹 이미지들(최상급)
	private final static Image BOSSPOLYMORPH = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/boss/bosspolymorph_resized.png");
	private final static Image BOSSORIGINAL = Toolkit.getDefaultToolkit().createImage("resource/images/monsters/boss/bossoriginal_resized.png");

	// 버튼 패널 & 로그 패널 배경(테두리)
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/background_border_log.png");
	private final static Image BUTTONBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/GameScreen/background_border_buttons.png");

	// 버튼 이미지들(탐색,공격,가방....)
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

	// 스킬 이펙트 이미지들
	private final static Image PLAYERBASICATTACK = Toolkit.getDefaultToolkit().createImage("resource/images/effects/player/player_basic_attack.gif");
	private final static Image MONSTERATTACK = Toolkit.getDefaultToolkit().createImage("resource/images/effects/monster/monster_attack_resized.gif");
	private final static Image BEINGATTACKED = Toolkit.getDefaultToolkit().createImage("resource/images/effects/both sides/Being attacked_resized.gif");
	private final static Image BOSSNORMALATTACK = Toolkit.getDefaultToolkit().createImage("resource/images/effects/monster/boss/Boss Normal attack_resized.gif");
	private final static Image BOSSSKILLATTACK = Toolkit.getDefaultToolkit().createImage("resource/images/effects/monster/boss/Boss Skill_resized.gif");

	/** 필드 영역 **/
	private static String c_name, m_name, c_job; // 캐릭터명 & 몬스터이름
	// 캐릭터 스탯 관련 정보 (스탯창 열었을때 보여줌) 레벨, 힘, 민첩, 지능, 체력, 마나, 경험치, 다음 경험치
	public static int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp;
	public static int current_monster_hp, m_hp, m_exp; // 몹 체력 & 몹 최대체력 & 몹이 주는 경험치
	public static Boolean battle = false; // 전투 발생을 알려주는 변수. 전투 발생 시 true로 전환(기본값 false)
	private static Boolean bossphase2 = false; // 페이즈 2 진입 여부
	private static Boolean bossfight = false; // 보스전 시작 여부
	private static Boolean skillused = false; // 보스 스킬이 한번만 사용될 수 있도록 제한하는 변수

	private Boolean buff = false; // 플레이어 버프 상황(걸려있는지 아닌지)
	private Thread p_check, m_check; // 플레이어, 몹 상태 확인 Thread
	private LinkedList<DSMonsters> lowmonsters = null; // 초급몹정보가 저장돼있는 LinkedList
	private LinkedList<DSMonsters> middlemonsters = null; // 중급몹정보가 저장돼있는 LinkedList
	private LinkedList<DSMonsters> highmonsters = null; // 고급몹정보가 저장돼있는 LinkedList
	private LinkedList<DSMonsters> bossmonsters = null; // 보스몹정보가 저장돼있는 LinkedList
	private LinkedList<DSItems> iteminfo; // 게임 내 아이템 정보가 담겨 있는 iteminfo
	private ArrayList<String> Rewards = new ArrayList<String>(); // 평범해보이는 상자
	private ArrayList<String> GoodRewards = new ArrayList<String>(); // 괜찮아보이는 상자
	private ArrayList<String> VeryGoodRewards = new ArrayList<String>(); // 화려해보이는 상자
	private HashMap<Integer, Integer> exptable; // 게임 내 경험치 정보가 담겨 있는 exptable;
	private String[] dropitem; // 몹이 드랍하는 아이템이 저장되어있는 String 배열
	private static int playeratk; // 플레이어 공격력
	private static int playerdef; // 플레이어 방어력

	private static int equipdef; // 방어구의 총 방어력
	private static int monsteratk, monsterdef; // 몬스터 공격력, 방어력

	private int prevlv; // 이전 레벨(버프 관련해서 사용하게 되는 변수)
	private DSService service = new DSService(); // DB 접속 서비스

	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel, GameScreenimgLabel, characterLabel, monsterimgLabel; // 이미지 라벨들
	private static JLabel playerattackLabel, monsterattackLabel, playerbeingattackedLabel, monsterbeingattackedLabel;
	private static JLabel bossskillLabel;
	private static JLabel message;
	public static JLabel SkillEffectLabel1, SkillEffectLabel2, SkillEffectLabel3, SkillEffectLabel4_1,SkillEffectLabel4_2;

	private static JPanel CharacterPanel, MonsterPanel; // 캐릭터 이미지가 출력되는 패널, 몹 이미지가 출력되는 패널
	private static JPanel skilleffectpanel; // 공격 및 스킬 이팩트가 출력되는 패널
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	private static JProgressBar playerHpbar, playerMpbar, MonsterHpbar; // 플레이어 체력막대,마나막대, 몹 체력막대

	private static String helmet, armor, glove, boots, weapon; // 캐릭터가 착용하고 있는 아이템명(장비 창으로 넘길 값들)
	private static int def_helmet, def_armor, def_glove, def_boots, atk_weapon; // 캐릭터가 착용하고 있는 아이템의 공&방
	private static LinkedList<DSItems> inven = new LinkedList<DSItems>(); // 플레이어 인벤토리 내용물
	private static int current_user_hp, current_user_mp; // 현재 플레이어 체력 & 마나

//	public static void main(String[] args) {
//		new GameScreen("춘식이", 30, "모험가", 1000, 1000, 1000, 1400, 880);
//	}

	/** 메소드 영역 **/
	public GameScreen(String name, int l, String job, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() 호출");
		// MainScreen ~ LoadingScreen 에서 사용되던 bgm을 종료하고 GameScreen에서 새로운 bgm 재생
		DSAudio audio = DSAudio.getInstance();
//		audio.offTitle();
		audio.playGame();

		lowmonsters = service.monsterData("초급"); // 초급 몹 정보 저장
		middlemonsters = service.monsterData("중급"); // 중급 몹 정보 저장
		highmonsters = service.monsterData("고급"); // 고급 몹 정보 저장
		bossmonsters = service.monsterData("보스"); // 보스 몹 정보 저장
		iteminfo = service.itemData(); // 아이템 정보 저장
		exptable = service.expData(); // 경험치 정보 저장(Key - 레벨 / Value - 다음 경험치)

		GameScreen.c_name = name; // 캐릭터명
		GameScreen.c_job = job; // 직업
		GameScreen.c_lv = l; // 레벨
		GameScreen.c_str = s; // 힘
		GameScreen.playeratk = (c_str / 2) + atk_weapon; // 캐릭터 공격력은 (힘/2)+장비공격력
		GameScreen.c_dex = d; // 민첩
		GameScreen.playerdef = (c_dex / 5) + equipdef; // 캐릭터 방어력은 (민첩/5)+장비방어력
		GameScreen.c_int = i; // 지능

		GameScreen.c_hp = hp; // 체력
		GameScreen.current_user_hp = c_hp; // 플레이어 현재 체력
		GameScreen.c_mp = mp + (c_int * 2); // 마나는 기본 마나값 + 지능*2
		GameScreen.current_user_mp = c_mp; // 플레이어 현재 마나

		GameScreen.c_exp = 0; // 초기 경험치 보유량 0
		GameScreen.c_next_exp = exptable.get(c_lv); // 다음 경험치 요구량 50

		// 장비 기본값은 "없음"
		weapon = "없음";
		helmet = "없음";
		armor = "없음";
		glove = "없음";
		boots = "없음";
		System.out.println("[info] GameScreen() 필드 초기화 완료.");


		addInventory(new String[] { "단검", "체력 물약", "마나 물약", "체력 물약", "마나 물약" }); // 기본 템																													// 지급
//		addInventory(new String[] { "기간틱 액스", "미스릴 투구", "미스릴 갑옷", "미스릴 장갑", "미스릴 신발" }); // 테스팅용

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

		// 캐릭터 이미지 출력되는 패널(체력/마나 막대 + 캐릭터 이미지)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(650, 60, 210, 300);
		CharacterPanel.setOpaque(false);

		// 캐릭터 이미지 출력하는 Label
		characterLabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
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
		MonsterPanel.setOpaque(false);

		monsterimgLabel = new JLabel("", SwingConstants.CENTER);
		monsterimgLabel.setBounds(10, 25, 330, 290);

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
		logarea.setFocusable(false);
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
			// 탐색 버튼을 눌렸을 시 3가지의 이벤트 중 무작위로 하나가 발생됨(전투,상자획득,함정이벤트)
			public void actionPerformed(ActionEvent e) {
				// battle이 true인 경우(전투 발생) 탐색불가. 전투 종료 후 탐색 재개 가능
				if (battle) {
					JLabel message = new JLabel("<html><p style='font-family:맑은 고딕;'>전투 중에는 탐색이 불가능합니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "탐색", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				// 1 ~ 3 분기 발생
				int switchnum = (int) (Math.random() * 10) + 1; // 1 ~ 10
				System.out.println("나온 탐색 분기 : " + switchnum);
				switch (switchnum) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:// 전투 발생
					battle = true; // 전투 발생 시 true로 전환, 해당 변수는 전투가 종료되면 다시 false로 바뀜
					System.out.println("[info] 전투 발생");
					createBattle(c_lv); // 플레이어 레벨에 따라서 생성되는 몹의 구간(?)이 달라짐. 1 ~ 10, 11 ~ 20, 21 ~ 30
					break;
				case 8:
					System.out.println("[info] 상자 획득 이벤트 발생");
					// 1 ~ 10 상자 이벤트 (1 ~ 6 일반 상자, 7 ~ 9 괜찮아 보이는 상자, 10 화려한 상자)
					int chestevent = (int) (Math.random() * 10) + 1;
					System.out.println("[info] 발생한 상자 획득 이벤트	 : " + chestevent);
					createGetItemEvent(chestevent);
					break;
				case 9: case 10:
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
					JLabel message = new JLabel("<html><p style='font-family:맑은 고딕;'>몬스터가 존재하지 않습니다.</p></html>");
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
				// JOptionPane 버튼 글자 스타일 설정
				UIManager.put("OptionPane.buttonFont", new Font("맑은 고딕", Font.PLAIN, 14));
				JLabel message = new JLabel(
						"<html><p style='font-family:맑은 고딕;'>정말 가실꺼에요?<br/>아직 용사님의 도움을 필요로 하는 곳이 많아요!!</p></html>");
				int a = JOptionPane.showOptionDialog(null, message, "게임종료", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "미안하지만, 지금은 좀 바빠.", "그래? 내 도움이 필요한 곳이 어디야??" },
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
		logoLabel.setBounds(325, 85, 90, 90);
		logoLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				if (c_lv == 30) {
					createFinalBossBattle_Phase1();
				} else {
					JLabel message = new JLabel("<html><p style='font-family:맑은 고딕;'>"
							+ "레벨이 부족하여 보스의 방에 입장할 수 없습니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "최후의 결전", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		ButtonPanel.add(logoLabel);

		skilleffectpanel = new JPanel(null);
		skilleffectpanel.setBounds(150, 70, 670, 280);
		skilleffectpanel.setOpaque(false);

		// 플레이어 기본 공격 이팩트 라벨
		playerattackLabel = new JLabel();
		playerattackLabel.setBounds(150, 0, 380, 280);

		// 몬스터 공격 이팩트 라벨
		monsterattackLabel = new JLabel();
		monsterattackLabel.setBounds(320, 30, 201, 253);

		// 보스 스킬 이팩트 라벨
		bossskillLabel = new JLabel();
		bossskillLabel.setBounds(320, 30, 950, 330);

		// 플레이어 피격 이팩트 라벨
		playerbeingattackedLabel = new JLabel();
		playerbeingattackedLabel.setBounds(460, 30, 250, 250);

		// 몬스터 피격 이팩트 라벨
		monsterbeingattackedLabel = new JLabel();
		monsterbeingattackedLabel.setBounds(0, 30, 250, 250);

		// 스킬 이팩트 라벨
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

	// 30 레벨이 된 후 최종보스전 진입
	void createFinalBossBattle_Phase1() { // 보스 전 Phase 1
		UIManager.put("OptionPane.buttonFont", new Font("맑은 고딕", Font.PLAIN, 12));
		JLabel message = new JLabel(
				"<html><p style='font-family:맑은 고딕;'>이제 사악한 드래곤을 무찌르는 일만 남았습니다.<br/>최후의 결전을 벌일 준비가 되었습니까?</p></html>");
		int select = JOptionPane.showOptionDialog(this, message, "최후의 결전", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "물론이지!", "아직은 조금 두려워..." }, null);
		switch (select) {
		case 0: // 'YES'
			battle = true;
			bossfight = true;
			// Phase 1 시작
			writeLog("\n\n마지막 전투가 시작되었습니다.\n이 세계의 운명은 용사님의 손에 달려있습니다!");
			System.out.println("[info] 전투 발생");
			createBossMonster(0);
			System.out.println("[info] 보스 전 - Phase 1 시작");
			writeLog("드래곤이 인간의 형상을 하고 있다는 건 \n용사님을 얕잡아보고 있다는 것입니다.\n방심을 틈타 기회를 노리세요!");
			break;
		case 1: // 'NO'
			break;
		}

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
		if (buff) {
			System.out.println("[info] 버프 걸려있음");
		}
		int switchnum = (int) (Math.random() * 3); // 0 ~ 2 랜덤
		if (level >= 1 && level <= 10) { // 1 ~ 10 레벨은 초급 몹
			if (c_lv <= 5) { // 캐릭터 레벨이 5 이하 인 경우
				switchnum = (int) (Math.random() * 2); // 0 ~ 1 랜덤(골렘 빼고 리젠)
				createLowMonster(switchnum);
			} else {
				createLowMonster(switchnum);
			}
		} else if (level >= 11 && level <= 20) { // 11 ~ 20 레벨은 중급 몹
			if (c_lv <= 15) { // 캐릭터 레벨이 15 이하 인 경우
				switchnum = (int) (Math.random() * 2); // 0 ~ 1 랜덤(라크리 뭐시기 빼고 리젠)
				createMiddleMonster(switchnum);
			} else {
				createMiddleMonster(switchnum);
			}
		} else { // 둘 다 아니면 21 ~ 부터니까 고급 몹 생성
			if (c_lv != 25) {
				switchnum = (int) (Math.random() * 2); // 0 ~ 1 랜덤(아이스 드래곤 빼고 리젠)
				createHighMonster(switchnum);
			} else {
				createHighMonster(switchnum);
			}
		}
	}

	// 아이템 획득 이벤트(상자 이벤트)
	public void createGetItemEvent(int event) {
		try {
			if (event >= 1 && event <= 6) { // 1 ~ 6 : 일반 상자
				writeLog("\n평범해 보이는 상자를 발견했다.");
				// '하급' 키워드가 들어간 아이템 명을 담을 문자열 리스트
				for (int i = 0; i < iteminfo.size(); i++) {
					if (iteminfo.get(i).getI_name().contains("하급")) {
						Rewards.add(iteminfo.get(i).getI_name());
					}
				}
				System.out.println("[info] 하급 상자 보상 사이즈 : " + Rewards.size());
				// 0 ~ 4 까지 중복 없이 숫자 2개 뽑기
				int[] ran = makeRanNums();

				// 뽑은 2개의 숫자가 보상 인덱스
				String[] low = new String[2];
				for (int i = 0; i < 2; i++) {
					low[i] = Rewards.get(ran[i]);
				}
				addInventory(low); // 인벤토리에 추가
				Rewards.clear(); // 보상 획득 후 기존 리스트 내용 초기화

			} else if (event >= 7 && event < 10) { // 7 ~ 9 : 괜찮아 보이는 상자
				writeLog("\n괜찮아 보이는 상자를 발견했다.");
				// '중급' 키워드가 들어간 아이템 명을 담을 문자열 리스트
				for (int i = 0; i < iteminfo.size(); i++) {
					if (iteminfo.get(i).getI_name().contains("중급")) {
						GoodRewards.add(iteminfo.get(i).getI_name());
					}
				}
				System.out.println("[info] 중급 상자 보상 사이즈 : " + GoodRewards.size());
				// 0 ~ 4 까지 중복 없이 숫자 2개 뽑기
				int[] ran = makeRanNums();

				// 뽑은 2개의 숫자가 보상 인덱스
				String[] good = new String[2];
				for (int i = 0; i < 2; i++) {
					good[i] = GoodRewards.get(ran[i]);
				}
				addInventory(good); // 인벤토리에 추가
				GoodRewards.clear();

			} else { // 10 : 화려해 보이는 상자
				writeLog("\n화려해 보이는 상자를 발견했다.");
				// '고오급' 키워드가 들어간 아이템 명을 담을 문자열 리스트
				for (int i = 0; i < iteminfo.size(); i++) {
					if (iteminfo.get(i).getI_name().contains("고오급")) {
						VeryGoodRewards.add(iteminfo.get(i).getI_name());
					}
				}
				System.out.println("[info] 고오급 상자 보상 사이즈 : " + VeryGoodRewards.size());
				// 0 ~ 4 까지 중복 없이 숫자 2개 뽑기
				int[] ran = makeRanNums();

				// 뽑은 2개의 숫자가 보상 인덱스
				String[] VeryGood = new String[2];
				for (int i = 0; i < 2; i++) {
					VeryGood[i] = VeryGoodRewards.get(ran[i]);
				}
				addInventory(VeryGood); // 인벤토리에 추가
				VeryGoodRewards.clear();
			}
		} catch (Exception e) {
			System.out.println("[Error] 상자 이벤트 에러 발생");
			System.out.println(e.getMessage());
		}
	}

	// 버프,함정 이벤트
	public void createSubEvent(int event) {
		switch (event) {
		case 1: // 1은 버프
			System.out.println("[info] 버프 전 능력치 : " + "힘 " + c_str + " / " + " 민첩 " + c_dex + " / " + " 지능 " + c_int);
			if (buff) { // 버프 상태가 true 일때 해당 이벤트가 실행되면 중복 버프를 받음. 중복 버프 방지함
				System.out.println("[info] 중복 버프 발생");
				return;
			}
			System.out.println("[info] 버프 이벤트 발생");
			buff = true;
			prevlv = c_lv; // 레벨업 후의 버프 수치랑 레벨업 전의 버프 수치랑 달라지는것을 방지하기 위한 변수.
			writeLog("\n요정의 축복을 받아 능력치가 일시적으로 향상되었습니다.\n(해당 버프는 다음 첫 전투에만 적용됩니다.)");
			// 능력치 버프는 기존 능력치 + 현재 캐릭터 레벨*5 만큼 증가시킴
			c_str += (c_lv * 5);
			c_dex += (c_lv * 5);
			c_int += (c_lv * 5);
			System.out.println("[info] 버프 후 능력치 : 힘 " + c_str + " / " + " 민첩 " + c_dex + " / " + " 지능 " + c_int);
			break;
		case 2: // 2는 함정
			System.out.println("[info] 함정 이벤트 발생");
			int randomtrap = (int) (Math.random() * 3) + 1; // 1 ~ 3 랜덤 트랩 발생
			DSAudio trap = DSAudio.getInstance();
			trap.playTrapScream();
			if (randomtrap == 1) { // 1은 불 함정
				writeLog("\n함정 발동 : 외벽에서 뜨거운 불이 뿜어져 나왔다.\n체력이 감소했다.");
				current_user_hp -= (c_lv * 20); // 캐릭터 레벨 * 2 수치 만큼 체력 깎임
				setBackgroundimg(FIRETRAP, BATTLEBACKGROUND);
			} else if (randomtrap == 2) { // 2는 가시(바닥) 함정
				writeLog("\n함정 발동 : 바닥에서 날카로운 가시가 솟구쳐 올라왔다.\n체력이 감소했다.");
				current_user_hp -= (c_lv * 30); // 캐릭터 레벨 * 3 수치 만큼 체력 깎임
				setBackgroundimg(SPIKETRAP, BATTLEBACKGROUND);
			} else { // 3은 우물 함정
				writeLog("\n함정발동 : 오염된 우물안의 물을 마셨다.\n체력이 감소했다.");
				current_user_hp -= (c_lv * 40); // 캐릭터 레벨 * 4 수치 만큼 체력 깎임
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
		recoverimg.schedule(recovertask, 2000);
	}

	// 0 ~ 4 랜덤 만드는 메소드(상자 이벤트에서 사용)
	int[] makeRanNums() {
		int[] ran = new int[2];
		for (int i = 0; i < 2; i++) {
			ran[i] = (int) (Math.random() * 4); // 0 ~ 3 랜덤 (0,1,2,3)
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
		DSAudio playerhit = DSAudio.getInstance();
		if (!battle) {
			System.out.println("[info] 전투 중이 아닙니다.");
			return;
		}
		writeLog("'" + c_name + "' 의 공격!\n");

		playerattackLabel.setIcon(new ImageIcon(PLAYERBASICATTACK)); // 플레이어 기본공격 이펙트 출력
		playerhit.playAtk_P();
		monsterbeingattackedLabel.setIcon(new ImageIcon(BEINGATTACKED)); // 피격 이팩트 출력
		playerhit.playBeinghit();

		int damage = playeratk - monsterdef; // 데미지는 플레이어 공격력 - 몬스터 방어력
		System.out.println("나온 데미지 : " + damage);
		if (damage <= 0) { // 플레이어 공격력 - 몬스터 방어력의 결과가 0보다 작거나 같을 경우 (= 몬스터의 방어력이 플레이어 공격력보다 높을 경우)
			damage = 1; // 최소 데미지 1이 적용되도록 설정함.
			current_monster_hp -= damage; // damage 수치만큼 몬스터 현재 체력 감소
			writeLog("'" + c_name + "' (은/는) " + m_name + " 에게 " + damage + " 의 피해를 입혔다!");
		} else {
			int mindam = damage / 2; // 최소데미지는 데미지 / 2
			int maxdam = damage; // 최대데미지
			System.out.println("최소데미지 : " + mindam);
			System.out.println("최대데미지 : " + maxdam);
			if (mindam == 1 || maxdam == 1) {
				int randomdamage = 1;
				current_monster_hp -= randomdamage; // randomdamage 수치만큼 몹 현재 체력 감소
				writeLog("'" + c_name + "' (은/는) " + m_name + " 에게 " + randomdamage + " 의 피해를 입혔다!");
			} else {
				int randomdamage = (int) (Math.random() * maxdam) + mindam; // 최소데미지 ~ 플레이어 데미지 사이의 랜덤데미지 결정
				if (randomdamage > maxdam) {
					randomdamage = maxdam;
				}
				System.out.println("나온 랜덤데미지 : " + randomdamage);
				current_monster_hp -= randomdamage; // randomdamage 수치만큼 몹 현재 체력 감소
				writeLog("'" + c_name + "' (은/는) " + m_name + " 에게 " + randomdamage + " 의 피해를 입혔다!");
			}
		}

		Timer pAttacktimer = new Timer();
		TimerTask pAttackTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				playerattackLabel.setIcon(null); // 플레이어 기본공격 이펙트 출력
				monsterbeingattackedLabel.setIcon(null); // 피격 이팩트 출력
			}
		};
		pAttacktimer.schedule(pAttackTask, 800);
	}

	// 몬스터 공격(평타)
	public static void attack_monster() {
		DSAudio monsterhit = DSAudio.getInstance();
		if (!battle) {
			System.out.println("[info] 전투 중이 아닙니다.");
			return;
		}

		writeLog("'" + m_name + "' 의 공격!");
		if (battle == true && bossphase2 == true) { // 전투 중이며, 보스전 phase 2가 진행 중이면
			System.out.println("[Info] 페이즈 2 진행중");
			DSAudio playdragonattack = DSAudio.getInstance();
			playdragonattack.playDragonAttack();
			monsterattackLabel.setIcon(new ImageIcon(BOSSNORMALATTACK)); // 보스 기본 공격 이팩트 출력
			monsterattackLabel.setBounds(340, 30, 380, 280);
		} else {
			monsterattackLabel.setIcon(new ImageIcon(MONSTERATTACK)); // 몬스터 공격 이펙트 출력
			monsterhit.playAtk_M();
			playerbeingattackedLabel.setIcon(new ImageIcon(BEINGATTACKED)); // 피격 이팩트 출력
			monsterhit.playBeinghit();
		}

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
		if (current_monster_hp <= 0) {
			return;
		} else {
			mAttackEnd.schedule(mAttackEndTask, Calendar.getInstance().get(Calendar.MILLISECOND) + 300);
		}
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
						Thread.sleep(300);
						System.out.println("[info] 캐릭터 레벨 체크..");
						if (c_lv > 10 && c_lv < 30) { // 11 ~ 29 레벨 전사 이미지
							c_job = "전사"; // 11 ~ 29 레벨 직업은 전사
							characterLabel.setIcon(new ImageIcon(PLAYERWARRIOR));
						} else if (c_lv >= 30) { // 기사 이미지
							c_job = "기사"; // 만렙(30) 직업은 기사
							if (bossphase2) {
								characterLabel.setIcon(new ImageIcon(PLAYERKNIGHT_BOSS));
							} else {
								characterLabel.setIcon(new ImageIcon(PLAYERKNIGHT));
							}
						}
						System.out.println("[info] 캐릭터 체력 & 마나 상태 체크..");
						playerHpbar.setValue(current_user_hp);
						playerHpbar.setString(current_user_hp + " / " + c_hp);
						playerMpbar.setValue(current_user_mp);
						playerMpbar.setString(current_user_mp + " / " + c_mp);

						if (current_user_hp <= 0) {
							JLabel message = new JLabel("<html><body style='background-color:black;'>"
									+ "<p style='font-family:맑은 고딕; font-size:13px; color:white; text-align:center;'>"
									+ "캐릭터가 전사하였습니다.<br/><br/>용사님이 돌아오실 때까지 저희가 막아내고 있겠습니다!!"
									+ "</p></body></html>");

							UIManager.put("OptionPane.background", Color.BLACK);
							UIManager.put("Panel.background", Color.BLACK);

							JOptionPane.showMessageDialog(null, message, "캐릭터 사망", JOptionPane.DEFAULT_OPTION, null);
							System.exit(0);
						}

						System.out.println("[info] 캐릭터 공격력&방어력 상태 체크..");
						playeratk = (c_str / 2) + atk_weapon; // 플레이어 총 공격력(기본공격력 + 무기공격력)
						equipdef = def_helmet + def_armor + def_glove + def_boots; // 투구,갑옷,장갑,신발 방어력 합계
						playerdef = (c_dex / 5) + equipdef; // 플레이어 총 방어력(기본방어력 + 방어구 총방어력)

						System.out.println("[info] 캐릭터 현재 경험치 & 다음 경험치 상태 체크..");
						c_next_exp = exptable.get(c_lv);
						System.out.println("[info] 현재 경험치 : " + c_exp);
						System.out.println("[info] 다음 경험치 : " + c_next_exp);
						// 레벨업(현재 경험치가 다음 경험치보다 크거나 같을 때)
						if (c_exp >= c_next_exp) {
							c_lv++;
							writeLog("\n---------- 레벨 업 ----------\n캐릭터 레벨이 " + c_lv + " 가 되었다.\n");
							int strup, dexup, intup;

							if (c_lv >= 1 && c_lv <= 10) { // 레벨이 1 ~ 10인 경우 스텟 가중치 (5 ~ 10 랜덤), 체력 +40 /마나 +10
								strup = (int) (Math.random() * 6) + 5;
								c_str += strup;
								writeLog("힘이 " + strup + " 올랐다.");
								dexup = (int) (Math.random() * 6) + 5;
								c_dex += dexup;
								writeLog("민첩이 " + dexup + " 올랐다.");
								intup = (int) (Math.random() * 6) + 5;
								c_int += intup;
								writeLog("지능이 " + intup + " 올랐다.");
								c_hp += 40;
								c_mp += 10;
								writeLog("체력이 40 올랐다.\n마나가 10 올랐다.");

								current_user_hp = c_hp;
								current_user_mp = c_mp;
								playerHpbar.setValue(current_user_hp);
								playerMpbar.setValue(current_user_mp);
								playerHpbar.setMaximum(current_user_hp);
								playerMpbar.setMaximum(current_user_mp);
								playerHpbar.setString(current_user_hp + " / " + c_hp);
								playerMpbar.setString(current_user_mp + " / " + c_mp);

							} else if (c_lv >= 11 && c_lv <= 20) { // 레벨이 11 ~ 20인 경우 스텟 가중치 (10 ~ 15 랜덤), 체력 + 70 /마나 +
																	// 30
								strup = (int) (Math.random() * 6) + 10;
								c_str += strup;
								writeLog("힘이 " + strup + " 올랐다.");
								dexup = (int) (Math.random() * 6) + 10;
								c_dex += dexup;
								writeLog("민첩이 " + dexup + " 올랐다.");
								intup = (int) (Math.random() * 6) + 10;
								c_int += intup;
								writeLog("지능이 " + intup + " 올랐다.");
								c_hp += 70;
								c_mp += 20;
								writeLog("체력이 70 올랐다.\n마나가 20 올랐다.");

								current_user_hp = c_hp;
								current_user_mp = c_mp;
								playerHpbar.setValue(current_user_hp);
								playerMpbar.setValue(current_user_mp);
								playerHpbar.setMaximum(current_user_hp);
								playerMpbar.setMaximum(current_user_mp);
								playerHpbar.setString(current_user_hp + " / " + c_hp);
								playerMpbar.setString(current_user_mp + " / " + c_mp);

							} else if (c_lv >= 21 && c_lv <= 29) { // 21 ~ 29 스텟 가중치 (15 ~ 20 랜덤), 체력 + 100 /마나 + 30
								strup = (int) (Math.random() * 6) + 15;
								c_str += strup;
								writeLog("힘이 " + strup + " 올랐다.");
								dexup = (int) (Math.random() * 6) + 15;
								c_dex += dexup;
								writeLog("민첩이 " + dexup + " 올랐다.");
								intup = (int) (Math.random() * 6) + 15;
								c_int += intup;
								writeLog("지능이 " + intup + " 올랐다.");
								c_hp += 100;
								c_mp += 30;
								writeLog("체력이 100 올랐다.\n마나가 30 올랐다.");

								current_user_hp = c_hp;
								current_user_mp = c_mp;
								playerHpbar.setValue(current_user_hp);
								playerMpbar.setValue(current_user_mp);
								playerHpbar.setMaximum(current_user_hp);
								playerMpbar.setMaximum(current_user_mp);
								playerHpbar.setString(current_user_hp + " / " + c_hp);
								playerMpbar.setString(current_user_mp + " / " + c_mp);

							} else { // 30 레벨 달성(만렙) 올스탯+80, 체,마 + 500
								c_hp += 300;
								c_mp += 300;
								c_str += 80;
								c_dex += 80;
								c_int += 80;
								writeLog("레벨 30이 되었다.\n체력이 300 올랐다.\n마나가 300 올랐다.\n모든 능력치가 80 올랐다.");

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

						System.out.println("[info] 캐릭터 버프 상태 체크..");
						System.out.println("[info] 버프 상태 : " + buff);
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
							Thread.sleep(300);
							System.out.println("[info] 몹 체력 상태 체크..");
							MonsterHpbar.setValue(current_monster_hp);
							MonsterHpbar.setString(current_monster_hp + " / " + m_hp);
							// 페이즈 2 돌입
							if (battle == true && bossphase2 == true) {
								System.out.println("[Info] 보스전 페이즈 2 진행중..");
								if (current_monster_hp >= 1200 && current_monster_hp <= 2400) { // 보스 체력이 60% 이하일 때 스킬 공격 1회
																								// 사용
									if (!skillused) {
										useDragonSkill();
									}
								}

								if (current_monster_hp >= 1 && current_monster_hp <= 1199) { // 보스 체력이 30% 이하일 떄 스킬 공격 1회 사용
									if (skillused) {
										useDragonSkill();
										skillused = false;
									}
								}

								if (current_monster_hp <= 0) {
									bossfight = false;
									bossphase2 = false;
									battle = false;
									System.out.println("[Info] 보스전 페이즈 2 종료, 보스 쥬금");
									GameScreenimgLabel.setIcon(BATTLEBACKGROUND);
									MonsterPanel.setVisible(false);
									JLabel message = new JLabel("<html><body style='background-color:black;'>"
											+ "<p style='font-family:맑은 고딕; font-size:13px; color:white; text-align:center;'>"
											+ "드래곤의 압도적인 힘에 눌려 쓰러지기를 수 차례...<br/>높이 뛰어오른 용사는 마지막 힘을  쏟아내어 드래곤의 심장에 검을 박아넣었다."
											+ "<br/>검이 지나간 상처 사이로 검붉은 피가 뿜어져 나왔고,"
											+ "<br/>철옹성 같기만 하던 거대한 드래곤의 몸이 서서히 무너져 내렸다."
											+ "<br/><br/>'챙그랑!!!!!'<br/>용사의 손에 붙들려 있던 검이 땅으로 떨어지며 맑은 소리를 울렸다."
											+ "<br/>'용사님! 정신차리십시오!'<br/>뒤늦게 도착한 지원군이 달려오며 쓰러지는 용사를 끌어안았다."
											+ "<br/>용사는 흐려져 가는 의식 사이로 희미한 미소를 지으며 나지막이 중얼거렸다.<br/>'이제 되었다...'<br/>"
											+ "<br/>. . . . .<br/>. . .<br/>."
											+ "<br/><br/><br/><br/><br/><br/>축하합니다! 험난하기만 하였던 길고 긴 여정이 드디어 끝이 났군요."
											+ "<br/>마침내 사악한 드래곤이 사라지고 세상은 평화를 되찾았습니다."
											+ "<br/><br/><font color=purple>드래곤 슬레이어</font>&nbsp;&nbsp;<font color=red>"
											+ c_name + "</font>님의 위대한 영웅담은 후대에도 널리 퍼질 것입니다!</p>"
											+ "<p style='margin-top:50px; margin-bottom:50px; font-family:맑은 고딕; font-size:16px; color:blue; text-align:center;'>지금까지 드래곤 슬레이어와 함께 해주셔서 감사합니다.</p></body></html>");

									UIManager.put("OptionPane.background", Color.BLACK);
									UIManager.put("Panel.background", Color.BLACK);

									JOptionPane.showMessageDialog(null, message, "Ending", JOptionPane.DEFAULT_OPTION,
											null);
									System.exit(0);
								}
							}
							if (battle) { // 전투 발생 시
								// 몹이 죽으면 경험치 & 아이템 획득(전투 종료)
								if (current_monster_hp <= 0 && battle == true) {
									monsterattackLabel.setIcon(null);
									playerbeingattackedLabel.setIcon(null);
									playerattackLabel.setIcon(null);
									if (bossfight) {
										System.out.println("[Info] 보스전 페이즈 1 종료");
										writeLog("\n" + m_name + "(이/가) 쓰러졌다.");
										bossphase2 = true;
										createBossMonster(1); // 2페이즈 돌입
									} else {
										writeLog(m_name + "(이/가) 쓰러졌다.");
										writeLog("경험치가 " + m_exp + " 올랐습니다.");
										if (c_lv == 30) {
											m_exp = 0; // 만렙일 시 경험치 획득 X
										}
										c_exp += m_exp; // 현재 경험치에 몹 경험치를 더함(경험치 획득)
										addInventory(dropitem); // 몹이 드랍한 아이템 인벤토리에 추가
										for (int i = 0; i < dropitem.length; i++) {
											writeLog(dropitem[i] + " (을/를) 얻었다.");
										}
										battle = false; // 전투 종료
										if (buff) { // 버프가 걸려있는 경우 전투 종료 후 버프를 해제 해야함
											buff = false; // 버프 상태 해제(전투 종료)
											if (c_lv != prevlv) { // 현재 레벨과 버프받기전 레벨이 같지 않은 경우(즉, 버프 받고 레벨업 한 경우)
												writeLog("\n요정에게서 받은 버프가 사라졌다.");
												c_str -= (prevlv * 5); // 버프 받은 수치만큼 - 해줘서 원래 능력치로 돌아감
												c_dex -= (prevlv * 5); // 민첩 수치
												c_int -= (prevlv * 5); // 지능 수치
											}
											writeLog("\n요정에게서 받은 버프가 사라졌다.");
											c_str -= (c_lv * 5); // 버프 받은 수치만큼 - 해줘서 원래 능력치로 돌아감
											c_dex -= (c_lv * 5); // 민첩 수치
											c_int -= (c_lv * 5); // 지능 수치
											System.out.println(
													"[info] 버프 받기 전 능력치 : " + c_str + " / " + c_dex + " / " + c_int);
										}
										MonsterPanel.setVisible(false); // 몹패널 visible을 false
									}
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
	
	// 페이즈 2 보스 스킬 사용 메소드
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
		writeLog("'" + m_name + "' 이 강력한 브레스를 사용했다.");
		current_user_hp -= 700;
		writeLog("'" + c_name + "' 의 체력이 700 감소했다.");
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

	// SkillScreen에서 호출되는 스킬 메소드들
	static void skill_DragonSlasher() {
		int requiredLv = 6;
		int requiredMp = 70;
		int skilldamage = 0;
		Timer removeEffectTimer;
		TimerTask removeEffectTask;
		try {
			if (battle) {
				if (c_lv < requiredLv) {
					message = new JLabel("<html><p style='font-family:맑은 고딕;'>레벨이 부족합니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "드래곤 슬래셔", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (current_user_mp < requiredMp) {
						message = new JLabel("<html><p style='font-family:맑은 고딕;'>마나가 부족합니다.</p></html>");
						JOptionPane.showMessageDialog(null, message, "드래곤 슬래셔", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						DSAudio dragonsound = DSAudio.getInstance();
						dragonsound.playDragonSlasher();
						SkillEffectLabel1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
								.createImage("resource/images/effects/player/skill1_Dragon_Slasher_resize.gif")));
						skilldamage = (playeratk * 2) - monsterdef; // 스킬데미지 = 스킬계수*2 - 몹 방어력
						if (skilldamage <= 0) {
							skilldamage = 1; // 최소데미지는 무조건 1
							current_user_mp -= requiredMp;
							System.out.println("[info] 남은 마나 : " + current_user_mp);
							current_monster_hp -= skilldamage;
						} else {
							int mindam = skilldamage / 2;
							int maxdam = skilldamage;
							System.out.println("드래곤 슬래셔 최소 데미지 : " + mindam + " 최대 데미지 : " + maxdam);
							if (mindam == 1 || maxdam == 1) {
								skilldamage = 1;
								current_user_mp -= requiredMp;
								System.out.println("[info] 남은 마나 : " + current_user_mp);
								current_monster_hp -= skilldamage;
							} else {
								skilldamage = (int) (Math.random() * maxdam) + mindam; // 최소데미지 ~ 스킬데미지 사이 랜덤 데미지
								if (skilldamage > maxdam) {
									skilldamage = maxdam;
								}
								current_user_mp -= requiredMp;
								System.out.println("[info] 남은 마나 : " + current_user_mp);
								current_monster_hp -= skilldamage;
							}
						}
			
					writeLog("\n스킬 - 드래곤 슬래셔 사용\n" + m_name + " 에게 피해를 " + skilldamage + " 입혔다.");
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
				message = new JLabel("<html><p style='font-family:맑은 고딕;'>스킬 사용은 전투 중에만 가능합니다.</p></html>");
				JOptionPane.showMessageDialog(null, message, "드래곤 슬래셔", JOptionPane.ERROR_MESSAGE);
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
					message = new JLabel("<html><p style='font-family:맑은 고딕;'>레벨이 부족합니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "오러 블레이드", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (current_user_mp < requiredMp) {
						message = new JLabel("<html><p style='font-family:맑은 고딕;'>마나가 부족합니다.</p></html>");
						JOptionPane.showMessageDialog(null, message, "오러 블레이드", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						DSAudio aurablade = DSAudio.getInstance();
						aurablade.playAuraBlade();
						SkillEffectLabel2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
								.createImage("resource/images/effects/player/skill2_Aura_Blade_resize.gif")));
						skilldamage = (playeratk * 3) - monsterdef; // 스킬데미지 = 스킬계수*3 - 몹 방어력
						if (skilldamage <= 0) {
							skilldamage = 1; // 최소데미지는 무조건 1
							current_user_mp -= requiredMp;
							System.out.println("[info] 남은 마나 : " + current_user_mp);
							current_monster_hp -= skilldamage;
						} else {
							int mindam = skilldamage / 2;
							int maxdam = skilldamage;
							System.out.println("오러 블레이드 최소 데미지 : " + mindam + " 최대 데미지 : " + maxdam);
							if (mindam == 1 || maxdam == 1) {
								skilldamage = 1;
								current_user_mp -= requiredMp;
								System.out.println("[info] 남은 마나 : " + current_user_mp);
								current_monster_hp -= skilldamage;
							} else {
								skilldamage = (int) (Math.random() * maxdam) + mindam; // 최소데미지 ~ 스킬데미지 사이 랜덤 데미지
								if (skilldamage > maxdam) {
									skilldamage = maxdam;
								}
								current_user_mp -= requiredMp;
								System.out.println("[info] 남은 마나 : " + current_user_mp);
								current_monster_hp -= skilldamage;
							}
						}

					writeLog("\n스킬 - 오러 블레이드 사용\n" + m_name + " 에게 피해를 " + skilldamage + " 입혔다.");
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
				message = new JLabel("<html><p style='font-family:맑은 고딕;'>스킬 사용은 전투 중에만 가능합니다.</p></html>");
				JOptionPane.showMessageDialog(null, message, "오러 블레이드", JOptionPane.ERROR_MESSAGE);
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
					message = new JLabel("<html><p style='font-family:맑은 고딕;'>레벨이 부족합니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "데모닉 소드", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (current_user_mp < requiredMp) {
						message = new JLabel("<html><p style='font-family:맑은 고딕;'>마나가 부족합니다.</p></html>");
						JOptionPane.showMessageDialog(null, message, "데모닉 소드", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						DSAudio demonic = DSAudio.getInstance();
						demonic.playDemonicSword();
						SkillEffectLabel3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
								.createImage("resource/images/effects/player/skill3_Demonic_Swords_resize.gif")));
						skilldamage = (playeratk * 4) - monsterdef; // 스킬데미지 = 스킬계수*6 - 몹 방어력
						if (skilldamage <= 0) {
							skilldamage = 1; // 최소데미지는 무조건 1
							current_user_mp -= requiredMp;
							System.out.println("[info] 남은 마나 : " + current_user_mp);
							current_monster_hp -= skilldamage;
						} else {
							int mindam = skilldamage / 2;
							int maxdam = skilldamage;
							System.out.println("데모닉 소드 최소 데미지 : " + mindam + " 최대 데미지 : " + maxdam);
							if (mindam == 1 || maxdam == 1) {
								skilldamage = 1;
								current_user_mp -= requiredMp;
								System.out.println("[info] 남은 마나 : " + current_user_mp);
								current_monster_hp -= skilldamage;
							} else {
								skilldamage = (int) (Math.random() * maxdam) + mindam; // 최소데미지 ~ 스킬데미지 사이 랜덤 데미지
								if (skilldamage > maxdam) {
									skilldamage = maxdam;
								}
								current_user_mp -= requiredMp;
								System.out.println("[info] 남은 마나 : " + current_user_mp);
								current_monster_hp -= skilldamage;
							}
						}

					writeLog("\n스킬 - 데모닉 소드 사용\n" + m_name + " 에게 피해를 " + skilldamage + " 입혔다.");
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
				message = new JLabel("<html><p style='font-family:맑은 고딕;'>스킬 사용은 전투 중에만 가능합니다.</p></html>");
				JOptionPane.showMessageDialog(null, message, "데모닉 소드", JOptionPane.ERROR_MESSAGE);
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
					message = new JLabel("<html><p style='font-family:맑은 고딕;'>레벨이 부족합니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "디멘션 브레이커", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (current_user_mp < requiredMp) {
						message = new JLabel("<html><p style='font-family:맑은 고딕;'>마나가 부족합니다.</p></html>");
						JOptionPane.showMessageDialog(null, message, "디멘션 브레이커", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						showUltimateSkill();
						skilldamage = (playeratk * 8) - monsterdef; // 스킬데미지 = 스킬계수*8 - 몹 방어력
						if (skilldamage <= 0) {
							skilldamage = 1; // 최소데미지는 무조건 1
							current_user_mp -= requiredMp;
							System.out.println("[info] 남은 마나 : " + current_user_mp);
							current_monster_hp -= skilldamage;
						} else {
							int mindam = skilldamage / 2;
							int maxdam = skilldamage;
							System.out.println("디멘션 브레이커 최소 데미지 : " + mindam + " 최대 데미지 : " + maxdam);
							if (mindam == 1 || maxdam == 1) {
								skilldamage = 1;
								current_user_mp -= requiredMp;
								System.out.println("[info] 남은 마나 : " + current_user_mp);
								current_monster_hp -= skilldamage;
							} else {
								skilldamage = (int) (Math.random() * maxdam) + mindam; // 최소데미지 ~ 스킬데미지 사이 랜덤 데미지
								if (skilldamage > maxdam) {
									skilldamage = maxdam;
								}
								current_user_mp -= requiredMp;
								System.out.println("[info] 남은 마나 : " + current_user_mp);
								current_monster_hp -= skilldamage;
							}
						}
						writeLog("스킬 - 디멘션 브레이커 사용\n" + m_name + " 에게 피해를 " + skilldamage + " 입혔다.");
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

					writeLog("\n스킬 - 디멘션 브레이커 사용\n" + m_name + " 에게 피해를 " + skilldamage + " 입혔다.");
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
				message = new JLabel("<html><p style='font-family:맑은 고딕;'>스킬 사용은 전투 중에만 가능합니다.</p></html>");
				JOptionPane.showMessageDialog(null, message, "디멘션 브레이커", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 궁극기 연출 메소드
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
		TimerTask changeImgTask2 = new TimerTask() { // 궁극기 연출 후 원 화면으로 복귀
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CharacterPanel.setVisible(true);
				MonsterPanel.setLocation(60, 40);
				if (bossphase2) { // 보스전 2페이즈 진행 중 일 경우
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
				changeImg2.schedule(changeImgTask2, 2100); // 2.1초 뒤 원래 화면으로 돌아가기

			}
		};
		changeImg1.schedule(changeImgTask1, 4200); // 첫 번째 궁극기 스킬 이펙트 실행 후 4.2초 뒤 두번째 스킬 이펙트 실행(첫 번째 스킬 이펙트 길이가 4초라서..)
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
		System.out.println("몹 이름 : " + m_name);
		System.out.println("몹 체력 : " + m_hp);
		System.out.println("몹 공격력 : " + monsteratk);
		System.out.println("몹 방어력 : " + monsterdef);
		System.out.println("몹 경험치 : " + m_exp);
		System.out.println("몹 드랍 : " + Arrays.toString(dropitem));
	}

	// 중급 몹 기본 정보 저장
	void settingMiddleMobInfo(int switchnum) {
		m_hp = middlemonsters.get(switchnum).getM_hp(); // 몹 체력 저장
		m_name = middlemonsters.get(switchnum).getM_name(); // 몹 이름 저장
		monsteratk = middlemonsters.get(switchnum).getM_atk(); // 몹 공격력 저장
		monsterdef = middlemonsters.get(switchnum).getM_def(); // 몹 방어력 저장
		m_exp = middlemonsters.get(switchnum).getM_exp(); // 몹 경험치 저장
		dropitem = middlemonsters.get(switchnum).getMobDrop(); // 몹 드랍템
		System.out.println("몹 이름 : " + m_name);
		System.out.println("몹 체력 : " + m_hp);
		System.out.println("몹 공격력 : " + monsteratk);
		System.out.println("몹 방어력 : " + monsterdef);
		System.out.println("몹 경험치 : " + m_exp);
		System.out.println("몹 드랍 : " + Arrays.toString(dropitem));
	}

	// 고급 몹 기본 정보 저장
	void settingHighMobInfo(int switchnum) {
		m_hp = highmonsters.get(switchnum).getM_hp(); // 몹 체력 저장
		m_name = highmonsters.get(switchnum).getM_name(); // 몹 이름 저장
		monsteratk = highmonsters.get(switchnum).getM_atk(); // 몹 공격력 저장
		monsterdef = highmonsters.get(switchnum).getM_def(); // 몹 방어력 저장
		m_exp = highmonsters.get(switchnum).getM_exp(); // 몹 경험치 저장
		dropitem = highmonsters.get(switchnum).getMobDrop(); // 몹 드랍템
		System.out.println("몹 이름 : " + m_name);
		System.out.println("몹 체력 : " + m_hp);
		System.out.println("몹 공격력 : " + monsteratk);
		System.out.println("몹 방어력 : " + monsterdef);
		System.out.println("몹 경험치 : " + m_exp);
		System.out.println("몹 드랍 : " + Arrays.toString(dropitem));
	}

	// 보스 몹 기본 정보 저장
	void settingBossMobInfo(int switchnum) {
		m_hp = bossmonsters.get(switchnum).getM_hp(); // 몹 체력 저장
		m_name = bossmonsters.get(switchnum).getM_name(); // 몹 이름 저장
		monsteratk = bossmonsters.get(switchnum).getM_atk(); // 몹 공격력 저장
		monsterdef = bossmonsters.get(switchnum).getM_def(); // 몹 방어력 저장
		m_exp = bossmonsters.get(switchnum).getM_exp(); // 몹 경험치 저장
		dropitem = bossmonsters.get(switchnum).getMobDrop(); // 몹 드랍템
		System.out.println("몹 이름 : " + m_name);
		System.out.println("몹 체력 : " + m_hp);
		System.out.println("몹 공격력 : " + monsteratk);
		System.out.println("몹 방어력 : " + monsterdef);
		System.out.println("몹 경험치 : " + m_exp);
		System.out.println("몹 드랍 : " + Arrays.toString(dropitem));
	}

	// 초급 몹 생성
	public void createLowMonster(int switchnum) {
		MonsterPanel.setVisible(true);
		switch (switchnum) {
		case 0: // Skelwarrior
			monsterimgLabel.setIcon(new ImageIcon(SKEL));
			writeLog("\n" + lowmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp); // 현재 몹 체력으로 몹 체력바 값 설정
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp); // 막대 안에 보이는 문자열 설정
			MonsterHpbar.setVisible(true);
			break;
		case 1: // Orcwarrior
			monsterimgLabel.setIcon(new ImageIcon(ORC));
			writeLog("\n" + lowmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			break;
		case 2: // Golem
			monsterimgLabel.setIcon(new ImageIcon(GOLEM));
			writeLog("\n" + lowmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingLowMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			break;
		}
	}

	// 중급 몹 생성
	public void createMiddleMonster(int swtichnum) {
		MonsterPanel.setVisible(true);
		switch (swtichnum) {
		case 0: // SkelKing
			writeLog("\n" + middlemonsters.get(swtichnum).getM_name() + " 이/가 나타났다.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(SKELKING));
			break;
		case 1: // Hatchling
			writeLog("\n" + middlemonsters.get(swtichnum).getM_name() + " 이/가 나타났다.\n");
			settingMiddleMobInfo(swtichnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(HATCHLING));
			break;
		case 2: // Lagiacrus
			writeLog("\n" + middlemonsters.get(swtichnum).getM_name() + " 이/가 나타났다.\n");
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
			writeLog("\n" + highmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(DRAKE));
			break;

		case 1: // Chimera
			writeLog("\n" + highmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingHighMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(CHIMERA));
			break;

		case 2: // IceDragon
			writeLog("\n" + highmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
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

	// 보스 몹 생성
	public void createBossMonster(int switchnum) {
		MonsterPanel.setVisible(true);
		switch (switchnum) {
		case 0: // Boss Phase1 - Boss Polymorph mode
			writeLog("\n" + bossmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			settingBossMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp) + " / " + m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(BOSSPOLYMORPH));
			break;

		case 1: // Boss Phase2 - Boss Original mode
			System.out.println("[info] 보스 전 - Phase 2 시작");
			writeLog("\n 분노가 극에 달한 드래곤이 본 모습을 드러내었습니다.\n부디 조심하시기 바랍니다!");
			writeLog(bossmonsters.get(switchnum).getM_name() + " 이/가 나타났다.\n");
			DSAudio roar = DSAudio.getInstance();
			roar.playDragonRoar();
			settingBossMobInfo(switchnum);
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(m_hp); // 체력바의 최대수치를 몹 체력으로 설정
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