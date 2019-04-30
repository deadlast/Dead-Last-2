package com.deadlast.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.deadlast.entities.Player;
import com.deadlast.entities.PowerUp;

@RunWith(GdxTestRunner.class)
public class PlayerTest {
	
	private Player player;
	
	@Before
	public void init() {
		player  = new Player(null, null, null, 0.4f, new Vector2(5,5), 50, 4, 50, 50);
	}
	
	@Test
	public void playerHealthIsInt() {
		assertNotNull(player.getHealth());
		assertTrue(Integer.class.isInstance(player.getHealth()));
	}
	
	@Test
	public void playerSpeedIsInt() {
		assertNotNull(player.getSpeed());
		assertTrue(Integer.class.isInstance(player.getSpeed()));
	}
	
	@Test
	public void playerStrengthIsInt() {
		assertNotNull(player.getStrength());
		assertTrue(Integer.class.isInstance(player.getStrength()));
	}
	
	@Test
	public void playerStealthIsInt() {
		assertNotNull(player.getStealthStat());
		assertTrue(Integer.class.isInstance(player.getStealthStat()));
	}
	
	@Test
	public void playerHealthIsInitialised() {
		assertNotNull(player.getHealth());
		assertEquals(player.getHealth(), player.getMaxHealth());
	}
	
	@Test
	public void playerAcceptsDamage() {
		assertNotNull(player.getHealth());
		int health = player.getHealth();
		player.applyDamage(10);
		assertEquals(player.getHealth(), health - 10);
	}
	
	@Test
	public void playerDies() {
		assertNotNull(player.getHealth());
		player.setHealth(5);
		player.applyDamage(5);
		assertEquals(player.getHealth(), 0);
		player.applyDamage(5);
		assertEquals(player.getHealth(), 0);
	}
	
	@Test
	public void playerSetHealth() {
		assertNotNull(player.getHealth());
		int health = player.getHealth();
		player.setHealth(player.getHealth() + 5);
		assertEquals(player.getHealth(), health + 5);
	}
	
	@Test
	public void playerInvalidPosition() {
		assertThrows(IllegalArgumentException.class, () -> new Player(null, null, null, 0.4f, null, 50, 4, 50, 50));
	}

    @Test
    public void getStealthStat() {
        assertEquals(player.getStealthStat(), 50);
    }

    private Boolean validDamage(Integer val) {
        if(val == 2 || val == 1) {
            return true;
        }
        return false;
    }

    @Test
    public void getDamageMultiplier() {
	    assertNotNull(player.getDamageMultiplier());
        assertTrue(validDamage(player.getDamageMultiplier()));
        player.isPowerUpActive(PowerUp.Type.DOUBLE_DAMAGE);
        assertTrue(validDamage(player.getDamageMultiplier()));
    }

    @Test
    public void getCooldown() {
	    assertNotNull(player.getCooldown());
    }
}
