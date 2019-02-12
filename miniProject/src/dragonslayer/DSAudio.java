package dragonslayer;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

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
	private static String dragonslasher = "resource/sound/skill_dragonslasher.wav";
	private static String aurablade = "resource/sound/skill_aurablade.wav";
	private static String demonicsword = "resource/sound/skill_demonicsword.wav";
	private static String dimension_1 = "resource/sound/skill_dimension_first.wav";
	private static String dimension_2 = "resource/sound/skill_dimension_second.wav";
	private static String dimension_3 = "resource/sound/skill_dimension_third.wav";
	private static String dimension_4 = "resource/sound/skill_dimension_fourth.wav";
	
	private static Clip titleclip = null;
	private static Clip gameclip = null;
	private static Clip beinghitclip = null;
	private static Clip playeratkclip = null;
	private static Clip monsteratkclip = null;
	private static Clip dragonslasherclip = null;
	private static Clip aurabladeclip = null;
	private static Clip demonicswordclip = null;
	private static Clip dimensionclip1,dimensionclip2,dimensionclip3,dimensionclip4;
	
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
			System.out.println(e.getMessage());
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
			System.out.println("[Error] ����� ��� ����(����)");
			System.out.println(e.getMessage());
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
			System.out.println("[Error] ����� ��� ����(�ǰ���)");
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
			System.out.println("[Error] ����� ��� ����(������_����)");
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
			System.out.println("[Error] ����� ��� ����(������_��)");
		}
	}
	
	// �巡�� ������ ȿ����
	public void playDragonSlasher() {
		try {
			File bgm = new File(dragonslasher);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			dragonslasherclip = AudioSystem.getClip();
			dragonslasherclip.open(ais);
			dragonslasherclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(ȿ����_�巡�ｽ����)");
		}
	}
	
	// ���� ���̵� ȿ����
	public void playAuraBlade() {
		try {
			File bgm = new File(aurablade);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			aurabladeclip = AudioSystem.getClip();
			aurabladeclip.open(ais);
			aurabladeclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(ȿ����_�������̵�)");
		}
	}
	
	// ����� �ҵ� ȿ����
	public void playDemonicSword() {
		try {
			File bgm = new File(demonicsword);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			demonicswordclip = AudioSystem.getClip();
			demonicswordclip.open(ais);
			demonicswordclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(ȿ����_�������̵�)");
		}
	}
	
	// ���� �극��Ŀ ȿ����
	public void playDimensionBreaker() {
		try {
			File bgm = new File(dimension_1);
			File bgm2 = new File(dimension_2);
			File bgm3 = new File(dimension_3);
			File bgm4 = new File(dimension_4);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			AudioInputStream ais2 = AudioSystem.getAudioInputStream(bgm2);
			AudioInputStream ais3 = AudioSystem.getAudioInputStream(bgm3);
			AudioInputStream ais4 = AudioSystem.getAudioInputStream(bgm4);
			
			dimensionclip1 = AudioSystem.getClip();
			dimensionclip2 = AudioSystem.getClip();
			dimensionclip3 = AudioSystem.getClip();
			dimensionclip4 = AudioSystem.getClip();
			
			dimensionclip1.open(ais);
			dimensionclip2.open(ais2);
			dimensionclip3.open(ais3);
			dimensionclip4.open(ais4);
			
			dimensionclip1.start(); // ù��° ȿ���� ���
			Timer secondeffect = new Timer();
			TimerTask secondeffecttask = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dimensionclip2.start(); // �ι�° ȿ���� ���
					Timer thirdeffect = new Timer();
					TimerTask thirdeffecttask = new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							dimensionclip3.start();
							Timer fourtheffect = new Timer();
							TimerTask fourtheffecttask = new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									dimensionclip4.start();
								}
							};
							fourtheffect.schedule(fourtheffecttask, 1300);
						}
					};
					thirdeffect.schedule(thirdeffecttask, 1400);
				}
			};
			secondeffect.schedule(secondeffecttask, 1400); // ù��° ȿ���� ��� �� 2�� �� ����
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(ȿ����_���Ǻ극��Ŀ)");
		}
	}
	
	static DSAudio getInstance() {
		return audio;
	}
}
