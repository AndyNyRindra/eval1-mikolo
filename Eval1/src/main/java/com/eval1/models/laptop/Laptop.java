package com.eval1.models.laptop;

import com.eval1.models.brand.Brand;
import com.eval1.models.cpu.Cpu;
import com.eval1.models.drive.DriveType;
import com.eval1.models.ram.RamType;
import com.eval1.models.screen.ScreenType;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.String;
import java.lang.Double;


@Getter
@Setter
@Entity
@Table(name = "laptop")
public class Laptop extends HasId {

	public Laptop(Long cpuId, Long ramTypeId, Long driveTypeId, Long screenTypeId, Long brandId) throws CustomException {
		Cpu cpu1 = new Cpu();
		cpu1.setId(cpuId);
		setCpu(cpu1);
		RamType ramType1 = new RamType();
		ramType1.setId(ramTypeId);
		setRamType(ramType1);
		DriveType driveType1 = new DriveType();
		driveType1.setId(driveTypeId);
		setDriverType(driveType1);
		ScreenType screenType1 = new ScreenType();
		screenType1.setId(screenTypeId);
		setScreenType(screenType1);
		Brand brand1 = new Brand();
		brand1.setId(brandId);
		setBrand(brand1);
	}

	private String reference;
	@ManyToOne()
	@JoinColumn(name = "screen_type_id")
	private ScreenType screenType;
	@ManyToOne()
	@JoinColumn(name = "driver_type_id")
	private DriveType driverType;
	private String name;
	@ManyToOne()
	@JoinColumn(name = "ram_type_id")
	private RamType ramType;
	@ManyToOne()
	@JoinColumn(name = "cpu_id")
	private Cpu cpu;
	private Double screenSize;
	private Double ramValue;
	private Double driverSize;
	@ManyToOne()
	@JoinColumn(name = "brand_id")
	private Brand brand;

	public Laptop() {

	}

	public void setScreenSize(Double screenSize) throws CustomException {
		if (screenSize < 0)
			throw new CustomException("La taille de l'écran doit être positive");
		this.screenSize = screenSize;
	}

	public void setRamValue(Double ramValue) throws CustomException {
		if (ramValue < 0)
			throw new CustomException("La valeur de la ram doit être positive");
		this.ramValue = ramValue;
	}

	public void setDriverSize(Double driverSize) throws CustomException {
		if (driverSize < 0)
			throw new CustomException("La valeur du disque doit être positive");
		this.driverSize = driverSize;
	}
}