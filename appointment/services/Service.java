package com.ray.oops.appointment.services;


import com.ray.oops.appointment.model.Patients;

public interface Service {
void addDocter();
void deleteDocter();
void sortDocter();
void updateDocter();
void searchDocter();

Patients addPatient();
void deletePatient();
void sortPatient();
void updatePatient();


void takeAppointment();
void deleteAppointment();
void updateAppointment();

}
