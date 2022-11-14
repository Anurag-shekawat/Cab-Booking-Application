package com.masai.module;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CabDriver extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;

	@NotNull(message = "License Number cannot be null")
	@Column(unique = true)
	private String licenseNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cabId")
	@JsonIgnore
	private Cab cab;

	@NotNull(message = "Rating cannot be null")
	@Max(value = 5, message = "Rating should be betwee 1-5")
	private Float rating;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "cabDriver",orphanRemoval = true)
	@JsonIgnore
	List<TripBooking> tripDetailsList = new ArrayList<>();

//	private Boolean availablity = true;

}
