package com.ray.oops.appointment.services;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.ray.algorithm.Util;
import com.ray.oops.appointment.model.Appointment;
import com.ray.oops.appointment.model.Docters;
import com.ray.oops.appointment.model.Patients;
import com.ray.oops.appointment.util.Utility;
import com.ray.oops.appointment.view.Report;

public class ServiceImplement implements Service {

	public void addDocter() {

		Docters docter = new Docters();
		System.out.println("ENTER THE DOCTERS VALUES");
		System.out.println("ID");
		long id = Util.nextLong();
		docter.setId(id);
		System.out.println("Name");
		String name = Util.next();
		docter.setName(name);
		System.out.println("Speciality");
		String spcl = Util.next();
		docter.setSpcl(spcl);

		System.out.println("Availiblity");
		String avil = Util.next();
		docter.setAvail(avil);
		Utility.writeDocter(docter);

	}

	@Override
	public void deleteDocter() {
		// Taking data in list form to delete
		LinkedList<Docters> docters = (LinkedList<Docters>) Utility.readDocter();
		System.out.println("Enter Id For Deleteion");
		long id = Util.nextLong();
		for (int i = 0; i < docters.size(); i++) {
			// check and delete
			if (docters.get(i).getId() == id) {
				docters.remove(docters.get(i));
			}
		}
		// converting Updating data in JSON and and save in file
		Utility.save("Docters.json", (JSONArray) Utility.convertDocter(docters));

	}

	public Patients addPatient() {
		// Taking data
		Patients patient = new Patients();

		System.out.println("ENTER THE Patients VALUES");

		System.out.println("ID");
		long id = Util.nextLong();
		patient.setId(id);

		System.out.println("Name");
		String name = Util.next();
		patient.setName(name);

		System.out.println("Age");
		long age = Util.nextLong();
		patient.setAge(age);

		System.out.println("Number");
		long number = Util.nextLong();
		patient.setM_number(number);

		// Writing in json file
		Utility.writePatient(patient);
		return patient;
	}

	@Override
	public void deletePatient() {
		// Taking data to delete
		List<Patients> patient = Utility.readPatient();
		System.out.println("Enter Id For Deleteion");
		long id = Util.nextLong();
		for (int i = 0; i < patient.size(); i++) {
			if (patient.get(i).getId() == id) {
				patient.remove(patient.get(i));
			}
		}
		// Converting Updated data in JSON form and save in file
		Utility.save("Patients.json", (JSONArray) Utility.convertPatient(patient));

	}

	@SuppressWarnings("unchecked")
	@Override
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
		Utility.save("Appoinments.json", appoinments);

		System.out.println("Appointment Submit SuccessFully!");

	}

//  find by Docter by ID
	public Docters passDocters(long id) {
		LinkedList<Docters> docters = (LinkedList<Docters>) Utility.readDocter();
		Docters docter = null;
		for (int i = 0; i < docters.size(); i++) {
			
			if (id == docters.get(i).getId()) {
				docter = new Docters();
				docter.setId(docters.get(i).getId());
				docter.setAvail(docters.get(i).getAvail());

				break;
			}

		}
		return docter;
	}

	// Taking appointment
	public void takeAppointment() {
		Appointment appointment = new Appointment();
		// taking Patients information
		Patients patient = addPatient();
		appointment.setPatients(patient.getId(), patient.getName(), patient.getAge(), patient.getM_number());
//Displaying Docter List
		Report.printDocter(Utility.readDocter());
		System.out.println("Select Docters ID");
		long id = Util.nextLong();
		int flag = 0;
		do {
			Docters docter = new Docters();
			if (passDocters(id) != null) {
				// Checking Availbility
				if (Utility.checkBYId(id) < 3) {
					docter = passDocters(id);
					appointment.setDocter(docter.getId(), docter.getAvail());
					flag = 1;
				} else {
					System.out.println("Today Appoinmemnt is full cahnge date");
					flag = 0;
					System.out.println("Select Others date Again");
					id = Util.nextLong();

				}
			} else {
				System.out.println("Select Docters ID Again");
				id = Util.nextLong();
			}

		} while (flag == 0);

		System.out.println("Select date");
		String date = Util.next();
		appointment.setDate(date);
		// to save appointment
		meetingSave(appointment);

	}

	@Override
	public void sortDocter() {
		
		System.out.println("Enter Option For Sort");
		System.out.println("1:Id\t2:Name\t3:Speciality\t4:Availbility");
		int option=Util.nextInt();
		
		LinkedList<Docters> docters=(LinkedList<Docters>) Utility.readDocter();
		
		for(int i=0;i<docters.size()-1;i++) {
			for(int j=i+1;j<docters.size();j++) {
				
				switch(option) {
				case 1:   if(docters.get(i).getId()>docters.get(j).getId())	
					   Collections.swap(docters, i, j);
				break;
				case 2: if(docters.get(i).getName().compareToIgnoreCase(docters.get(j).getName())>0)	
					   Collections.swap(docters, i, j);
				break;
				case 3: if(docters.get(i).getSpcl().compareToIgnoreCase(docters.get(j).getSpcl())>0)	
					   Collections.swap(docters, i, j);
				break;
				case 4:if(docters.get(i).getAvail().compareToIgnoreCase(docters.get(j).getAvail())>0)	
					   Collections.swap(docters, i, j);
				break;
					  
				
				
			}
			
		}
		}
		Report.printDocter(docters);
		
	}

	@Override
	public void sortPatient() {
		System.out.println("Enter Option For Sort");
		System.out.println("1:Id\t2:Name\t3:Age\t4:Number");
		int option=Util.nextInt();
		LinkedList<Patients> patients=(LinkedList<Patients>) Utility.readPatient();
		for(int i=0;i<patients.size()-1;i++) {
			for(int j=i+1;j<patients.size();j++) {
				switch(option) {
				case 1:
					  if(patients.get(i).getId()>patients.get(j).getId())	
					   Collections.swap(patients, i, j);
					  break;
				case 2:
					  if(patients.get(i).getName().compareToIgnoreCase(patients.get(j).getName())>0)	
					   Collections.swap(patients, i, j);
					  break;
				case 3:
					  if(patients.get(i).getAge()>patients.get(j).getAge())	
					   Collections.swap(patients, i, j);
					  break;
				case 4:
					  if(patients.get(i).getM_number()>patients.get(j).getM_number())	
					   Collections.swap(patients, i, j);
					  break;
				}  
				
				
			}
			
		}
		Report.printPatients(patients);
		
		
	}

	@Override
	public void updateDocter() {
		System.out.println("1:Name\t2:Spaciality\t3:Avaibility");
		LinkedList<Docters> docters=(LinkedList<Docters>) Utility.readDocter();
		
		
		
	}

	@Override
	public void updatePatient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAppointment() {
		Report.printAppointment();
		List<Appointment> appointments=Utility.readAppointment();
		System.out.println("Enter id for delete record");
		long id=Util.nextLong();
		
		//Java 8 API Stream Apply
		Predicate<Appointment> remove =p -> (p.getPatients().getId()!= id); 
		appointments=(List<Appointment>) appointments.stream().filter(remove).collect(Collectors.toList());
	
		// Java 7	
	/*for(int i=0;i<appointments.size();i++) {
	
			if(appointments.get(i).getPatients().getId()==id) {
				appointments.remove(appointments.get(i));
				System.out.println("Deleted");
			}
		}*/
		
		Utility.save("Appoinments.json",Utility.convertAppoinment(appointments));
	
		Report.printAppointment();
	}

	@Override
	public void updateAppointment() {
		// TODO Auto-generated method stub
		
	}

}
