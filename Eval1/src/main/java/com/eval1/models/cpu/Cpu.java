package com.eval1.models.cpu;

import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import custom.springutils.model.HasName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.String;
import java.lang.Integer;
import java.lang.Double;


@Getter
@Setter
@Entity
@Table(name = "cpu")
public class Cpu extends HasName {

	private Double frequency;

	public void setFrequency(Double frequency) throws CustomException {
		if (frequency < 0)
			throw new CustomException("La fréquence doit être positive");
		this.frequency = frequency;
	}
}