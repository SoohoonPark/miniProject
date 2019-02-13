package dragonslayer;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

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
	
	// MainScreen ~ LoadingScreen 까지 사용되는 bgm
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
	
	// GameScreen bgm 끔
	public void offGame() {
		gameclip.stop();
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
			System.out.println("[Error] 오디오 재생 에러(효과음_데모닉소드)");
		}
	}
	
	// 디멘션 브레이커 효과음
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
			
			dimensionclip1.start(); // 첫번째 효과음 재생
			Timer secondeffect = new Timer();
			TimerTask secondeffecttask = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dimensionclip2.start(); // 두번째 효과음 재생
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
			secondeffect.schedule(secondeffecttask, 1400); // 첫번째 효과음 재생 후 2초 뒤 실행
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(효과음_디멘션브레이커)");
		}
	}
	
	// 용 스킬 효과음
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
			System.out.println("[Error] 오디오 재생 에러(효과음_용스킬)");
		}
	}
	
	// 용 평타 효과음
	public void playDragonAttack() {
		try {
			File bgm = new File(dragonattack);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			dragonattackclip = AudioSystem.getClip();
			dragonattackclip.open(ais);
			dragonattackclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(효과음_용평타)");
		}
	}
	
	// 함정 비명 소리
	public void playTrapScream() {
		try {
			File bgm = new File(trapscream);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			trapscreamclip = AudioSystem.getClip();
			trapscreamclip.open(ais);
			trapscreamclip.start();
		}catch(Exception e) {
			System.out.println("[Error] 오디오 재생 에러(효과음_함정비명)");
		}
	}
	
	// 용 페이즈2 진입 사운드
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
			System.out.println("[Error] 오디오 재생 에러(효과음_용포효)");
			System.out.println(e.getMessage());
		}
	}
	
	// 메인화면 & 캐릭터 생성창 버튼 클릭 음
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
			System.out.println("[Error] 오디오 에러 - 버튼 클릭");
			System.out.println(e.getMessage());
		}
	}
	
	static DSAudio getInstance() {
		return audio;
	}
}
