package com.ray.oops.appointment.model;

public class Docters {
private long id;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSpcl() {
	return spcl;
}
public void setSpcl(String spcl) {
	this.spcl = spcl;
}
public String getAvail() {
	return avail;
}
public void setAvail(String avail) {
	this.avail = avail;
}
private String name;
private String spcl;
private String avail;

}
