package com.deadlast.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.deadlast.entities.Enemy;
import com.deadlast.entities.Player;
import com.deadlast.entities.PowerUp;
import com.deadlast.entities.NPC;

/**
 * Handles contact interactions with world bodies.
 * @author Xzytl
 *
 */
public class WorldContactListener implements ContactListener {
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fA = contact.getFixtureA();
		Fixture fB = contact.getFixtureB();
		
		if (fA.getUserData() == null || fB.getUserData() == null) return;
		
		FixtureType fTypeA = (FixtureType) fA.getUserData();
		FixtureType fTypeB = (FixtureType) fB.getUserData();
		
		// System.out.println("Contact begun between fixtures of type " + fTypeA + " and " + fTypeB);
		
		switch(fTypeA) {
		case ENEMY:
			enemyContactBegun(fA, fTypeB, fB);
			break;
		case PLAYER:
			playerContactBegun(fA, fTypeB, fB);
			break;
		case HEARING_SENSOR:
		case VISUAL_SENSOR:
			enemySensorContactBegun(fA, fTypeB, fB);
			break;
		case POWERUP:
			powerUpContactBegun(fA, fTypeB, fB);
			break;
		case MELEE_SENSOR:
			meleeSensorContactBegun(fA, fTypeB, fB);
			break;
		case END_ZONE:
			endZoneContactBegun(fA, fTypeB, fB);
			break;
		case NPC:
			npcContactBegun(fA, fTypeB, fB);
			break;
		default:
			break;
		}
	}
	
	public void enemyContactBegun(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case PLAYER:
			((Enemy)fA.getBody().getUserData()).beginContact(fB.getBody());
			break;
		case MELEE_SENSOR:
			((Player)fB.getBody().getUserData()).onMeleeRangeEntered((Enemy)fA.getBody().getUserData());
			break;
		case NPC:
			((NPC)fB.getBody().getUserData()).applyDamage(((Enemy)fA.getBody().getUserData()).getStrength());
			break;
		default:
			break;
		}
	}
	
	public void playerContactBegun(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case ENEMY:
			((Enemy)fB.getBody().getUserData()).beginContact(fA.getBody());
			break;
		case HEARING_SENSOR:
		case VISUAL_SENSOR:
			((Enemy)fB.getBody().getUserData()).beginDetection(fA.getBody());
			break;
		case POWERUP:
			((Player)fA.getBody().getUserData()).onPickup((PowerUp)fB.getBody().getUserData());
			((PowerUp)fB.getBody().getUserData()).setAlive(false);
			break;
		case END_ZONE:
			((Player)fA.getBody().getUserData()).onEndZoneReached();
			break;
		default:
			break;
		}
	}
	
	public void enemySensorContactBegun(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case PLAYER:
			((Enemy)fA.getBody().getUserData()).beginDetection(fB.getBody());
			break;
		default:
			break;
		}
	}
	
	public void powerUpContactBegun(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case PLAYER:
			((Player)fB.getBody().getUserData()).onPickup((PowerUp)fA.getBody().getUserData());
			((PowerUp)fA.getBody().getUserData()).setAlive(false);
			break;
		default:
			break;
		}
	}
	
	public void meleeSensorContactBegun(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case ENEMY:
			((Player)fA.getBody().getUserData()).onMeleeRangeEntered((Enemy)fB.getBody().getUserData());
			break;
		case NPC:
			((Player)fA.getBody().getUserData()).onMeleeRangeEntered((NPC)fB.getBody().getUserData());
			break;
		default:
			break;
		}
	}
	
	public void endZoneContactBegun(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case PLAYER:
			((Player)fB.getBody().getUserData()).onEndZoneReached();
			break;
		default:
			break;
		}
	}
	
	public void npcContactBegun(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case MELEE_SENSOR:
			((Player)fB.getBody().getUserData()).onMeleeRangeEntered((NPC)fA.getBody().getUserData());
			break;
		case ENEMY:
			((NPC)fA.getBody().getUserData()).applyDamage(((Enemy)fB.getBody().getUserData()).getStrength());
			break;
		default:
			break;
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fA = contact.getFixtureA();
		Fixture fB = contact.getFixtureB();
		
		if (fA.getUserData() == null || fB.getUserData() == null) return;
		
		FixtureType fTypeA = (FixtureType) fA.getUserData();
		FixtureType fTypeB = (FixtureType) fB.getUserData();
		
		// System.out.println("Contact lost between fixtures of type " + fTypeA + " and " + fTypeB);
		
		switch(fTypeA) {
		case ENEMY:
			enemyContactEnded(fA, fTypeB, fB);
			break;
		case PLAYER:
			playerContactEnded(fA, fTypeB, fB);
			break;
		case VISUAL_SENSOR:
			enemySensorContactEnded(fA, fTypeB, fB);
			break;
		case MELEE_SENSOR:
			meleeSensorContactEnded(fA, fTypeB, fB);
			break;
		case NPC:
			npcContactEnded(fA, fTypeB, fB);
			break;
		default:
			break;
		}
	}
	
	public void enemyContactEnded(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case PLAYER:
			((Enemy)fA.getBody().getUserData()).endContact(fB.getBody());
			break;
		case MELEE_SENSOR:
			((Player)fB.getBody().getUserData()).onMeleeRangeLeft((Enemy)fA.getBody().getUserData());
			break;
		default:
			break;
		}
	}
	
	public void playerContactEnded(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case ENEMY:
			((Enemy)fB.getBody().getUserData()).endContact(fA.getBody());
			break;
		case VISUAL_SENSOR:
			((Enemy)fB.getBody().getUserData()).endDetection(fA.getBody());
			break;
		default:
			break;
		}
	}
	
	public void enemySensorContactEnded(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case PLAYER:
			((Enemy)fA.getBody().getUserData()).endDetection(fB.getBody());
			break;
		default:
			break;
		}
	}
	
	public void meleeSensorContactEnded(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case ENEMY:
			((Player)fA.getBody().getUserData()).onMeleeRangeLeft((Enemy)fB.getBody().getUserData());
			break;
		case NPC:
			((Player)fA.getBody().getUserData()).onMeleeRangeLeft((NPC)fB.getBody().getUserData());
			break;
		default:
			break;
		}
	}
	
	public void npcContactEnded(Fixture fA, FixtureType fTypeB, Fixture fB) {
		switch(fTypeB) {
		case MELEE_SENSOR:
			((Player)fB.getBody().getUserData()).onMeleeRangeLeft((NPC)fA.getBody().getUserData());
			break;
		default:
			break;
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
}
