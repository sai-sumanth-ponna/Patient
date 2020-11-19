package com.cg.healthassist.doctorpatient.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




/** This is an entity class for bookings with getters and setters  and constructor
 * 
 * @author Sai Sumanth Ponna
 * @version 1.0
 *
 */
@Entity
@Table(name = "BoookingDetails")
public class PatientBooking implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private int BedId;
	@Column
	private String Description;
	@Column
	private String BookingType;
	@Column
	private LocalDateTime BookingDate;
	
	public PatientBooking() {
		
	}

	public PatientBooking(int bedId, String description, String bookingType, LocalDateTime bookingDate) {
		super();
		BedId = bedId;
		Description = description;
		BookingType = bookingType;
		BookingDate = bookingDate;
	}

	public int getBedId() {
		return BedId;
	}

	public void setBedId(int bedId) {
		BedId = bedId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getBookingType() {
		return BookingType;
	}

	public void setBookingType(String bookingType) {
		BookingType = bookingType;
	}

	public LocalDateTime getBookingDate() {
		return BookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		BookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "PatientBooking [BedId=" + BedId + ", Description=" + Description + ", BookingType=" + BookingType
				+ ", BookingDate=" + BookingDate + "]";
	}
	
	


}
