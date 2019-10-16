package com.ray.oops.appointment.control;

import com.ray.oops.appointment.services.ServiceImplement;
import com.ray.oops.appointment.util.Utility;
import com.ray.oops.appointment.view.Report;
import com.ray.utilty.Util;

public class Menu {
	private ServiceImplement object=null;

	public Menu() {
		object = new ServiceImplement();
	}

	public  void Docters() {
		System.out.print("1:Add\t2:Edit\t3:Display\t4:Sort\t5:Search\t6:Delete\n");
		int option = Util.nextInt();
		switch (option) {
		case 1:
			object.addDocter();
			break;
		case 2://object.updateDocter();
			break;
			
		case 3:
			Report.printDocter(Utility.readDocter());
			break;
		case 4:object.sortDocter();
		break;
		case 5://object.searchDocter
			break;
		default:
			System.out.println("Invalid options \nTry Again");
		}
	}

	public  void Patients() {
		System.out.print("1:Add\t2:Edit\t3:Display\t4:Sort\t5:Search\t6:Delete\n");
		int option = Util.nextInt();
		switch (option) {
		case 1:
			object.addPatient();
			break;
		case 2:
			//object.updatePatient();
			break;
		case 3:Report.printPatients(Utility.readPatient());
		break;
			
		case 4:object.sortPatient();
		break;
		case 5://object.searchPatient();
			break;
		case 6:object.deletePatient();
		default:
			System.out.println("Invalid options \nTry Again");
		}

	}

	public  void Appointments() {
		System.out.print("1:Add\t2:Edit\t3:Display\t4:Sort\t5:Search\t6:Delete");
		int option = Util.nextInt();
		switch (option) {
		case 1:
			object.takeAppointment();
			break;
		case 2://object.updateAppointment();
			break;
		case 3:Report.printAppointment();
			break;
		case 4://object.sortAppointment();
			break;
		case 5://object.searchAppointment();
			break;
		case 6:object.deleteAppointment();
		
		break;
		default:
			System.out.println("Invalid options \nTry Again");
		}

	}

	public  void menu() {

		int option = 0,count=0;
		do {
			
			System.out.print("Enter option\n");
			System.out.print("1:Docters Detaile\t2:Patients Details\t3:Take Appointment\n");
			option = Util.nextInt();
		switch (option) {
		case 1:
			System.out.print("Enter AdminPassword\n");
			int pw = Util.nextInt();
			
			if (pw == 123) 
			{
				     Docters();
			} else 
			{
				System.out.println("Wrong try agaain");
			}
			
			break;

		case 2:
			Patients();
			break;
		case 3: Appointments();
			break;
			default:System.out.println("Invalid option");
			count++;
			option=0;
			break;
		}
		
		if(count>2) {
			System.out.println("\nMaximum 3 Times Wrong");
			option=5;
		}
		
	}while(option<4);
		System.out.println("Successfully Logout!\nThank You");
	}

}
