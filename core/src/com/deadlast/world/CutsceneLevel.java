package com.deadlast.world;

import com.deadlast.game.DeadLast;

public class CutsceneLevel extends Level{
		
		public CutsceneLevel(DeadLast game, int textNum) {
			super(game,"Cutscene Room");
			this.foregroundLayers = new int[] {0};
			
		}
	}

