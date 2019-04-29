package com.deadlast.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.deadlast.game.DeadLast;

/**
 * Factory/utility class for building instances of {@link Enemy}.
 * 
 * @author Xzytl
 *
 */
public class EnemyFactory {

	private static EnemyFactory instance;

	private DeadLast game;

	private EnemyFactory(DeadLast game) {
		this.game = game;
	}

	public static EnemyFactory getInstance(DeadLast game) {
		if (instance == null) {
			instance = new EnemyFactory(game);
		}
		return instance;
	}

	public Enemy.Builder get(Enemy.Type type) {
		Enemy.Builder builder = new Enemy.Builder().setGame(game);
		switch (type) {
		case BOMBER:
			builder.setHealthStat(8).setSpeedStat(2).setStrengthStat(10).setDetectionStat(9)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/bomber_zombie.png"))))
					.setScoreValue(60).setBodyRadius(0.5f).setDensity(10);
			break;
		case FAST:
			builder.setHealthStat(4).setSpeedStat(5).setStrengthStat(5).setDetectionStat(7)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/fast_zombie.png"))))
					.setScoreValue(20).setBodyRadius(0.4f).setDensity(8);
			break;
		case HEAVY:
			builder.setHealthStat(10).setSpeedStat(3).setStrengthStat(10).setDetectionStat(9)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/heavy_zombie.png"))))
					.setScoreValue(50).setBodyRadius(0.75f).setDensity(12);
			break;
		case HORDLING:
			builder.setHealthStat(2).setSpeedStat(5).setStrengthStat(1).setDetectionStat(10)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/hordling_zombie.png"))))
					.setScoreValue(5).setBodyRadius(0.3f).setDensity(7);
			break;
		case JOCKEY:

			builder.setHealthStat(10).setSpeedStat(4).setStrengthStat(4).setDetectionStat(9)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/jockey_zombie.png"))))
					.setScoreValue(80).setBodyRadius(0.4f).setDensity(10);
			break;
		case NORMAL:

			builder.setHealthStat(6).setSpeedStat(3).setStrengthStat(6).setDetectionStat(6)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/normal_zombie.png"))))
					.setScoreValue(10).setBodyRadius(0.4f).setDensity(10);
			break;
		case BOSS:
			builder.setHealthStat(20).setSpeedStat(1).setStrengthStat(4).setDetectionStat(10)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/boss1.png")))).setScoreValue(100)
					.setBodyRadius(1.5f).setDensity(15);
			break;
		case BOSS2:
			builder.setHealthStat(40).setSpeedStat(3).setStrengthStat(3).setDetectionStat(10)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/boss2.png")))).setScoreValue(200)
					.setBodyRadius(1.5f).setDensity(15);
			break;
		case GOOSE:
			// doesnt work atm
			builder.setHealthStat(400).setSpeedStat(4).setStrengthStat(0).setDetectionStat(0)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/goose.png")))).setScoreValue(0)
					.setBodyRadius(1.5f);
			break;
		case CURED:
			// doesnt work atm
			builder.setHealthStat(400).setSpeedStat(4).setStrengthStat(0).setDetectionStat(0)
					.setSprite(new Sprite(new Texture(Gdx.files.internal("entities/cured.png")))).setScoreValue(0)
					.setBodyRadius(1.5f);
			break;
		default:
			break;
		}
		return builder;
	}

}
