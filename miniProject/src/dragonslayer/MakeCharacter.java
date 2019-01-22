package dragonslayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MakeCharacter extends JFrame{
	private Image backgroundimg = Toolkit.getDefaultToolkit().createImage("resource/images/background/charactermakebackground.png");
	private Image iconimg = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image characterstart = Toolkit.getDefaultToolkit().createImage("resource/images/button/buttonstart.png");
	private Image charactercancel = Toolkit.getDefaultToolkit().createImage("resource/images/button/buttoncancel.png");
	private static JTextField txtname; // 캐릭터명 입력란
	private static JButton btnStart, btnCancel; // 시작버튼, 취소버튼
	private final static int STR = 10;
	private final static int DEX = 10;
	private final static int INT = 10;
	private final static int HP = 100;
	private final static int MP = 50;
	
	public MakeCharacter() {
		createMakeCharacterWindow();
	}
	
	void createMakeCharacterWindow() {
		setTitle("캐릭터 생성");
		setSize(400, 600);
		setIconImage(iconimg);
		getContentPane().setBackground(Color.BLACK);
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
		inputnamePanel.setBorder(new LineBorder(Color.BLACK));
		
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
		characterstatPanel.setBorder(new LineBorder(Color.BLACK));
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
		buttonPanel.setBorder(new LineBorder(Color.BLACK));
		buttonPanel.setOpaque(false);
		
		btnStart = new JButton(new ImageIcon(characterstart));
		btnStart.setSize(100, 50);
		btnStart.setBounds(10, 10, 100, 50);
		btnStart.setContentAreaFilled(false);
		btnStart.setFocusPainted(false);
		btnStart.setOpaque(false);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(txtname.getText().isEmpty()) && txtname.getText().length() <= 6) {
					JOptionPane.showMessageDialog(btnStart, "캐릭터를 생성하였습니다.");
					System.out.println("게임을 시작합니다.");
				} else {
					JOptionPane.showMessageDialog(btnStart, "캐릭터 명을 다시 입력하세요.");
				}
			}
		});
		
		btnCancel = new JButton(new ImageIcon(charactercancel));
		btnCancel.setSize(100, 50);
		btnCancel.setBounds(140, 10, 100, 50);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFocusPainted(false);
		btnCancel.setOpaque(false);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnCancel, "메인화면으로 돌아갑니다.");
				System.out.println("취소하였습니다.");
				new MainScreen();
				dispose();
				
				
			}
		});
		
		buttonPanel.add(btnStart);
		buttonPanel.add(btnCancel);
		
		layer.add(backgroundLabel, new Integer(1));
		layer.add(inputnamePanel, new Integer(2));
		layer.add(characterstatPanel, new Integer(2));
		layer.add(buttonPanel, new Integer(2));
		
		setVisible(true);
	}
}
