package dragonslayer;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

@SuppressWarnings("serial")
public class InventoryScreen extends JFrame {
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	
	public InventoryScreen() {
		setSize(400, 350);
		setLayout(null);
		setResizable(false);
		setLocation(1480, 180);
		setTitle("Inventory");
		setIconImage(iconimage);
		
		final DefaultListModel<String> l1 = new DefaultListModel<>();  
        l1.addElement("°­Ã¶ ´ë°Ë"); 
        l1.addElement("°¡Á× Àå°©");
        l1.addElement("°¡Á× ¸ðÀÚ");
        l1.addElement("°¡Á× Àå°©");
        
        JButton use = new JButton("ÀåÂø / »ç¿ë");
		use.setBounds(260, 40, 110, 50);
		JButton drop = new JButton("¹ö¸®±â");
		drop.setBounds(260, 110, 110, 50);
		final JList<String> invenlist = new JList<>(l1);
		invenlist.setBounds(20, 30, 230, 250);
		invenlist.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		
		add(use);
		add(drop);
		add(invenlist);
		
		// ÇØ´ç ÇÁ·¹ÀÓÀÌ ´ÝÈú ¶§ ½ÇÇàµÇ´Â windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// ÇØ´ç ÇÁ·¹ÀÓÀÌ ´ÝÈú ¶§ GameScreenÀÇ '°¡¹æ' ¹öÆ°À» È°¼ºÈ­½ÃÅ´.
				GameScreen.getInvenbutton().setEnabled(true);
			}
		});
		
		setVisible(true);
	}
	
	
//	public static void main(String[] args) {
//		new InventoryScreen();
//	}
}
