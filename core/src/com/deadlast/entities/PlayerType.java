package com.deadlast.entities;

public enum PlayerType {
	
	TANK(0.5f, 100, 3, 8, 20, "entities/boxer.png"),
	STEALTH(0.3f, 40, 4, 4, 90, "entities/ninja.png"),
	RUNNER(0.3f, 30, 6, 4, 40, "entities/runner.png"),
	STANDARD(0.4f, 50, 4, 5, 50, "entities/student.png");
	
	float bodyRadius;
	int health;
	int speed;
	int strength;
	int stealth;
	String sprite;
	
	PlayerType(float bodyRadius, int health, int speed, int strength, int stealth, String sprite) {
		this.bodyRadius = bodyRadius;
		this.health = health;
		this.speed = speed;
		this.strength = strength;
		this.stealth = stealth;
		this.sprite = sprite;
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
	
	public String getSprite() {
		return sprite;
	}

}
