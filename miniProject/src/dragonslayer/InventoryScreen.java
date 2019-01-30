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
	private int current_player_maxhp; // 플레이어 총 체력(체력 물약 먹었을때 최대 체력 이상으로 회복 불가능)
	private int current_player_maxmp; // 플레이어 총 마나(마나 물약 먹었을때 최대 마나 이상으로 회복 불가능)
	private int current_player_hp; // 플레이어 현재 체력
	private int current_player_mp; // 플레이어 현재 마나
	private int equipatk, equipdef; // 장비공격력 & 방어력
	private int def_helmet, def_armor, def_glove, def_boots; // 투구,갑옷,장갑,신발 방어력(해당 방어력들을 최종적으로 합산하면 현재장비방어력이 됨)
	private String w_name, h_name, a_name, g_name, b_name; // 무기,투구,갑옷,장갑,신발 아이템명
	private Thread check, atkdef, equip; // 플레이어 체&마, 공&방 체크, 장비명 체크 Thread
	private JButton use, drop;

//	public static void main(String[] args) {
//		LinkedList<DSItems> testdata = new LinkedList<DSItems>();
//		testdata.add(new DSItems("체력 물약",0,0,50,"N","N"));
//		testdata.add(new DSItems("마나 물약",0,0,50,"N","N"));
//		testdata.add(new DSItems("강철 대검",20,0,0,"Y","W"));
//		testdata.add(new DSItems("강철 도끼",25,0,0,"Y","W"));
//		testdata.add(new DSItems("가죽 모자",0,2,0,"Y","H"));
//		testdata.add(new DSItems("가죽 갑옷",0,5,0,"Y","A"));
//		testdata.add(new DSItems("가죽 장갑",0,3,0,"Y","G"));
//		testdata.add(new DSItems("가죽 신발",0,4,0,"Y","B"));
//		testdata.add(new DSItems("강철 투구",0,5,0,"Y","H"));
//		testdata.add(new DSItems("강철 갑옷",0,12,0,"Y","A"));
//		testdata.add(new DSItems("강철 장갑",0,7,0,"Y","G"));
//		testdata.add(new DSItems("강철 장화",0,9,0,"Y","B"));
//		new InventoryScreen(testdata, 25, 20, 100, 50); // 체력 25/100, 마나 20/50
//	}
	public InventoryScreen(LinkedList<DSItems> inventory, int userhp, int usermp, int maxhp, int maxmp, int atk, int def) {
		System.out.println("[info] 인벤토리(가방)창 열림");
		this.inventorydata = inventory;
		this.current_player_hp = userhp;
		this.current_player_mp = usermp;
		this.current_player_maxhp = maxhp;
		this.current_player_maxmp = maxmp;
		this.equipatk = atk; // 플레이어 현재 장비 공격력
		this.equipdef = def; // 플레이어 현재 장비 방어력

		System.out.println("[info] 현재 인벤토리 크기 : " + inventorydata.size());
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
		invenlist.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		invenlist.setSelectedIndex(0);

		JScrollPane invenscroll = new JScrollPane(invenlist);
		invenscroll.setBounds(20, 30, 230, 200);
		add(invenscroll);

		use = new JButton("장착 / 사용");
		use.setBounds(260, 40, 110, 50);
		use.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selecteditemindex = invenlist.getSelectedIndex();
				if (inventorydata.get(selecteditemindex).getI_equip().equals("N")) { // N = 장비불가능(=물약)
					System.out.println("[info] 물약 사용");
					/** 물약 사용 로직 **/
					if (inventorydata.get(selecteditemindex).getI_name().equals("체력 물약")) {
						System.out.println("[info] 체력 물약 사용");
						if (current_player_hp == current_player_maxhp) {
							JLabel message = new JLabel(
									"<html><p style='font-family:맑은 고딕; font-size:14pt;'>체력이 가득 차있습니다.</p></html>");
							JOptionPane.showMessageDialog(SwingUtilities.getRoot(use), message, "물약",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						// 체력 회복은 현재 최대 체력을 넘어갈 수 없음.
						// 70/100 일 경우 30만 회복. 50/100 일 경우 50 회복.....
						int requireRegenhp = current_player_maxhp - current_player_hp; // 총 체력 - 현재 체력 = 회복해야 할 체력
						if (requireRegenhp <= inventorydata.get(selecteditemindex).getI_regen()) { // 회복해야할 체력 < 물약의 회복량
							current_player_hp += requireRegenhp;
						} else {
							current_player_hp += inventorydata.get(selecteditemindex).getI_regen();
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
						} else {
							current_player_mp += inventorydata.get(selecteditemindex).getI_regen();
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

		add(use);

		drop = new JButton("버리기");
		drop.setBounds(260, 110, 110, 50);
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
		add(drop);

		// 해당 프레임이 닫힐 때 실행되는 windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 해당 프레임이 닫힐 때 GameScreen의 '가방' 버튼을 활성화시킴.
				GameScreen.setInventorydata(inventorydata);
				GameScreen.getInvenbutton().setEnabled(true);
				check.interrupt();
				equip.interrupt();
				atkdef.interrupt();
				System.out.println("[info] 인벤토리 창 닫힘");
			}
		});
		setPlayerhpmp();
		setPlayeratkdef();
		setPlayerEquip();
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
			equipatk = inventorydata.get(itemindex).getI_atk(); // 선택한 아이템의 공격력 수치를 변수에 저장.
			w_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + w_name + " / 장착된 아이템 공격력 :" + equipatk);
			break;
		case "H": // 투구
			h_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			def_helmet = inventorydata.get(itemindex).getI_def(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + h_name + " / 장착된 아이템 방어력 :" + def_helmet);
			break;
		case "A": // 갑옷
			a_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			def_armor = inventorydata.get(itemindex).getI_def(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + a_name + " / 장착된 아이템 방어력 :" + def_armor);
			break;
		case "G": // 장갑
			g_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			def_glove = inventorydata.get(itemindex).getI_def(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + g_name + " / 장착된 아이템 방어력 :" + def_glove);
			break;
		case "B": // 신발
			b_name = inventorydata.get(itemindex).getI_name(); // 아이템 이름 저장
			def_boots = inventorydata.get(itemindex).getI_def(); // 아이템 이름 저장
			System.out.println("장착된 아이템 명 :" + b_name + " / 장착된 아이템 방어력 :" + def_boots);
			break;
		}
		equipdef = def_helmet + def_armor + def_glove + def_boots; // 방어구 총 방어력(투구,갑옷,장갑,신발)
	}

	// 회복된 체력&마나를 GameScreen에 저장하는 Thread
	void setPlayerhpmp() {
		check = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(50);
						System.out.println("현재 플레이어 체력 :" + current_player_hp);
						System.out.println("현재 플레이어 마나 :" + current_player_mp);
						GameScreen.setPlayerhpmp(current_player_hp, current_player_mp);
					} catch (InterruptedException e) {
						System.out.println("[Error] 쓰레드 interrupt 발생");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		check.start();
	}

	// 장비공격력&방어력이 반영된 값들을 GameScreen에 저장하는 Thread
	void setPlayeratkdef() {
		atkdef = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(50);
						System.out.println("장착한 장비 공격력 :" + equipatk);
						System.out.println("장착한 장비 방어력 :" + equipatk);
						GameScreen.setPlayerEquipatk(equipatk, equipdef);
					} catch (InterruptedException e) {
						System.out.println("[Error] 쓰레드 interrupt 발생");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		atkdef.start();
	}

	// 플레이어가 장착한 장비명을 GameScreen에 넘김
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
						System.out.println("[Error] 쓰레드 interrupt 발생");
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		equip.start();
	}
}
