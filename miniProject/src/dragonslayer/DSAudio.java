package dragonslayer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// ���� ������ ����Ǵ� ��� Audio�� ����ϴ� Ŭ����
public class DSAudio{
	private static String titleBGM = "resource/sound/titlebgm.wav";
	private static String gameBGM = "resource/sound/gamebgm.wav";
	
	// MainScreen ~ LoadingScreen ���� ���Ǵ� bgm
	public static Clip playTitle() {
		Clip clip = null;
		try {
			File bgm = new File(titleBGM);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			return clip;
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(Ÿ��Ʋ)");
		}
		return clip;
	}
	
	// GameScreen���� ���Ǵ� bgm
	public static Clip playGame() {
		Clip clip = null;
		try {
			File bgm = new File(gameBGM);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			return clip;
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(Ÿ��Ʋ)");
		}
		return clip;
	}
}
