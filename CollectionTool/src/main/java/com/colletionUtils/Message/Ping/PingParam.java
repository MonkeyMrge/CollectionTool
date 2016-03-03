package com.colletionUtils.Message.Ping;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.colletionUtils.Message.MsgBody;

@Entity
@Table(name = "t_pingbody")
public class PingParam extends MsgBody implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String msgBody;

	public PingParam(String clientId) {
		super();
		msgBody = "This is a Ping Msg from " + clientId + "!";
	}

	@Id
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = {
			@Parameter(name = "property", value = "pingMsg") })
	@GeneratedValue(generator = "pkGenerator")
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
