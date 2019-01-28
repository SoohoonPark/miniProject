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

// ½ºÅÈ ÇöÈ²À» º¸¿©ÁÖ´Â Ã¢
@SuppressWarnings("serial")
public class StatScreen extends JFrame{
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image namebgimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/window_namejob.png");
	private Image statbgimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/window_stat.png");
	private Image iconstr = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statstr.png");
	private Image icondex = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statdex.png");
	private Image iconint = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statint.png");
	private Image iconatk = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statatk.png");
	private Image icondef = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statdef.png");
	private Image iconexp = Toolkit.getDefaultToolkit().createImage("resource/images/icon/statexp.png");
	private String cName; // Ä³¸¯ÅÍ¸í
	private String cJob; // Á÷¾÷
	private int cLv; // ·¹º§
	private int cStr, cDex, cInt; // Èû,¹ÎÃ¸,Áö´É
	private int cAtk, cDef; // °ø°Ý·Â, ¹æ¾î·Â
	private int CurrentExp, NextExp; // ÇöÀç °æÇèÄ¡, ´ÙÀ½ °æÇèÄ¡
	
	public StatScreen(String n, String j, int l, int s, int dex, int i, int a, int def, int ce, int ne) {
		System.out.println("[info] ½ºÅÝÃ¢ ¿­¸²");
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
		System.out.println("[info] StatScreen ÇÊµå ÃÊ±âÈ­ ¿Ï·á.");
		createStatScreen();
	}
	
	void createStatScreen() {
		setSize(305, 460);
		setTitle("Status");
		setLocation(135, 180);
		setIconImage(iconimage);
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		JLayeredPane layer = getLayeredPane();
		BackgroundImagePanel namelvjob = new BackgroundImagePanel(namebgimage);
		namelvjob.setBounds(10, 10, 280, 100);
		namelvjob.setOpaque(false);
		
		JLabel lbl_cName = new JLabel("<html><p style='font-size:26pt; font-family:¸¼Àº °íµñ;'>"+cName+"</p></html>",SwingConstants.CENTER);
		lbl_cName.setForeground(Color.white);
		lbl_cName.setBounds(60, 10, 160, 40);
		namelvjob.add(lbl_cName);
		
		JLabel lbl_cJobLv = new JLabel("<html><p style='font-size:20pt; font-family:¸¼Àº °íµñ;'>Lv "+cLv+" "+cJob+"</p></html>",SwingConstants.CENTER);
		lbl_cJobLv.setForeground(Color.ORANGE);
		lbl_cJobLv.setBounds(20, 50, 240, 40);
		namelvjob.add(lbl_cJobLv);
		
		BackgroundImagePanel stat = new BackgroundImagePanel(statbgimage);
		stat.setBounds(10, 120, 280, 304);
		stat.setOpaque(false);
		
		UIManager.put("Label.foreground", Color.WHITE);
		JLabel lbl_str = new JLabel("<html><p style='font-size:14pt; font-family:¸¼Àº °íµñ;'>&nbsp;Èû "+cStr+"</p></html>");
		lbl_str.setIcon(new ImageIcon(iconstr));
		lbl_str.setBounds(85, 20, 120, 40);
		
		JLabel lbl_dex = new JLabel("<html><p style='font-size:14pt; font-family:¸¼Àº °íµñ;'>&nbsp;¹ÎÃ¸ "+cDex+"</p></html>");
		lbl_dex.setIcon(new ImageIcon(icondex));
		lbl_dex.setBounds(85, 60, 120, 40);
		
		JLabel lbl_int = new JLabel("<html><p style='font-size:14pt; font-family:¸¼Àº °íµñ;'>&nbsp;Áö´É "+cInt+"</p></html>");
		lbl_int.setIcon(new ImageIcon(iconint));
		lbl_int.setBounds(85, 100, 120, 40);
		
		JLabel lbl_atk = new JLabel("<html><p style='font-size:14pt; font-family:¸¼Àº °íµñ;'>&nbsp;°ø°Ý·Â "+cAtk+"</p></html>");
		lbl_atk.setIcon(new ImageIcon(iconatk));
		lbl_atk.setBounds(85, 140, 120, 40);
		
		JLabel lbl_def = new JLabel("<html><p style='font-size:14pt; font-family:¸¼Àº °íµñ;'>&nbsp;¹æ¾î·Â "+cDef+"</p></html>");
		lbl_def.setIcon(new ImageIcon(icondef));
		lbl_def.setBounds(85, 180, 120, 40);
		
		JLabel lbl_exp = new JLabel("<html><p style='font-size:14pt; font-family:¸¼Àº °íµñ;'>&nbsp;°æÇèÄ¡ "+CurrentExp+" / "+NextExp+"</p></html>");
		lbl_exp.setIcon(new ImageIcon(iconexp));
		lbl_exp.setBounds(85, 220, 200, 40);
		
		stat.add(lbl_str);
		stat.add(lbl_dex);
		stat.add(lbl_int);
		stat.add(lbl_atk);
		stat.add(lbl_def);
		stat.add(lbl_exp);
		
		layer.add(namelvjob, new Integer(1));
		layer.add(stat, new Integer(1));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new StatScreen("»ï½ÄÀÌ","¸ðÇè°¡",1,1,1,1,1,1,1,50);
	}
}
