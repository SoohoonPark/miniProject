package dragonslayer;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SkillScreen extends JFrame {
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	
	public SkillScreen() {
		setSize(430, 350);
		setLayout(null);
		setLocation(1480, 180);
		setTitle("Equipment");
		setIconImage(iconimage);
		
		// 
	}
	
	public static void main(String[] args) {
		new SkillScreen();
	}
}
