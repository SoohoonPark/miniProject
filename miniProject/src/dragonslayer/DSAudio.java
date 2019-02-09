package dragonslayer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// 게임 내에서 재생되는 모든 Audio를 담당하는 클래스
public class DSAudio{
	private static String titleBGM = "resource/sound/titlebgm.wav";
	private static String gameBGM = "resource/sound/gamebgm.wav";
	
	// MainScreen ~ LoadingScreen 까지 사용되는 bgm
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
			System.out.println("[Error] 오디오 재생 에러(타이틀)");
		}
		return clip;
	}
	
	// GameScreen에서 사용되는 bgm
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
			System.out.println("[Error] 오디오 재생 에러(타이틀)");
		}
		return clip;
	}
}
