package com.colletionUtils.Message.Ask;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_askBody")
public class AskParam implements Serializable {
	@Id
	@GeneratedValue
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
