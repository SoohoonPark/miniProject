package dragonslayer;

import java.awt.Color;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class InventoryScreen extends JFrame {
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image backgroundimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/InventoryScreen/inventory_background.png");
	private LinkedList<DSItems> inventorydata; // GameScreen���� �Ѿ�� inventorydata(�ش� �����͸� ���߿� �ٽ� GameScreen���� �Ѱ���)
	private DefaultListModel<String> items = new DefaultListModel<String>(); // JList�� �ö� �����͸�
	private JList<String> invenlist; // �κ��丮 ����Ʈ�� ����� JList
	private int current_player_maxhp; // �÷��̾� �� ü��(ü�� ���� �Ծ����� �ִ� ü�� �̻����� ȸ�� �Ұ���)
	private int current_player_maxmp; // �÷��̾� �� ����(���� ���� �Ծ����� �ִ� ���� �̻����� ȸ�� �Ұ���)
	private int current_player_hp; // �÷��̾� ���� ü��
	private int current_player_mp; // �÷��̾� ���� ����
	private int atk_weapon, def_helmet, def_armor, def_glove, def_boots; // ����,����,����,�尩,�Ź� ������ ��(���ݷ�,����)
	private String w_name, h_name, a_name, g_name, b_name; // ����,����,����,�尩,�Ź� �����۸�
	private JButton use, drop; // ���/����, ������ ��ư
	
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp, int usermp, int maxhp, int maxmp) {
		System.out.println("[info] �κ��丮(����)â ����");
		this.inventorydata = inventory;
		this.current_player_hp = userhp;
		this.current_player_mp = usermp;
		this.current_player_maxhp = maxhp;
		this.current_player_maxmp = maxmp;

		System.out.println("[info] ���� �κ��丮 ũ�� : " + inventorydata.size());
		setBounds(1480, 180, 406, 329);
		setLayout(null);
		setResizable(false);
		setTitle("Inventory");
		setIconImage(iconimage);
		
		JLayeredPane layer = getLayeredPane();
		
		JLabel backgroundimglabel = new JLabel(new ImageIcon(backgroundimage));
		backgroundimglabel.setBounds(0, 0, 400, 300);

		for (int i = 0; i < inventorydata.size(); i++) {
			items.addElement(inventorydata.get(i).getI_name());
		}
		invenlist = new JList<>(items);
		invenlist.setFont(new Font("���� ���", Font.PLAIN, 14));
		invenlist.setSelectedIndex(0);

		JScrollPane invenscroll = new JScrollPane(invenlist);
		invenscroll.setBounds(30, 50, 230, 200);
		layer.add(invenscroll, new Integer(2));

		use = new JButton("���� / ���");
		use.setBounds(270, 50, 100, 50);
		use.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inventorydata.isEmpty()) {
					JLabel message = new JLabel("<html><p style='font-family:���� ���; font-size:14pt;'>�κ��丮�� ����ֽ��ϴ�.</p></html>");
					JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "���/���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int selecteditemindex = invenlist.getSelectedIndex();
				if (inventorydata.get(selecteditemindex).getI_equip().equals("N")) { // N = ���Ұ���(=����)
					System.out.println("[info] ���� ���");
					/** ���� ��� ���� **/
					if (inventorydata.get(selecteditemindex).getI_name().equals("ü�� ����")) {
						System.out.println("[info] ü�� ���� ���");
						if (current_player_hp == current_player_maxhp) {
							JLabel message = new JLabel("<html><p style='font-family:���� ���; font-size:14pt;'>ü���� ���� ���ֽ��ϴ�.</p></html>");
							JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "����",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						// ü�� ȸ���� ���� �ִ� ü���� �Ѿ �� ����.
						// 70/100 �� ��� 30�� ȸ��. 50/100 �� ��� 50 ȸ��.....
						int requireRegenhp = current_player_maxhp - current_player_hp; // �� ü�� - ���� ü�� = ȸ���ؾ� �� ü��
						if (requireRegenhp <= inventorydata.get(selecteditemindex).getI_regen()) { // ȸ���ؾ��� ü�� < ������ ȸ����
							current_player_hp += requireRegenhp;
							GameScreen.setPlayerhp(current_player_hp);
						} else {
							current_player_hp += inventorydata.get(selecteditemindex).getI_regen();
							GameScreen.setPlayerhp(current_player_hp);
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
							GameScreen.setPlayermp(current_player_mp);
						} else {
							current_player_mp += inventorydata.get(selecteditemindex).getI_regen();
							GameScreen.setPlayermp(current_player_mp);
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

		layer.add(use, new Integer(3));

		drop = new JButton("������");
		drop.setBounds(270, 120, 100, 50);
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
		
		layer.add(drop, new Integer(3));

		// �ش� �������� ���� �� ����Ǵ� windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
				GameScreen.setInventorydata(inventorydata); // GameScreen�� inventorydata�� ����(�κ��丮 �ֽ�ȭ)
				GameScreen.getInvenbutton().setEnabled(true);
				System.out.println("[info] �κ��丮 â ����");
			}
		});
		
		layer.add(backgroundimglabel, new Integer(1));
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
			atk_weapon = inventorydata.get(itemindex).getI_atk(); // ������ �������� ���ݷ� ��ġ�� ������ ����.
			w_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + w_name + " / ������ ������ ���ݷ� :" + atk_weapon);
			GameScreen.setEquipAtk(atk_weapon);
			GameScreen.setPlayerEquipNameWeapon(w_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		case "H": // ����
			h_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			def_helmet = inventorydata.get(itemindex).getI_def(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + h_name + " / ������ ������ ���� :" + def_helmet);
			GameScreen.setEquipDef_Helmet(def_helmet);
			GameScreen.setPlayerEquipNameHelmet(h_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		case "A": // ����
			a_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			def_armor = inventorydata.get(itemindex).getI_def(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + a_name + " / ������ ������ ���� :" + def_armor);
			GameScreen.setEquipDef_Armor(def_armor);
			GameScreen.setPlayerEquipNameArmor(a_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		case "G": // �尩
			g_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			def_glove = inventorydata.get(itemindex).getI_def(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + g_name + " / ������ ������ ���� :" + def_glove);
			GameScreen.setEquipDef_Glove(def_glove);
			GameScreen.setPlayerEquipNameGlove(g_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		case "B": // �Ź�
			b_name = inventorydata.get(itemindex).getI_name(); // ������ �̸� ����
			def_boots = inventorydata.get(itemindex).getI_def(); // ������ �̸� ����
			System.out.println("������ ������ �� :" + b_name + " / ������ ������ ���� :" + def_boots);
			GameScreen.setEquipDef_Boots(def_boots);
			GameScreen.setPlayerEquipNameBoots(b_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		}
	}
}
