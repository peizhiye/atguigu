package com.atguigu.java8;

public class Goddess {

	private String name;

	public Goddess() {
	}

	public Goddess(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Goddess [name=" + name + "]";
	}

}
