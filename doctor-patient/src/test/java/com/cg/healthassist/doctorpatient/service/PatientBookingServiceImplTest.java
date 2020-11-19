package com.cg.healthassist.doctorpatient.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.healthassist.doctorpatient.entity.PatientBooking;
import com.cg.healthassist.doctorpatient.repository.PatientBookingRepository;
import com.cg.healthassist.doctorpatient.service.Impl.PatientBookingServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
class PatientBookingServiceImplTest {
	
	@MockBean
	private PatientBookingRepository pbRepository;
	
	@Autowired
	private PatientBookingServiceImpl pbService;
	


	@Test
	void testGetAllPatients() {
		PatientBooking pBooking = new PatientBooking();
		pBooking.setBedId(123);
		pBooking.setBookingType("Multiple Fracs");
		pBooking.setDescription("For Muscluars");
		pBooking.setBookingDate(LocalDateTime.now());
		
		PatientBooking pB = new PatientBooking();
		pB.setBedId(124);
		pB.setBookingType("Multiple injuries");
		pB.setDescription("For Treatment");
		pB.setBookingDate(LocalDateTime.now());
		
		PatientBooking pBook = new PatientBooking();
		pBook.setBedId(124);
		pBook.setBookingType("Cardiac");
		pBook.setDescription("For heart surgery");
		pBook.setBookingDate(LocalDateTime.now());
		
		List<PatientBooking> patientBookingList= new ArrayList<>();
		patientBookingList.add(pBooking);
		patientBookingList.add(pB);
		patientBookingList.add(pBook);
		
		Mockito.when(pbRepository.findAll()).thenReturn(patientBookingList);
		assertThat(pbService.GetAllPatients()).isEqualTo(patientBookingList);
		
	}

	@Test
	void testGetBookingDetailsById() {
		PatientBooking pBooking = new PatientBooking();
		pBooking.setBedId(123);
		pBooking.setBookingType("Multiple Fracs");
		pBooking.setDescription("For Muscluars");
		pBooking.setBookingDate(LocalDateTime.now());
		
		assertThat(pBooking.getBedId()).isEqualTo(123);
	}

	@Test
	void testBookAppointment() {
		PatientBooking pBooking = new PatientBooking();
		pBooking.setBedId(001);
		pBooking.setBookingType("Online");
		pBooking.setDescription("For Knees");
		pBooking.setBookingDate(LocalDateTime.now());
		
		Mockito.when(pbRepository.save(pBooking)).thenReturn(pBooking);
		assertThat(pbService.BookAppointment(pBooking)).isEqualTo(pBooking);
	}


	@Test
	void testRemoveAppointmentById() {
		PatientBooking pBooking = new PatientBooking();
		pBooking.setBedId(121);
		pBooking.setBookingType("For Injury");
		pBooking.setDescription("For Muscular Surgery");
		pBooking.setBookingDate(LocalDateTime.now());
		
		pbRepository.deleteById(pBooking.getBedId());
		Assert.assertTrue(pbRepository.findById(121).isEmpty());
		}

}
