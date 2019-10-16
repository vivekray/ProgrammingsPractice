package com.ray.oops.appointment.model;

public class Appointment {
private Docters docter;
private Patients patients;
private long id;
private String date;
public Appointment(){
	docter=new Docters();
	patients=new Patients();
}

public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}


public Docters getDocter() {
	this.docter.getId();
	this.docter.getName();
	this.docter.getSpcl();
	this.docter.getAvail();
	return docter;
}
public void setDocter(long id,String avail) {
	
	this.docter.setId(id);
	//this.docter.setName(name);
	//this.docter.setSpcl(spcl);
	this.docter.setAvail(avail);
	//this.docter;
}
public Patients getPatients() {
	this.patients.getId();
	this.patients.getName();
	this.patients.getAge();
	this.patients.getM_number();
	return this.patients;
}
public void setPatients(long id,String name,long age,long number) {
	Patients p=new Patients();
	p.setId(id);
	p.setName(name);
	p.setAge(age);
	p.setM_number(number);
	this.patients=p;
}

}
