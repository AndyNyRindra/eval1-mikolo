package com.eval1.models;

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
@Table(name = "v_global_loss")
public class VGlobalLoss extends HasId {

	private Double depenses;
	private Long id;
	private Date mois;
	private Long nombrePertes;

}