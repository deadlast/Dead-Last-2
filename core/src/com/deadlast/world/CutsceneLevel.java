package com.deadlast.world;

import com.deadlast.game.DeadLast;

public class CutsceneLevel extends Level{
		
		int textNum;
		public CutsceneLevel(DeadLast game, int textNum) {
			super(game,"Cutscene Room");
			this.textNum = textNum;
			this.foregroundLayers = new int[] {0};
			
		}
		public int getLevelNum() {
			return this.textNum;
		}
		public String pickText() {
			
			switch (textNum){
				
			case 0:
				return "Text before CompSci";
				
			case 1:
				return "Text before Hes East";
				
			case 2:
				return "Text before DBar";
				
			case 3:
				return "Text before Library";
				
			case 4:
				return "Text before Under Lake";
				
			case 5:
				return "Text before Central Hall";
				
			default:
				return "Default Text";
				
			}
		}
	}

