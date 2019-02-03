package dragonslayer;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

@SuppressWarnings("serial")
public class FinalBossCutScene extends JFrame{
	Thread runcheck;
	public FinalBossCutScene() {
//		boolean found = new NativeDiscovery().discover();
//		System.out.println(found);
//		System.out.println(LibVlc.INSTANCE.libvlc_get_version());
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"VLC");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
		setBounds(100, 100, 1000, 600);
		setVisible(true);
		
		Canvas c = new Canvas();
		c.setBackground(Color.BLACK);
		JPanel p = new JPanel(new BorderLayout());
		p.add(c);
		add(p);
		
		MediaPlayerFactory mpf = new MediaPlayerFactory();
		EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(this));
		emp.setVideoSurface(mpf.newVideoSurface(c));
//		emp.toggleFullScreen();
		emp.setEnableMouseInputHandling(false);
		emp.setEnableKeyInputHandling(false);
		
		String file = "resource/video/test_video.mp4";
		emp.prepareMedia(file);
		emp.play();
		
		runcheck = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(500);
						System.out.println(emp.isPlaying());
						if(!emp.isPlaying()) {
							dispose();
							Thread.currentThread().interrupt();
						}
					}catch(Exception e) {
						System.out.println("[Error] Thread error");
					}
				}
			}
		});
		runcheck.start();
	}
	
//	public static void main(String[] args) {
//		new FinalBossCutScene();
//	}
}
