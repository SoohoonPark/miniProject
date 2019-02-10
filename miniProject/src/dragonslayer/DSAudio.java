package dragonslayer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

// ���� ������ ����Ǵ� ��� Audio�� ����ϴ� Ŭ����
public class DSAudio{
	private static String titleBGM = "resource/sound/titlebgm.wav";
	private static String gameBGM = "resource/sound/gamebgm.wav";
	private static String beingHit = "resource/sound/being_hit.wav";
	private static String playeratk = "resource/sound/attack_player.wav";
	private static String monsteratk = "resource/sound/attack_monster.wav";
	private static Clip titleclip = null;
	private static Clip gameclip = null;
	private static Clip beinghitclip = null;
	private static Clip playeratkclip = null;
	private static Clip monsteratkclip = null;
	
	private static DSAudio audio = new DSAudio();
	private DSAudio() {}
	
	// MainScreen ~ LoadingScreen ���� ���Ǵ� bgm
	public void playTitle() {
		try {
			File bgm = new File(titleBGM);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			titleclip = AudioSystem.getClip();
			titleclip.open(ais);
			titleclip.loop(Clip.LOOP_CONTINUOUSLY);
			FloatControl gainControl = (FloatControl) titleclip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
			titleclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(Ÿ��Ʋ)");
		}
	}
	
	// Ÿ��Ʋ bgm ���� �޼ҵ�
	public void offTitle() {
		titleclip.stop();
	}
	
	
	// GameScreen���� ���Ǵ� bgm
	public void playGame() {
		try {
			File bgm = new File(gameBGM);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			gameclip = AudioSystem.getClip();
			gameclip.open(ais);
			gameclip.loop(Clip.LOOP_CONTINUOUSLY);
			gameclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(Ÿ��Ʋ)");
		}
	}
	
	// �ǰ��� (�÷��̾� & �� ����)
	public void playBeinghit() {
		try {
			File bgm = new File(beingHit);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			beinghitclip = AudioSystem.getClip();
			beinghitclip.open(ais);
			beinghitclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(Ÿ��Ʋ)");
		}
	}
	
	// �÷��̾� ���� ȿ����
	public void playAtk_P() {
		try {
			File bgm = new File(playeratk);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			playeratkclip = AudioSystem.getClip();
			playeratkclip.open(ais);
			playeratkclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(Ÿ��Ʋ)");
		}
	}
	
	// �� ����(��Ÿ) ȿ����
	public void playAtk_M() {
		try {
			File bgm = new File(monsteratk);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			monsteratkclip = AudioSystem.getClip();
			monsteratkclip.open(ais);
			monsteratkclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(Ÿ��Ʋ)");
		}
	}
	
	static DSAudio getInstance() {
		return audio;
	}
}
