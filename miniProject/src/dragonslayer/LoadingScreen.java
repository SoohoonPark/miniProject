package dragonslayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class LoadingScreen extends JFrame {
	private Image loading = Toolkit.getDefaultToolkit().createImage("resource/images/background/LoadingScreen.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private static JProgressBar loadingbar = new JProgressBar(0, 100);
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
		setLocationRelativeTo(null);	// ȭ���� �߾ӿ� ���� ��ɾ�
		setResizable(false);			// ȭ�� ũ�� ���� ���� ��ɾ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(iconimage);
		setVisible(true);
		
		// ���̾� �г� ����
		JLayeredPane layer = getLayeredPane();
		
		JLabel mainimageLabel = new JLabel(new ImageIcon(loading));
		mainimageLabel.setBounds(0, 0, 900, 506);
		
		loadingbar.setBounds(600, 400, 250, 20);
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
							new GameScreen(name, STR, DEX, INT, HP, MP);
							dispose();
							Thread.currentThread().interrupt();	// loadingbar�� 100�� �Ǹ� ������ ����
						}
					}catch(Exception e) {
						System.out.println("[Error] ������ ����");
					}
				}
			}
		});
		loading.start();
	}
}
