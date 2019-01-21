package miniProject;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// 프로그램을 처음 실행 시 출력되는 화면(메인화면)
@SuppressWarnings("serial")
public class MainScreen extends JFrame{
	// 메인 이미지(타이틀과 별개)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/mainimage.jpg");
	public MainScreen() {
		System.out.println("[info] 프로그램 실행");
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
