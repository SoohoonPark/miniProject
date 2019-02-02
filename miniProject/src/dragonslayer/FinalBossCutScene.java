package dragonslayer;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;

@SuppressWarnings("serial")
public class FinalBossCutScene extends JFrame{
	
	public FinalBossCutScene() {
		boolean found = new NativeDiscovery().discover();
        System.out.println(found);
        System.out.println(LibVlc.INSTANCE.libvlc_get_version());
        
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		Canvas c = new Canvas();
		c.setBackground(Color.BLACK);
		JPanel p = new JPanel(new BorderLayout());
		p.add(c);
		add(p);
		
//		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"VLC");
//		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		
		MediaPlayerFactory mpf = new MediaPlayerFactory();
		EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(this));
		emp.setVideoSurface(mpf.newVideoSurface(c));
//		emp.toggleFullScreen();
		emp.setEnableMouseInputHandling(false);
		emp.setEnableKeyInputHandling(false);
		
		String file = "resource/video/test_video.mp4";
		emp.prepareMedia(file);
		emp.play();
	}

	
	public static void main(String[] args) {
		new FinalBossCutScene();
	}
}
