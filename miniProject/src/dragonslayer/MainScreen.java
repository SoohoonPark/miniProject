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

// 프로그램을 처음 실행 시 출력되는 화면(메인화면)
@SuppressWarnings("serial")
public class MainScreen extends JFrame {
	// 이미지(메인배경이미지, 타이틀이미지, 아이콘이미지)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/mainimage.png");
	private Image titleimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleimg.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image buttonimage1 = Toolkit.getDefaultToolkit().createImage("resource/images/button/buttongamestart.png");
	private Image buttonimage2 = Toolkit.getDefaultToolkit().createImage("resource/images/button/buttongamequit.png");

	// 버튼(게임시작,게임종료)
	private JButton btnGamestart, btnGameQuit;

	public static void main(String[] args) {
		System.out.println("[info] 프로그램 실행");
		new MainScreen();
	}
	
	public MainScreen() {
		setTitle("Dragon Slayer");
		setSize(700, 525);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(iconimage); // 아이콘 이미지

		// 레이어패널(해당 레이어 위에 차곡차곡 쌓을 수 있음. 즉, 여러 컴포넌트 출력 가능)
		JLayeredPane layer = getLayeredPane();

		// 각각의 JLabel에 이미지를 넣어서 출력
		JLabel mainimageLabel = new JLabel(new ImageIcon(mainimage));
		mainimageLabel.setBounds(0, 0, 700, 525);

		JLabel titleimageLabel = new JLabel(new ImageIcon(titleimage));
		titleimageLabel.setBounds(470, 185, 220, 67);

		// 게임시작,게임종료 버튼이 들어갈 JPanel
		JPanel buttonPanel = new JPanel(null);
		buttonPanel.setBounds(510, 250, 150, 130);
		buttonPanel.setOpaque(false); // 패널 배경 투명
		// 게임시작 버튼
		btnGamestart = new JButton(new ImageIcon(buttonimage1));
		btnGamestart.setContentAreaFilled(false);
		btnGamestart.setFocusPainted(false);
		btnGamestart.setOpaque(false);
		btnGamestart.setBounds(10, 10, 130, 50);
		btnGamestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("게임을 시작합니다.");
				new MakeCharacter();
				dispose();	// 반납하기
				
			}
		});
		// 게임종료 버튼
		btnGameQuit = new JButton(new ImageIcon(buttonimage2));
		btnGameQuit.setContentAreaFilled(false);
		btnGameQuit.setFocusPainted(false);
		btnGameQuit.setOpaque(false);
		btnGameQuit.setBounds(10, 70, 130, 50);
		btnGameQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnGameQuit, "게임을 종료하시겠습니까?");
				System.exit(1);
				
			}
		});
		// 패널에 버튼 add
		buttonPanel.add(btnGamestart);
		buttonPanel.add(btnGameQuit);

		// 레이어에 라벨들이랑 패널 붙임.
		layer.add(mainimageLabel, new Integer(1));
		layer.add(titleimageLabel, new Integer(2));
		layer.add(buttonPanel, new Integer(2));

		// 모니터에 해당 Frame 출력
		setVisible(true);
	}
}