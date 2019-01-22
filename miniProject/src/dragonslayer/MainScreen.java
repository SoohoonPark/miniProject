package dragonslayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

// ���α׷��� ó�� ���� �� ��µǴ� ȭ��(����ȭ��)
@SuppressWarnings("serial")
public class MainScreen extends JFrame {
	// �̹���(���ι���̹���, Ÿ��Ʋ�̹���, �������̹���)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/mainimage.png");
	private Image titleimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleimg.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image buttonimage1 = Toolkit.getDefaultToolkit().createImage("resource/images/button/buttongamestart.png");
	private Image buttonimage2 = Toolkit.getDefaultToolkit().createImage("resource/images/button/buttongamequit.png");

	// ��ư(���ӽ���,��������)
	private JButton btnGamestart, btnGameQuit;

	public static void main(String[] args) {
		System.out.println("[info] ���α׷� ����");
		new MainScreen();
	}
	
	public MainScreen() {
		setTitle("Dragon Slayer");
		setSize(700, 525);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(iconimage); // ������ �̹���

		// ���̾��г�(�ش� ���̾� ���� �������� ���� �� ����. ��, ���� ������Ʈ ��� ����)
		JLayeredPane layer = getLayeredPane();

		// ������ JLabel�� �̹����� �־ ���
		JLabel mainimageLabel = new JLabel(new ImageIcon(mainimage));
		mainimageLabel.setBounds(0, 0, 700, 525);

		JLabel titleimageLabel = new JLabel(new ImageIcon(titleimage));
		titleimageLabel.setBounds(470, 185, 220, 67);

		// ���ӽ���,�������� ��ư�� �� JPanel
		JPanel buttonPanel = new JPanel(null);
		buttonPanel.setBounds(510, 250, 150, 130);
		buttonPanel.setOpaque(false); // �г� ��� ����
		// ���ӽ��� ��ư
		btnGamestart = new JButton(new ImageIcon(buttonimage1));
		btnGamestart.setContentAreaFilled(false);
		btnGamestart.setFocusPainted(false);
		btnGamestart.setOpaque(false);
		btnGamestart.setBounds(10, 10, 130, 50);
		btnGamestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("������ �����մϴ�.");
				new MakeCharacter();
				dispose();	// �ݳ��ϱ�
				
			}
		});
		// �������� ��ư
		btnGameQuit = new JButton(new ImageIcon(buttonimage2));
		btnGameQuit.setContentAreaFilled(false);
		btnGameQuit.setFocusPainted(false);
		btnGameQuit.setOpaque(false);
		btnGameQuit.setBounds(10, 70, 130, 50);
		btnGameQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnGameQuit, "������ �����Ͻðڽ��ϱ�?");
				System.exit(1);
				
			}
		});
		// �гο� ��ư add
		buttonPanel.add(btnGamestart);
		buttonPanel.add(btnGameQuit);

		// ���̾ �󺧵��̶� �г� ����.
		layer.add(mainimageLabel, new Integer(1));
		layer.add(titleimageLabel, new Integer(2));
		layer.add(buttonPanel, new Integer(2));

		// ����Ϳ� �ش� Frame ���
		setVisible(true);
	}
}