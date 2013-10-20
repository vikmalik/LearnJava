package com.learnjava.jaxb.history;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "call")
@XmlType(propOrder = { "callId", "callerName", "callStartTime", "callEndTime", "callType" })
public class Call {
	private String callId;
	private String callerName;
	private Date callStartTime;
	private Date callEndTime;
	private CallType callType;
	
	
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public String getCallerName() {
		return callerName;
	}
	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}
	public Date getCallStartTime() {
		return callStartTime;
	}
	public void setCallStartTime(Date callStartTime) {
		this.callStartTime = callStartTime;
	}
	public Date getCallEndTime() {
		return callEndTime;
	}
	public void setCallEndTime(Date callEndTime) {
		this.callEndTime = callEndTime;
	}
	public CallType getCallType() {
		return callType;
	}
	public void setCallType(CallType callType) {
		this.callType = callType;
	}
}
