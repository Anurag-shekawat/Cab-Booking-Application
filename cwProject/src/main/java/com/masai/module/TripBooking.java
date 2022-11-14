package com.masai.module;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TripBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripBookingId;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "customerId") // renaming column name to customerId
//	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "cabDriverId")
	private CabDriver cabDriver;

	@NotNull(message = "Pickup Location cannot be null")
	private String fromLocation;

	@NotNull(message = "Drop Location cannot be null")
	private String toLocation;
	
	private LocalDateTime fromDateTime;
	
	private LocalDateTime toDateTime;

	private Boolean status = false;

	@NotNull(message = "Distance cannot be null")
	private Float distance;

	private Float totalFare;

}
