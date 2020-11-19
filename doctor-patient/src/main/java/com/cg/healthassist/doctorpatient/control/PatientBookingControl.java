package com.cg.healthassist.doctorpatient.control;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassist.doctorpatient.entity.PatientBooking;
import com.cg.healthassist.doctorpatient.service.Impl.PatientBookingServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class PatientBookingControl {
	@Autowired
	private PatientBookingServiceImpl pbService;
	
	@GetMapping("/PatientBookingDetails")
	public List<PatientBooking> getAllPatients(){
		return pbService.GetAllPatients();
	}
	@GetMapping("/PatientBookingDetails/{id}")
	public @ResponseBody PatientBooking GetBookingDetailsById(@PathVariable Integer id)
	{
		return pbService.GetBookingDetailsById(id);
	}
	@PostMapping("/PatientBookingDetails")
	public @ResponseBody PatientBooking BookAppointment(PatientBooking patientBooking)
	{
		return pbService.BookAppointment(patientBooking);
	}
	@PutMapping("/PatientBookingDetails/{id}")
	public @ResponseBody PatientBooking UpdateAppointmentById(@PathVariable int bedId,@PathVariable String description,@PathVariable String bookingType,@PathVariable String bookingDate) {
		return pbService.UpdateAppointmentById(bedId,description, bookingType,bookingDate);
	}
	@DeleteMapping("PatientBookingDetails/{id}")
	public @ResponseBody boolean RemoveAppointmentById(@PathVariable Integer bedId) {
		return pbService.RemoveAppointmentById(bedId);
	}
		
}
