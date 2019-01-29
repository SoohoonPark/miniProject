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
	private int current_player_hp; // 플레이어 현재 체력
	private Thread check;
	
//	public static void main(String[] args) {
//		LinkedList<DSItems> testdata = new LinkedList<DSItems>();
//		testdata.add(new DSItems("체력 물약", 0, 0, 50, "N"));
//		testdata.add(new DSItems("마나 물약", 0, 0, 50, "N"));
//		testdata.add(new DSItems("강철 대검", 20, 0, 0, "Y"));
//		testdata.add(new DSItems("강철 도끼", 25, 0, 0, "Y"));
//		new InventoryScreen(testdata, 50);
//	}
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp) {
		System.out.println("[info] 인벤토리(가방)창 열림");
		this.inventorydata = inventory;
		System.out.println("[info] 현재 인벤토리 크기 : "+inventorydata.size());
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
		invenlist.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JScrollPane invenscroll = new JScrollPane(invenlist);
		invenscroll.setBounds(20, 30, 230, 250);
		add(invenscroll);
		
        JButton use = new JButton("장착 / 사용");
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
		
		JButton drop = new JButton("버리기");
		drop.setBounds(260, 110, 110, 50);
		add(drop);

		// 해당 프레임이 닫힐 때 실행되는 windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 해당 프레임이 닫힐 때 GameScreen의 '가방' 버튼을 활성화시킴.
				GameScreen.setInventorydata(inventorydata);
				GameScreen.getInvenbutton().setEnabled(true);
				check.interrupt();
				System.out.println("[info] 인벤토리 창 닫힘");
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
						System.out.println("[Error] 쓰레드 interrupt 발생");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		check.start();
	}
}
