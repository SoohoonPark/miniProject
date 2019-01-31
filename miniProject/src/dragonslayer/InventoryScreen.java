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
	private LinkedList<DSItems> inventorydata; // GameScreen에서 넘어온 inventorydata(해당 데이터를 나중에 다시 GameScreen으로 넘겨줌)
	private DefaultListModel<String> items = new DefaultListModel<String>(); // JList에 올라갈 데이터모델
	private JList<String> invenlist; // 인벤토리 리스트를 출력할 JList
	private int current_player_maxhp; // 플레이어 총 체력(체력 물약 먹었을때 최대 체력 이상으로 회복 불가능)
	private int current_player_maxmp; // 플레이어 총 마나(마나 물약 먹었을때 최대 마나 이상으로 회복 불가능)
	private int current_player_hp; // 플레이어 현재 체력
	private int current_player_mp; // 플레이어 현재 마나
	private int atk_weapon, def_helmet, def_armor, def_glove, def_boots; // 무기,투구,갑옷,장갑,신발 아이템 값(공격력,방어력)
	private String w_name, h_name, a_name, g_name, b_name; // 무기,투구,갑옷,장갑,신발 아이템명
	private JButton use, drop; // 사용/장착, 버리기 버튼
	
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp, int usermp, int maxhp, int maxmp) {
		System.out.println("[info] 인벤토리(가방)창 열림");
		this.inventorydata = inventory;
		this.current_player_hp = userhp;
		this.current_player_mp = usermp;
		this.current_player_maxhp = maxhp;
		this.current_player_maxmp = maxmp;

		System.out.println("[info] 현재 인벤토리 크기 : " + inventorydata.size());
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
		invenlist.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		invenlist.setSelectedIndex(0);

		JScrollPane invenscroll = new JScrollPane(invenlist);
		invenscroll.setBounds(30, 50, 230, 200);
		layer.add(invenscroll, new Integer(2));

		use = new JButton("장착 / 사용");
		use.setBounds(270, 50, 100, 50);
		use.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inventorydata.isEmpty()) {
					JLabel message = new JLabel("<html><p style='font-family:맑은 고딕; font-size:14pt;'>인벤토리가 비어있습니다.</p></html>");
					JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "사용/장비", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int selecteditemindex = invenlist.getSelectedIndex();
				if (inventorydata.get(selecteditemindex).getI_equip().equals("N")) { // N = 장비불가능(=물약)
					System.out.println("[info] 물약 사용");
					/** 물약 사용 로직 **/
					if (inventorydata.get(selecteditemindex).getI_name().equals("체력 물약")) {
						System.out.println("[info] 체력 물약 사용");
						if (current_player_hp == current_player_maxhp) {
							JLabel message = new JLabel("<html><p style='font-family:맑은 고딕; font-size:14pt;'>체력이 가득 차있습니다.</p></html>");
							JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "물약",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						// 체력 회복은 현재 최대 체력을 넘어갈 수 없음.
						// 70/100 일 경우 30만 회복. 50/100 일 경우 50 회복.....
						int requireRegenhp = current_player_maxhp - current_player_hp; // 총 체력 - 현재 체력 = 회복해야 할 체력
						if (requireRegenhp <= inventorydata.get(selecteditemindex).getI_regen()) { // 회복해야할 체력 < 물약의 회복량
							current_player_hp += requireRegenhp;
							GameScreen.setPlayerhp(current_player_hp);
						} else {
							current_player_hp += inventorydata.get(selecteditemindex).getI_regen();
							GameScreen.setPlayerhp(current_player_hp);
						}
						inventorydata.remove(selecteditemindex);
						refreshItemList();

					} else if (inventorydata.get(selecteditemindex).getI_name().equals("마나 물약")) {
						System.out.println("[info] 마나 물약 사용");
						if (current_player_mp == current_player_maxmp) {
							JLabel message = new JLabel(
									"<html><p style='font-family:맑은 고딕; font-size:14pt;'>마나가 가득 차있습니다.</p></html>");
							JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "물약",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						int requireRegenmp = current_player_maxmp - current_player_mp; // 총 체력 - 현재 체력 = 회복해야 할 체력
						if (requireRegenmp <= inventorydata.get(selecteditemindex).getI_regen()) { // 회복해야할 체력 < 물약의 회복량
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
					System.out.println("[info] 장비 장착");
					setCharEquip(selecteditemindex);
				}
			}
		});

		layer.add(use, new Integer(3));

		drop = new JButton("버리기");
		drop.setBounds(270, 120, 100, 50);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					inventorydata.remove(invenlist.getSelectedIndex());
					refreshItemList();
				} catch (Exception exception) {
					System.out.println("[Error] 인벤토리 버리기 예외 발생");
					JLabel message = new JLabel(
							"<html><p style='font-family:맑은 고딕; font-size:14pt;'>인벤토리가 비어있습니다.</p></html>");
					JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "버리기",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		layer.add(drop, new Integer(3));

		// 해당 프레임이 닫힐 때 실행되는 windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 해당 프레임이 닫힐 때 GameScreen의 '가방' 버튼을 활성화시킴.
				GameScreen.setInventorydata(inventorydata); // GameScreen의 inventorydata를 저장(인벤토리 최신화)
				GameScreen.getInvenbutton().setEnabled(true);
				System.out.println("[info] 인벤토리 창 닫힘");
			}
		});
		
		layer.add(backgroundimglabel, new Integer(1));
		setVisible(true);
	}

	// 인벤토리 리스트를 새로고침
	void refreshItemList() {
		items.clear();
		for (int i = 0; i < inventorydata.size(); i++) {
			items.addElement(inventorydata.get(i).getI_name()); // 모델에 요소들 다시 추가함(삭제된 데이터를 다시 추가)
		}
		invenlist.setModel(items);
		invenlist.setSelectedIndex(0);
	}

	// 아이템 장착 메소드
	void setCharEquip(int itemindex) {
		String parts = inventorydata.get(itemindex).getI_parts();
		switch (parts) {
		case "W": // 무기
			atk_weapon = inventorydata.get(itemindex).getI_atk(); // 선택한 아이템의 공격력 수치를 변수에 저장.
			w_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + w_name + " / 장착된 아이템 공격력 :" + atk_weapon);
			GameScreen.setEquipAtk(atk_weapon);
			GameScreen.setPlayerEquipNameWeapon(w_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		case "H": // 투구
			h_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			def_helmet = inventorydata.get(itemindex).getI_def(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + h_name + " / 장착된 아이템 방어력 :" + def_helmet);
			GameScreen.setEquipDef_Helmet(def_helmet);
			GameScreen.setPlayerEquipNameHelmet(h_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		case "A": // 갑옷
			a_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			def_armor = inventorydata.get(itemindex).getI_def(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + a_name + " / 장착된 아이템 방어력 :" + def_armor);
			GameScreen.setEquipDef_Armor(def_armor);
			GameScreen.setPlayerEquipNameArmor(a_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		case "G": // 장갑
			g_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			def_glove = inventorydata.get(itemindex).getI_def(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + g_name + " / 장착된 아이템 방어력 :" + def_glove);
			GameScreen.setEquipDef_Glove(def_glove);
			GameScreen.setPlayerEquipNameGlove(g_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		case "B": // 신발
			b_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			def_boots = inventorydata.get(itemindex).getI_def(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + b_name + " / 장착된 아이템 방어력 :" + def_boots);
			GameScreen.setEquipDef_Boots(def_boots);
			GameScreen.setPlayerEquipNameBoots(b_name);
			inventorydata.remove(itemindex);
			refreshItemList();
			break;
		}
	}
}
