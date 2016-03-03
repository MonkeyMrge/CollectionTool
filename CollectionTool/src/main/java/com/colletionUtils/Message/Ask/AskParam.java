package com.colletionUtils.Message.Ask;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_askBody")
public class AskParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", insertable = true, updatable = true, nullable = false)
	private String id;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
