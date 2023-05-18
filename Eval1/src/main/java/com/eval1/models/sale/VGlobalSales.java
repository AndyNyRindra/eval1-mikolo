package com.eval1.models.sale;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Double;
import java.lang.Long;
import java.sql.Date;


@Getter
@Setter
@Entity
@Table(name = "v_global_sales")
public class VGlobalSales extends HasId {

	private Double recettes;
	private Long nombreVentes;
	private Long id;
	private Date mois;

}