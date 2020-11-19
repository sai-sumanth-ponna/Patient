package com.cg.healthassist.doctorpatient.services;

import java.util.List;

import com.cg.healthassist.doctorpatient.entity.PatientBooking;

public interface PatientBookingService {
	
	public List<PatientBooking> GetAllPatients();
	
	public PatientBooking GetBookingDetailsById(int id);
	
	public PatientBooking BookAppointment(PatientBooking patientBooking);
	
	public PatientBooking UpdateAppointmentById(int bedId,String description,String bookingType,String bookingDate);
	
	public boolean RemoveAppointmentById(int bedId);

}
