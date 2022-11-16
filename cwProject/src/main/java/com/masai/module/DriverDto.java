package com.masai.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto extends User {

	private int driverId;

	private String licenseNumber;

	private String numberPlate;

	private Float ratePerKms;

	private String carType;

}
