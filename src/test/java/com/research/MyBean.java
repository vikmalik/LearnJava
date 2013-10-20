package com.research;

import java.util.Objects;

/**
 * 
 * @author vikmalik
 */
public class MyBean {

    private String id;
    private String jid;

    public MyBean(String id, String jid) {
	this.id = id;
	this.jid = jid;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getJid() {
	return jid;
    }

    public void setJid(String jid) {
	this.jid = jid;
    }

    @Override
    public String toString() {
	return String.format("Id: %s, JID: %s", id, jid);
    }

    @Override
    public boolean equals(Object obj) {
	boolean returnValue = false;

	if (obj instanceof MyBean) {
	    MyBean obj1 = (MyBean) obj;

	    if (id.equals(obj1.id) && jid.equals(obj1.jid)) {
		returnValue = true;
	    }
	}
	return returnValue;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 79 * hash + Objects.hashCode(this.id);
	hash = 79 * hash + Objects.hashCode(this.jid);
	return hash;
    }
}
