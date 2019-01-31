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
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class SkillScreen extends JFrame {
	private Image Background = Toolkit.getDefaultToolkit().createImage("resource/images/background/SkillScreen/skill_background.png");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private Image Skill1 = Toolkit.getDefaultToolkit().createImage("resource/images/icon/skill_1_dragon_slasher.png");
	private Image Skill2 = Toolkit.getDefaultToolkit().createImage("resource/images/icon/skill_2_aura_blade.png");
	private Image Skill3 = Toolkit.getDefaultToolkit().createImage("resource/images/icon/skill_3_healing.png");
	private Image Skill4 = Toolkit.getDefaultToolkit().createImage("resource/images/icon/skill_4_fatal_attack.png");
	private static JPanel SkillPanel1, SkillPanel2, SkillPanel3, SkillPanel4; // 스킬 정보 출력 패널
	private static JButton Use1, Use2, Use3, Use4; // 스킬 사용 버튼
	private static JLabel SkillIconLabel1, SkillIconLabel2, SkillIconLabel3, SkillIconLabel4, SkillMessageLabel1,
						SkillMessageLabel2, SkillMessageLabel3, SkillMessageLabel4; // 스킬 아이콘 및 설명 라벨
	public SkillScreen() {
		setSize(400, 690);
		setLayout(null);
		setLocation(1480, 180);
		setTitle("Skills");
		setIconImage(iconimage);
		getContentPane().setBackground(Color.BLACK);
		
		// 레이어 설정
		JLayeredPane layer = getLayeredPane();
		
		// 스킬 정보가 출력되는 패널
		SkillPanel1 = new JPanel(null);
		SkillPanel1.setBounds(20, 50, 345, 130);
		SkillPanel1.setBorder(new LineBorder(Color.BLUE));
		SkillPanel1.setOpaque(false);
		
		SkillPanel2 = new JPanel(null);
		SkillPanel2.setBounds(20, 190, 345, 130);
		SkillPanel2.setBorder(new LineBorder(Color.BLUE));
		SkillPanel2.setOpaque(false);
		
		SkillPanel3 = new JPanel(null);
		SkillPanel3.setBounds(20, 330, 345, 130);
		SkillPanel3.setBorder(new LineBorder(Color.BLUE));
		SkillPanel3.setOpaque(false);
		
		SkillPanel4 = new JPanel(null);
		SkillPanel4.setBounds(20, 470, 345, 130);
		SkillPanel4.setBorder(new LineBorder(Color.BLUE));
		SkillPanel4.setOpaque(false);
		
		// 배경 테두리 이미지
		JLabel BackgroundLabel = new JLabel(new ImageIcon(Background));
		BackgroundLabel.setBounds(0, 0, 380, 650);
		
		// 스킬 아이콘
		SkillIconLabel1 = new JLabel(new ImageIcon(Skill1));
		SkillIconLabel1.setBounds(5, 5, 210, 40);
		SkillIconLabel1.setText(" 드래곤 슬래셔 (습득 Lv : 10)");
		SkillIconLabel1.setForeground(new Color(0, 84, 255));
		
		SkillMessageLabel1 = new JLabel();
		SkillMessageLabel1.setBounds(5, 45, 210, 60);
		SkillMessageLabel1.setForeground(new Color(80, 175, 73));
		SkillMessageLabel1.setText("<html><p style='font-size:14pt; font-family:맑은 고딕;'>MP 70 소비,130%의 데미지로<br/>드래곤의 형상을 한 검기를<br/>소환하여 수차례 쏘아 보낸다.</p></html>");
		
		SkillIconLabel2 = new JLabel(new ImageIcon(Skill2));
		SkillIconLabel2.setBounds(5, 5, 210, 40);
		SkillIconLabel2.setText(" 오러 블레이드 (습득 Lv : 15)");
		SkillIconLabel2.setForeground(new Color(250, 237, 125));
		
		SkillMessageLabel2 = new JLabel();
		SkillMessageLabel2.setBounds(5, 45, 250, 60);
		SkillMessageLabel2.setForeground(new Color(80, 175, 73));
		SkillMessageLabel2.setText("<html><p style='font-size:14pt; font-family:맑은 고딕;'>MP 120 소비, 200%의 데미지로<br/>무기 전체를 타오르는 불꽃으로 휘감아<br/>전방을 향해 내려찍는다.</p></html>");
		
		SkillIconLabel3 = new JLabel(new ImageIcon(Skill3));
		SkillIconLabel3.setBounds(5, 5, 175, 40);
		SkillIconLabel3.setText(" 체력 회복 (습득 Lv : 5)");
		SkillIconLabel3.setForeground(new Color(191, 255, 183));
		
		SkillMessageLabel3 = new JLabel();
		SkillMessageLabel3.setBounds(5, 45, 240, 50);
		SkillMessageLabel3.setForeground(new Color(80, 175, 73));
		SkillMessageLabel3.setText("<html><p style='font-size:14pt; font-family:맑은 고딕;'>MP 50 소비, 체력을 회복하는 주문을<br/>사용하여 전체 HP의 40%를 회복한다.</p></html>");
		
		SkillIconLabel4 = new JLabel(new ImageIcon(Skill4));
		SkillIconLabel4.setBounds(5, 5, 220, 40);
		SkillIconLabel4.setText(" 디맨션 브레이커 (습득 Lv : 30)");
		SkillIconLabel4.setForeground(new Color(113, 18, 255));
		
		SkillMessageLabel4 = new JLabel();
		SkillMessageLabel4.setBounds(5, 35, 270, 100);
		SkillMessageLabel4.setForeground(new Color(80, 175, 73));
		SkillMessageLabel4.setText("<html><p style='font-size:14pt; font-family:맑은 고딕;'>MP 400 소비, 170%의 데미지로<br/>10회 공격하여 망자의 표식을 새기고<br/>마지막 타격에 350%의 데미지로<br/>차원 가르기를 사용한다. (궁극기)</p></html>");
		
		// 스킬 사용 버튼
		Use1 = new JButton("사용");
		Use1.setBounds(275, 30, 60, 60);
		Use1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Use2 = new JButton("사용");
		Use2.setBounds(275, 30, 60, 60);
		Use2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Use3 = new JButton("사용");
		Use3.setBounds(275, 30, 60, 60);
		Use3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Use4 = new JButton("사용");
		Use4.setBounds(275, 30, 60, 60);
		Use4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		SkillPanel1.add(Use1);
		SkillPanel1.add(SkillIconLabel1);
		SkillPanel1.add(SkillMessageLabel1);
		SkillPanel2.add(Use2);
		SkillPanel2.add(SkillIconLabel2);
		SkillPanel2.add(SkillMessageLabel2);
		SkillPanel3.add(Use3);
		SkillPanel3.add(SkillIconLabel3);
		SkillPanel3.add(SkillMessageLabel3);
		SkillPanel4.add(Use4);
		SkillPanel4.add(SkillIconLabel4);
		SkillPanel4.add(SkillMessageLabel4);
		
		layer.add(BackgroundLabel, new Integer(1));
		layer.add(SkillPanel1, new Integer(2));
		layer.add(SkillPanel2, new Integer(2));
		layer.add(SkillPanel3, new Integer(2));
		layer.add(SkillPanel4, new Integer(2));
				
		setVisible(true); 
	}
	
	public static void main(String[] args) {
		new SkillScreen();
	}
}
