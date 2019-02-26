package com.deadlast.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.deadlast.entities.PowerUpFactory;

class PowerUpFactoryTest {

    @Test
    void getInstance() {
        assertNotNull(PowerUpFactory.getInstance(null));
    }
}