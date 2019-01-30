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
	private int def_helmet, def_armor, def_glove, def_boots; // ����,����,�尩,�Ź� ����(�ش� ���µ��� ���������� �ջ��ϸ� ������������ ��)
	private String w_name, h_name, a_name, g_name, b_name; // ����,����,����,�尩,�Ź� �����۸�
	private Thread check, atkdef, equip; // �÷��̾� ü&��, ��&�� üũ, ���� üũ Thread
	private JButton use, drop;

//	public static void main(String[] args) {
//		LinkedList<DSItems> testdata = new LinkedList<DSItems>();
//		testdata.add(new DSItems("ü�� ����",0,0,50,"N","N"));
//		testdata.add(new DSItems("���� ����",0,0,50,"N","N"));
//		testdata.add(new DSItems("��ö ���",20,0,0,"Y","W"));
//		testdata.add(new DSItems("��ö ����",25,0,0,"Y","W"));
//		testdata.add(new DSItems("���� ����",0,2,0,"Y","H"));
//		testdata.add(new DSItems("���� ����",0,5,0,"Y","A"));
//		testdata.add(new DSItems("���� �尩",0,3,0,"Y","G"));
//		testdata.add(new DSItems("���� �Ź�",0,4,0,"Y","B"));
//		testdata.add(new DSItems("��ö ����",0,5,0,"Y","H"));
//		testdata.add(new DSItems("��ö ����",0,12,0,"Y","A"));
//		testdata.add(new DSItems("��ö �尩",0,7,0,"Y","G"));
//		testdata.add(new DSItems("��ö ��ȭ",0,9,0,"Y","B"));
//		new InventoryScreen(testdata, 25, 20, 100, 50); // ü�� 25/100, ���� 20/50
//	}
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp, int usermp, int maxhp, int maxmp, int atk, int def) {
		System.out.println("[info] �κ��丮(����)â ����");
		this.inventorydata = inventory;
		this.current_player_hp = userhp;
		this.current_player_mp = usermp;
		this.current_player_maxhp = maxhp;
		this.current_player_maxmp = maxmp;
		this.equipatk = atk; // �÷��̾� ���� ��� ���ݷ�
		this.equipdef = def; // �÷��̾� ���� ��� ����

		System.out.println("[info] ���� �κ��丮 ũ�� : " + inventorydata.size());
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setLocation(1480, 180);
		setTitle("Inventory");
		setIconImage(iconimage);

		for (int i = 0; i < inventorydata.size(); i++) {
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
				if (inventorydata.get(selecteditemindex).getI_equip().equals("N")) { // N = ���Ұ���(=����)
					System.out.println("[info] ���� ���");
					/** ���� ��� ���� **/
					if (inventorydata.get(selecteditemindex).getI_name().equals("ü�� ����")) {
						System.out.println("[info] ü�� ���� ���");
						if (current_player_hp == current_player_maxhp) {
							JLabel message = new JLabel(
									"<html><p style='font-family:���� ���; font-size:14pt;'>ü���� ���� ���ֽ��ϴ�.</p></html>");
							JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "����",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						// ü�� ȸ���� ���� �ִ� ü���� �Ѿ �� ����.
						// 70/100 �� ��� 30�� ȸ��. 50/100 �� ��� 50 ȸ��.....
						int requireRegenhp = current_player_maxhp - current_player_hp; // �� ü�� - ���� ü�� = ȸ���ؾ� �� ü��
						if (requireRegenhp <= inventorydata.get(selecteditemindex).getI_regen()) { // ȸ���ؾ��� ü�� < ������ ȸ����
							current_player_hp += requireRegenhp;
						} else {
							current_player_hp += inventorydata.get(selecteditemindex).getI_regen();
						}
						inventorydata.remove(selecteditemindex);
						refreshItemList();

					} else if (inventorydata.get(selecteditemindex).getI_name().equals("���� ����")) {
						System.out.println("[info] ���� ���� ���");
						if (current_player_mp == current_player_maxmp) {
							JLabel message = new JLabel(
									"<html><p style='font-family:���� ���; font-size:14pt;'>������ ���� ���ֽ��ϴ�.</p></html>");
							JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "����",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						int requireRegenmp = current_player_maxmp - current_player_mp; // �� ü�� - ���� ü�� = ȸ���ؾ� �� ü��
						if (requireRegenmp <= inventorydata.get(selecteditemindex).getI_regen()) { // ȸ���ؾ��� ü�� < ������ ȸ����
							current_player_mp += requireRegenmp;
						} else {
							current_player_mp += inventorydata.get(selecteditemindex).getI_regen();
						}
						inventorydata.remove(selecteditemindex);
						refreshItemList();
					}
				} else {
					System.out.println("[info] ��� ����");
					setCharEquip(selecteditemindex);
				}
			}
		});

		add(use);

		drop = new JButton("������");
		drop.setBounds(260, 110, 110, 50);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					inventorydata.remove(invenlist.getSelectedIndex());
					refreshItemList();
				} catch (Exception exception) {
					System.out.println("[Error] �κ��丮 ������ ���� �߻�");
					JLabel message = new JLabel(
							"<html><p style='font-family:���� ���; font-size:14pt;'>�κ��丮�� ����ֽ��ϴ�.</p></html>");
					JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "������",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(drop);

		// �ش� �������� ���� �� ����Ǵ� windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
				GameScreen.setInventorydata(inventorydata);
				GameScreen.getInvenbutton().setEnabled(true);
				check.interrupt();
				equip.interrupt();
				atkdef.interrupt();
				System.out.println("[info] �κ��丮 â ����");
			}
		});
		setPlayerhpmp();
		setPlayeratkdef();
		setPlayerEquip();
		setVisible(true);
	}

	// �κ��丮 ����Ʈ�� ���ΰ�ħ
	void refreshItemList() {
		items.clear();
		for (int i = 0; i < inventorydata.size(); i++) {
			items.addElement(inventorydata.get(i).getI_name()); // �𵨿� ��ҵ� �ٽ� �߰���(������ �����͸� �ٽ� �߰�)
		}
		invenlist.setModel(items);
		invenlist.setSelectedIndex(0);
	}

	// ������ ���� �޼ҵ�
	void setCharEquip(int itemindex) {
		String parts = inventorydata.get(itemindex).getI_parts();
		switch (parts) {
		case "W": // ����
			equipatk = inventorydata.get(itemindex).getI_atk(); // ������ �������� ���ݷ� ��ġ�� ������ ����.
			w_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + w_name + " / ������ ������ ���ݷ� :" + equipatk);
			break;
		case "H": // ����
			h_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			def_helmet = inventorydata.get(itemindex).getI_def(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + h_name + " / ������ ������ ���� :" + def_helmet);
			break;
		case "A": // ����
			a_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			def_armor = inventorydata.get(itemindex).getI_def(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + a_name + " / ������ ������ ���� :" + def_armor);
			break;
		case "G": // �尩
			g_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			def_glove = inventorydata.get(itemindex).getI_def(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + g_name + " / ������ ������ ���� :" + def_glove);
			break;
		case "B": // �Ź�
			b_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			def_boots = inventorydata.get(itemindex).getI_def(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + b_name + " / ������ ������ ���� :" + def_boots);
			break;
		}
		equipdef = def_helmet + def_armor + def_glove + def_boots; // �� �� ����(����,����,�尩,�Ź�)
	}

	// ȸ���� ü��&������ GameScreen�� �����ϴ� Thread
	void setPlayerhpmp() {
		check = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(50);
						System.out.println("���� �÷��̾� ü�� :" + current_player_hp);
						System.out.println("���� �÷��̾� ���� :" + current_player_mp);
						GameScreen.setPlayerhpmp(current_player_hp, current_player_mp);
					} catch (InterruptedException e) {
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
		atkdef = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(50);
						System.out.println("������ ��� ���ݷ� :" + equipatk);
						System.out.println("������ ��� ���� :" + equipatk);
						GameScreen.setPlayerEquipatk(equipatk, equipdef);
					} catch (InterruptedException e) {
						System.out.println("[Error] ������ interrupt �߻�");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		atkdef.start();
	}

	// �÷��̾ ������ ������ GameScreen�� �ѱ�
	void setPlayerEquip() {
		equip = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(50);
						GameScreen.setPlayerEquipName(w_name, h_name, a_name, g_name, b_name);
					} catch (InterruptedException e) {
						System.out.println("[Error] ������ interrupt �߻�");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		equip.start();
	}
}
