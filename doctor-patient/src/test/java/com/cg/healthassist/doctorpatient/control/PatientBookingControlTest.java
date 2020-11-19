package com.cg.healthassist.doctorpatient.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.healthassist.doctorpatient.entity.PatientBooking;
import com.cg.healthassist.doctorpatient.service.Impl.PatientBookingServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(value=PatientBookingControl.class)
class PatientBookingControlTest {
	
	private static final String URI = null;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PatientBookingServiceImpl pbService;

	@Test
	void testGetAllPatients() throws Exception {
		String URI = "/PatientBooking/getAllPatients";
		PatientBooking pBooking = new PatientBooking();
		pBooking.setBedId(133);
		pBooking.setBookingType("Multiple Fracs");
		pBooking.setDescription("For Muscluars");
		pBooking.setBookingDate(LocalDateTime.now());
		
		PatientBooking pB = new PatientBooking();
		pB.setBedId(134);
		pB.setBookingType("Multiple injuries");
		pB.setDescription("For Treatment");
		pB.setBookingDate(LocalDateTime.now());
		
		PatientBooking pBook = new PatientBooking();
		pBook.setBedId(144);
		pBook.setBookingType("Cardiac");
		pBook.setDescription("For heart surgery");
		pBook.setBookingDate(LocalDateTime.now());
		
		List<PatientBooking> patientBookingList= new ArrayList<>();
		patientBookingList.add(pBooking);
		patientBookingList.add(pB);
		patientBookingList.add(pBook);
	      
        String jsonInput = this.converttoJson(patientBookingList);

        Mockito.when(pbService.GetAllPatients()).thenReturn(patientBookingList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isNotEqualTo(jsonOutput);
        Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}

	@Test
	void testGetBookingDetailsById() throws Exception {
		String URI = "/PatientBooking/getBookingDetailsById/{bedId}";
		PatientBooking pBooking=new PatientBooking();
		pBooking.setBedId(101);
		pBooking.setBookingType("lalalala");
		pBooking.setDescription("treatment");
		pBooking.setBookingDate(LocalDateTime.now());
		
        String jsonInput = this.converttoJson(pBooking);

        Mockito.when(pbService.GetBookingDetailsById(Mockito.anyInt())).thenReturn(pBooking);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 101).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertNotNull(jsonOutput);
        Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
        
	}

	@Test
	void testBookAppointment() throws Exception {
		String URI = "/PatientBooking/bookAppointment";
		PatientBooking pBooking=new PatientBooking();
		pBooking.setBedId(100);
		pBooking.setBookingType("lala");
		pBooking.setDescription("treatment");
		pBooking.setBookingDate(LocalDateTime.now());
		
		String jsonInput = this.converttoJson(pBooking);
		

        Mockito.when(pbService.BookAppointment(Mockito.any(PatientBooking.class))).thenReturn(pBooking);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isNotEqualTo(jsonOutput);
        Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	void testUpdateAppointmentById() throws Exception {
		String URI = "/PatientBooking/{id}";
		PatientBooking pBooking= new PatientBooking();
		pBooking.setBedId(100);
		pBooking.setBookingType("for fracs");
		pBooking.setDescription("treatment to be done");
		pBooking.setBookingDate(LocalDateTime.now());
		
		  String jsonInput = this.converttoJson(pBooking);

		
		 Mockito.when(pbService.UpdateAppointmentById(Mockito.anyInt(),Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(pBooking);
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 100).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertNotNull(jsonOutput);

		
	}

	@Test
	void testRemoveAppointmentById() throws Exception{
		String URI= "/PatientBooking/{id}";
		PatientBooking pBooking = new PatientBooking();
		pBooking.setBedId(121);
		pBooking.setBookingType("For Injury");
		pBooking.setDescription("For Muscular Surgery");
		pBooking.setBookingDate(LocalDateTime.now());
	    String jsonInput = this.converttoJson(pBooking);
	    Mockito.when(pbService.RemoveAppointmentById(121)).thenReturn(true);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 121).accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

	    Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
	
	  /**
     * Convert Object into Json String by using Jackson ObjectMapper
     * @param ticket
     * @return
     * @throws JsonProcessingException
     */
    private String converttoJson(Object doctor) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(doctor);
    }


}
