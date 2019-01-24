package dragonslayer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundImagePanel extends JPanel{
	private Image backgroundimg;

	public BackgroundImagePanel(Image img) {
		this.backgroundimg = img;
		setSize(img.getWidth(null), img.getHeight(null));
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundimg, 0, 0, null);
	}
}