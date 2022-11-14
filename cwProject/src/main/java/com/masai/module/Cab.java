package com.masai.module;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cabId;

	@NotNull(message = "Number Plate cannot be null")
	@Pattern(regexp = "[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}", message = "Number Plate should be in format of DL12DE4659")
	private String numberPlate;

	@NotNull(message = "Car type cannot be null")
	private String carType;

	@NotNull(message = "Rate cannot be null")
	private Float ratePerKms;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "driverID")
	private CabDriver cabDriver;

}
