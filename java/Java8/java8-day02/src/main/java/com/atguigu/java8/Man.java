package com.atguigu.java8;

public class Man {

	private Goddess god;

	public Man() {
	}

	public Man(Goddess god) {
		this.god = god;
	}

	public Goddess getGod() {
		return god;
	}

	public void setGod(Goddess god) {
		this.god = god;
	}

	@Override
	public String toString() {
		return "Man [god=" + god + "]";
	}

}
