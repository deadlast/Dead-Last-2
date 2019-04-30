package com.deadlast.entities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.deadlast.game.DeadLast;
import com.deadlast.game.GameManager;
import com.deadlast.util.EffectDuration;
import com.deadlast.world.BodyFactory;
import com.deadlast.world.FixtureType;
import com.deadlast.world.WorldContactListener;

import box2dLight.ConeLight;
import box2dLight.PointLight;

/**
 * This class represents the player character.
 * @author Xzytl
 *
 */
public class Player extends Mob {
	
	private int stealthStat;
	
	/**
	 * Represents whether the zombies on the map are aware of the player by default.
	 * Currently unimplemented.
	 */
	@SuppressWarnings("unused")
	private boolean isHidden;

	private boolean attkCooldown = false;
	
	/**
	 * Whether the player is attempting to use their attack ability.
	 */
	private boolean isAttacking;
	
	/**
	 * Contains the power-ups currently active on the player.
	 * Float is the number of seconds remaining until the effect expires.
	 */
	private Map<PowerUp.Type, EffectDuration> activePowerUps;
	
	/**
	 * The time until the player can next be healed by a regen power-up.
	 */
	private float healCooldown = 1f;
	
	/**
	 * The time until the player can next use the attack ability.
	 */
	private float attackCooldown = 0f;
	/**
	 * The player's default sprite.
	 */
	private Sprite defaultSprite; 
	/**
	 * The sprite the player changes to when attacking.
	 */
	private Sprite attackSprite; 
	/**
	 * Contains the enemies currently in range and in front of the player that will be
	 * damaged when the attack ability is used.
	 */
	private Set<Mob> mobsInRange;
	
	private PointLight effectRadius;
	private double effectTimer;
	
	/**
	 * Default constructor
	 * @param game			a reference to the DeadLast game instance
	 * @param sprite		the graphical sprite to use
	 * @param bRadius		the radius of the player's hitbox circle
	 * @param initialPos	the initial position to place the player
	 * @param healthStat	the player's normal health
	 * @param speedStat		the player's normal speed
	 * @param strengthStat	the player's normal strength
	 * @param stealthStat	the player's normal stealth level
	 */
	public Player(
			DeadLast game, Sprite defaultSprite, Sprite attackSprite, float bRadius,
			Vector2 initialPos, int healthStat, int speedStat, int strengthStat, int stealthStat
	) {
		super(game, 0, defaultSprite, bRadius, initialPos, healthStat, speedStat, strengthStat);
		this.attackSprite = attackSprite;
		if (attackSprite != null) {
			attackSprite.setSize(bRadius * 2, bRadius * 2);
			attackSprite.setOrigin(bRadius, bRadius);
		}
		this.defaultSprite = defaultSprite;
		this.sprite = defaultSprite;
		this.stealthStat = stealthStat;
		this.isHidden = true;
		this.activePowerUps = new ConcurrentHashMap<>();
		this.mobsInRange = new HashSet<>();
	}
	
	public int getStealthStat() {
		return this.stealthStat;
	}
	
	public boolean isAttacking() {
		return isAttacking;
	}
	
	public void isAttacking(boolean bool) {
		this.isAttacking = bool;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Vector2 mousePos = GameManager.getInstance(this.game).getMouseWorldPos();
		double angle = Math.toDegrees(Math.atan2(mousePos.y - b2body.getPosition().y, mousePos.x - b2body.getPosition().x));
		this.setAngle(angle - 90);
		this.setOpacity();
		super.render(batch);
	}
	
	@Override
	public void defineBody() {
		BodyDef bDef = new BodyDef();
		bDef.type = BodyDef.BodyType.DynamicBody;
		bDef.position.set(initialPos);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(this.bRadius);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.filter.categoryBits = Entity.PLAYER;
		fDef.filter.maskBits = Entity.BOUNDARY | Entity.ENEMY | Entity.POWERUP | Entity.ENEMY_HEARING | Entity.ENEMY_VISION | Entity.END_ZONE | Entity.NPC;
		
		b2body = world.createBody(bDef);
		b2body.createFixture(fDef).setUserData(FixtureType.PLAYER);
		
		BodyFactory bFactory = BodyFactory.getInstance(world);
		bFactory.makeMeleeSensor(b2body, 7, 60, 1.5f);
		
		coneLight = new ConeLight(gameManager.getRayHandler(), 7, Color.BLUE, 2, b2body.getPosition().x, b2body.getPosition().y, b2body.getAngle() + 90, 35);
		coneLight.attachToBody(b2body, 0, 0, 90);
		
		b2body.setUserData(this);
		b2body.setSleepingAllowed(false);

		shape.dispose();
	}
	
	public void createEffectRadius(float radius, Color color, double time) {
		effectRadius = new PointLight(gameManager.getRayHandler(), 16, color, radius, b2body.getPosition().x, b2body.getPosition().y);
		effectRadius.attachToBody(b2body);
		effectTimer = time;
	}
	
	public void removeEffectRadius() {
		if (effectRadius != null) {
			effectRadius.remove(true);
			effectRadius = null;
			effectTimer = 0;
		}
	}
	
	/**
	 * Called by {@link WorldContactListener} when the player makes contact with a power-up.
	 * @param powerUp the power-up the user obtained
	 */
	public void onPickup(PowerUp powerUp) {
		activePowerUps.put(powerUp.getType(), new EffectDuration(powerUp.getDuration()));
	}
	
	/**
	 * Called by {@link WorldContactListener} when a {@link Mob} enters the player's effective melee zone.
	 * @param mob
	 */
	public void onMeleeRangeEntered(Mob mob) {
		this.mobsInRange.add(mob);
	}
	
	/**
	 * Called by {@link WorldContactListener} when a {@link Mob} leaves the player's effective melee zone.
	 * @param mob
	 */
	public void onMeleeRangeLeft(Mob mob) {
		this.mobsInRange.remove(mob);
	}
	
	/**
	 * Convenience method to check whether a player has a particular active power-up
	 * @param type
	 * @return
	 */
	public boolean isPowerUpActive(PowerUp.Type type) {
		return activePowerUps.containsKey(type);
	}
	
	public void removePowerUp(PowerUp.Type type) {
		activePowerUps.remove(type);
	}
	
	public void onEndZoneReached() {
		gameManager.levelComplete();
	}
	
	@Override
	public void update(float delta) {
		if (isPowerUpActive(PowerUp.Type.REGEN)) {
			healCooldown -= delta;
			if (healCooldown <= 0 && this.getHealth() < this.getMaxHealth()) {
				this.setHealth(this.getHealth() + 1);
				healCooldown = 1f;
			} 
		}
		for(Map.Entry<PowerUp.Type, EffectDuration> entry : activePowerUps.entrySet()) {
			EffectDuration effectDuration = entry.getValue();
//			if (entry.getValue() - delta >= 0) {
//				activePowerUps.put(entry.getKey(), entry.getValue() - delta);
//			} else {
//				activePowerUps.remove(entry.getKey());
//			}
			effectDuration.update(delta);
			if (entry.getValue().isZero()) {
				activePowerUps.remove(entry.getKey());
			}
		}
		if(attkCooldown){
			this.sprite = attackSprite;
			if(attackCooldown - delta <= 0){
				attkCooldown = false;
			} else {
				attackCooldown -= delta;
			}
			
		}else {
			this.sprite = defaultSprite;
		}
		if (isAttacking) {
			if (!attkCooldown) {
				mobsInRange.forEach(m -> m.applyDamage(this.getStrength() * getDamageMultiplier()));
				attackCooldown = 0.5f;
				attkCooldown = true;
				this.sprite = attackSprite;
			}
		}
		if (effectRadius != null) {
			if (effectTimer - delta <= 0) {
				removeEffectRadius();
			} else {
				effectTimer -= delta;
			}
		}
	}

	/**
	 * Calculates the player's current damage modifier
	 * @return the damage multiplier
	 */
	public int getDamageMultiplier(){
		if(this.isPowerUpActive(PowerUp.Type.DOUBLE_DAMAGE)){
			return 2;
		} else {
			return 1;
		}
	}
	
	public void setOpacity() {
		if(this.isPowerUpActive(PowerUp.Type.STEALTH)) {
			this.sprite.setAlpha(0.4f);
		}else {
			this.sprite.setAlpha(1f);
		}
	}
	
	

	public boolean getCooldown() {
		return this.attkCooldown;
	}
	
	@Override
	public void delete() {
		super.delete();
		if (effectRadius != null) {
			effectRadius.remove(true);
		}
	}
	
	public static class Builder {
		
		private DeadLast game;
		private Sprite defaultSprite;
		private Sprite attackSprite;
		private float bRadius;
		private Vector2 initialPos;
		private int healthStat;
		private int speedStat;
		private int strengthStat;
		private int stealthStat;

		public Builder setGame(DeadLast game) {
			this.game = game;
			return this;
		}
		
		public Builder setSprites(String[] sprites) {
			this.defaultSprite = new Sprite(new Texture(Gdx.files.internal(sprites[0])));
			this.attackSprite = new Sprite(new Texture(Gdx.files.internal(sprites[1])));
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
		
		public Builder setHealthStat(int healthStat) {
			this.healthStat = healthStat;
			return this;
		}
		
		public Builder setSpeedStat(int speedStat) {
			this.speedStat = speedStat;
			return this;
		}
		
		public Builder setStrengthStat(int strengthStat) {
			this.strengthStat = strengthStat;
			return this;
		}
		
		public Builder setStealthStat(int stealthStat) {
			this.stealthStat = stealthStat;
			return this;
		}
		
		public Player build() {
			return new Player(
				game, defaultSprite, attackSprite, bRadius, initialPos, healthStat, speedStat, strengthStat, stealthStat
			);
		}

	}

}
