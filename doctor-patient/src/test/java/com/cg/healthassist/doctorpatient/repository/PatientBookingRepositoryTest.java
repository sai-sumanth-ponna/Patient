package com.cg.healthassist.doctorpatient.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.healthassist.doctorpatient.entity.PatientBooking;
@RunWith(SpringRunner.class)
@DataJpaTest

@AutoConfigureTestDatabase(replace=Replace.NONE)
class PatientBookingRepositoryTest {
	
	@Autowired
	private PatientBookingRepository pbRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void testGetBookingDetailsById() throws Exception{
		PatientBooking pBooking = new PatientBooking();
		pBooking.setBedId(01);
		pBooking.setBookingType("HIHI");
		pBooking.setDescription("aaaa");
		pBooking.setBookingDate(LocalDateTime.now());
		
		// Insert data into in memory DB
		PatientBooking saveInDb = testEntityManager.persist(pBooking);
		// Get Data from DB
		PatientBooking getInDb = pbRepository.findById(pBooking.getBedId()).get();
		assertThat(getInDb).isEqualTo(saveInDb);
	}
	
	@Test
	public void testRemoveAppointmentById() {
		PatientBooking pBooking1 = new PatientBooking();
		pBooking1.setBedId(02);
		pBooking1.setBookingType("jojo");
		pBooking1.setDescription("huhu");
		pBooking1.setBookingDate(LocalDateTime.now());
		
		PatientBooking pBooking2 = new PatientBooking();
		pBooking2.setBedId(03);
		pBooking2.setBookingType("mimi");
		pBooking2.setDescription("lolo");
		pBooking2.setBookingDate(LocalDateTime.now());
		
		PatientBooking pBook = testEntityManager.persist(pBooking1);
		testEntityManager.persist(pBooking2);
		
		//delete one booking in DB
		testEntityManager.remove(pBook);
		//to do
		//List<PatientBooking> pBook2 = (List<PatientBooking>) pbRepository.findAll();
		  //Assert.assertEquals(pBook2.size(), 1);
		}
	
	@Test
	public void testUpdateAppointmentById() {
		PatientBooking pBook2= new PatientBooking();
		pBook2.setBedId(02);
		pBook2.setBookingType("jojo");
		pBook2.setDescription("huhu");
		pBook2.setBookingDate(LocalDateTime.now());
		
		testEntityManager.persist(pBook2);
		
		PatientBooking getFromDb = pbRepository.findById(pBook2.getBedId()).get();
		getFromDb.setBedId(104);
		testEntityManager.persist(getFromDb);
		
		assertThat(getFromDb.getBedId()).isEqualTo(104);
	}

}
