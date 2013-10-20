package com.learnjava.jaxb.history;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "com.learn.jaxb.history")
public class CallHistory {
	@XmlElement(name = "call")
	private ArrayList<Call> callList;

	public ArrayList<Call> getCallsList() {
		return callList;
	}

	public void setCallList(ArrayList<Call> callList) {
		this.callList = callList;
	}

}
