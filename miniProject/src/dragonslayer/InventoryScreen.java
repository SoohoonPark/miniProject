package dragonslayer;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class InventoryScreen extends JFrame {
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private LinkedList<DSItems> inventorydata;
	private int current_player_hp; // �÷��̾� ���� ü��
	private Thread check;
	
//	public static void main(String[] args) {
//		LinkedList<DSItems> testdata = new LinkedList<DSItems>();
//		testdata.add(new DSItems("ü�� ����", 0, 0, 50, "N"));
//		testdata.add(new DSItems("���� ����", 0, 0, 50, "N"));
//		testdata.add(new DSItems("��ö ���", 20, 0, 0, "Y"));
//		testdata.add(new DSItems("��ö ����", 25, 0, 0, "Y"));
//		new InventoryScreen(testdata, 50);
//	}
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp) {
		System.out.println("[info] �κ��丮(����)â ����");
		this.inventorydata = inventory;
		System.out.println("[info] ���� �κ��丮 ũ�� : "+inventorydata.size());
		setSize(400, 350);
		setLayout(null);
		setResizable(false);
		setLocation(1480, 180);
		setTitle("Inventory");
		setIconImage(iconimage);
		
		DefaultListModel<String> items = new DefaultListModel<String>();
		for(int i=0; i<inventorydata.size(); i++) {
			items.addElement(inventorydata.get(i).getI_name());
		}
		JList<String> invenlist = new JList<>(items);
		invenlist.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JScrollPane invenscroll = new JScrollPane(invenlist);
		invenscroll.setBounds(20, 30, 230, 250);
		add(invenscroll);
		
        JButton use = new JButton("���� / ���");
		use.setBounds(260, 40, 110, 50);
		use.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println();
				for(int i=0; i<inventorydata.size(); i++) {
					if(invenlist.getSelectedValue().equals(inventorydata.get(i).getI_name())) {
						
					}
				}
			}
		});
		add(use);
		
		JButton drop = new JButton("������");
		drop.setBounds(260, 110, 110, 50);
		add(drop);

		// �ش� �������� ���� �� ����Ǵ� windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
				GameScreen.setInventorydata(inventorydata);
				GameScreen.getInvenbutton().setEnabled(true);
				check.interrupt();
				System.out.println("[info] �κ��丮 â ����");
			}
		});
		
		System.out.println(isFocused());
		
		checkPlayerhp();
		setVisible(true);
	}
	
	void checkPlayerhp() {
		check = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(500);
						current_player_hp = GameScreen.getPlayerhp();
						System.out.println(current_player_hp);
					}catch(InterruptedException e) {
						System.out.println("[Error] ������ interrupt �߻�");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		check.start();
	}
}
