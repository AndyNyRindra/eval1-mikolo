package com.eval1.models.comission;

import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Double;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "comission")
public class Comission extends HasId {

	private Double minValue;
	private Double percent;
	private Double maxValue;

	public void setMinValue(Double minValue) throws CustomException {
		if (minValue == null || minValue < 0)
			throw new CustomException("La valeur minimale est nulle ou négative");
		this.minValue = minValue;
	}

	public void setPercent(Double percent) throws CustomException {
		if (percent == null || percent < 0)
			throw new CustomException("Le pourcentage est nul ou négatif");
		if ( percent <= 0 || percent >= 100)
			throw new CustomException("Le pourcentage doit être compris entre 0 et 100");
		this.percent = percent;
	}

	public void setMaxValue(Double maxValue) throws CustomException {
		if (maxValue == null || maxValue < 0)
			throw new CustomException("La valeur maximale est nulle ou négative");
		this.maxValue = maxValue;
	}
}