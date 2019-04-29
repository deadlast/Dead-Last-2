package com.deadlast.entities;

public enum PlayerType {
	

	TANK(0.4f, 100, 4, 8, 20, new String[] {"entities/boxer.png","entities/boxer_attack.png"}),
	STEALTH(0.3f, 50, 6, 4, 90, new String[] {"entities/ninja.png","entities/ninja_attack.png"}),
	RUNNER(0.3f, 40, 8, 4, 40, new String[] {"entities/runner.png","entities/runner_attack.png"}),
	STANDARD(0.4f, 65, 6, 5, 50, new String[] {"entities/student.png","entities/student_attack.png"}),
	ZOMBIE(0.4f, 200, 6, 8, 0, new String[] {"entities/normal_zombie.png","entities/normal_zombie.png"});

	
	float bodyRadius;
	int health;
	int speed;
	int strength;
	int stealth;
	String[] sprites;
	
	PlayerType(float bodyRadius, int health, int speed, int strength, int stealth, String[] sprites) {
		this.bodyRadius = bodyRadius;
		this.health = health;
		this.speed = speed;
		this.strength = strength;
		this.stealth = stealth;
		this.sprites = sprites;
	}
	
	public float getBodyRadius() {
		return bodyRadius;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getStealth() {
		return stealth;
	}
	
	public String[] getSprites() {
		return sprites;
	}
	public String getDefaultSprite() {
		return sprites[0];
	}

}
