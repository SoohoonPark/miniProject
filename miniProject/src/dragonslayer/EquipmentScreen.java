package dragonslayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class EquipmentScreen extends JFrame {
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	// 플레이어 이미지(모험가)
	private final static Image PLAYERBEGINNER = Toolkit.getDefaultToolkit()
			.createImage("resource/images/player/playercharacter_beginner.png");
	private static JPanel CharacterPanel, EquipPanel;

	public EquipmentScreen() {
		setSize(430, 350);
		setLayout(null);
		setLocation(1480, 180);
		setTitle("Equipment");
		setIconImage(iconimage);

		// 캐릭터 이미지 출력되는 패널(캐릭터 이미지)
		CharacterPanel = new JPanel(null);
		CharacterPanel.setBounds(180, 35, 200, 260);
		CharacterPanel.setBorder(new LineBorder(Color.BLUE));
		CharacterPanel.setOpaque(false);

		// 장비 내용이 출력되는 패널
		EquipPanel = new JPanel(null);
		EquipPanel.setBounds(25, 35, 150, 260);
		EquipPanel.setBorder(new LineBorder(Color.GREEN));
		EquipPanel.setOpaque(false);

		// 캐릭터 이미지 출력하는 Label
		JLabel characterlabel = new JLabel(new ImageIcon(PLAYERBEGINNER));
		characterlabel.setBounds(35, 30, 150, 226);
		// 장비 이미지 및 설명을 출력하는 Label
		JLabel weaponlabel = new JLabel("무기 : 강철 대검");
		weaponlabel.setBounds(5, 5, 120, 30);

		EquipPanel.add(weaponlabel);
		CharacterPanel.add(characterlabel);

		add(CharacterPanel);
		add(EquipPanel);

		// 해당 프레임이 닫힐 때 실행되는 windowListener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 해당 프레임이 닫힐 때 GameScreen의 '가방' 버튼을 활성화시킴.
				GameScreen.getEquipbutton().setEnabled(true);
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new EquipmentScreen();
	}
}
