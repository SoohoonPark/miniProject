package dragonslayer;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// ���α׷��� ó�� ���� �� ��µǴ� ȭ��(����ȭ��)
@SuppressWarnings("serial")
public class MainScreen extends JFrame {
	// �̹���(���ι���̹���, Ÿ��Ʋ�̹���, �������̹���)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/mainimage.png");
	private Image titleimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleimg.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	
	private Image btnimgstart = Toolkit.getDefaultToolkit().createImage("resource/images/button/MainScreenButtons/main_btnstart.png");
	private Image btnimgstart_pressed = Toolkit.getDefaultToolkit().createImage("resource/images/button/MainScreenButtons/main_btnstart_pressed.png");
	private Image btnimgquit = Toolkit.getDefaultToolkit().createImage("resource/images/button/MainScreenButtons/main_btnquit.png");
	private Image btnimgquit_pressed = Toolkit.getDefaultToolkit().createImage("resource/images/button/MainScreenButtons/main_btnquit_pressed.png");

	// ��ư(���ӽ���,��������)
	private JButton btnGamestart, btnGameQuit;

	public static void main(String[] args) {
		System.out.println("[info] ���α׷� ����");
		new MainScreen();
	}
	
	public MainScreen() {
		setTitle("Dragon Slayer");
		setSize(700, 525);
		setLocationRelativeTo(null);
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
		btnGamestart = new JButton(new ImageIcon(btnimgstart));
		btnGamestart.setBorderPainted(false);
		btnGamestart.setContentAreaFilled(false);
		btnGamestart.setFocusPainted(false);
		btnGamestart.setBounds(10, 10, 130, 50);
		
		// ��ư�� �ڿ��������� ���� ���콺 ������ add (���콺�� ��ư�� ������ ��, ���콺�� ��ư ���� ������ ������ �� ����)
		btnGamestart.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnGamestart.setIcon(new ImageIcon(btnimgstart_pressed));
			}
			public void mouseExited(MouseEvent e) {
				btnGamestart.setIcon(new ImageIcon(btnimgstart));
			}
		});
		btnGamestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("������ �����մϴ�.");
				new MakeCharacter();
				dispose();	// �ݳ��ϱ�
			}
		});
		
		// �������� ��ư
		btnGameQuit = new JButton(new ImageIcon(btnimgquit));
		btnGameQuit.setBorderPainted(false);
		btnGameQuit.setContentAreaFilled(false);
		btnGameQuit.setFocusPainted(false);
		btnGameQuit.setBounds(10, 70, 130, 50);
		
		// ��ư�� �ڿ��������� ���� ���콺 ������ add (���콺�� ��ư�� ������ ��, ���콺�� ��ư ���� ������ ������ �� ����)
		btnGameQuit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnGameQuit.setIcon(new ImageIcon(btnimgquit_pressed));
			}
			public void mouseExited(MouseEvent e) {
				btnGameQuit.setIcon(new ImageIcon(btnimgquit));
			}
		});
		btnGameQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ �����Ͻðڽ��ϱ�?");
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