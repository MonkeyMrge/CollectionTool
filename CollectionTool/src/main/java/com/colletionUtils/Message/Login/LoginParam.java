package com.colletionUtils.Message.Login;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Netty服务端登录账号、密码信息
 */
@Entity
@Table(name = "t_loginbody")
public class LoginParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", insertable = true, updatable = true, nullable = false)
	private String id;
	/**
	 * Netty服务端登录账号
	 */
	private String userName;
	/**
	 * Netty服务端登录密码
	 */
	private String password;

	public LoginParam(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LoginParam [userName=" + userName + ", password=" + password + "]";
	}

}
