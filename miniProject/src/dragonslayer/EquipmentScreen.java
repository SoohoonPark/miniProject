package dragonslayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class EquipmentScreen extends JFrame {
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image backgroundimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/EquipmentScreen/equiment_background.png");
	private Image iconweapon = Toolkit.getDefaultToolkit().createImage("resource/images/icon/icon_weapon.png");
	private Image iconhelmet = Toolkit.getDefaultToolkit().createImage("resource/images/icon/icon_helmet.png");
	private Image iconarmor = Toolkit.getDefaultToolkit().createImage("resource/images/icon/icon_armor.png");
	private Image iconglove = Toolkit.getDefaultToolkit().createImage("resource/images/icon/icon_glove.png");
	private Image iconboots = Toolkit.getDefaultToolkit().createImage("resource/images/icon/icon_boots.png");
	// �÷��̾� �̹���(���谡)

	public EquipmentScreen(Image character, String weapon, String helmet, String armor, String glove, String boots) {

		setTitle("Equipment");
		setBounds(1480, 180, 416, 359);
		setIconImage(iconimage);
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		
		JLayeredPane layer = getLayeredPane();

		// ���ι�� ��� ��
		JLabel bgimglabel = new JLabel(new ImageIcon(backgroundimage));
		bgimglabel.setBounds(0, 0, 410, 330);

		// ��� ������ ��µǴ� �г�
		JPanel EquipPanel = new JPanel(null);
		EquipPanel.setBounds(25, 40, 150, 260);
		EquipPanel.setOpaque(false);

		// ĳ���� �̹��� ��µǴ� �г�(ĳ���� �̹���)
		JPanel CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(180, 35, 200, 260);
		CharacterPanel.setOpaque(false);

		// ĳ���� �̹��� ����ϴ� Label
		JLabel characterlabel = new JLabel(new ImageIcon(character));
		characterlabel.setBounds(35, 30, 150, 226);

		// ��� �̹��� �� ������ ����ϴ� Label
		JLabel weaponlabel = new JLabel(new ImageIcon(iconweapon), SwingConstants.LEFT);
		weaponlabel.setBounds(5, 5, 120, 40);
		weaponlabel.setText("<html><p style='color:white; font-size:10px; font-family:���� ���;'>����<br/>"+weapon+"</p></html>");

		JLabel helmetlabel = new JLabel(new ImageIcon(iconhelmet), SwingConstants.LEFT);
		helmetlabel.setBounds(5, 55, 120, 40);
		helmetlabel.setText("<html><p style='color:white; font-size:10px; font-family:���� ���;'>����<br/>"+helmet+"</p></html>");

		JLabel armorlabel = new JLabel(new ImageIcon(iconarmor), SwingConstants.LEFT);
		armorlabel.setBounds(5, 105, 120, 40);
		armorlabel.setText("<html><p style='color:white; font-size:10px; font-family:���� ���;'>����<br/>"+armor+"</p></html>");

		JLabel glovelabel = new JLabel(new ImageIcon(iconglove), SwingConstants.LEFT);
		glovelabel.setBounds(5, 155, 120, 40);
		glovelabel.setText("<html><p style='color:white; font-size:10px; font-family:���� ���;'>�尩<br/>"+glove+"</p></html>");

		JLabel bootslabel = new JLabel(new ImageIcon(iconboots), SwingConstants.LEFT);
		bootslabel.setBounds(5, 205, 120, 40);
		bootslabel.setText("<html><p style='color:white; font-size:10px; font-family:���� ���;'>��ȭ<br/>"+boots+"</p></html>");

		EquipPanel.add(weaponlabel);
		EquipPanel.add(helmetlabel);
		EquipPanel.add(armorlabel);
		EquipPanel.add(glovelabel);
		EquipPanel.add(bootslabel);
		CharacterPanel.add(characterlabel);

		layer.add(bgimglabel, new Integer(1));
		layer.add(EquipPanel, new Integer(2));
		layer.add(CharacterPanel, new Integer(2));

		// �ش� �������� ���� �� ����Ǵ� windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// �ش� �������� ���� �� GameScreen�� '����' ��ư�� Ȱ��ȭ��Ŵ.
				GameScreen.getEquipbutton().setEnabled(true);
			}
		});

		setVisible(true);
	}
}
