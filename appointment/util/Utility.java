package com.ray.oops.appointment.util;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.ray.oops.appointment.model.Appointment;
import com.ray.oops.appointment.model.Docters;
import com.ray.oops.appointment.model.Patients;
import com.ray.oops.appointment.repo.Reposetory;

public class Utility {
	static Reposetory repo;
	
	@SuppressWarnings("unchecked")
	public static JSONArray convertDocter(List<Docters> list) {

		JSONArray doctersList = new JSONArray();
		JSONObject patient;
		for (int i = 0; i < list.size(); i++) {
			patient = new JSONObject();
			patient.put("Id", list.get(i).getId());
			patient.put("Name", list.get(i).getName());
			patient.put("Spcl", list.get(i).getSpcl());
			patient.put("Avail", list.get(i).getAvail());
			doctersList.add(patient);
		}
		return doctersList;

	}

	@SuppressWarnings("unchecked")
	public static JSONArray convertPatient(List<Patients> patient) {

		JSONArray array = new JSONArray();
		JSONObject ob;
		for (int i = 0; i < patient.size(); i++) {
			ob = new JSONObject();
			ob.put("Id", patient.get(i).getId());
			ob.put("Name", patient.get(i).getName());
			ob.put("Age", patient.get(i).getAge());
			ob.put("Number", patient.get(i).getM_number());
			array.add(ob);
		}
		return array;

	}
//Information about patients

	
	// Inforamtion About Appointment
	public static List<Appointment> readAppointment() {
		    repo=new Reposetory ();
		List<Appointment> list = new LinkedList<>();
		JSONArray array = (JSONArray) Reposetory.accessFileData("Appoinments.json");
		Appointment ob;
		JSONObject jo;
		for (int i = 0; i < array.size(); i++) {
			ob = new Appointment();
			jo = (JSONObject) array.get(i);
			ob.setPatients((long) jo.get("Pid"), (String) jo.get("Pname"), (long) jo.get("Page"),
					(long) jo.get("Pnumber"));
			ob.setDocter((long) jo.get("Did"), (String) jo.get("Davail"));
			ob.setDate((String) jo.get("Ddate"));
			list.add(ob);

		}

		return list;

	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray convertAppoinment(List<Appointment> list) {

		JSONArray apps = new JSONArray();
		JSONObject ob;
		Appointment app = null;
		for (int i = 0; i < list.size(); i++) {
			ob = new JSONObject();
			app = list.get(i);
			ob.put("Pid", app.getPatients().getId());
			ob.put("Pname", app.getPatients().getName());
			ob.put("Page", app.getPatients().getAge());
			ob.put("Pnumber", app.getPatients().getM_number());
			ob.put("Did", app.getDocter().getId());
			ob.put("Ddate", app.getDate());
			System.out.println(app.getDocter().getAvail());
			ob.put("Davail", app.getDocter().getAvail());
			apps.add(ob);
		}
		return apps;
	}
	

	
	public static int checkBYId(long docter_id) {
		int size = 0;
		for (int i = 0; i < readAppointment().size(); i++) {
			if (docter_id== readAppointment().get(i).getDocter().getId()) {
				size++;
			}
		}
		return size;
	}


}
