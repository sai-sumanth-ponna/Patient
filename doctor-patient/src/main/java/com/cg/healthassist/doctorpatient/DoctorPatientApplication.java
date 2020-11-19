package com.cg.healthassist.doctorpatient;

import java.time.LocalDateTime;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cg.healthassist.doctorpatient.entity.PatientBooking;
import com.cg.healthassist.doctorpatient.service.Impl.PatientBookingServiceImpl;



@SpringBootApplication
@ComponentScan(basePackages = "com.cg.healthassist")
@EnableCaching
public class DoctorPatientApplication {
	
	@Autowired
	private PatientBookingServiceImpl pbService;
	

	public static void main(String[] args) {
		SpringApplication.run(DoctorPatientApplication.class, args);
	}
	

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			
			pbService.BookAppointment(new PatientBooking(1, "For Fracs", "Multi Fracs", LocalDateTime.now()));
			pbService.BookAppointment(new PatientBooking(2, "For knees", "Knneee", LocalDateTime.now()));
			pbService.BookAppointment(new PatientBooking(3, "For knees", "Knneee", LocalDateTime.now()));
			
			
		};
	}

}
