package com.ray.oops.appointment.repo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ray.oops.appointment.model.Appointment;
import com.ray.oops.appointment.model.Docters;
import com.ray.oops.appointment.model.Patients;
import com.ray.oops.appointment.util.Utility;

public class Reposetory {

	// To save date in json file
	public static Object accessFileData(String filename) {
		Object obj = null;
		try {
			obj = new JSONParser().parse(new BufferedReader(new FileReader(filename)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public void save(String fileName, JSONArray array) {
		try {
			PrintWriter pw = new PrintWriter(fileName);
			pw.write(array.toJSONString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// to write file in json
	@SuppressWarnings("unchecked")
	public void writeDocter(Docters docter) {
		JSONArray docters = (JSONArray) Utility.convertDocter(readDocter());
		JSONObject ob;
		ob = new JSONObject();
		ob.put("Id", docter.getId());
		ob.put("Name", docter.getName());
		ob.put("Spcl", docter.getSpcl());
		ob.put("Avail", docter.getAvail());
		docters.add(ob);
		save("Docters.json", docters);
	}

	public  List<Docters> readDocter() {
		LinkedList<Docters> doctersList = new LinkedList<Docters>();

		JSONArray array = (JSONArray) accessFileData("Docters.json");
		Docters docter;
		for (int i = 0; i < array.size(); i++) {
			docter = new Docters();

			docter.setId((Long) ((JSONObject) (array.get(i))).get("Id"));
			docter.setName((String) ((JSONObject) (array.get(i))).get("Name"));
			docter.setSpcl((String) ((JSONObject) (array.get(i))).get("Spcl"));
			docter.setAvail((String) ((JSONObject) (array.get(i))).get("Avail"));
			doctersList.add(docter);

		}

		return doctersList;
	}

	
	@SuppressWarnings("unchecked")
	public  void writePatient(Patients patient) {

		JSONArray patients = (JSONArray) Utility.convertPatient(readPatient());
		JSONObject ob = new JSONObject();
		ob.put("Id", patient.getId());
		ob.put("Name", patient.getName());
		ob.put("Age", patient.getAge());
		ob.put("Number", patient.getM_number());

		patients.add(ob);

		save("Patients.json", patients);

	}

	public  List<Patients> readPatient() {

		List<Patients> list = new LinkedList<Patients>();

		JSONArray array = (JSONArray) accessFileData("Patients.json");
		Patients patient;
		for (int i = 0; i < array.size(); i++) {
			patient = new Patients();

			patient.setId((Long) ((JSONObject) (array.get(i))).get("Id"));
			patient.setName((String) ((JSONObject) (array.get(i))).get("Name"));
			patient.setAge((long) ((JSONObject) (array.get(i))).get("Age"));
			patient.setM_number((long) ((JSONObject) (array.get(i))).get("Number"));
			list.add(patient);

		}

		return list;

	}
	//Save Appoinment
	public void meetingSave(Appointment app) {
		// collection of appointment

		JSONArray appoinments = Utility.convertAppoinment(Utility.readAppointment());

		// for taking one object of appointment
		JSONObject appoinment = new JSONObject();
		appoinment.put("Pid", app.getPatients().getId());
		appoinment.put("Pname", app.getPatients().getName());
		appoinment.put("Page", app.getPatients().getAge());
		appoinment.put("Pnumber", app.getPatients().getM_number());
		appoinment.put("Did", app.getDocter().getId());
		appoinment.put("Ddate", app.getDate());
		appoinment.put("Davail", app.getDocter().getAvail());
		appoinments.add(appoinment);
		// save appointments with new appointment
		save("Appoinments.json", appoinments);

		System.out.println("Appointment Submit SuccessFully!");

	}

	// Inforamtion About Appointment
	public  List<Appointment> readAppointment() {
		List<Appointment> list = new LinkedList<>();
		JSONArray array = (JSONArray) accessFileData("Appoinments.json");
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

	
	
	public Object object() {
		return null;
	}
}
