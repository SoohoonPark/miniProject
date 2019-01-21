package miniProject;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

// ���α׷��� ó�� ���� �� ��µǴ� ȭ��(����ȭ��)
@SuppressWarnings("serial")
public class MainScreen extends JFrame{
	// ���� �̹���(Ÿ��Ʋ�� ����)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/mainimage.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private JButton btnGamestart, btnGameQuit;
	
	public static void main(String[] args) {
		System.out.println("[info] ���α׷� ����");
		new MainScreen().createMainScreen();
	}
	
	public void createMainScreen() {
		setSize(700, 525);
		setTitle("Dragon Slayer");
		setIconImage(iconimage);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ���� �̹���
		JLabel mainLabel = new JLabel();
		mainLabel.setBounds(0, 0, 700, 525);
		mainLabel.setIcon(new ImageIcon(mainimage));
		
		JLabel titleLabel = new JLabel();
		titleLabel.setBounds(500, 100, 150, 80);
		titleLabel.setBorder(new LineBorder(Color.BLACK));
		
		JPanel testpanel = new JPanel(null);
		testpanel.setBounds(50, 50, 200, 200);
		testpanel.setBorder(new LineBorder(Color.RED));
		
		add(mainLabel);
		add(testpanel);
		setVisible(true);
	}
	
	
}
