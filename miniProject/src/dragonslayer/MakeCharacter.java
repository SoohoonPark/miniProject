package dragonslayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MakeCharacter extends JFrame{
	// 배경이미지 & 아이콘이미지
	private Image backgroundimg = Toolkit.getDefaultToolkit().createImage("resource/images/background/MakeCharacter/makebackground.png");
	private Image iconimg = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	
	// 버튼 이미지
	private Image btncharstart = Toolkit.getDefaultToolkit().createImage("resource/images/button/MakeCharacter/make_btnstart.png");
	private Image btncharstart_pressed = Toolkit.getDefaultToolkit().createImage("resource/images/button/MakeCharacter/make_btnstart_pressed.png");
	private Image btncharcancel = Toolkit.getDefaultToolkit().createImage("resource/images/button/MakeCharacter/make_btncancel.png");
	private Image btncharcancel_pressed = Toolkit.getDefaultToolkit().createImage("resource/images/button/MakeCharacter/make_btncancel_pressed.png");
	
	private static JTextField txtname; // 캐릭터명 입력란
	private static JButton btnStart, btnCancel; // 시작버튼, 취소버튼
	
	public MakeCharacter() {
		System.out.println("[info] MakeCharacter() 실행");
		createMakeCharacterWindow();
	}
	
	void createMakeCharacterWindow() {
		System.out.println("[info] createMakeCharacterWindow() 실행");
		setTitle("Dragon slayer");
		setSize(400, 600);
		setIconImage(iconimg);
		getContentPane().setBackground(Color.BLACK);
		setLocationRelativeTo(null); // 현재 모니터 정중앙에 프레임 출력
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 레이어 생성
		JLayeredPane layer = getLayeredPane();
		
		// 메인 배경 이미지 JLabel
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundimg));
		backgroundLabel.setBounds(10, 10, 375, 536);
		
		JPanel inputnamePanel = new JPanel(null);
		inputnamePanel.setBounds(75, 140, 260, 40);
		inputnamePanel.setOpaque(false);
		
		JLabel lblname = new JLabel("캐릭터명");
		lblname.setBounds(20, 10, 100, 20);
		lblname.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		txtname = new JTextField();
		txtname.setBounds(120, 10, 100, 21);
		txtname.setBackground(new Color(234, 234, 234));
		
		inputnamePanel.add(lblname);
		inputnamePanel.add(txtname);
		
		JPanel characterstatPanel = new JPanel(null);
		characterstatPanel.setBounds(75, 190, 260, 140);
		characterstatPanel.setOpaque(false);
		
		JLabel lblstr = new JLabel("STR 10");
		lblstr.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/icon/statstr.png")));
		lblstr.setBounds(10, 10, 140, 30);
		lblstr.setFont(new Font("맑은 고딕",Font.BOLD,23));
		
		JLabel lbldex = new JLabel("DEX 10");
		lbldex.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/icon/statdex.png")));
		lbldex.setBounds(10, 50, 140, 30);
		lbldex.setFont(new Font("맑은 고딕",Font.BOLD,23));
		
		JLabel lblint = new JLabel("INT 10");
		lblint.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/icon/statint.png")));
		lblint.setBounds(10, 90, 140, 30);
		lblint.setFont(new Font("맑은 고딕",Font.BOLD,23));
		
		JLabel lblhp = new JLabel("체력 100");
		lblhp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/icon/stathp.png")));
		lblhp.setBounds(130, 10, 140, 30);
		lblhp.setFont(new Font("맑은 고딕",Font.BOLD,23));
		
		JLabel lblmp = new JLabel("마나 80");
		lblmp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/images/icon/statmana.png")));
		lblmp.setBounds(130, 50, 140, 30);
		lblmp.setFont(new Font("맑은 고딕",Font.BOLD,23));
		
		characterstatPanel.add(lblstr);
		characterstatPanel.add(lbldex);
		characterstatPanel.add(lblint);
		characterstatPanel.add(lblhp);
		characterstatPanel.add(lblmp);
		
		JPanel buttonPanel = new JPanel(null);
		buttonPanel.setBounds(75, 350, 260, 80);
		buttonPanel.setOpaque(false);
		
		btnStart = new JButton(new ImageIcon(btncharstart));
		btnStart.setBounds(10, 10, 100, 50);
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setFocusPainted(false);
		btnStart.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnStart.setIcon(new ImageIcon(btncharstart_pressed));
			}
			public void mouseExited(MouseEvent e) {
				btnStart.setIcon(new ImageIcon(btncharstart));
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DSAudio click = DSAudio.getInstance();
				click.playButtonClick();
				JLabel message = new JLabel();
				if(txtname.getText().isEmpty()) { // 캐릭터명이 공백인 경우
					message.setText("<html><p style='font-family:맑은 고딕;'>캐릭터명이 공백입니다.</p></html>");
					JOptionPane.showMessageDialog(null, message, "캐릭터생성", JOptionPane.WARNING_MESSAGE, null);
					return;
				}else {
					if(txtname.getText().trim().length() >= 2 && txtname.getText().trim().length() <= 6) { // 캐릭터명 길이는 2 ~ 6자
						message.setText("<html><body><p style='font-family:맑은 고딕;'>"
								+ "캐릭터를 생성하였습니다.<br/>게임을 시작합니다.</p></body></html>");
						JOptionPane.showMessageDialog(null, message, "캐릭터 생성", JOptionPane.DEFAULT_OPTION, null);
						new LoadingScreen(txtname.getText());
						dispose();
					}else { // 길이가 2 ~ 6자가 아닌 경우
						message.setText("<html><body><p style='font-family:맑은 고딕;'>"
								+ "캐릭터명 길이는 2 ~ 6 자 입니다.</p></body></html>");
						JOptionPane.showMessageDialog(null, message, "캐릭터 생성", JOptionPane.DEFAULT_OPTION, null);
						return;
					}
				}
			}
		});
		
		btnCancel = new JButton(new ImageIcon(btncharcancel));
		btnCancel.setBounds(140, 10, 100, 50);
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFocusPainted(false);
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnCancel.setIcon(new ImageIcon(btncharcancel_pressed));
			}
			public void mouseExited(MouseEvent e) {
				btnCancel.setIcon(new ImageIcon(btncharcancel));
			}
		});	
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DSAudio click = DSAudio.getInstance();
				click.playButtonClick();
				JLabel message = new JLabel("<html><p style='font-family:맑은 고딕;'>메인화면으로 돌아가시겠습니까?</p></html>");
				int a = JOptionPane.showConfirmDialog(null, message, "캐릭터 생성", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				switch(a) {
				case 0:
					new MainScreen();
					dispose();
					break;
				case 1:
					break;
				}
			}
		});
	
		buttonPanel.add(btnStart);
		buttonPanel.add(btnCancel);
		
		layer.add(backgroundLabel, new Integer(1));
		layer.add(inputnamePanel, new Integer(2));
		layer.add(characterstatPanel, new Integer(2));
		layer.add(buttonPanel, new Integer(2));
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent w) {
				DSAudio offtitle = DSAudio.getInstance();
				offtitle.offTitle();
			}
		});
	}
}