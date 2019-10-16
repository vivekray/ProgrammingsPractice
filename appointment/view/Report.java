package com.ray.oops.appointment.view;

import java.util.List;

import com.ray.oops.appointment.model.Appointment;
import com.ray.oops.appointment.model.Docters;
import com.ray.oops.appointment.model.Patients;
import com.ray.oops.appointment.util.Utility;

public class Report {
		public static void printDocter(List<Docters> list) {
			System.out.print("Id Name Special Availibilty\n");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).getId() + " " + list.get(i).getName() + " " + list.get(i).getSpcl() + "  "
						+ list.get(i).getAvail());
				System.out.print(" \n");
			}

		}
		
		public static void printPatients(List<Patients> list) {
			System.out.print("Id  Name  Age   Numbers\n");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).getId() + " " + list.get(i).getName() + " " + list.get(i).getAge() + "  "
						+ list.get(i).getM_number());
				System.out.print(" \n");
			}

		}
		
		
		
		
		public static void printAppointment() {
			List<Appointment> list = Utility.readAppointment();
			System.out.println("Pid  Pname  Page  Number  Date  Did Availbility");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getPatients().getId() + "   " + list.get(i).getPatients().getName() + "   "
						+ list.get(i).getPatients().getAge() + " " + list.get(i).getPatients().getM_number() + " "
						+ list.get(i).getDate() + "   " + list.get(i).getDocter().getId() + "   "
						+ list.get(i).getDocter().getAvail());
			}
		
			
		
	}
}


