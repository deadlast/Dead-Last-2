package com.deadlast.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.deadlast.entities.Enemy;

@RunWith(GdxTestRunner.class)
public class EnemyTest {

	private Enemy enemy;

	@Before
	public void init() {
		enemy = new Enemy(null, 10, null, 0.4f, new Vector2(5, 5), 4, 10, 5, 7, 10);
	}

	@Test
	public void enemyHealthIsInt() {
		assertNotNull(enemy.getHealth());
		assertTrue(Integer.class.isInstance(enemy.getHealth()));
	}

	@Test
	public void enemySpeedIsInt() {
		assertNotNull(enemy.getSpeed());
		assertTrue(Integer.class.isInstance(enemy.getSpeed()));
	}

	@Test
	public void enemyStrengthIsInt() {
		assertNotNull(enemy.getStrength());
		assertTrue(Integer.class.isInstance(enemy.getStrength()));
	}

	@Test
	public void enemyHealthIsInitialised() {
		assertNotNull(enemy.getHealth());
		assertEquals(enemy.getHealth(), enemy.getMaxHealth());
	}

	@Test
	public void enemyAcceptsDamage() {
		assertNotNull(enemy.getHealth());
		int health = enemy.getHealth();
		enemy.applyDamage(1);
		assertEquals(enemy.getHealth(), health - 1);
	}

	@Test
	public void enemyDies() {
		assertNotNull(enemy.getHealth());
		enemy.setHealth(5);
		enemy.applyDamage(5);
		assertEquals(enemy.getHealth(), 0);
		enemy.applyDamage(5);
		assertEquals(enemy.getHealth(), 0);
	}

	@Test
	public void enemyScoreValueIsInt() {
		assertNotNull(enemy.getScoreValue());
		assertTrue(Integer.class.isInstance(enemy.getScoreValue()));
	}

	@Test
	public void enemySetHealth() {
		assertNotNull(enemy.getHealth());
		int health = enemy.getHealth();
		enemy.setHealth(enemy.getHealth() + 5);
		assertEquals(enemy.getHealth(), health + 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void enemyInvalidPosition() {
		new Enemy(null, 10, null, 0.4f, null, 4, 10, 5, 7, 10);
	}

	@Test
	public void enemyCanSeePlayer() {
		assertTrue(!enemy.isPlayerVisible());
	}
}
