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
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class InventoryScreen extends JFrame {
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private LinkedList<DSItems> inventorydata;
	private DefaultListModel<String> items = new DefaultListModel<String>();
	private JList<String> invenlist;
	private int current_player_maxhp; // �÷��̾� �� ü��(ü�� ���� �Ծ����� �ִ� ü�� �̻����� ȸ�� �Ұ���)
	private int current_player_maxmp; // �÷��̾� �� ����(���� ���� �Ծ����� �ִ� ���� �̻����� ȸ�� �Ұ���)
	private int current_player_hp; // �÷��̾� ���� ü��
	private int current_player_mp; // �÷��̾� ���� ����
	private int equipatk, equipdef; // �����ݷ� & ����
	private int def_helmet, def_armor, def_glove, def_shoes; // ����,����,�尩,�Ź� ����(�ش� ���µ��� ���������� �ջ��ϸ� ������������ ��)
	private Thread check, equip; // �÷��̾� ü&��, ��&�� üũ Thread
	private JButton use;
	
	public static void main(String[] args) {
		LinkedList<DSItems> testdata = new LinkedList<DSItems>();
		testdata.add(new DSItems("ü�� ����", 0, 0, 50, "N"));
		testdata.add(new DSItems("���� ����", 0, 0, 50, "N"));
		testdata.add(new DSItems("��ö ���", 20, 0, 0, "Y"));
		testdata.add(new DSItems("��ö ����", 25, 0, 0, "Y"));
		testdata.add(new DSItems("ü�� ����", 0, 0, 50, "N"));
		testdata.add(new DSItems("���� ����", 0, 0, 50, "N"));
		testdata.add(new DSItems("��ö ���", 20, 0, 0, "Y"));
		testdata.add(new DSItems("��ö ����", 25, 0, 0, "Y"));
		testdata.add(new DSItems("ü�� ����", 0, 0, 50, "N"));
		testdata.add(new DSItems("���� ����", 0, 0, 50, "N"));
		testdata.add(new DSItems("��ö ���", 20, 0, 0, "Y"));
		testdata.add(new DSItems("��ö ����", 25, 0, 0, "Y"));
		new InventoryScreen(testdata, 25, 20, 100, 50); // ü�� 25/100, ���� 20/50
	}
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp, int usermp, int maxhp, int maxmp) { // �ش� ��ü�� ������ �� �����ڷ� ���� �÷��̾� ü�¼�ġ�� �޾ƿ�.
		System.out.println("[info] �κ��丮(����)â ����");
		this.inventorydata = inventory;
		this.current_player_hp = userhp;
		this.current_player_mp = usermp;
		this.current_player_maxhp = maxhp;
		this.current_player_maxmp = maxmp;
		
		System.out.println("[info] ���� �κ��丮 ũ�� : "+inventorydata.size());
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setLocation(1480, 180);
		setTitle("Inventory");
		setIconImage(iconimage);
		
		
		for(int i=0; i<inventorydata.size(); i++) {
			items.addElement(inventorydata.get(i).getI_name());
		}
		invenlist = new JList<>(items);
		invenlist.setFont(new Font("���� ���", Font.PLAIN, 14));
		invenlist.setSelectedIndex(0);
		
		JScrollPane invenscroll = new JScrollPane(invenlist);
		invenscroll.setBounds(20, 30, 230, 200);
		add(invenscroll);
		
        use = new JButton("���� / ���");
		use.setBounds(260, 40, 110, 50);
		use.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selecteditemindex = invenlist.getSelectedIndex();
				if(inventorydata.get(selecteditemindex).getI_equip().equals("N")) { // N = ���Ұ���(=����)
					System.out.println("[info] ���� ���");
					/** ���� ��� ���� **/
					if(inventorydata.get(selecteditemindex).getI_name().equals("ü�� ����")) {
						System.out.println("[info] ü�� ���� ���");
						if(current_player_hp == current_player_maxhp) {
							JLabel message = new JLabel("<html><p style='font-family:���� ���; font-size:14pt;'>ü���� ���� ���ֽ��ϴ�.</p></html>");
							JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "����", JOptionPane.ERROR_MESSAGE);
							return;
						}
						// ü�� ȸ���� ���� �ִ� ü���� �Ѿ �� ����.
						// 70/100 �� ��� 30�� ȸ��. 50/100 �� ��� 50 ȸ��.....
						int requireRegenhp = current_player_maxhp-current_player_hp; // �� ü�� - ���� ü�� = ȸ���ؾ� �� ü��
						if(requireRegenhp <= inventorydata.get(selecteditemindex).getI_regen()) { // ȸ���ؾ��� ü�� < ������ ȸ����
							current_player_hp += requireRegenhp;
						}else {
							current_player_hp += inventorydata.get(selecteditemindex).getI_regen();
						}
						inventorydata.remove(selecteditemindex);
						refreshItemList();
						
					}else if(inventorydata.get(selecteditemindex).getI_name().equals("���� ����")) {
						System.out.println("[info] ���� ���� ���");
						if(current_player_mp == current_player_maxmp) {
							JLabel message = new JLabel("<html><p style='font-family:���� ���; font-size:14pt;'>������ ���� ���ֽ��ϴ�.</p></html>");
							JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "����", JOptionPane.ERROR_MESSAGE);
							return;
						}
						int requireRegenmp = current_player_maxmp-current_player_mp; // �� ü�� - ���� ü�� = ȸ���ؾ� �� ü��
						if(requireRegenmp <= inventorydata.get(selecteditemindex).getI_regen()) { // ȸ���ؾ��� ü�� < ������ ȸ����
							current_player_mp += requireRegenmp;
						}else {
							current_player_mp += inventorydata.get(selecteditemindex).getI_regen();
						}
						inventorydata.remove(selecteditemindex);
						refreshItemList();
					}
				}else {
					System.out.println("[info] ��� ����");
					if(inventorydata.get(selecteditemindex).getI_name().contains("��ö")) {
						System.out.println("������ �����۸� : "+inventorydata.get(selecteditemindex).getI_name());
						System.out.println("������ ������ ���ݷ� : "+inventorydata.get(selecteditemindex).getI_atk());
						System.out.println("������ ������ ���� : "+inventorydata.get(selecteditemindex).getI_def());
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
					refreshItemList();
				}catch(Exception exception) {
					System.out.println("[Error] �κ��丮 ������ ���� �߻�");
					JLabel message = new JLabel("<html><p style='font-family:���� ���; font-size:14pt;'>�κ��丮�� ����ֽ��ϴ�.</p></html>");
					JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "������", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(drop);

		// �ش� �������� ���� �� ����Ǵ� windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
//				GameScreen.setInventorydata(inventorydata);
//				GameScreen.getInvenbutton().setEnabled(true);
				check.interrupt();
				System.out.println("[info] �κ��丮 â ����");
			}
		});
		
		setPlayerhpmp();
		setVisible(true);
	}
	
	// ȸ���� ü��&������ GameScreen�� �����ϴ� Thread
	void setPlayerhpmp() {
		check = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(500);
						System.out.println("���� �÷��̾� ü�� :"+current_player_hp);
						System.out.println("���� �÷��̾� ���� :"+current_player_mp);
						GameScreen.setPlayerhpmp(current_player_hp, current_player_mp);
					}catch(InterruptedException e) {
						System.out.println("[Error] ������ interrupt �߻�");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		check.start();
	}
	
	// �����ݷ�&������ �ݿ��� ������ GameScreen�� �����ϴ� Thread
	void setPlayeratkdef() {
		equip = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(500);
						System.out.println("������ ��� ���ݷ� :"+equipatk);
						System.out.println("������ ��� ���� :"+equipatk);
//						GameScreen.setPlayerEquipatk(equipatk, equipdef);
					}catch(InterruptedException e) {
						System.out.println("[Error] ������ interrupt �߻�");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		equip.start();
	}
	
	// �κ��丮 ����Ʈ�� ���ΰ�ħ
	void refreshItemList() {
		items.clear();
		for(int i=0; i<inventorydata.size(); i++) {
			items.addElement(inventorydata.get(i).getI_name()); // �𵨿� ��ҵ� �ٽ� �߰���(������ �����͸� �ٽ� �߰�)
		}
		invenlist.setModel(items);
		invenlist.setSelectedIndex(0);
	}
}
