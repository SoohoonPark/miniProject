package dragonslayer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

// 게임 내에서 재생되는 모든 Audio를 담당하는 클래스
public class DSAudio{
	private static String titleBGM = "resource/sound/titlebgm.wav";
	private static String gameBGM = "resource/sound/gamebgm.wav";
	private static String beingHit = "resource/sound/being_hit.wav";
	private static String playeratk = "resource/sound/attack_player.wav";
	private static String monsteratk = "resource/sound/attack_monster.wav";
	private static String dragonslasher = "resource/sound/skill_dragonslasher.wav";
	private static String aurablade = "resource/sound/skill_aurablade.wav";
	private static String demonicsword = "resource/sound/skill_demonicsword.wav";
	private static Clip titleclip = null;
	private static Clip gameclip = null;
	private static Clip beinghitclip = null;
	private static Clip playeratkclip = null;
	private static Clip monsteratkclip = null;
	private static Clip dragonslasherclip = null;
	private static Clip aurabladeclip = null;
	private static Clip demonicswordclip = null;
	
	private static DSAudio audio = new DSAudio();
	private DSAudio() {}
	
	// MainScreen ~ LoadingScreen 까지 사용되는 bgm
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
			System.out.println("[Error] 오디오 재생 에러(타이틀)");
			System.out.println(e.getMessage());
		}
	}
	
	// 타이틀 bgm 끄는 메소드
	public void offTitle() {
		titleclip.stop();
	}
	
	
	// GameScreen에서 사용되는 bgm
	public void playGame() {
		try {
			File bgm = new File(gameBGM);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			gameclip = AudioSystem.getClip();
			gameclip.open(ais);
			gameclip.loop(Clip.LOOP_CONTINUOUSLY);
			gameclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(게임)");
			System.out.println(e.getMessage());
		}
	}
	
	// 피격음 (플레이어 & 몹 공용)
	public void playBeinghit() {
		try {
			File bgm = new File(beingHit);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			beinghitclip = AudioSystem.getClip();
			beinghitclip.open(ais);
			beinghitclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(피격음)");
		}
	}
	
	// 플레이어 공격 효과음
	public void playAtk_P() {
		try {
			File bgm = new File(playeratk);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			playeratkclip = AudioSystem.getClip();
			playeratkclip.open(ais);
			playeratkclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(공격음_유저)");
		}
	}
	
	// 몹 공격(평타) 효과음
	public void playAtk_M() {
		try {
			File bgm = new File(monsteratk);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			monsteratkclip = AudioSystem.getClip();
			monsteratkclip.open(ais);
			monsteratkclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(공격음_몹)");
		}
	}
	
	// 드래곤 슬래셔 효과음
	public void playDragonSlasher() {
		try {
			File bgm = new File(dragonslasher);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			dragonslasherclip = AudioSystem.getClip();
			dragonslasherclip.open(ais);
			dragonslasherclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(효과음_드래곤슬래셔)");
		}
	}
	
	// 오러 블레이드 효과음
	public void playAuraBlade() {
		try {
			File bgm = new File(aurablade);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			aurabladeclip = AudioSystem.getClip();
			aurabladeclip.open(ais);
			aurabladeclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(효과음_오러블레이드)");
		}
	}
	
	// 데모닉 소드 효과음
	public void playDemonicSword() {
		try {
			File bgm = new File(demonicsword);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			demonicswordclip = AudioSystem.getClip();
			demonicswordclip.open(ais);
			demonicswordclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(효과음_오러블레이드)");
		}
	}
	
	
	static DSAudio getInstance() {
		return audio;
	}
}
