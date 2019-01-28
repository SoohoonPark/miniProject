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

// ���� ��Ȳ�� �����ִ� â

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// ���� ��Ȳ�� �����ִ� â
@SuppressWarnings("serial")
public class StatScreen extends JFrame{
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image namebgimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/window_namejob.png");
	private String cName; // ĳ���͸�
	private String cJob; // ����
	private int cLv; // ����
	private int cStr, cDex, cInt; // ��,��ø,����
	private int cAtk, cDef; // ���ݷ�, ����
	private int CurrentExp, NextExp; // ���� ����ġ, ���� ����ġ
	
	public StatScreen(String n, String j, int l, int s, int dex, int i, int a, int def, int ce, int ne) {
		System.out.println("[info] ����â ����");
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
		System.out.println("[info] StatScreen �ʵ� �ʱ�ȭ �Ϸ�.");
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
		
		JLabel lbl_cName = new JLabel("<html><p style='font-size:26pt; font-family:���� ���;'>"+cName+"</p></html>",SwingConstants.CENTER);
		lbl_cName.setForeground(Color.white);
		lbl_cName.setBounds(60, 10, 160, 40);
		namelvjob.add(lbl_cName);
		
		JLabel lbl_cJobLv = new JLabel("<html><p style='font-size:20pt; font-family:���� ���;'>Lv "+cLv+" "+cJob+"</p></html>",SwingConstants.CENTER);
		lbl_cJobLv.setForeground(Color.ORANGE);
		lbl_cJobLv.setBounds(20, 50, 240, 40);
		namelvjob.add(lbl_cJobLv);
		
		layer.add(namelvjob, new Integer(1));
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
				//GameScreen.getEquipbutton().setEnabled(true);
			}
		});
	}
	
//	public static void main(String[] args) {
//		new StatScreen("�����","���谡",1,1,1,1,1,1,1,50);
//	}
}
