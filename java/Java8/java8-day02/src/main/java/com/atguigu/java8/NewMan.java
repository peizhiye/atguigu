package com.atguigu.java8;

import java.util.Optional;

//注意：Optional 不能被序列化
public class NewMan {

	private Optional<Goddess> goddess = Optional.empty();
	
	public NewMan() {
	}

	public NewMan(Optional<Goddess> goddess) {
		this.goddess = goddess;
	}

	public Optional<Goddess> getGoddess() {
		return goddess;
	}

	public void setGoddess(Optional<Goddess> goddess) {
		this.goddess = goddess;
	}

	@Override
	public String toString() {
		return "NewMan [goddess=" + goddess + "]";
	}

}
