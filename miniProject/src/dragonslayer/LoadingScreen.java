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

@SuppressWarnings("serial")
public class LoadingScreen extends JFrame {
	private Image loading = Toolkit.getDefaultToolkit().createImage("resource/images/background/LoadingScreen.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private JProgressBar loadingbar;
	private String name;
	private final static int STR = 10, DEX = 10, INT = 10, HP = 100, MP = 50; 
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
		
		// 레이어 패널 생성
		JLayeredPane layer = getLayeredPane();
		
		JLabel mainimageLabel = new JLabel(new ImageIcon(loading));
		mainimageLabel.setBounds(0, 0, 900, 506);
		
		UIManager.put("ProgressBar.background", Color.WHITE);
		UIManager.put("ProgressBar.foreground", Color.BLUE);
		UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
		UIManager.put("ProgressBar.selectionForeground", Color.YELLOW);
		loadingbar = new JProgressBar(0, 100);
		loadingbar.setBounds(600, 400, 250, 20);
		loadingbar.setValue(0);
		loadingbar.setStringPainted(true);
		
		layer.add(mainimageLabel, new Integer(1));
		layer.add(loadingbar, new Integer(2));
		
		setVisible(true);
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
						UIManager.put("ProgressBar.foreground", Color.BLUE);
						loadingbar.setString(String.valueOf(loading)+" %");
						if(loading == 100) {
							Thread.sleep(500);
							new GameScreen(name, STR, DEX, INT, HP, MP);
							dispose();
							Thread.currentThread().interrupt();
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
