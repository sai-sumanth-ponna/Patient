package com.cg.healthassist.doctorpatient.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassist.doctorpatient.entity.PatientBooking;
import com.cg.healthassist.doctorpatient.repository.PatientBookingRepository;
import com.cg.healthassist.doctorpatient.services.PatientBookingService;

@Service
public class PatientBookingServiceImpl implements PatientBookingService {
	@Autowired
	private PatientBookingRepository pbRepository;
	
	public List<PatientBooking> GetAllPatients(){
		return pbRepository.findAll();
	}
	
	public PatientBooking GetBookingDetailsById(int id) {
	 return pbRepository.findAll().get(id);
	}
	
	public PatientBooking BookAppointment(PatientBooking patientBooking) {
		System.out.println("Appointment Fixed Successfully");
		return pbRepository.save(patientBooking);
		}
	
	public PatientBooking UpdateAppointmentById(int bedId,String description,String bookingType,String bookingDate) {
		PatientBooking pBooking=new PatientBooking();
		pBooking.setBedId(bedId);
		pBooking.setBookingType(bookingType);
		pBooking.setDescription(description);
		pBooking.setBookingDate(LocalDateTime.now());
		
		return pbRepository.save(pBooking);
	}
	
	public boolean RemoveAppointmentById(int bedId) {
		pbRepository.deleteById(bedId);
		PatientBooking pBooking = pbRepository.findById(bedId).get();
		if(null==pBooking) {
			return true;
		}
		return false;
		
	}

}
