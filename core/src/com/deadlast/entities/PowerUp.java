package com.deadlast.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.deadlast.game.DeadLast;
import com.deadlast.util.EffectDuration;
import com.deadlast.world.FixtureType;

import box2dLight.PointLight;

public class PowerUp extends Entity {
	
	private Type type;
	
	private PointLight pointLight;
	
	public PowerUp(DeadLast game, int scoreValue, Sprite sprite, float bRadius, Vector2 initialPos, Type type) {
		super(game, scoreValue, sprite, bRadius, initialPos);
		this.type = type;
	}

	public enum Type {
		STEALTH(EffectDuration.Time.SHORT),
		DOUBLE_DAMAGE(EffectDuration.Time.NORMAL),
		DOUBLE_POINTS(EffectDuration.Time.LONG),
		REGEN(EffectDuration.Time.NORMAL),
		SPEED(EffectDuration.Time.NORMAL),
		COIN(EffectDuration.Time.INSTANT),
		CURE(EffectDuration.Time.INFINITE);
		
		private EffectDuration.Time duration;
		
		Type(EffectDuration.Time duration) {
			this.duration = duration;
		}
		
		public EffectDuration.Time getDuration() {
			return duration;
		}
	}
	
	public Type getType() {
		return type;
	}
	
	public EffectDuration.Time getDuration() {
		return type.duration;
	}

	@Override
	public void defineBody() {
		BodyDef bDef = new BodyDef();
		bDef.type = BodyDef.BodyType.StaticBody;
		bDef.position.set(initialPos);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(this.bRadius);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.filter.categoryBits = Entity.POWERUP;
		fDef.filter.maskBits = Entity.PLAYER;
		
		b2body = world.createBody(bDef);
		b2body.createFixture(fDef).setUserData(FixtureType.POWERUP);
		b2body.setUserData(this);
		
		if (this.type == PowerUp.Type.COIN) {
			pointLight = new PointLight(gameManager.getRayHandler(), 32, Color.WHITE, 0.3f, b2body.getPosition().x, b2body.getPosition().y);
		} else {
			pointLight = new PointLight(gameManager.getRayHandler(), 32, Color.GOLD, 1, b2body.getPosition().x, b2body.getPosition().y);
		}
		
		pointLight.attachToBody(b2body);

		shape.dispose();
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete() {
		super.delete();
		if (pointLight != null) {
			pointLight.remove(true);
		}
	}
	
	public static class Builder {
		
		private DeadLast game;
		private int scoreValue;
		private Sprite sprite;
		private float bRadius;
		private Vector2 initialPos;
		private Type type;
		
		public Builder setGame(DeadLast game) {
			this.game = game;
			return this;
		}
		
		public Builder setScoreValue(int scoreValue) {
			this.scoreValue = scoreValue;
			return this;
		}
		
		public Builder setSprite(Sprite sprite) {
			this.sprite = sprite;
			return this;
		}
		
		public Builder setBodyRadius(float bRadius) {
			this.bRadius = bRadius;
			return this;
		}
		
		public Builder setInitialPosition(Vector2 initialPos) {
			this.initialPos = initialPos;
			return this;
		}
		
		public Builder setType(Type type) {
			this.type = type;
			return this;
		}
		
		/**
		 * Converts builder object into instance of PowerUP
		 * @return an instance of PowerUp with the provided parameters
		 * @throws IllegalArgumentException if mandatory parameters are not set
		 */
		public PowerUp build() {
			if (game == null) {
				throw new IllegalArgumentException("Invalid 'game' parameter");
			}
			if (type == null) {
				throw new IllegalArgumentException("Invalid 'type' parameter");
			}
			if (sprite == null) {
				sprite = new Sprite(new Texture(Gdx.files.internal("entities/blank_powerup.png")));
			}
			if (bRadius == 0) {
				bRadius = 0.25f;
			}
			if (initialPos == null) {
				throw new IllegalArgumentException("Invalid 'initialPos' parameter");
			}
			return new PowerUp(game, scoreValue, sprite, bRadius, initialPos, type);
		}
		
	}

}
