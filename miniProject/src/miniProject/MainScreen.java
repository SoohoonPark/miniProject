package miniProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

// 프로그램을 처음 실행 시 출력되는 화면(메인화면)
@SuppressWarnings("serial")
public class MainScreen extends JFrame{
	// 메인 이미지(타이틀과 별개)
	private Image mainimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/mainimage.png");
	private Image titleimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleimage.jpg");
	private Image iconimage = Toolkit.getDefaultToolkit().createImage("resource/images/title/titleicon.png");
	private JButton btnGamestart, btnGameQuit;
	
	public MainScreen() {  
	    super("Dragon Slayer");
	    setSize(700, 525);
	    setResizable(false);
	    
	    JLayeredPane layer = getLayeredPane();
	    
	    JLabel mainimageLabel = new JLabel(new ImageIcon(mainimage));
	    mainimageLabel.setBounds(0, 0, 700, 525);
	    
	    JLabel titleimageLabel = new JLabel(new ImageIcon(titleimage));
	    titleimageLabel.setBounds(0, 0, 302, 182);
	    
	    JPanel buttonPanel = new JPanel(null);
	    buttonPanel.setBounds(400, 200, 120, 240);
	    buttonPanel.setOpaque(false);
	    
	    btnGamestart = new JButton("게임시작");
	    btnGamestart.setBounds(5, 10, 100, 50);
	    
	    btnGameQuit = new JButton("게임종료");
	    btnGameQuit.setBounds(5, 70, 100, 50);
	    
	    buttonPanel.add(btnGamestart);
	    buttonPanel.add(btnGameQuit);
	    
	    layer.add(mainimageLabel, new Integer(1));
	    layer.add(titleimageLabel, new Integer(2));
	    layer.add(buttonPanel, new Integer(3));
	    setVisible(true);
	  } 
	
	  public static void main(String[] args) {  
		  new MainScreen();
	  }  
}
