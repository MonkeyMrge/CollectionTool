package com.colletionUtils.Message.Ask;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.colletionUtils.Message.MsgBody;

@Entity
@Table(name = "t_askBody")
public class AskParam extends MsgBody implements Serializable {

	private int id;
	private static final long serialVersionUID = 1L;
	private AskType askType;

	public AskParam(AskType askType) {
		super();
		this.askType = askType;
	}

	public AskType getAskType() {
		return askType;
	}

	public void setAskType(AskType askType) {
		this.askType = askType;
	}

	@Override
	public String toString() {
		return "AskParam [askType=" + askType + "]";
	}

	@Id
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = {
			@Parameter(name = "property", value = "askMsg") })
	@GeneratedValue(generator = "pkGenerator")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
