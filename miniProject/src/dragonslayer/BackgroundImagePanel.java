package dragonslayer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundImagePanel extends JPanel{
	private Image backgroundimg = Toolkit.getDefaultToolkit().createImage("resource/images/background/battlebackground_1_resize.png");

	public BackgroundImagePanel() {
		setSize(backgroundimg.getWidth(null), backgroundimg.getHeight(null));
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundimg, 0, 0, null);
	}
}

