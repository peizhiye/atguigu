package com.atguigu.mapper.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
	CREATE TABLE `table_user` (
		`user_id` INT NOT NULL AUTO_INCREMENT,
		`user_name` VARCHAR (100) NULL,
		`address` VARCHAR (100) NULL,
		`season` VARCHAR (100) NULL,
		PRIMARY KEY (`user_id`)
	);
 * @author Lenovo
 *
 */
@Table(name="table_user")
public class User {
	
	@Id
	private Integer userId;
	
	private String userName;
	
	//@ColumnType(typeHandler=AddressTypeHandler.class)
	/**
	 * 对于复杂类型的字段，有必要加 @Column 注解，并在 MyBatis 配置文件中配置 typeHandlers，否则通用 Mapper 会忽略复杂类型的字段映射。
	 */
	@Column
	private Address address;
	
	// 由于 EnumTypeHandler 的泛型问题，在枚举类型这里无法使用 @ColumnType 注解注册 MyBatis 内置的枚举类型处理器
	//@ColumnType(typeHandler=EnumTypeHandler.class)
	/**
	 * 对于复杂类型的字段，有必要加 @Column 注解，并在 MyBatis 配置文件中配置 typeHandlers，否则通用 Mapper 会忽略复杂类型的字段映射。
	 */
	@Column
	private SeasonEnum season;
	
	public User() {
		
	}

	public User(Integer userId, String userName, Address address, SeasonEnum season) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.address = address;
		this.season = season;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", address=" + address + ", season=" + season
				+ "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public SeasonEnum getSeason() {
		return season;
	}

	public void setSeason(SeasonEnum season) {
		this.season = season;
	}

}
