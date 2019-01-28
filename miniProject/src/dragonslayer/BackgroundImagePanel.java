package dragonslayer;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

@SuppressWarnings("serial")
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
	
	public void setBackgroundImg(Image img) {
		this.backgroundimg = img;
	}
}