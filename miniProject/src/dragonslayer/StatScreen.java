package dragonslayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// ���� ��Ȳ�� �����ִ� â
@SuppressWarnings("serial")
public class StatScreen extends JFrame{
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image namebgimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/window_namejob.png");
	private Image statbgimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/window_stat.png");
	private Image iconstr = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statstr.png");
	private Image icondex = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statdex.png");
	private Image iconint = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statint.png");
	private Image iconatk = Toolkit.getDefaultToolkit().createImage("resource/images/icon/iconatk.png");
	private Image icondef = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statdef.png");
	private Image iconexp = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statexp.png");
	
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
		setSize(305, 460);
		setTitle("Status");
		setIconImage(iconimage);
		setResizable(false);
		setLocation(135, 180);
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
		
		BackgroundImagePanel stat = new BackgroundImagePanel(statbgimage);
		stat.setBounds(10, 115, 280, 300);
		stat.setOpaque(false);
		
		JLabel lbl_cStr = new JLabel("<html><p style='font-size:15pt; color:white; font-family:���� ���;'>&nbsp;&nbsp;��&nbsp;"+cStr+"</p></html>");
		lbl_cStr.setIcon(new ImageIcon(iconstr));
		lbl_cStr.setBounds(70, 20, 150, 40);
		
		JLabel lbl_cDex = new JLabel("<html><p style='font-size:15pt; color:white; font-family:���� ���;'>&nbsp;&nbsp;��ø&nbsp;"+cDex+"</p></html>");
		lbl_cDex.setIcon(new ImageIcon(icondex));
		lbl_cDex.setBounds(70, 60, 150, 40);
		
		JLabel lbl_cInt = new JLabel("<html><p style='font-size:15pt; color:white; font-family:���� ���;'>&nbsp;&nbsp;����&nbsp;"+cInt+"</p></html>");
		lbl_cInt.setIcon(new ImageIcon(iconint));
		lbl_cInt.setBounds(70, 100, 150, 40);
		
		JLabel lbl_cAtk = new JLabel("<html><p style='font-size:15pt; color:white; font-family:���� ���;'>&nbsp;&nbsp;���ݷ�&nbsp;"+cAtk+"</p></html>");
		lbl_cAtk.setIcon(new ImageIcon(iconatk));
		lbl_cAtk.setBounds(70, 140, 150, 40);
		
		JLabel lbl_cDef = new JLabel("<html><p style='font-size:15pt; color:white; font-family:���� ���;'>&nbsp;&nbsp;����&nbsp;"+cDef+"</p></html>");
		lbl_cDef.setIcon(new ImageIcon(icondef));
		lbl_cDef.setBounds(70, 180, 150, 40);
		
		JLabel lbl_cExp = new JLabel("<html><p style='font-size:15pt; color:white; font-family:���� ���;'>&nbsp;&nbsp;����ġ&nbsp;"+CurrentExp+" / "+NextExp+"</p></html>");
		lbl_cExp.setIcon(new ImageIcon(iconexp));
		lbl_cExp.setBounds(70, 220, 180, 40);
			
		stat.add(lbl_cStr);
		stat.add(lbl_cDex);
		stat.add(lbl_cInt);
		stat.add(lbl_cAtk);
		stat.add(lbl_cDef);
		stat.add(lbl_cExp);
		
		layer.add(namelvjob, new Integer(1));
		layer.add(stat, new Integer(1));
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
				System.out.println("[info] ����â ����");
				GameScreen.getStatbutton().setEnabled(true);
			}
		});
	}
}
