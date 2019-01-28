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
        l1.addElement("��ö ���"); 
        l1.addElement("���� �尩");
        l1.addElement("���� ����");
        l1.addElement("���� �尩");
        
        JButton use = new JButton("���� / ���");
		use.setBounds(260, 40, 110, 50);
		JButton drop = new JButton("������");
		drop.setBounds(260, 110, 110, 50);
		final JList<String> invenlist = new JList<>(l1);
		invenlist.setBounds(20, 30, 230, 250);
		invenlist.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		add(use);
		add(drop);
		add(invenlist);
		
		// �ش� �������� ���� �� ����Ǵ� windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
				GameScreen.getInvenbutton().setEnabled(true);
			}
		});
		
		setVisible(true);
	}
	
	
//	public static void main(String[] args) {
//		new InventoryScreen();
//	}
}
