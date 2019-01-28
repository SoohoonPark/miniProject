package dragonslayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

// 스탯 현황을 보여주는 창

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// 스탯 현황을 보여주는 창
@SuppressWarnings("serial")
public class StatScreen extends JFrame{
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image namebgimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/window_namejob.png");
	private String cName; // 캐릭터명
	private String cJob; // 직업
	private int cLv; // 레벨
	private int cStr, cDex, cInt; // 힘,민첩,지능
	private int cAtk, cDef; // 공격력, 방어력
	private int CurrentExp, NextExp; // 현재 경험치, 다음 경험치
	
	public StatScreen(String n, String j, int l, int s, int dex, int i, int a, int def, int ce, int ne) {
		System.out.println("[info] 스텟창 열림");
		this.cName = n;
		this.cJob = j;
		this.cLv = l;
		this.cStr = s;
		this.cDex = dex;
		this.cInt = i;
		this.cAtk = a;
		this.cDef = def;
		this.CurrentExp = ce;
		this.NextExp = ne;
		System.out.println("[info] StatScreen 필드 초기화 완료.");
		createStatScreen();
	}
	
	void createStatScreen() {
		setSize(305, 400);
		setTitle("Status");
		setIconImage(iconimage);
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		JLayeredPane layer = getLayeredPane();
		BackgroundImagePanel namelvjob = new BackgroundImagePanel(namebgimage);
		namelvjob.setBounds(10, 10, 280, 100);
		namelvjob.setOpaque(false);
		
		JLabel lbl_cName = new JLabel("<html><p style='font-size:26pt; font-family:맑은 고딕;'>"+cName+"</p></html>",SwingConstants.CENTER);
		lbl_cName.setForeground(Color.white);
		lbl_cName.setBounds(60, 10, 160, 40);
		namelvjob.add(lbl_cName);
		
		JLabel lbl_cJobLv = new JLabel("<html><p style='font-size:20pt; font-family:맑은 고딕;'>Lv "+cLv+" "+cJob+"</p></html>",SwingConstants.CENTER);
		lbl_cJobLv.setForeground(Color.ORANGE);
		lbl_cJobLv.setBounds(20, 50, 240, 40);
		namelvjob.add(lbl_cJobLv);
		
		layer.add(namelvjob, new Integer(1));
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 해당 프레임이 닫힐 때 GameScreen의 '가방' 버튼을 활성화시킴.
				//GameScreen.getEquipbutton().setEnabled(true);
			}
		});
	}
	
//	public static void main(String[] args) {
//		new StatScreen("삼식이","모험가",1,1,1,1,1,1,1,50);
//	}
}
