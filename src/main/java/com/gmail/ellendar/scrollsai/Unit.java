package com.gmail.ellendar.scrollsai;

public class Unit {
	private int power;
	private int health;
	private int initialAttackDelay;
	private int attackDelay;
	
	private int x, y;
	
	public Unit() {
	}
	
	public Unit(int power, int attackDelay, int health, int x, int y) {
		this.power = power;
		this.health = health;
		this.initialAttackDelay = attackDelay;
		this.attackDelay = attackDelay;
		this.x = x;
		this.y = y;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getInitialAttackDelay() {
		return initialAttackDelay;
	}

	public void setInitialAttackDelay(int initialAttackDelay) {
		this.initialAttackDelay = initialAttackDelay;
	}

	public int getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(int attackDelay) {
		this.attackDelay = attackDelay;
	}
	
	@Override
	public String toString() {
		return power + "/" + attackDelay + "/" + health;
	}
}