package miniProject;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// ���α׷��� ó�� ���� �� ��µǴ� ȭ��(����ȭ��)
@SuppressWarnings("serial")
public class MainScreen extends JFrame{
	// ���� �̹���(Ÿ��Ʋ�� ����)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/mainimage.jpg");
	public MainScreen() {
		System.out.println("[info] ���α׷� ����");
		createMainScreen();
	}
	
	public void createMainScreen() {
		setSize(mainimage.getWidth(null), mainimage.getHeight(null));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel mainLabel = new JLabel();
		mainLabel.setBounds(0, 0, mainimage.getWidth(null), mainimage.getHeight(null));
		mainLabel.setIcon(new ImageIcon(mainimage));
		
		add(mainLabel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainScreen();
	}
}
