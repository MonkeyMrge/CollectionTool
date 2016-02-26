package com.colletionUtils.message;

import java.io.Serializable;

public class AskParam implements Serializable {

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

}
