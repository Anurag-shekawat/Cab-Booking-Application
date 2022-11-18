package com.masai.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto extends User {

	private int driverId;

	private String licenseNumber;

	private String numberPlate;

	private Float ratePerKms;

	private String carType;

}
