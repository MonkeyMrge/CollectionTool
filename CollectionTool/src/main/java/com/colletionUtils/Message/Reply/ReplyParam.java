package com.colletionUtils.Message.Reply;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.colletionUtils.Message.MsgBody;

@Entity
@Table(name = "t_replybody")
public class ReplyParam extends MsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String msgBody;

	public ReplyParam(String msgBody) {
		this.msgBody = msgBody;
	}

	public Object getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	@Id
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = {
			@Parameter(name = "property", value = "replyMsg") })
	@GeneratedValue(generator = "pkGenerator")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReplyParam [msgBody=" + msgBody + "]";
	}

}
