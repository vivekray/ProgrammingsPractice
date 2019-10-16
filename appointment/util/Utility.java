package com.ray.oops.appointment.util;

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

public class Utility {
	// Information about Docters

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

	public static void save(String fileName, JSONArray array) {
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
	public static void writeDocter(Docters docter) {
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

	public static List<Docters> readDocter() {
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

	public static void writePatient(Patients patient) {

		// TODO Auto-generated method stub
		JSONArray patients = (JSONArray) convertPatient(readPatient());
		JSONObject ob = new JSONObject();
		ob.put("Id", patient.getId());
		ob.put("Name", patient.getName());
		ob.put("Age", patient.getAge());
		ob.put("Number", patient.getM_number());

		patients.add(ob);

		save("Patients.json", patients);

	}

	public static List<Patients> readPatient() {

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

	// Inforamtion About Appointment
	public static List<Appointment> readAppointment() {
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
	
	public static JSONArray convertAppoinment(List<Appointment> list) {

		JSONArray array = new JSONArray();
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
			array.add(ob);
		}
		return array;
	}
	
	//Searcching
	
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
