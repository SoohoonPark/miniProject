package dragonslayer;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

// ���α׷��� ó�� ���� �� ��µǴ� ȭ��(����ȭ��)
@SuppressWarnings("serial")
public class MainScreen extends JFrame {
	// �̹���(���ι���̹���, Ÿ��Ʋ�̹���, �������̹���)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/mainimage.png");
	private Image titleimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleimage.jpg");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");

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
		titleimageLabel.setBounds(0, 0, 302, 182);

		// ���ӽ���,�������� ��ư�� �� JPanel
		JPanel buttonPanel = new JPanel(null);
		buttonPanel.setBounds(400, 200, 120, 240);
		buttonPanel.setOpaque(false); // �г� ��� ����

		btnGamestart = new JButton("���ӽ���");
		btnGamestart.setBounds(5, 10, 100, 50);

		btnGameQuit = new JButton("��������");
		btnGameQuit.setBounds(5, 70, 100, 50);

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
