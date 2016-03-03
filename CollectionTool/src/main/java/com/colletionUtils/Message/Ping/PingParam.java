package com.colletionUtils.Message.Ping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_pingbody")
public class PingParam implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", insertable = true, updatable = true, nullable = false)
	private String id;
	private String msgBody;

	public PingParam(String clientId) {
		super();
		msgBody = "This is a Ping Msg from " + clientId + "!";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PingParam [msgBody=" + msgBody + "]";
	}

}
