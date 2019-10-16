package com.ray.oops.appointment.services;

import com.ray.oops.appointment.model.Appointment;
import com.ray.oops.appointment.model.Patients;

public interface Service {
void addDocter();
void deleteDocter();
void sortDocter();
void updateDocter();

Patients addPatient();
void deletePatient();
void sortPatient();
void updatePatient();

void meetingSave(Appointment list);
void takeAppointment();
void deleteAppointment();
void updateAppointment();

}
