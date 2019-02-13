package dragonslayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class LoadingScreen extends JFrame {
	private Image loading = Toolkit.getDefaultToolkit().createImage("resource/images/background/LoadingScreen/loadingbackground.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private JProgressBar loadingbar;
	
	private String name; // 캐릭터명
	private final static int STR = 10, DEX = 10, INT = 10, HP = 100, MP = 50; // 기본 스탯
	private final static String JOB = "모험가"; // 초기 직업(모험가 - 전사 - 기사)

	public LoadingScreen(String n) {
		this.name = n;
		CreateLoadingScreen();
		loadingbarThread();
	}
	
	public void CreateLoadingScreen() {
		setTitle("Dragon Slayer");
		setSize(900, 506);
		setLocationRelativeTo(null);	// 화면을 중앙에 띄우는 명령어
		setResizable(false);			// 화면 크기 조정 방지 명령어
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(iconimage);
		setVisible(true);
		
		// 레이어 패널 생성
		JLayeredPane layer = getLayeredPane();
		
		JLabel mainimageLabel = new JLabel(new ImageIcon(loading));
		mainimageLabel.setBounds(0, 0, 900, 506);
		
		UIManager.put("ProgressBar.background", Color.BLACK); // bar가 채워지기 전 배경 색
		UIManager.put("ProgressBar.foreground", Color.ORANGE); // bar가 채워진 후 배경 색
		UIManager.put("ProgressBar.selectionBackground", Color.GRAY); // bar가 채워지기 전 글자 색
		UIManager.put("ProgressBar.selectionForeground", Color.GRAY); // bar가 채워진 후 글자 색
		loadingbar = new JProgressBar(0, 100);
		loadingbar.setBounds(600, 400, 250, 18);
		loadingbar.setBorder(new LineBorder(Color.WHITE));
		loadingbar.setValue(0);
		loadingbar.setStringPainted(true);
		
		layer.add(mainimageLabel, new Integer(1));
		layer.add(loadingbar, new Integer(2));
	}
	
	void loadingbarThread() {
		Thread loading = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("[info] loading Thread start!");
				int loading = 0;
				while(!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(100);
						loading+=2;
						loadingbar.setValue(loading);
						if(loading == 100) {
							Thread.sleep(500);
							new GameScreen(name,1,JOB,STR,DEX,INT,HP,MP); // 게임스크린 클래스에 캐릭터명,레벨,직업,힘,민첩,지능,체력,마나 값을 넘김.
							dispose();
							Thread.currentThread().interrupt();	// loadingbar가 100이 되면 스레드 정지
							System.out.println("[info] loading Thread is interrupted!");
						}
					}catch(Exception e) {
						System.out.println("[Error] 스레드 에러");
					}
				}
			}
		});
		loading.start();
	}
}
