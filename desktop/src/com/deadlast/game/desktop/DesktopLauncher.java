package com.deadlast.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.deadlast.game.DeadLast;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dead Last";
		config.width = 1600;
		config.height = 900;
		config.resizable = false;
		new LwjglApplication(new DeadLast(), config);
	}
}
