package com.deadlast.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.deadlast.game.DeadLast;
import com.deadlast.world.FixtureType;

import box2dLight.ConeLight;

public class NPC extends Mob {
	
	private float invulnerableTimer = 1f;

	public NPC(DeadLast game, Vector2 initialPos) {
		super(game, -10, new Sprite(new Texture("entities/student.png")), 0.4f, initialPos, 1, 4, 2);
	}

	@Override
	public void defineBody() {
		BodyDef bDef = new BodyDef();
		bDef.type = BodyDef.BodyType.DynamicBody;
		bDef.position.set(initialPos);
		
		// The physical body of the enemy
		FixtureDef fBodyDef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(this.bRadius);
		fBodyDef.shape = shape;
		fBodyDef.filter.categoryBits = Entity.NPC;
		fBodyDef.filter.maskBits = Entity.BOUNDARY | Entity.PLAYER | Entity.ENEMY | Entity.PLAYER_MELEE;
		
		// Create body and add fixtures
		b2body = world.createBody(bDef);
		b2body.createFixture(fBodyDef).setUserData(FixtureType.NPC);
		
		coneLight = new ConeLight(gameManager.getRayHandler(), 7, Color.VIOLET, 3, b2body.getPosition().x, b2body.getPosition().y, b2body.getAngle() + 90, 35);
		coneLight.attachToBody(b2body, 0, 0, 90);
		
		b2body.setUserData(this);

		shape.dispose();
		
		b2body.setLinearDamping(5.0f);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		if (invulnerableTimer - delta > 0) {
			invulnerableTimer -= delta;
		} else {
			invulnerableTimer = 0;
		}
		Vector2 playerPos = gameManager.getPlayer().getPos();
		if (gameManager.getPlayerType() != PlayerType.ZOMBIE) {
			if ((this.getPos().sub(playerPos)).len2() <= 25) {
				double angle = Math.toDegrees(Math.atan2(playerPos.y - b2body.getPosition().y, playerPos.x - b2body.getPosition().x)) - 90;
				this.setAngle(angle);
			}
		} else {
			this.b2body.setLinearVelocity(-(playerPos.x - b2body.getPosition().x), -(playerPos.y - b2body.getPosition().y));
		}
	}
	
	@Override
	public void applyDamage(int damage) {
		if (invulnerableTimer > 0) {
			return;
		}
		super.applyDamage(damage);
	}

}
