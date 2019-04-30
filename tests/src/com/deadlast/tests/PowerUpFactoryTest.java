package com.deadlast.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.deadlast.entities.PowerUpFactory;

@RunWith(GdxTestRunner.class)
public class PowerUpFactoryTest {

	@Test
	public void getInstance() {
		assertNotNull(PowerUpFactory.getInstance(null));
	}
}