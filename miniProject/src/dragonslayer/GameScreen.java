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
	/** 이미지 영역 **/
	// 게임아이콘
	private final static Image ICONIMAGE = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	
	// 게임배경화면(사각형 테두리)
	private final static Image MAINBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/gamescreenmainbackground.png");
	
	// 전투배경
	private final static Image BATTLEBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/battlebackground_resize.png");
	
	// 이벤트 배경
	private final static Image EVENTBACKGROUND1 = Toolkit.getDefaultToolkit().createImage("resource/images/background/Event1_resize.png");
	private final static Image EVENTBACKGROUND2 = null;
	private final static Image EVENTBACKGROUND3 = null;
	
	// 플레이어 이미지(모험가)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit().createImage("resource/images/player/playercharacter_beginner.png");
	private final static Image PLAYERWARRIOR = null;
	private final static Image PLAYERKNIGHT = null;
	
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
	
	// 버튼 패널 & 로그 패널 배경(테두리)
	private final static Image LOGBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/LogPanelBorder.png");
	private final static Image BUTTONBACKGROUND = Toolkit.getDefaultToolkit().createImage("resource/images/background/ButtonPanelBorder.png");
	
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
	private final static Image LOGO = Toolkit.getDefaultToolkit().createImage("resource/images/background/logo.png");
	
	/** 필드 영역 **/
	private String c_name, m_name, c_job; // 캐릭터명 & 몬스터이름
	private int c_lv, c_str, c_dex, c_int, c_hp, c_mp, c_exp, c_next_exp; // 캐릭터 스탯 관련 정보 (스탯창 열었을때 보여줌)
	private int c_atk, c_def, m_Atk, m_Def; // 캐릭터 공격력&방어력, 몹 공격력&방어력
	private int current_user_hp, current_user_mp, current_monster_hp, m_hp; // 현재 플레이어 체력 & 몹 체력 & 몹 최대체력
	private Boolean battle = false; // 전투 발생을 알려주는 변수. 전투 발생 시 true로 전환(기본값 false)
	private LinkedList<DSMonsters> lowmonsters = null; // 초급몹정보가 저장돼있는 LinkedList
	private LinkedList<DSMonsters> middlemonsters = null; // 중급몹정보가 저장돼있는 LinkedList
	private LinkedList<DSMonsters> highmonsters = null; // 고급몹정보가 저장돼있는 LinkedList
	
	private static JButton buttonsearch, buttonattack, buttoninven, buttonequip, buttonstat, buttonskill, buttonexit;
	private static JLabel mainbackgroundimgLabel, GameScreenimgLabel, monsterimgLabel; // 이미지 라벨들
	private static JPanel CharacterPanel,MonsterPanel; // 캐릭터 이미지가 출력되는 패널, 몹 이미지가 출력되는 패널
	private static JTextArea logarea;
	private static JScrollPane logscroll;
	private static JProgressBar playerHpbar, playerMpbar, MonsterHpbar; // 플레이어 체력막대,마나막대, 몹 체력막대
	
	private static int playeratk, playerdef;	// 플레이어 공격력, 방어력
	private static int monsteratk, monsterdef;	// 몬스터 공격력, 방어력
	private DSService service = DSService.getInstance();
	
	/** 메소드 영역 **/
	public GameScreen(String name, String job, int s, int d, int i, int hp, int mp) {
		System.out.println("[info] GameScreen() 호출");
		this.c_name = name; // 캐릭터명
		this.c_job = job; // 직업
		this.c_lv = 1; // 1레벨
		this.c_str = s; // 힘
		this.c_atk = c_str/2; // 캐릭터 공격력은 힘/2
		this.c_dex = d; // 민첩
		this.c_def = c_dex/5; // 캐릭터 방어력은 민첩/5
		this.c_int = i; // 지능
		
		this.c_hp = hp; // 체력
		this.current_user_hp = c_hp; // 플레이어 현재 체력
		this.c_mp = mp+(c_int*2); // 마나는 기본 마나값 + 지능*2
		this.current_user_mp = c_mp; // 플레이어 현재 마나
		
		this.c_exp = 0; // 초기 경험치 보유량 0
		this.c_next_exp = 50; // 다음 경험치 요구량 50
		lowmonsters = service.monsterData("초급"); // 초급 몹 정보 저장
//		middlemonsters = service.monsterData("중급"); // 중급 몹 정보 저장
//		highmonsters = service.monsterData("고급"); // 고급 몹 정보 저장
		if(!lowmonsters.isEmpty()) {
			System.out.println("[info] 초급 몹 정보 가져오기 완료");
		}
//		if(!middlemonsters.isEmpty()) {
//			System.out.println("[info] 중급 몹 정보 가져오기 완료");
//		}
//		if(!highmonsters.isEmpty()) {
//			System.out.println("[info] 고급 몹 정보 가져오기 완료");
//		}
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
		System.out.println(getLocation().x);
		System.out.println(getLocation().y);
		setResizable(false);
		
		// 레이어 설정
		JLayeredPane layer = getLayeredPane();
		
		// 게임화면 테두리
		mainbackgroundimgLabel = new JLabel(new ImageIcon(MAINBACKGROUND));
		mainbackgroundimgLabel.setBounds(5, 5, 1020, 638);
		
		// 게임진행 이미지 라벨
		GameScreenimgLabel = new JLabel(new ImageIcon(BATTLEBACKGROUND));
		GameScreenimgLabel.setBounds(40, 35, 950, 330);
		GameScreenimgLabel.setBorder(new LineBorder(Color.WHITE));
		
		// 캐릭터 이미지 출력되는 패널(체력/마나 막대 + 캐릭터 이미지)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(650,60, 200, 300);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		CharacterPanel.setOpaque(false);
		
		// 캐릭터 이미지 출력하는 Label
		JLabel characterLabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
		characterLabel.setBounds(30, 70, 150, 226);
		
		UIManager.put("ProgressBar.selectionBackground", Color.BLACK); // bar가 채워지기 전 글자 색
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK); // bar가 채워진 후 글자 색
		
		playerHpbar = new JProgressBar(0,c_hp); // 플레이어 캐릭터 체력바
		playerHpbar.setBorderPainted(false);
		playerHpbar.setFont(new Font("맑은 고딕",Font.BOLD, 11));
		playerHpbar.setBackground(Color.WHITE);
		playerHpbar.setForeground(Color.RED);
		playerHpbar.setValue(current_user_hp); // 값은 현재 플레이어 체력
		playerHpbar.setBounds(10, 10, 180, 15);
		playerHpbar.setStringPainted(true);
		playerHpbar.setString(current_user_hp+" / "+c_hp); // JProgressBar 안에 문자열 값 지정(현재 체력 / 총 체력)
		
		playerMpbar = new JProgressBar(0, c_mp); // 플레이어 캐릭터 마나바
		playerMpbar.setBorderPainted(false);
		playerMpbar.setFont(new Font("맑은 고딕",Font.BOLD, 11));
		playerMpbar.setBackground(Color.WHITE);
		playerMpbar.setForeground(Color.BLUE);
		playerMpbar.setValue(current_user_mp); // 값은 현재 플레이어 마나
		playerMpbar.setBounds(10, 25, 180, 15);
		playerMpbar.setStringPainted(true);
		playerMpbar.setString(current_user_mp+" / "+c_mp); // JProgressBar 안에 문자열 값 지정
		
		CharacterPanel.add(playerHpbar);
		CharacterPanel.add(playerMpbar);
		CharacterPanel.add(characterLabel); // 캐릭터 패널에 캐릭터 이미지 추가
		
		// 몹 이미지 출력 패널(몹 체력 막대 + 몹 이미지)
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
		logarea.setText("게임 시작\n");
		logarea.setText("\n모험이 시작되었습니다!\n사악한 드래곤을 무찌르고 최강의 드래곤 슬레이어가 되십시오.\n\n");
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
				if(battle) {
					JLabel message = new JLabel("<html><p style='font-size:14pt; font-family:맑은 고딕;'>전투 중에는 탐색이 불가능합니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "탐색", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				// 1 ~ 3 분기 발생
				switch(createRandom()) {
				case 1: // 전투 발생
					battle = true; // 전투 발생 시 true로 전환, 해당 변수는 전투가 종료되면 다시 false로 바뀜
					System.out.println("[info] 전투 발생");
					createBattle(c_lv);
					break;
				case 2:
					System.out.println("[info] 아이템 획득");
					writeLog("test1\n");
					break;
				case 3:
					System.out.println("[info] 특수 이벤트 발생");
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
					JLabel message = new JLabel("<html><p style='font-size:14pt; font-family:맑은 고딕;'>적이 없으면 공격이 불가능합니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "공격", JOptionPane.WARNING_MESSAGE, null);
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
				// 중복 클릭을 통한 여러 창 띄우는걸 방지하기 위해 해당 버튼을 클릭하면 버튼 비활성화.
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
		int switchnum = createRandom()-1; // 0 ~ 2 랜덤
		if(level >= 1 && level <= 10) { // 1 ~ 10 레벨은 초급 몹
			createLowMonster(switchnum);
		}else if(level >=11 && level <= 20) { // 11 ~ 20 레벨은 중급 몹
			createMiddleMonster(switchnum);
		}else { // 둘 다 아니면 21 ~ 부터니까 고급 몹 생성
			createHighMonster(switchnum);
		}
	}
	
	// 초급 몹 생성
	public void createLowMonster(int swtichnum) {
		switch(swtichnum) {
		case 0: // Skelwarrior
			writeLog(lowmonsters.get(0).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(0).getM_hp();
			m_name = lowmonsters.get(0).getM_name();
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(0).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(SKEL));
			break;
		case 1: // Orcwarrior
			writeLog(lowmonsters.get(1).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(1).getM_hp();
			m_name = lowmonsters.get(1).getM_name();
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(1).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(ORC));
			break;
		case 2: // Golem
			writeLog(lowmonsters.get(2).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(2).getM_hp();
			m_name = lowmonsters.get(2).getM_name();
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(2).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(GOLEM));
			break;
		}
	}
	
	// 중급 몹 생성
	public void createMiddleMonster(int swtichnum) {
		switch(swtichnum) {
		case 0: // SkelKing
			writeLog(lowmonsters.get(0).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(0).getM_hp();
			m_name = lowmonsters.get(0).getM_name();
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(0).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(SKELKING));
			break;
		case 1: // Hatchling
			writeLog(lowmonsters.get(1).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(1).getM_hp();
			m_name = lowmonsters.get(1).getM_name();
			current_monster_hp = lowmonsters.get(1).getM_hp(); // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(1).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(HATCHLING));
			break;
		case 2: // Lagiacrus
			writeLog(lowmonsters.get(2).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(2).getM_hp();
			m_name = lowmonsters.get(2).getM_name();
			current_monster_hp = lowmonsters.get(2).getM_hp(); // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(2).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(LAGIA));
			break;
		}
	}
	
	// 고급 몹 생성
	public void createHighMonster(int swtichnum) {
		switch(swtichnum) {
		case 0: // Drake
			writeLog(lowmonsters.get(0).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(0).getM_hp();
			m_name = lowmonsters.get(0).getM_name();
			current_monster_hp = m_hp; // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(0).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp)+" / "+m_hp);
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(DRAKE));
			break;
		case 1: // Chimera
			writeLog(lowmonsters.get(1).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(1).getM_hp();
			m_name = lowmonsters.get(1).getM_name();
			current_monster_hp = lowmonsters.get(1).getM_hp(); // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(1).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(CHIMERA));
			break;
		case 2: // IceDragon
			writeLog(lowmonsters.get(2).getM_name()+" 이/가 나타났다.\n");
			m_hp = lowmonsters.get(2).getM_hp();
			m_name = lowmonsters.get(2).getM_name();
			current_monster_hp = lowmonsters.get(2).getM_hp(); // 현재 몹 체력에 새로 생성된 몹 체력 저장(새삥)
			MonsterHpbar.setMaximum(lowmonsters.get(2).getM_hp()); // 체력바의 최대수치를 몹 체력으로 설정
			MonsterHpbar.setValue(current_monster_hp);
			MonsterHpbar.setString(String.valueOf(current_monster_hp));
			MonsterHpbar.setVisible(true);
			monsterimgLabel.setIcon(new ImageIcon(ICEDRAGON));
			break;
		}
	}
	
	// 플레이어 공격 메소드
	public void attack_player() {
		writeLog("'" + c_name + "' 의 공격!\n");
		int damage = playeratk - monsterdef; // 데미지는 플레이어 공격력 - 몬스터 방어력
		if(damage <= 0) { // 플레이어 공격력 - 몬스터 방어력의 결과가 0보다 작거나 같을 경우 (= 몬스터의 방어력이 플레이어 공격력보다 높을 경우)
			damage = 1; // 최소 데미지 1이 적용되도록 설정함.
			current_monster_hp -= damage; // damage 수치만큼 몬스터 현재 체력 감소
			writeLog("'" + c_name + "' (은/는) " + m_name + " 에게 " + damage + " 의 피해를 입혔다!\n");
		} else {
			int randomdamage = (int) (Math.random() * damage) + 1; // 1 ~ 플레이어 데미지 사이의 랜덤데미지 결정
			current_monster_hp -= randomdamage; // randomdamage 수치만큼 몹 현재 체력 감소
			writeLog("'" + c_name + "' (은/는) " + m_name + " 에게 " + randomdamage + " 의 피해를 입혔다!\n");
		}
	}
	
	// 몬스터 공격 메소드
	public void attack_monster() {
		writeLog("'" + m_name + "' 의 공격!\n");
		int damage = monsteratk - playerdef; // 데미지는 몬스터 공격력 - 플레이어 방어력
		if(damage <= 0) { // 몬스터 공격력 - 플레이어 방어력의 결과가 0보다 작거나 같을 경우 (= 플레이어의 방어력이 몬스터의 공격력보다 높을 경우)
			damage = 1;
			current_user_hp -= damage; // damage 수치만큼 플레이어 현재 체력 감소
			writeLog("'" + m_name + "' (은/는) " + c_name + " 에게 " + damage + " 의 피해를 입혔다!\n");
		} else {
			int randomdamage = (int) (Math.random() * damage) + 1; // 1 ~ 몬스터 데미지 사이의 랜덤데미지 결정
			current_user_hp -= randomdamage; // randomdamage 수치만큼 플레이어 현재 체력 감
			writeLog("'" + m_name + "' (은/는) " + c_name + " 에게 " + randomdamage + " 의 피해를 입혔다!\n");
		}
	}
}