package dragonslayer;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class InventoryScreen extends JFrame {
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private LinkedList<DSItems> inventorydata;
	private DefaultListModel<String> items = new DefaultListModel<String>();
	private int current_player_hp; // 플레이어 현재 체력
	private Thread check;
	
	public static void main(String[] args) {
		LinkedList<DSItems> testdata = new LinkedList<DSItems>();
		testdata.add(new DSItems("체력 물약", 0, 0, 50, "N"));
		testdata.add(new DSItems("마나 물약", 0, 0, 50, "N"));
		testdata.add(new DSItems("강철 대검", 20, 0, 0, "Y"));
		testdata.add(new DSItems("강철 도끼", 25, 0, 0, "Y"));
		new InventoryScreen(testdata, 50);
	}
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp) { // 해당 객체가 생성될 때 생성자로 현재 플레이어 체력수치도 받아옴.
		System.out.println("[info] 인벤토리(가방)창 열림");
		this.inventorydata = inventory;
		this.current_player_hp = userhp;
		System.out.println("[info] 현재 인벤토리 크기 : "+inventorydata.size());
		setSize(400, 350);
		setLayout(null);
		setResizable(false);
		setLocation(1480, 180);
		setTitle("Inventory");
		setIconImage(iconimage);
		
		
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
				String selecteditem = invenlist.getSelectedValue();
				for(int i=0; i<inventorydata.size(); i++) {
					if(selecteditem.equals(inventorydata.get(i).getI_name())) { // 선택한 아이템값 = 인벤토리 데이터의 아이템명
						System.out.println("아이템의 공격력 : "+inventorydata.get(i).getI_atk());
					}
				}
			}
		});
		add(use);
		
		JButton drop = new JButton("버리기");
		drop.setBounds(260, 110, 110, 50);
		drop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					inventorydata.remove(invenlist.getSelectedIndex());
					items.clear(); // 모델 데이터들을 클리어 시킴
					for(int i=0; i<inventorydata.size(); i++) {
						items.addElement(inventorydata.get(i).getI_name()); // 모델에 요소들 다시 추가함(삭제된 데이터를 다시 추가)
					}
					invenlist.setModel(items);
					System.out.println(items.size());
				}catch(Exception exception) {
					System.out.println("[Error] 인벤토리 버리기 예외 발생");
					JLabel message = new JLabel("<html><p style='font-family:맑은 고딕; font-size:14pt;'>인벤토리가 비어있습니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "버리기", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(drop);

		// 해당 프레임이 닫힐 때 실행되는 windowListener
//		addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				// 해당 프레임이 닫힐 때 GameScreen의 '가방' 버튼을 활성화시킴.
//				GameScreen.setInventorydata(inventorydata);
//				GameScreen.getInvenbutton().setEnabled(true);
//				check.interrupt();
//				System.out.println("[info] 인벤토리 창 닫힘");
//			}
//		});
		
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
						current_player_hp = GameScreen.getPlayerhp(); // Thread가 돌면서 회복반영된 체력수치를 GameScreen에 계속 넘겨준다.
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
