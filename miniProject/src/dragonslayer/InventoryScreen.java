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
	private int current_player_hp; // �÷��̾� ���� ü��
	private Thread check;
	
	public static void main(String[] args) {
		LinkedList<DSItems> testdata = new LinkedList<DSItems>();
		testdata.add(new DSItems("ü�� ����", 0, 0, 50, "N"));
		testdata.add(new DSItems("���� ����", 0, 0, 50, "N"));
		testdata.add(new DSItems("��ö ���", 20, 0, 0, "Y"));
		testdata.add(new DSItems("��ö ����", 25, 0, 0, "Y"));
		new InventoryScreen(testdata, 50);
	}
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp) { // �ش� ��ü�� ������ �� �����ڷ� ���� �÷��̾� ü�¼�ġ�� �޾ƿ�.
		System.out.println("[info] �κ��丮(����)â ����");
		this.inventorydata = inventory;
		this.current_player_hp = userhp;
		System.out.println("[info] ���� �κ��丮 ũ�� : "+inventorydata.size());
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
		invenlist.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JScrollPane invenscroll = new JScrollPane(invenlist);
		invenscroll.setBounds(20, 30, 230, 250);
		add(invenscroll);
		
        JButton use = new JButton("���� / ���");
		use.setBounds(260, 40, 110, 50);
		use.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selecteditem = invenlist.getSelectedValue();
				for(int i=0; i<inventorydata.size(); i++) {
					if(selecteditem.equals(inventorydata.get(i).getI_name())) { // ������ �����۰� = �κ��丮 �������� �����۸�
						System.out.println("�������� ���ݷ� : "+inventorydata.get(i).getI_atk());
					}
				}
			}
		});
		add(use);
		
		JButton drop = new JButton("������");
		drop.setBounds(260, 110, 110, 50);
		drop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					inventorydata.remove(invenlist.getSelectedIndex());
					items.clear(); // �� �����͵��� Ŭ���� ��Ŵ
					for(int i=0; i<inventorydata.size(); i++) {
						items.addElement(inventorydata.get(i).getI_name()); // �𵨿� ��ҵ� �ٽ� �߰���(������ �����͸� �ٽ� �߰�)
					}
					invenlist.setModel(items);
					System.out.println(items.size());
				}catch(Exception exception) {
					System.out.println("[Error] �κ��丮 ������ ���� �߻�");
					JLabel message = new JLabel("<html><p style='font-family:���� ���; font-size:14pt;'>�κ��丮�� ����ֽ��ϴ�.</p></html>");
					JOptionPane.showMessageDialog(null, message, "������", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(drop);

		// �ش� �������� ���� �� ����Ǵ� windowListener
//		addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
//				GameScreen.setInventorydata(inventorydata);
//				GameScreen.getInvenbutton().setEnabled(true);
//				check.interrupt();
//				System.out.println("[info] �κ��丮 â ����");
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
						current_player_hp = GameScreen.getPlayerhp(); // Thread�� ���鼭 ȸ���ݿ��� ü�¼�ġ�� GameScreen�� ��� �Ѱ��ش�.
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
