package com.hl.contract.business.main.service.dto;

public class CarModelDTO {
	private String id;

	private String carSysCode;
	private String carSysName;
	private String carBrandCode;
	private String carBrandName;
	private String carModelCode;

	private String carModelName;

	private String vin;

	private String batteryNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarSysCode() {
		return carSysCode;
	}

	public void setCarSysCode(String carSysCode) {
		this.carSysCode = carSysCode;
	}

	public String getCarSysName() {
		return carSysName;
	}

	public void setCarSysName(String carSysName) {
		this.carSysName = carSysName;
	}

	public String getCarBrandCode() {
		return carBrandCode;
	}

	public void setCarBrandCode(String carBrandCode) {
		this.carBrandCode = carBrandCode;
	}

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public String getCarModelCode() {
		return carModelCode;
	}

	public void setCarModelCode(String carModelCode) {
		this.carModelCode = carModelCode;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBatteryNo() {
		return batteryNo;
	}

	public void setBatteryNo(String batteryNo) {
		this.batteryNo = batteryNo;
	}
}
