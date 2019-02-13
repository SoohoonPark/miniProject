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

// 프로그램을 처음 실행 시 출력되는 화면(메인화면)
@SuppressWarnings("serial")
public class MainScreen extends JFrame {
	// 이미지(메인배경이미지, 타이틀이미지, 아이콘이미지)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/background/MainScreen/mainimage.png");
	private Image titleimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleimg.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	
	// 버튼 이미지
	private Image btnimgstart = Toolkit.getDefaultToolkit().createImage("resource/images/button/MainScreen/main_btnstart.png");
	private Image btnimgstart_pressed = Toolkit.getDefaultToolkit().createImage("resource/images/button/MainScreen/main_btnstart_pressed.png");
	private Image btnimgquit = Toolkit.getDefaultToolkit().createImage("resource/images/button/MainScreen/main_btnquit.png");
	private Image btnimgquit_pressed = Toolkit.getDefaultToolkit().createImage("resource/images/button/MainScreen/main_btnquit_pressed.png");

	// 버튼(게임시작,게임종료)
	private JButton btnGamestart, btnGameQuit;
	public static void main(String[] args) {
		System.out.println("[info] 프로그램 실행");
		new MainScreen();
	}
	
	public MainScreen() {
		DSAudio audio = DSAudio.getInstance();
		audio.playTitle();

		setTitle("Dragon Slayer");
		setSize(700, 525);
		setLocationRelativeTo(null);
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
		btnGamestart = new JButton(new ImageIcon(btnimgstart));
		btnGamestart.setBorderPainted(false);
		btnGamestart.setContentAreaFilled(false);
		btnGamestart.setFocusPainted(false);
		btnGamestart.setBounds(10, 10, 130, 50);
		
		// 버튼의 자연스러움을 위해 마우스 리스너 add (마우스가 버튼을 눌렸을 때, 마우스가 버튼 영역 밖으로 나갔을 때 설정)
		btnGamestart.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnGamestart.setIcon(new ImageIcon(btnimgstart_pressed));
			}
			public void mouseExited(MouseEvent e) {
				btnGamestart.setIcon(new ImageIcon(btnimgstart));
			}
		});
		
		// 마우스 액션 리스너 add
		btnGamestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MakeCharacter();
				dispose();	// 반납하기
			}
		});
		
		// 게임종료 버튼
		btnGameQuit = new JButton(new ImageIcon(btnimgquit));
		btnGameQuit.setBorderPainted(false);
		btnGameQuit.setContentAreaFilled(false);
		btnGameQuit.setFocusPainted(false);
		btnGameQuit.setBounds(10, 70, 130, 50);
		
		// 버튼의 자연스러움을 위해 마우스 리스너 add (마우스가 버튼을 눌렸을 때, 마우스가 버튼 영역 밖으로 나갔을 때 설정)
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
				JLabel message = new JLabel("<html><body><p style='font-family:맑은 고딕; font-size:11px;'>게임을 종료하시겠습니까?</p></body></html>");
				int a = JOptionPane.showConfirmDialog(null,message,"게임종료",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				switch(a) {
				case 0:
					System.exit(1);
					break;
				case 1:
					break;
				}
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