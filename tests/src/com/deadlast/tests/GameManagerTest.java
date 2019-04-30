package com.deadlast.tests;

import com.deadlast.entities.Enemy;
import com.deadlast.game.GameManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(GdxTestRunner.class)
public class GameManagerTest {

    GameManager manager;
    Enemy boss;

    @Before
     public void init() {
        manager  = GameManager.getInstance(null);
    }

    @Test
    public void addEnemy() {
        manager.addEnemy(Enemy.Type.BOSS2, new Vector2(5, 5));
    }

    @Test
    public void getPlayer(){
        assertNotNull(manager.getPlayer());
    }

    @Test
    public void checkBoss() {
        String CurrentLevel = manager.getLevelName();
        manager.addEnemy(Enemy.Type.BOSS2, new Vector2(5, 5));
        manager.update(1);
        manager.checkBoss();
        assertNotEquals(CurrentLevel, manager.getLevelName());
    }


    @Test
    public void getMinigame() {
        manager.setMinigameActive(true);
        assertNotNull(manager.isMinigameActive());
    }
}