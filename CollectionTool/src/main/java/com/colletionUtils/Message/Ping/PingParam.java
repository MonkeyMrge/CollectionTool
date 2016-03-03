package com.colletionUtils.Message.Ping;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_pingbody")
public class PingParam implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String msgBody;

	public PingParam(String clientId) {
		super();
		msgBody = "This is a Ping Msg from " + clientId + "!";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PingParam [msgBody=" + msgBody + "]";
	}

}
