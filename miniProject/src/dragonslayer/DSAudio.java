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
	private static String dragonslasher1 = "resource/sound/skill_dragonslasher_first.wav";
	private static String dragonslasher2 = "resource/sound/skill_dragonslasher_second.wav";
	private static String aurablade = "resource/sound/skill_aurablade.wav";
	private static String demonicsword = "resource/sound/skill_demonicsword.wav";
	private static String dimension_1 = "resource/sound/skill_dimension_first.wav";
	private static String dimension_2 = "resource/sound/skill_dimension_second.wav";
	private static String dimension_3 = "resource/sound/skill_dimension_third.wav";
	private static String dimension_4 = "resource/sound/skill_dimension_fourth.wav";
	private static String dragonskill1 = "resource/sound/dragon_skill_first.wav";
	private static String dragonskill2 = "resource/sound/dragon_skill_second.wav";
	private static String dragonattack = "resource/sound/dragon_attack.wav";
	private static String trapscream = "resource/sound/trap_scream.wav";
	private static String dragonroar = "resource/sound/dragon_roar.wav";
	private static String buttonclicked = "resource/sound/button_clicked.wav";
	
	private static Clip titleclip,buttonclickclip;
	private static Clip gameclip = null;
	private static Clip beinghitclip = null;
	private static Clip playeratkclip = null;
	private static Clip monsteratkclip = null;
	private static Clip dragonslasherclip1,dragonslasherclip2;
	private static Clip aurabladeclip = null;
	private static Clip demonicswordclip = null;
	private static Clip dimensionclip1,dimensionclip2,dimensionclip3,dimensionclip4;
	private static Clip dragonskillclip1,dragonskillclip2,dragonattackclip,dragonroarclip;
	private static Clip trapscreamclip;
	
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
			gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
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
	
	// GameScreen bgm ��
	public void offGame() {
		gameclip.stop();
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
			File bgm = new File(dragonslasher1);
			File bgm2 = new File(dragonslasher2);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			AudioInputStream ais2 = AudioSystem.getAudioInputStream(bgm2);
			dragonslasherclip1 = AudioSystem.getClip();
			dragonslasherclip2 = AudioSystem.getClip();
			dragonslasherclip1.open(ais);
			dragonslasherclip2.open(ais2);
			dragonslasherclip1.start();
			Timer secondeffect = new Timer();
			TimerTask secondeffecttask = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dragonslasherclip2.start();
				}
			};
			secondeffect.schedule(secondeffecttask, 400);
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
			System.out.println("[Error] ����� ��� ����(ȿ����_����мҵ�)");
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
	
	// �� ��ų ȿ����
	public void playDragonSkill() {
		try {
			File bgm = new File(dragonskill1);
			File bgm2 = new File(dragonskill2);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			AudioInputStream ais2 = AudioSystem.getAudioInputStream(bgm2);
			
			dragonskillclip1 = AudioSystem.getClip();
			dragonskillclip2 = AudioSystem.getClip();
			
			dragonskillclip1.open(ais);
			dragonskillclip2.open(ais2);
			
			dragonskillclip1.start();
			Timer secondeffect = new Timer();
			TimerTask secondeffecttask = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dragonskillclip2.start();
				}
			};
			secondeffect.schedule(secondeffecttask, 2500);
			
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(ȿ����_�뽺ų)");
		}
	}
	
	// �� ��Ÿ ȿ����
	public void playDragonAttack() {
		try {
			File bgm = new File(dragonattack);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			dragonattackclip = AudioSystem.getClip();
			dragonattackclip.open(ais);
			dragonattackclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(ȿ����_����Ÿ)");
		}
	}
	
	// ���� ��� �Ҹ�
	public void playTrapScream() {
		try {
			File bgm = new File(trapscream);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			trapscreamclip = AudioSystem.getClip();
			trapscreamclip.open(ais);
			trapscreamclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(ȿ����_�������)");
		}
	}
	
	// �� ������2 ���� ����
	public void playDragonRoar() {
		try {
			File bgm = new File(dragonroar);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			dragonroarclip = AudioSystem.getClip();
			dragonroarclip.open(ais);
			FloatControl gainControl = (FloatControl) dragonroarclip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(6.0f); // set volume by 6(this audio maximum) decibels.
			dragonroarclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ��� ����(ȿ����_����ȿ)");
			System.out.println(e.getMessage());
		}
	}
	
	// ����ȭ�� & ĳ���� ����â ��ư Ŭ�� ��
	public void playButtonClick() {
		try {
			File bgm = new File(buttonclicked);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			buttonclickclip = AudioSystem.getClip();
			buttonclickclip.open(ais);
			FloatControl gainControl = (FloatControl) buttonclickclip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(6.0f); // set volume by 6(this audio maximum) decibels.
			buttonclickclip.start();
		}catch(Exception e) {
			System.out.println("[Error] ����� ���� - ��ư Ŭ��");
			System.out.println(e.getMessage());
		}
	}
	
	static DSAudio getInstance() {
		return audio;
	}
}
