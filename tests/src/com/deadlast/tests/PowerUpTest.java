package com.deadlast.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.deadlast.entities.Player;
import com.deadlast.entities.PowerUp;

@RunWith(GdxTestRunner.class)
public class PowerUpTest {

	Player player;

	@Before
	public void init() {
		player = new Player(null, null, null, 0.4f, new Vector2(5, 5), 50, 4, 50, 50);
	}

	@Test
	public void regenTest() {
		player.setHealth(1);
		player.onPickup(new PowerUp(null, 10, null, 0.25f, new Vector2(0, 0), PowerUp.Type.REGEN));
		player.update(1f);
		assertEquals(player.getHealth(), 2);
		player.update(1f);
		assertEquals(player.getHealth(), 3);
	}

	@Test
	public void regenNotExceedMaxHealth() {
		player.setHealth(player.getMaxHealth());
		player.onPickup(new PowerUp(null, 10, null, 0.25f, new Vector2(0, 0), PowerUp.Type.REGEN));
		player.update(1f);
		assertEquals(player.getHealth(), player.getMaxHealth());
	}

	@Test
	public void getDamageMultiplier() {
		assertNotNull(player.getDamageMultiplier());
		assertEquals(player.getDamageMultiplier(), 1);
		player.onPickup(new PowerUp(null, 10, null, 0.25f, new Vector2(0, 0), PowerUp.Type.DOUBLE_DAMAGE));
		assertEquals(player.getDamageMultiplier(), 2);
	}

}
